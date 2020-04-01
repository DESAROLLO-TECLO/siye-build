package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;

/**
 * 
 * @author beaatriz.orosio@unitis.com.mx
 *
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
	private static final Long ID_PARAMETRO_DIRECTORIO_ORT = 7L;
	private static final Long ID_PARAMETRO_PREFIJO_ARCHIVO_ORT = 8L;
	private static final String MSG_ERROR_PATH_DIRECTORIO_DESTINO = "No existe el directorio de lotes";
	private static final String MSG_ERROR_IO = "Hubo un error al guardar el archivo";
	private static final String MSG_ERROR_PATTER_FILE_PREFIX_NULO = "No hay un patron de fecha prefijo definido";
	private static final String MSG_ERROR_PATTER_FILE_PREFIX_INVALIDO = "El patron de fecha prefijo es invalido";

	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Override
	@Transactional
	public String almacenarArchivo(MultipartFile archivo) throws BusinessException {
		String fileName = getPrefijoNbArchivo() + StringUtils.cleanPath(archivo.getOriginalFilename());

		ConfiguracionOSDTO paramConfigDTO = configuracionDAO.findOne(ID_PARAMETRO_DIRECTORIO_ORT);
		Path directorioToAlmacenamiento = Paths.get(paramConfigDTO.getCdValorConfig()).toAbsolutePath().normalize();
		Path nvoArchivo = directorioToAlmacenamiento.resolve(fileName);
		try {
			Files.copy(archivo.getInputStream(), nvoArchivo, StandardCopyOption.REPLACE_EXISTING);
			return nvoArchivo.getFileName().toString();
		} catch (NoSuchFileException e) {
			throw new BusinessException(MSG_ERROR_PATH_DIRECTORIO_DESTINO);
		} catch (IOException e) {
			throw new BusinessException(MSG_ERROR_IO);
		}
	}

	private String getPrefijoNbArchivo() throws BusinessException {
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

}
