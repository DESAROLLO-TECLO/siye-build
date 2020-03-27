package mx.com.teclo.siye.negocio.service.conductor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConductorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.mybatis.dao.comun.ComunMyBatisDAO;
import mx.com.teclo.siye.persistencia.mybatis.dao.configuracion.ConfiguracionBDMyBatisDAO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorCompVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

@Service
public class ConductorServiceImpl implements ConductorService{
	
	@Autowired
	private UsuarioFirmadoService userSession;

	@Autowired
	private ConductorDAO conductorDAO;
	
	@Autowired
	private ConfiguracionBDMyBatisDAO configuracionBDMyBatisDAO;
	
	@Autowired
	private ComunMyBatisDAO comunMyBatisDAO;
	
	@Transactional
	@Override
	public PersonaGenericaVO nuevoConductor(PersonaGenericaVO personaGenericaVO, String mensajeErr) 
		throws Exception, BusinessException, NotFoundException {
		try {
			String nombre = personaGenericaVO.getNombre().toUpperCase();
			String aPaterno = personaGenericaVO.getaPaterno().toUpperCase();
			String aMaterno = personaGenericaVO.getaMaterno().toUpperCase();
			if(nombre == null || nombre.equals("")) {
				mensajeErr = "El nombre del conductor no puede estar vacío.";
				throw new NotFoundException("");
			}else {
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
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	}
	
	@Transactional
	@Override
	public void ordenarConductores(String mensajeErr) 
		throws Exception, BusinessException, NotFoundException {
		try {
			Map<String, String> parametros = getParametro("ORDEN_CONDUCTORES"); 
			String strOrdenConductores = parametros.get("ORDEN_CONDUCTORES");
			String[] arrayOrdenConductores = strOrdenConductores.split("\\|");
			String[] arrayordenConductoresCampos = arrayOrdenConductores[0].split("-");
			String ordenConductoresAscDesc = arrayOrdenConductores[1];
			String strOrdenQuery = "";
			for (int i = 0; i < arrayordenConductoresCampos.length; i++) {
				String campo = arrayordenConductoresCampos[i];
				if(campo.equals("ID")) {
					strOrdenQuery = "ID_CONDUCTOR " + ordenConductoresAscDesc;
				}else if(campo.equals("N") ) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_CONDUCTOR " + ordenConductoresAscDesc : strOrdenQuery + ", NB_CONDUCTOR " + ordenConductoresAscDesc;
				}else if(campo.equals("P")) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_APEPAT_CONDUCTOR " + ordenConductoresAscDesc : strOrdenQuery + ", NB_APEPAT_CONDUCTOR " + ordenConductoresAscDesc;
				}else if(campo.equals("M")) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_APEMAT_CONDUCTOR " + ordenConductoresAscDesc : strOrdenQuery + ", NB_APEMAT_CONDUCTOR " + ordenConductoresAscDesc;
				}
			}
			
			String consPersonalizada = 
			"SELECT "
			+ "	ID_CONDUCTOR AS idConductor, NB_CONDUCTOR AS nbConductor, NB_APEPAT_CONDUCTOR AS nbApepatConductor, "
			+ "	NB_APEMAT_CONDUCTOR AS nbApematConductor, NU_ORDEN AS nuOrden, ST_ACTIVO AS stActivo, "
			+ "	ID_USR_CREACION AS idUsrCreacion, FH_CREACION AS fhCreacion, ID_USR_MODIFICA AS idUsrModifica, "
			+ "	FH_MODIFICACION AS fhModificacion "
			+ "FROM TIE044C_IE_CONDUCTOR "
			+ "WHERE ID_CONDUCTOR != 9999 "
			+ "ORDER BY " + strOrdenQuery;
			List<ConductorCompVO> listaConductorCompVO = comunMyBatisDAO.consConductoresPersonalizada(consPersonalizada);
			
			for (int x = 0; x < listaConductorCompVO.size(); x++) {
				ConductorCompVO conductorCompVO = listaConductorCompVO.get(x);
				
				ConductorDTO conductorDTO = new ConductorDTO();
				
				conductorDTO.setIdConductor(conductorCompVO.getIdConductor());
				conductorDTO.setNbConductor(conductorCompVO.getNbConductor());
				conductorDTO.setNbApepatConductor(conductorCompVO.getNbApepatConductor());
				conductorDTO.setNbApematConductor(conductorCompVO.getNbApematConductor());
				conductorDTO.setNuOrden(x+2);
				conductorDTO.setStActivo(conductorCompVO.getStActivo());
				conductorDTO.setIdUsrCreacion(conductorCompVO.getIdUsrCreacion());
				conductorDTO.setFhCreacion(conductorCompVO.getFhCreacion());
				conductorDTO.setIdUsrModifica(conductorCompVO.getIdUsrModifica());
				conductorDTO.setFhModificacion(conductorCompVO.getFhModificacion());
				conductorDAO.update(conductorDTO);
			}
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	}
	
	@Transactional
	public Map<String, String> getParametro(String parametro){
		List<Map<String, String>> listaParametros = configuracionBDMyBatisDAO.getParametrosPorCdConfig(parametro);
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
}
