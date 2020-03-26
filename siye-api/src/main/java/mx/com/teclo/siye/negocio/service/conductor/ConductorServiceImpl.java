package mx.com.teclo.siye.negocio.service.conductor;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConductorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

@Service
public class ConductorServiceImpl implements ConductorService{
	
	@Autowired
	private UsuarioFirmadoService userSession;

	@Autowired
	private ConductorDAO conductorDAO;
	
	@Transactional
	@Override
	public PersonaGenericaVO nuevoConductor(PersonaGenericaVO personaGenericaVO) {
		String nombre = personaGenericaVO.getNombre().toUpperCase();
		String aPaterno = personaGenericaVO.getaPaterno().toUpperCase();
		String aMaterno = personaGenericaVO.getaMaterno().toUpperCase();
		personaGenericaVO.setNombre(nombre);
		personaGenericaVO.setaPaterno(aPaterno);
		personaGenericaVO.setaMaterno(aMaterno);
		
		List<ConductorDTO> listaConductorDTO = conductorDAO.getConductorXNombre(nombre, aPaterno, aMaterno);
		Boolean existeConductor = false;
		if(listaConductorDTO.size() > 0) {
			existeConductor = true;
		}else {
			existeConductor = false;
		}
		
		if(existeConductor == false) {
			Long idUsuario = userSession.getUsuarioFirmadoVO().getId();
			ConductorDTO conductorDTO = new ConductorDTO();
			
			conductorDTO.setNbConductor(nombre);
			conductorDTO.setNbApepatConductor(aPaterno);
			conductorDTO.setNbApematConductor(aMaterno);
			conductorDTO.setNuOrden(null);
			conductorDTO.setStActivo(true);
			conductorDTO.setIdUsrCreacion(idUsuario);
			conductorDTO.setFhCreacion(new Date());
			conductorDTO.setIdUsrModifica(idUsuario);
			conductorDTO.setFhModificacion(new Date());
			conductorDAO.save(conductorDTO);
			
			conductorDTO.setNuOrden(conductorDTO.getIdConductor());
			conductorDAO.update(conductorDTO);
			
			personaGenericaVO.setIdPersona(conductorDTO.getIdConductor());
			personaGenericaVO.setExistia(false);
		}else{
			personaGenericaVO.setIdPersona(listaConductorDTO.get(0).getIdConductor());
			personaGenericaVO.setExistia(true);
		}
		return personaGenericaVO;
	}
}
