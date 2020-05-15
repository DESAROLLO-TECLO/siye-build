package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;

/**
 * 
 * @author beaatriz.orosio@unitis.com.mx
 *
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaMasivaServiceImpl.class);
	private static final Long ID_PARAMETRO_DIRECTORIO_ORT = 7L;
	private static final Long ID_PARAMETRO_PREFIJO_ARCHIVO_ORT = 8L;
	private static final String MSG_ERROR_PATH_ORT_NO_DEFINIDO = "No hay una ruta de almacenamiento ORT configurada";
	private static final String MSG_ERROR_PATH_ORT_INVALIDA = "Ruta de almacenamiento ORT inv\u00E1lida";
	private static final String MSG_ERROR_PATH_DIRECTORIO_DESTINO = "No existe el directorio de lotes";
	private static final String MSG_ERROR_CREACION_PATH = "Hubo un error al crear el directorio {0}.";
	private static final String MSG_ERROR_YA_EXISTE_DIRECTORIO = "No fue necesario crear el directorio {0} porque ya existe";
	private static final String MSG_ERROR_IO = "Hubo un error al guardar el archivo";
	private static final String MSG_ERROR_PATTER_FILE_PREFIX_NULO = "No hay un patron de fecha prefijo definido";
	private static final String MSG_ERROR_PATTER_FILE_PREFIX_INVALIDO = "El patron de fecha prefijo es invalido";
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Override
	@Transactional
	public String almacenarArchivo(MultipartFile archivo) throws BusinessException {
		String fileName = getPrefijoNbArchivo() + StringUtils.cleanPath(archivo.getOriginalFilename());
		Path directorioToAlmacenamiento = crearDirectoriosAlmacenamiento();
		Path nvoArchivo = directorioToAlmacenamiento.resolve(fileName);
		try {
			Files.copy(archivo.getInputStream(), nvoArchivo, StandardCopyOption.REPLACE_EXISTING);			
			return nvoArchivo.normalize().toString();
		} catch (NoSuchFileException e) {
			throw new BusinessException(MSG_ERROR_PATH_DIRECTORIO_DESTINO);
		} catch (IOException e) {
			throw new BusinessException(MSG_ERROR_IO);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Path getRutaAlmacenamiento(TipoDirectorioStorageEnum tipoDirectorio) throws BusinessException {
		ConfiguracionOSDTO paramConfigDTO = configuracionDAO.findOne(ID_PARAMETRO_DIRECTORIO_ORT);

		if (paramConfigDTO == null) {
			throw new BusinessException(MSG_ERROR_PATH_ORT_NO_DEFINIDO);
		}
		String dirAlmacenamiento = paramConfigDTO.getCdValorConfig().trim();
		if (org.apache.commons.lang3.StringUtils.isBlank(dirAlmacenamiento)) {
			throw new BusinessException(MSG_ERROR_PATH_ORT_INVALIDA);
		}
		Path directorioToAlmacenamiento = Paths
				.get(dirAlmacenamiento + FILE_SEPARATOR + tipoDirectorio.getCdTipo()).toAbsolutePath().normalize();
		return directorioToAlmacenamiento;
	}

	@Override
	@Transactional	
	public String getPrefijoNbArchivo() throws BusinessException {
		ConfiguracionOSDTO configPrefijoDTO = configuracionDAO.findOne(ID_PARAMETRO_PREFIJO_ARCHIVO_ORT);
		if (configPrefijoDTO == null
				|| org.apache.commons.lang3.StringUtils.isBlank(configPrefijoDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_ERROR_PATTER_FILE_PREFIX_NULO);
		}

		try {
			return new SimpleDateFormat(configPrefijoDTO.getCdValorConfig()).format(new Date());
		} catch (IllegalArgumentException e) {
			throw new BusinessException(MSG_ERROR_PATTER_FILE_PREFIX_INVALIDO);
		}
	}	
	public static String extraerNbFinal(String nombreFinal) {
		Path directorioToAlmacenamiento = Paths.get(nombreFinal);
		return directorioToAlmacenamiento.getFileName().toString();
	}

	/**
	 * Crea un directorio especificado con la ruta recibida como par&aacute;metro
	 * @param path ruta en que se cear&aacute; el directorio
	 * @throws BusinessException
	 */
	private void crearDirectorio(Path path) throws BusinessException {
		boolean isDirectorioExistente = Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
		if (!isDirectorioExistente) {
			try {
				Files.createDirectory(path);
			} catch (FileAlreadyExistsException e) {
				LOGGER.info(MessageFormat.format(MSG_ERROR_YA_EXISTE_DIRECTORIO,
						TipoDirectorioStorageEnum.INPUT.getCdTipo()));
			} catch (IOException e) {
				throw new BusinessException(
						MessageFormat.format(MSG_ERROR_CREACION_PATH, TipoDirectorioStorageEnum.INPUT.getCdTipo()));
			}
		}

	}

	/**
	 * Crea los tres directorios de almacenamiento requeridos en la carga masiva:
	 * <ul>
	 * <li>Directorio recibido, en donde se guarda el archivo recibido</li>
	 * <li>Directorio validado, en donde se guarda el archivo validado en contenido</li>
	 * <li>Directorio entregado, en donde se guarda el archivo con IDs de registros creados</li>
	 * </ul>
	 * @return Path ruta del directorio recibido
	 * @throws BusinessException
	 */
	private Path crearDirectoriosAlmacenamiento() throws BusinessException {

		Path rutaRecibido = getRutaAlmacenamiento(TipoDirectorioStorageEnum.INPUT);
		Path rutaEntregado = Paths.get(rutaRecibido.normalize().toString().replaceFirst(
				TipoDirectorioStorageEnum.INPUT.getCdTipo(), TipoDirectorioStorageEnum.OUTPUT.getCdTipo()));
		Path rutaValidado = Paths.get(rutaRecibido.normalize().toString().replaceFirst(
				TipoDirectorioStorageEnum.INPUT.getCdTipo(), TipoDirectorioStorageEnum.STAGE.getCdTipo()));

		StringBuilder sb = new StringBuilder();
		sb.append(" ");

		try {
			crearDirectorio(rutaRecibido);
		} catch (BusinessException e) {
			sb.append(" ");
			sb.append(e.getMessage());
		}
		try {
			crearDirectorio(rutaEntregado);
		} catch (BusinessException e) {
			sb.append(" ");
			sb.append(e.getMessage());
		}
		try {
			crearDirectorio(rutaValidado);
		} catch (BusinessException e) {
			sb.append(" ");
			sb.append(e.getMessage());
		}

		String mensajeError = sb.toString().trim();
		if(org.apache.commons.lang3.StringUtils.isNotBlank(mensajeError)) {
			throw new BusinessException(mensajeError);
		}
		
		return rutaRecibido;
	}

}
