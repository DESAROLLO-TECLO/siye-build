package mx.com.teclo.siye.negocio.service.instalador;

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
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.PersonaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.PersonaTipoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoPersonaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaTipoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoPersonaDTO;
import mx.com.teclo.siye.persistencia.mybatis.dao.comun.ComunMyBatisDAO;
import mx.com.teclo.siye.persistencia.mybatis.dao.configuracion.ConfiguracionBDMyBatisDAO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaCompVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;

@Service
public class PersonasServiceImpl implements PersonasService{
	
	@Autowired
	private UsuarioFirmadoService userSession;

	@Autowired
	private PersonaDAO personaDAO;

	@Autowired
	private ConfiguracionBDMyBatisDAO configuracionBDMyBatisDAO;

	@Autowired
	private ComunMyBatisDAO comunMyBatisDAO;
	
	@Autowired
	private PersonaTipoDAO personaTipoDAO;
	
	@Autowired
	private TipoPersonaDAO tipoPersonaDAO;
	
	@Transactional
	@Override
	public PersonaGenericaVO nuevoInstalador(
		PersonaGenericaVO personaGenericaVO, String mensajeErr
	) throws Exception, BusinessException, NotFoundException {
		try {
			PersonaGenericaVO resultPersonaGenericaVO = new PersonaGenericaVO();
			String nombre = personaGenericaVO.getNombre().toUpperCase();
			String aPaterno = personaGenericaVO.getaPaterno().toUpperCase();
			String aMaterno = personaGenericaVO.getaMaterno().toUpperCase();
			Integer idTipoPersona = personaGenericaVO.getIdTipoPersona();
			 
			if(idTipoPersona == null || idTipoPersona < 1) {
				mensajeErr = "El tipo de la persona no puede estar vacío.";
				throw new NotFoundException("");
			}else if(nombre == null || nombre.equals("")) {
				mensajeErr = "El nombre de la persona no puede estar vacío.";
				throw new NotFoundException("");
			}else {
				resultPersonaGenericaVO.setNombre(nombre);
				resultPersonaGenericaVO.setaPaterno(aPaterno);
				resultPersonaGenericaVO.setaMaterno(aMaterno);
				
				List<PersonaDTO> listaPersonaDTO = personaDAO.getInstaladorXNombre(nombre, aPaterno, aMaterno);
				Boolean existePersona= false;
				Boolean existePersonaTipo = false;
				PersonaTipoDTO personaTipoDTO = null;
				PersonaDTO personaDTO = null;
				if(listaPersonaDTO.size() > 0) {
					existePersona = true;
					personaDTO = listaPersonaDTO.get(0);
					Integer idPersona = personaDTO.getIdPersona();
					personaTipoDTO = personaTipoDAO.getTecnicosXIdPersonaYIdTipoPersona(idPersona, idTipoPersona);
					
					if(personaTipoDTO != null) {
						existePersonaTipo = true;
					}else {
						existePersonaTipo = false;
					}
				}else {
					existePersona = false;
					existePersonaTipo = false;
				}
				
				if(existePersona == false) {
					Long idUsuario = userSession.getUsuarioFirmadoVO().getId();
					personaDTO = new PersonaDTO();
					
					personaDTO.setNbPersona(nombre);
					personaDTO.setNbPatPersona(aPaterno);
					personaDTO.setNbMatPersona(aMaterno);
					personaDTO.setNuOrden(null);
					personaDTO.setStActivo(true);
					personaDTO.setIdUsrCreacion(idUsuario);
					personaDTO.setFhCreacion(new Date());
					personaDTO.setIdUsrModifica(idUsuario);
					personaDTO.setFhModificacion(new Date());
					personaDAO.save(personaDTO);
					
					resultPersonaGenericaVO.setIdPersona(personaDTO.getIdPersona());
				}else{
					resultPersonaGenericaVO.setIdPersona(listaPersonaDTO.get(0).getIdPersona());
				}
				
				if(existePersonaTipo == false) {
					Integer idPersona = resultPersonaGenericaVO.getIdPersona();
					PersonaTipoDTO insertPersonaTipoDTO = new PersonaTipoDTO();
					insertPersonaTipoDTO.setPersona(personaDTO);
					
					TipoPersonaDTO TipoPersonaDTO = tipoPersonaDAO.getTipoPersonaXID(idTipoPersona);
					insertPersonaTipoDTO.setTipoPersona(TipoPersonaDTO);
					
					personaTipoDAO.save(insertPersonaTipoDTO);
					resultPersonaGenericaVO.setIdTipoPersona(insertPersonaTipoDTO.getIdPersonaTipo());
				}else {
					resultPersonaGenericaVO.setIdTipoPersona(personaTipoDTO.getIdPersonaTipo());
				}
				
				if(existePersona == true) {
					if(existePersonaTipo == true) {
						resultPersonaGenericaVO.setExistia(true);
					}else {
						resultPersonaGenericaVO.setExistia(false);
					}
				}else {
					resultPersonaGenericaVO.setExistia(false);
				}
				
				return resultPersonaGenericaVO;
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
	public void ordenarInstaladores(String mensajeErr) 
		throws Exception, BusinessException, NotFoundException{
		try {
			Map<String, String> parametros = getParametro("ORDER_INSTALADORES"); 
			String strOrdenInstaladores = parametros.get("ORDER_INSTALADORES");
			String[] arrayOrdenInstaladores = strOrdenInstaladores.split("\\|");
			String[] arrayordenInstaladoresCampos = arrayOrdenInstaladores[0].split("-");
			String ordenInstaladoresAscDesc = arrayOrdenInstaladores[1];
			String strOrdenQuery = "";
			for (int i = 0; i < arrayordenInstaladoresCampos.length; i++) {
				String campo = arrayordenInstaladoresCampos[i];
				if(campo.equals("ID")) {
					strOrdenQuery = strOrdenQuery == "" ? "ID_PERSONA " + ordenInstaladoresAscDesc : strOrdenQuery + ", ID_PERSONA " + ordenInstaladoresAscDesc;
				}else if(campo.equals("N") ) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_PERSONA " + ordenInstaladoresAscDesc : strOrdenQuery + ", NB_PERSONA " + ordenInstaladoresAscDesc;
				}else if(campo.equals("P")) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_PAT_PERSONA " + ordenInstaladoresAscDesc : strOrdenQuery + ", NB_PAT_PERSONA " + ordenInstaladoresAscDesc;
				}else if(campo.equals("M")) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_MAT_PERSONA " + ordenInstaladoresAscDesc : strOrdenQuery + ", NB_MAT_PERSONA " + ordenInstaladoresAscDesc;
				}
			}
			
			String consPersonalizada = 
			"SELECT "
			+ "	ID_PERSONA AS idPersona, NB_PERSONA AS nbPersona, "
			+ "	NB_PAT_PERSONA AS nbPatPersona, NB_MAT_PERSONA AS nbMatPersona, "
			+ "	NU_ORDEN AS nuOrden, ST_ACTIVO AS stActivo, ID_USR_CREACION AS idUsrCreacion, "
			+ "	FH_CREACION AS fhCreacion, ID_USR_MODIFICA AS idUsrModifica, "
			+ "	FH_MODIFICACION AS fhModificacion "
			+ "FROM TIE045C_IE_PERSONA "
			+ "WHERE ID_PERSONA != 9999 "
			+ "ORDER BY " + strOrdenQuery;
			List<PersonaCompVO> listaInstaladorCompVO = comunMyBatisDAO.consInstaladoresPersonalizada(consPersonalizada);
			
			for (int x = 0; x < listaInstaladorCompVO.size(); x++) {
				PersonaCompVO personaCompVO = listaInstaladorCompVO.get(x);
				
				PersonaDTO personaDTO = new PersonaDTO();
				
				personaDTO.setIdPersona(personaCompVO.getIdPersona());
				personaDTO.setNbPersona(personaCompVO.getNbPersona());
				personaDTO.setNbPatPersona(personaCompVO.getNbPatPersona());
				personaDTO.setNbMatPersona(personaCompVO.getNbMatPersona());
				personaDTO.setNuOrden(x+2);
				personaDTO.setStActivo(personaCompVO.getStActivo());
				personaDTO.setIdUsrCreacion(personaCompVO.getIdUsrCreacion());
				personaDTO.setFhCreacion(personaCompVO.getFhCreacion());
				personaDTO.setIdUsrModifica(personaCompVO.getIdUsrModifica());
				personaDTO.setFhModificacion(personaCompVO.getFhModificacion());
				personaDAO.update(personaDTO);
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
