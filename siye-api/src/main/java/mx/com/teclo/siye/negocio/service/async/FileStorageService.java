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
	 * 
	 * @param archivoLote MultipartFile recibido
	 * @return Ruta y nombre del archivo guardado
	 * @throws BusinessException
	 */
	String almacenarArchivo(MultipartFile archivoLote) throws BusinessException;

	/**
	 * Obtiene la ruta del directorio en que se depositar&aacute; el archivo ORT
	 * 
	 * @param tipoDirectorio
	 * @return
	 * @throws BusinessException
	 */
	Path getRutaAlmacenamiento(TipoDirectorioStorageEnum tipoDirectorio) throws BusinessException;

	/**
	 * Obtiene el prefijo que se le a&ntilde;adir&aacute; al nombre del archivo para
	 * hacerlo &uacute;nico en el sistema de archivos
	 * 
	 * @return
	 * @throws BusinessException
	 */
	String getPrefijoNbArchivo() throws BusinessException;
}
