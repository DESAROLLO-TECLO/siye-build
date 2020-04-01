package mx.com.teclo.siye.negocio.service.incidencia;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.AltaIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	@Autowired
	private StSeguimientoDAO stSeguimientoDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	private static final String MSG_ERROR_INCIDENCIA_NULA = "No se Encontraron Incidencias";
	private static final String MSG_ERROR_IMAGEN_NULA = "La imagen esta vac\u00EDa";
	private static final String MSG_ERROR_DESCRIPCION_NULA = "La descripci\u00f3n esta vac\u00EDa";


	@Override
	@Transactional
	public IncidenciaVO getIncidenciabycdIncidencia(String cdIncidencia)  throws NotFoundException{
		IncidenciaVO iVO = new IncidenciaVO();
		IncidenciaDTO iDTO = incidenciaDAO.getIncidenciabycdIncidencia(cdIncidencia);
		iVO = ResponseConverter.copiarPropiedadesFull(iDTO, IncidenciaVO.class);
		
		if (iDTO == null) {
			throw new NotFoundException(MSG_ERROR_INCIDENCIA_NULA);
		}
		
		return iVO;
	}
	
	@Override
	@Transactional
	public Boolean  altaIncidencia(AltaIncidenciaVO altaIncidenciaVO)  throws BusinessException{
		
		validarIncidencia(altaIncidenciaVO.getDescripcion(), altaIncidenciaVO.getExpedientesImgVO());
		IncidenciaDTO incidenciaDTO = new IncidenciaDTO();
		Boolean respuesta = false;
		SimpleDateFormat sdf2 = new SimpleDateFormat("yy");
		Date date = new Date();
		String year = sdf2.format(date);
		Long serial = incidenciaDAO.getUltimoId();
		String serie = "";
		if  (serial < 10) {
			serie = "00000" + serial;
		}
		if  (serial < 100) {
			serie = "0000" + serial;
		}
		if  (serial < 1000) {
			serie = "000" + serial;
		}
		if  (serial < 1000) {
			serie = "00" + serial;
		}
		if  (serial < 10000) {
			serie = "0" + serial;
		}
		if  (serial < 100000) {
			serie = "" + serial;
		}
		String cdIncidencia = "I" + year + serie;
		String nbIncidencia = "NB" + cdIncidencia;
		StSeguimientoDTO stAutorizacionDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo("NO_AUT_ATND");
		StSeguimientoDTO stIncidenciaDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo("NO_ATND");
		StSeguimientoDTO tpIncidenciaDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo(altaIncidenciaVO.getTpIncidencia().getCdStSeguimiento());
		StSeguimientoDTO prioridadDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo(altaIncidenciaVO.getPrioridad().getCdStSeguimiento());
		incidenciaDTO.setCdIncidencia(cdIncidencia);
		incidenciaDTO.setCdIncidencia(nbIncidencia);
		incidenciaDTO.setTxIncidencia(altaIncidenciaVO.getDescripcion());
		incidenciaDTO.setStActivo(true);
		incidenciaDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		incidenciaDTO.setFhCreacion(new Date());
		incidenciaDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		incidenciaDTO.setFhModificacion(new Date());
		incidenciaDTO.setTpIncidencia(tpIncidenciaDTO);
		incidenciaDTO.setStIncidencia(stIncidenciaDTO);
		incidenciaDTO.setStAutorizacion(stAutorizacionDTO);
		incidenciaDTO.setPrioridad(prioridadDTO);
		
		respuesta = (Boolean) incidenciaDAO.save(incidenciaDTO);
		
		return respuesta;
	}
	
	
	
	
	private void validarIncidencia(String descripcion, ExpedienteImgVO  expedientesImgDTO)  throws BusinessException{

		if (expedientesImgDTO == null) {
			throw new BusinessException(MSG_ERROR_IMAGEN_NULA);
		}
		if (descripcion ==  null || descripcion == "") {
			throw new BusinessException(MSG_ERROR_DESCRIPCION_NULA);
		}
	}

}
