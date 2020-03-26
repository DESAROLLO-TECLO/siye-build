package mx.com.teclo.siye.negocio.service.instalador;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.InstaladorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

@Service
public class InstaladorServiceImpl implements InstaladorService{
	
	@Autowired
	private UsuarioFirmadoService userSession;

	@Autowired
	private InstaladorDAO instaladorDAO;
	
	@Transactional
	@Override
	public PersonaGenericaVO nuevoInstalador(PersonaGenericaVO personaGenericaVO) {
		String nombre = personaGenericaVO.getNombre().toUpperCase();
		String aPaterno = personaGenericaVO.getaPaterno().toUpperCase();
		String aMaterno = personaGenericaVO.getaMaterno().toUpperCase();
		personaGenericaVO.setNombre(nombre);
		personaGenericaVO.setaPaterno(aPaterno);
		personaGenericaVO.setaMaterno(aMaterno);
		
		List<InstaladorDTO> listaInstaladorDTO = instaladorDAO.getInstaladorXNombre(nombre, aPaterno, aMaterno);
		Boolean existeConductor = false;
		if(listaInstaladorDTO.size() > 0) {
			existeConductor = true;
		}else {
			existeConductor = false;
		}
		
		if(existeConductor == false) {
			Long idUsuario = userSession.getUsuarioFirmadoVO().getId();
			InstaladorDTO instaladorDTO = new InstaladorDTO();
			
			instaladorDTO.setNbRhInstalador(nombre);
			instaladorDTO.setNbPatRhInstalador(aPaterno);
			instaladorDTO.setNbMatRhInstalador(aMaterno);
			instaladorDTO.setNuOrden(null);
			instaladorDTO.setStActivo(true);
			instaladorDTO.setIdUsrCreacion(idUsuario);
			instaladorDTO.setFhCreacion(new Date());
			instaladorDTO.setIdUsrModifica(idUsuario);
			instaladorDTO.setFhModificacion(new Date());
			instaladorDAO.save(instaladorDTO);
			
			instaladorDTO.setNuOrden(instaladorDTO.getIdRhInstalador());
			instaladorDAO.update(instaladorDTO);
			
			personaGenericaVO.setIdPersona(instaladorDTO.getIdRhInstalador());
			personaGenericaVO.setExistia(false);
		}else{
			personaGenericaVO.setIdPersona(listaInstaladorDTO.get(0).getIdRhInstalador());
			personaGenericaVO.setExistia(true);
		}
		return personaGenericaVO;
	}
}
