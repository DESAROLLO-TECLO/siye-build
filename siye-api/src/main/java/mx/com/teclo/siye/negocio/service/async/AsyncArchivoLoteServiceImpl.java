/**
 * 
 */
package mx.com.teclo.siye.negocio.service.async;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;

@Service
public class AsyncArchivoLoteServiceImpl implements AsyncArchivoLoteService {

	private static final String MSG_ARCHIVO_NULO = "El archivo lote no fue recibido";
	private static final String MSG_ARCHIVO_VACIO = "El archivo lote esta vac\u00EDo";
	private static final String MSG_ARCHIVO_CONTENT_TYPE_INVALIDO = "La extensi\u00F3n del archivo lote es inv\u00E1lida";
	private static final String MSG_ARCHIVO_COLUMNAS_INVALIDAS = "El numero de columnas del archivo lote no coincide con el layout vigente";
	private static final List<String> contentTypes = Arrays.asList("text/csv");
	private static final String MSG_LAYOUT_VIGENTE_NULO = "No existe un layout vigente. Contacte al administrador.";
	private static final String MSG_ARCHIVO_REGEX_NAME_NULO = "No hay una regla para validar el nombre del archivo.";
	private static final String MSG_ARCHIVO_REGEX_NAME_INVALIDO = "El nombre del archivo es invalido.";
	private static final String MSG_ARCHIVO_TAMANIO_REBASADO = "El tamanio del archivo excede el maximo configurado";
	private static final String MSG_DIRECTORIO_ORT_INDEFINIDO = "El directorio ort no ha sido especificado";
	private static final Long ID_CONTENT_TYPE = 6L;
	private static final Long ID_PROCESO_CON_RECHAZO = 9L;
	private static final String MSG_PARAM_CONTENT_TYPE_NULO = "El tipo de archivo esperado no esta definido";

	@Autowired
	private LayoutService layoutService;

	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Autowired
	private FileStorageService storageService;

	@Autowired
	private UsuarioFirmadoService contexto;

	@Autowired
	private StSeguimientoDAO seguimientoDAO;

	@Autowired
	private LoteOrdenServicioDAO loteDAO;

	@Override
	@Transactional
	public Long registrarArchivoLote(MultipartFile archivoLote) throws BusinessException {

		if (archivoLote == null) {
			throw new BusinessException(MSG_ARCHIVO_NULO);
		}

		boolean isProcesoConRechazo = getIsProcesoConRechazo();

		try {
			validarEstructuraBasica(archivoLote);
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
	public void cargarArchivoLote() throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarSeguimiento() throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArchivoLoteVO getArchivoLote(Long idArchivoLote) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Validar nombre, tamanio y columnas del archivo.<br>
	 * 
	 * @param archivoLote
	 * @throws BusinessException Cuando falte alguna configuracion requerida
	 */
	private void validarEstructuraBasica(MultipartFile archivoLote) throws BusinessException {

		if (archivoLote.isEmpty()) {
			throw new BusinessException(MSG_ARCHIVO_VACIO);
		}

		TipoLayoutVO layoutVigenteVO = layoutService.getLayoutVigente();

		if (layoutVigenteVO == null) {
			throw new BusinessException(MSG_LAYOUT_VIGENTE_NULO);
		}

		ConfiguracionOSDTO contentTypeDTO = configuracionDAO.findOne(ID_CONTENT_TYPE);

		if (contentTypeDTO == null || StringUtils.isBlank(contentTypeDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_PARAM_CONTENT_TYPE_NULO);
		}

		if (!archivoLote.getContentType().equalsIgnoreCase(contentTypeDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_ARCHIVO_CONTENT_TYPE_INVALIDO);
		}

		if (!StringUtils.isBlank(layoutVigenteVO.getTxMascara())) {
			// TODO: validar formato del nombre del archivo
			// throw new BusinessException(MSG_ARCHIVO_REGEX_NAME_NULO);
		}

		if (layoutVigenteVO.getCdTamanioMax() != null
				&& (archivoLote.getSize() / (1024 * 1024)) > layoutVigenteVO.getCdTamanioMax()) {
			throw new BusinessException(MSG_ARCHIVO_TAMANIO_REBASADO);
		}
	}

	/**
	 * Indicar si debe rechazar un archivo invalido
	 * 
	 * @return
	 */
	private boolean getIsProcesoConRechazo() {
		boolean rechazarArchivoInvalido = true;
		ConfiguracionOSDTO configDTO = configuracionDAO.findOne(ID_PROCESO_CON_RECHAZO);
		if (configDTO == null || StringUtils.isBlank(configDTO.getCdValorConfig())) {
			return rechazarArchivoInvalido;
		}
		try {
			return new Boolean(configDTO.getCdValorConfig()).booleanValue();
		} catch (IllegalArgumentException e) {
			return rechazarArchivoInvalido;
		}
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
