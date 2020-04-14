package mx.com.teclo.siye.negocio.service.async;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;

/**
 * Administra el copiado del archivo recibido al directorio destino configurado
 * para la aplicaci&oacute;n
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface FileStorageService {

	/**
	 * Almacena el archivo en el directorio configurado
	 * @param archivoLote MultipartFile recibido
	 * @return Ruta y nombre del archivo guardado
	 * @throws BusinessException
	 */
	String almacenarArchivo(MultipartFile archivoLote) throws BusinessException;

	Path getRutaAlmacenamiento(TipoDirectorioStorageEnum tipoDirectorio) throws BusinessException;
}
