/**
 * 
 */
package mx.com.teclo.siye.negocio.service.async;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.TipoLayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigLayoutVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.ResponseDowloadFileVO;
import mx.com.teclo.siye.util.comun.RutinasTiempoImpl;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;

@Service
public class AsyncArchivoLoteServiceImpl implements AsyncArchivoLoteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncArchivoLoteService.class);
	private static final String TIPO_ARCHIVO_ORT = "ORT";
	private static final String MSG_ARCHIVO_NULO = "El archivo lote no fue recibido";
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String MSG_ERROR_QUERIES_INCOMPLETOS = "No se generaron todos los comandos SQL para procesar el archivo ID {0}";
	private static final String MSG_CONFIG_CARGA_MASIVA_EXITOSA = "Configuracion completa del archivo ID {0}";
	private static final String MSG_INICIANDO_CARGA_MASIVA = "Iniciando la carga masiva del archivo ID {0}";
	private static final String MSG_ACTUALIZANDO_SEGUIMIENTO = "El archivo ID {0} sera actualizado en su seguimiento";

	@Autowired
	private LayoutService layoutService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private FileStorageService storageService;

	@Autowired
	private StSeguimientoDAO seguimientoDAO;

	@Autowired
	private LoteOrdenServicioDAO loteDAO;

	@Autowired
	private CargaMasivaService cargaMasivaService;

	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;
	
	@Autowired
	private RutinasTiempoImpl rutinasTiempo;

	@Override
	@Transactional
	public Long registrarArchivoLote(MultipartFile archivoLote) throws BusinessException {
		String errorArchivo = null;

		if (archivoLote == null) {
			throw new BusinessException(MSG_ARCHIVO_NULO);
		}
		boolean isProcesoConRechazo = layoutService.getIsProcesoConRechazo();

		try {
			uploadService.validarEstructuraBasica(archivoLote);
		} catch (Exception e) {
			if (isProcesoConRechazo) {
				throw e;
			} else {
				errorArchivo = e.getMessage();
			}
		}

		String nombreArchivo = storageService.almacenarArchivo(archivoLote);
		Long idArchivoLote = crearLote(nombreArchivo, errorArchivo);
		return idArchivoLote;

	}

	@Override
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void cargarArchivoLote(Long idArchivoLote) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_INICIANDO_CARGA_MASIVA, idArchivoLote));
		LoteOrdenServicioDTO loteDTO = loteDAO.findOne(idArchivoLote);

		if (!loteDTO.getStSeguimiento().getCdStSeguimiento()
				.equalsIgnoreCase(ArchivoSeguimientoEnum.RECIBIDO.getCdArchivoSeg())) {
			return;
		}
		
		cargaMasivaService.iniciarCargaMasiva(idArchivoLote);

		ConfigCargaMasivaVO config = null;
		
		try {
			config = layoutService.getConfigCargaMasiva(idArchivoLote);
		}catch(BusinessException e) {
			actualizarSeguimiento(idArchivoLote, ArchivoSeguimientoEnum.FINALIZADO,
					e.getMessage());
			return;
		}

		if (isMapaSQLValido(config)) {
			LOGGER.info(MessageFormat.format(MSG_CONFIG_CARGA_MASIVA_EXITOSA, idArchivoLote));
			cargaMasivaService.procesarLineas(config);

		} else {
			actualizarSeguimiento(idArchivoLote, ArchivoSeguimientoEnum.FINALIZADO,
					MessageFormat.format(MSG_ERROR_QUERIES_INCOMPLETOS, idArchivoLote));

		}
	}

	@Override
	@Transactional
	@Async
	public void actualizarSeguimiento(Long idArchivoLote, ArchivoSeguimientoEnum seguimiento, String txLoteOdsError)
			throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_ACTUALIZANDO_SEGUIMIENTO, idArchivoLote));
		LoteOrdenServicioDTO loteDTOrdenServicioDTO = loteDAO.findOne(idArchivoLote);
		StSeguimientoDTO seguimientoDTO = seguimientoDAO.obtenerStSeguimientoByCodigo(seguimiento.getCdArchivoSeg(),
				seguimiento.getCdTipoSeg(), seguimiento.getNbTipoSeg());
		loteDTOrdenServicioDTO.setStSeguimiento(seguimientoDTO);
		loteDTOrdenServicioDTO.setTxLoteOds(txLoteOdsError);
		loteDTOrdenServicioDTO.setFhModificacion(new Date());
		loteDAO.update(loteDTOrdenServicioDTO);
		loteDAO.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public ArchivoLoteVO obtenerArchivoLote(Long idArchivoLote) throws BusinessException {
		return loteDAO.obtenerLote(idArchivoLote);
	}

	/**
	 * Registra en el sistema el archivo recibido o rechazado
	 * 
	 * @param nombreFinal
	 * @param error
	 * @return
	 */
	@Transactional
	@Override
	public Long crearLote(String nombreFinal, String error) {
		LoteOrdenServicioDTO loteDTO = new LoteOrdenServicioDTO();
		loteDTO.setNbLoteOds(nombreFinal);
		loteDTO.setNbArchivoFinal(FileStorageServiceImpl.extraerNbFinal(nombreFinal));
		loteDTO.setCdLoteOds(TIPO_ARCHIVO_ORT);
		loteDTO.setIdUsrCreacion(1L);
		loteDTO.setFhCreacion(new Date());
		loteDTO.setIdUsrModifica(1L);
		loteDTO.setFhModificacion(new Date());
		loteDTO.setStActivo(Boolean.TRUE.booleanValue());
		ConfigLayoutVO layoutVigente = tipoLayoutDAO.getLayoutVigente();

		if (layoutVigente != null) {
			loteDTO.setTipoLayout(tipoLayoutDAO.findOne(layoutVigente.getIdTipoLayout()));
		}

		if (StringUtils.isNotBlank(error)) {
			loteDTO.setStSeguimiento(seguimientoDAO.obtenerStSeguimientoByCodigo(
					ArchivoSeguimientoEnum.RECHAZADO.getCdArchivoSeg(), ArchivoSeguimientoEnum.RECHAZADO.getCdTipoSeg(),
					ArchivoSeguimientoEnum.RECHAZADO.getNbTipoSeg()));
			loteDTO.setTxLoteOds(error);
		} else {
			loteDTO.setStSeguimiento(seguimientoDAO.obtenerStSeguimientoByCodigo(
					ArchivoSeguimientoEnum.RECIBIDO.getCdArchivoSeg(), ArchivoSeguimientoEnum.RECIBIDO.getCdTipoSeg(),
					ArchivoSeguimientoEnum.RECIBIDO.getNbTipoSeg()));
		}

		return (Long) loteDAO.save(loteDTO);

	}

	/**
	 * Verifica que existan los comandos insert y select por cada tabla
	 * 
	 * @param configCargaMasivaVO
	 * @return
	 * @throws BusinessException
	 */
	private boolean isMapaSQLValido(ConfigCargaMasivaVO configCargaMasivaVO) throws BusinessException {
				
		if(configCargaMasivaVO.getConfigInsercion() == null || 
				configCargaMasivaVO.getConfigInsercion().isEmpty()) {
			
		}
		int totalTablas = configCargaMasivaVO.getConfigInsercion().size();
		int totalQueries = 0;
		for (Map.Entry<String, InsercionTablaVO> entry : configCargaMasivaVO.getConfigMoldesSQL().entrySet()) {
			InsercionTablaVO value = entry.getValue();
			if (StringUtils.isNotBlank(value.getInsertSQL()) && value.getInsertSQL().startsWith(INSERT_INTO)) {
				totalQueries++;
			}
		}
		return totalTablas == totalQueries;

	}

	@Override
	@Transactional(readOnly = true)	
	public List<ArchivoLoteVO> getFilesUploadToDay() {
		Date dateCurrent=rutinasTiempo.getFechaActual();
		Long idUserSession=1L;
		return loteDAO.obtenerLotesPorFecha(idUserSession,dateCurrent);
	}
	
	@SuppressWarnings("resource")
	@Override
	@Transactional(readOnly = true)
	public ResponseDowloadFileVO dowloaderFileByIdLote(Long idLote) throws NotFoundException, IOException, BusinessException{
		ArchivoLoteVO lote=loteDAO.obtenerLote(idLote);
		
		if(lote == null || lote.getNbLoteOds() == null)
			throw new NotFoundException("No se encontro el archivo para su descarga");
		
		Path path=storageService.getRutaAlmacenamiento(TipoDirectorioStorageEnum.OUTPUT);
		String rutaFile=path.toString();
		
		File f = new File(rutaFile,lote.getNbArchivoFinal());
		if(!f.exists() || !f.canRead())
			throw new NotFoundException("No se encontro el archivo para su descarga");
			
	        FileInputStream fis = new FileInputStream(f);
	       
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); //no doubt here is 0
	            }
	            bos.close();
	            fis.close();
	        } catch (IOException ex) {
	        	throw new NotFoundException("No se encontro el archivo para su descarga");
	        }
		 
		 ResponseDowloadFileVO respDowFileVO=new ResponseDowloadFileVO();
		 respDowFileVO.setArrayFile(bos.toByteArray());
		 respDowFileVO.setNameFile(lote.getNbArchivoFinal());
		return respDowFileVO;
	}
	
}
