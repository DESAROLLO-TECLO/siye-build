/**
 * 
 */
package mx.com.teclo.siye.negocio.service.async;

import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.LoteOrdenServicioVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;

@Service
public class AsyncArchivoLoteServiceImpl implements AsyncArchivoLoteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncArchivoLoteService.class);

	private static final String MSG_ARCHIVO_NULO = "El archivo lote no fue recibido";
	private static final String MSG_INICIANDO_CARGA_DE_ARCHIVO = "INICIANDO LA CARGA DEL ARCHIVO ID {0}";

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

	@Override
	@Transactional
	public Long registrarArchivoLote(MultipartFile archivoLote) throws BusinessException {
		if (archivoLote == null) {
			throw new BusinessException(MSG_ARCHIVO_NULO);
		}
		boolean isProcesoConRechazo = layoutService.getIsProcesoConRechazo();
		try {
			uploadService.validarEstructuraBasica(archivoLote);
		} catch (BusinessException e) {
			if (isProcesoConRechazo) {
				throw e;
			} else {
				crearLote(archivoLote.getOriginalFilename(), e.getMessage());
			}
		}
		String nombreArchivo = storageService.almacenarArchivo(archivoLote);
		Long idArchivoLote = crearLote(nombreArchivo, null);
		return idArchivoLote;

	}

	@Override
	@Async
	public void cargarArchivoLote(Long idArchivoLote) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_INICIANDO_CARGA_DE_ARCHIVO, idArchivoLote));
		cargaMasivaService.iniciarCargaMasiva(idArchivoLote);		
		ConfigCargaMasivaVO configCargaMasivaVO = layoutService.getConfigCargaMasiva(idArchivoLote);
		cargaMasivaService.procesarLineas(configCargaMasivaVO);

	}

	@Override
	public void actualizarSeguimiento() throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
	public LoteOrdenServicioVO obtenerArchivoLote(Long idArchivoLote) throws BusinessException {
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
		loteDTO.setCdLoteOds("ORT");
		loteDTO.setIdUsrCreacion(1L);
		loteDTO.setFhCreacion(new Date());
		loteDTO.setIdUsrModifica(1L);
		loteDTO.setFhModificacion(new Date());
		loteDTO.setStActivo(Boolean.TRUE.booleanValue());

		if (StringUtils.isNotBlank(error)) {
			loteDTO.setIdStSeguimiento(seguimientoDAO.findOne(ArchivoSeguimientoEnum.RECHAZADO.getIdArchivoSeg()));
			loteDTO.setTxLoteOds(error);
		} else {
			loteDTO.setIdStSeguimiento(seguimientoDAO.findOne(ArchivoSeguimientoEnum.RECIBIDO.getIdArchivoSeg()));
		}

		return (Long) loteDAO.save(loteDTO);

	}

}
