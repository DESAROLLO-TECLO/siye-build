package mx.com.teclo.siye.negocio.service.incidencia;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.catalogo.CatalogoService;
import mx.com.teclo.siye.negocio.service.expedienteImg.ExpedienteImgService;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.OdsIncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesos.IEProcesosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.OdsIncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.AltaIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidencVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	@Autowired
	private StSeguimientoDAO stSeguimientoDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private ExpedienteImgService expedienteImgService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private EncuestasDAO encuestasDAO;
	
	@Autowired
	private OdsIncidenciaDAO odsIncidenciaDAO;
	
	@Autowired
	private IEProcesosDAO iEProcesosDAO;
	
	@Autowired
	private OrdenServicioDAO ordenServicioDAO;
	
	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;
	
	private static final String MSG_ERROR_INCIDENCIA_NULA = "No se encontraron incidencias";
	private static final String MSG_ERROR_IMAGEN_NULA = "La imagen esta vac\u00EDa";
	private static final String MSG_ERROR_DESCRIPCION_NULA = "La descripci\u00f3n esta vac\u00EDa";


	@Override
	@Transactional
	public IncidenciaVO getIncidenciabycdIncidencia(String cdIncidencia)  throws NotFoundException{
		IncidenciaVO iVO = new IncidenciaVO();
		IncidenciaDTO iDTO = incidenciaDAO.getIncidenciabycdIncidencia(cdIncidencia);
		if (iDTO == null) {
			throw new NotFoundException(MSG_ERROR_INCIDENCIA_NULA);
		}
		
		iVO = ResponseConverter.copiarPropiedadesFull(iDTO, IncidenciaVO.class);
		
		return iVO;
	}
	
	@Override
	@Transactional
	public String altaIncidencia(AltaIncidenciaVO altaIncidenciaVO)  throws BusinessException{
		try {
			ConfiguracionVO configuracionVO = catalogoService.configuracion("TIE051D_IMG_REQ");
			if (configuracionVO.getCdValorPConfig() == "Si") {
				validarIncidencia2(altaIncidenciaVO.getDescripcion(), altaIncidenciaVO.getListImagen());
			} else {
				validarIncidencia(altaIncidenciaVO.getDescripcion(), altaIncidenciaVO.getListImagen());
			}
			
		} catch (NotFoundException e1) {
			e1.printStackTrace();
		}
		IncidenciaDTO incidenciaDTO = new IncidenciaDTO();
		String respuesta = "";
		String respuestaIncidencia = "";
		String respuestaOdsIncidencia = "";
		String respuestaFinal = "";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yy");
		Date date = new Date();
		String year = sdf2.format(date);
		Long serial = incidenciaDAO.getUltimoId() + 1;
		String serie = "";
		if  (serial < 10) {
			serie = "00000" + serial;
		}
		if  (serial < 100 && serial > 9) {
			serie = "0000" + serial;
		}
		if  (serial < 1000 && serial > 99) {
			serie = "000" + serial;
		}
		if  (serial < 1000 && serial > 999) {
			serie = "00" + serial;
		}
		if  (serial < 10000 && serial > 9999) {
			serie = "0" + serial;
		}
		if  (serial < 100000&& serial > 99999) {
			serie = "" + serial;
		}
		String cdIncidencia = "I" + year + serie;
		String nbIncidencia = "Incidencia " + serie;
		StSeguimientoDTO stAutorizacionDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo("NO_AUT_ATND","ID_ST_AUTORIZACION","TIE051D_IE_INCIDENCIA");
		StSeguimientoDTO stIncidenciaDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo("NO_ATND","ST_INCIDENCIA","TIE051D_IE_INCIDENCIA");
		StSeguimientoDTO stSeguimiento = stSeguimientoDAO.obtenerStSeguimientoByCodigo("NUEVO","ID_ST_SEGUIMIENTO","TIE051D_IE_INCIDENCIA");
		StSeguimientoDTO tpIncidenciaDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo(altaIncidenciaVO.getTpIncidencia().getCdStSeguimiento(),"ID_TP_INCIDENCIA","TIE051D_IE_INCIDENCIA");
		StSeguimientoDTO prioridadDTO = stSeguimientoDAO.obtenerStSeguimientoByCodigo(altaIncidenciaVO.getPrioridad().getCdStSeguimiento(),"ID_PRIORIDAD","TIE051D_IE_INCIDENCIA");
		IEprocesosDTO procesoDTO = iEProcesosDAO.consultarProcesoByidProceso(altaIncidenciaVO.getIdProceso());
		EncuestasDTO encuestasDTO = encuestasDAO.encuestaIntento(altaIncidenciaVO.getIdEncuesta());
		incidenciaDTO.setCdIncidencia(cdIncidencia);
		incidenciaDTO.setNbIncidencia(nbIncidencia);
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
		incidenciaDTO.setStSeguimiento(stSeguimiento);
		incidenciaDTO.setEncuesta(encuestasDTO);
		incidenciaDTO.setiEproceso(procesoDTO);

		try {
			CentroInstalacionVO centroInstalacionVO = catalogoService.getModAten();
			CentroInstalacionDTO centroInstalacionDTO = new CentroInstalacionDTO();
			centroInstalacionDTO = centroInstalacionDAO.findOne(centroInstalacionVO.getIdCentroInstalacion());
			incidenciaDTO.setCentroInstalacion(centroInstalacionDTO);
			incidenciaDAO.save(incidenciaDTO);
			respuesta = "";
		} catch (Exception e) {
			respuesta = "Error al guardar la incidencia. ";
		}
		
		try {
			incidenciaDAO.save(incidenciaDTO);
			if (altaIncidenciaVO.getListImagen() == null || altaIncidenciaVO.getListImagen().isEmpty()) {
				respuestaIncidencia = "";
			} else {
				respuestaIncidencia = expedienteImgService.saveImagenIncidencia(altaIncidenciaVO.getListImagen(), incidenciaDTO);
			}
			if (altaIncidenciaVO.getIdOrdenServicio() != null && altaIncidenciaVO.getIdOrdenServicio() != 0 ) {
				OrdenServicioDTO ordenServicioDTO = ordenServicioDAO.obtenerOrdenServicio(altaIncidenciaVO.getIdOrdenServicio());
				OdsIncidenciaDTO odsIncidenciaDTO = new OdsIncidenciaDTO();
				odsIncidenciaDTO.setIdIncidencia(incidenciaDTO);
				odsIncidenciaDTO.setIdOrdenServicio(ordenServicioDTO);
				odsIncidenciaDAO.save(odsIncidenciaDTO);
			}
			respuestaOdsIncidencia = "";
		} catch (Exception e) {
			respuestaOdsIncidencia = "Error al guardar la relacion con la orden de servicio.";
		}
		if (respuesta == "" && respuestaIncidencia == "" && respuestaOdsIncidencia == "") {
			respuestaFinal = "Se guardo la incidencia correctamente con folio: " +  cdIncidencia;
			
		} else {
			respuestaFinal = respuesta + respuestaIncidencia + respuestaOdsIncidencia;
			
		} 
		return respuestaFinal;
	}
	
	
	
	
	private void validarIncidencia(String descripcion, List<ImagenVO>  listImagenVO) throws BusinessException{
		if (descripcion ==  null || descripcion == "") {
			throw new BusinessException(MSG_ERROR_DESCRIPCION_NULA);
		}
	}
	
	private void validarIncidencia2(String descripcion, List<ImagenVO>  listImagenVO) throws BusinessException{
		if (listImagenVO == null || listImagenVO.isEmpty()) {
			throw new BusinessException(MSG_ERROR_IMAGEN_NULA);
		}
		if (descripcion ==  null || descripcion == "") {
			throw new BusinessException(MSG_ERROR_DESCRIPCION_NULA);
		}
	}
	
	 @Override
	@Transactional
	public IncidencVO incidenciaByCdIncidencia(String cdIncidenc)  throws NotFoundException{
		IncidencVO incidenciaVO = new IncidencVO();
		IncidenciaDTO incidenciaDTO = incidenciaDAO.incidenciaBycdIncidencia(cdIncidenc);
		if (incidenciaDTO == null) {
			throw new NotFoundException(MSG_ERROR_INCIDENCIA_NULA);
		}
		
		incidenciaVO = ResponseConverter.copiarPropiedadesFull(incidenciaDTO, IncidencVO.class);
		
		return incidenciaVO;
	}
	 
	

}
