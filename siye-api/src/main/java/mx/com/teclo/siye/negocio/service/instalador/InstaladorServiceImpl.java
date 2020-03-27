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
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.InstaladorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;
import mx.com.teclo.siye.persistencia.mybatis.dao.comun.ComunMyBatisDAO;
import mx.com.teclo.siye.persistencia.mybatis.dao.configuracion.ConfiguracionBDMyBatisDAO;
import mx.com.teclo.siye.persistencia.vo.catalogo.InstaladorCompVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

@Service
public class InstaladorServiceImpl implements InstaladorService{
	
	@Autowired
	private UsuarioFirmadoService userSession;

	@Autowired
	private InstaladorDAO instaladorDAO;

	@Autowired
	private ConfiguracionBDMyBatisDAO configuracionBDMyBatisDAO;

	@Autowired
	private ComunMyBatisDAO comunMyBatisDAO;
	
	@Transactional
	@Override
	public PersonaGenericaVO nuevoInstalador(PersonaGenericaVO personaGenericaVO, String mensajeErr) 
		throws Exception, BusinessException, NotFoundException{
		try {
			String nombre = personaGenericaVO.getNombre().toUpperCase();
			String aPaterno = personaGenericaVO.getaPaterno().toUpperCase();
			String aMaterno = personaGenericaVO.getaMaterno().toUpperCase();
			
			if(nombre == null || nombre.equals("")) {
				mensajeErr = "El nombre del instalador no puede estar vacío.";
				throw new NotFoundException("");
			}else {
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
					strOrdenQuery = "ID_RH_INSTALADOR " + ordenInstaladoresAscDesc;
				}else if(campo.equals("N") ) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_RH_INSTALADOR " + ordenInstaladoresAscDesc : strOrdenQuery + ", NB_RH_INSTALADOR " + ordenInstaladoresAscDesc;
				}else if(campo.equals("P")) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_PAT_RH_INSTALADOR " + ordenInstaladoresAscDesc : strOrdenQuery + ", NB_PAT_RH_INSTALADOR " + ordenInstaladoresAscDesc;
				}else if(campo.equals("M")) {
					strOrdenQuery = strOrdenQuery == "" ? "NB_MAT_RH_INSTALADOR " + ordenInstaladoresAscDesc : strOrdenQuery + ", NB_MAT_RH_INSTALADOR " + ordenInstaladoresAscDesc;
				}
			}
			
			String consPersonalizada = 
			"SELECT ID_RH_INSTALADOR AS idRhInstalador, NB_RH_INSTALADOR AS nbRhInstalador, "
			+ "	NB_PAT_RH_INSTALADOR AS nbPatRhInstalador, NB_MAT_RH_INSTALADOR AS nbMatRhInstalador, "
			+ "	NU_ORDEN AS nuOrden, ST_ACTIVO AS stActivo, ID_USR_CREACION AS idUsrCreacion, "
			+ "	FH_CREACION AS fhCreacion, ID_USR_MODIFICA AS idUsrModifica, "
			+ "	FH_MODIFICACION AS fhModificacion "
			+ "FROM TIE045C_IE_RH_INSTALADOR "
			+ "WHERE ID_RH_INSTALADOR != 9999 "
			+ "ORDER BY " + strOrdenQuery;
			List<InstaladorCompVO> listaInstaladorCompVO = comunMyBatisDAO.consInstaladoresPersonalizada(consPersonalizada);
			
			for (int x = 0; x < listaInstaladorCompVO.size(); x++) {
				InstaladorCompVO instaladorCompVO = listaInstaladorCompVO.get(x);
				
				InstaladorDTO instaladorDTO = new InstaladorDTO();
				
				instaladorDTO.setIdRhInstalador(instaladorCompVO.getIdRhInstalador());
				instaladorDTO.setNbRhInstalador(instaladorCompVO.getNbRhInstalador());
				instaladorDTO.setNbPatRhInstalador(instaladorCompVO.getNbPatRhInstalador());
				instaladorDTO.setNbMatRhInstalador(instaladorCompVO.getNbMatRhInstalador());
				instaladorDTO.setNuOrden(x+2);
				instaladorDTO.setStActivo(instaladorCompVO.getStActivo());
				instaladorDTO.setIdUsrCreacion(instaladorCompVO.getIdUsrCreacion());
				instaladorDTO.setFhCreacion(instaladorCompVO.getFhCreacion());
				instaladorDTO.setIdUsrModifica(instaladorCompVO.getIdUsrModifica());
				instaladorDTO.setFhModificacion(instaladorCompVO.getFhModificacion());
				instaladorDAO.update(instaladorDTO);
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
