/**
 * 
 */
package mx.com.teclo.siye.negocio.service.async;

import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;

/**
 * Administra la carga masiva de &oacute;rdenes de servicios contenidos en un
 * archivo lote
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface AsyncArchivoLoteService {

	/**
	 * Despu&eacute;s de determinar si el nombre y tama&ntilde;io del archivo es
	 * correcto o incorrecto se registra el nombre en base de datos junto con la
	 * ruta temporal donde se resguard&aacute; para ser validado.
	 * 
	 * @param MultipartFile Archivo lote
	 * @return Long ID del lote
	 * @throws BusinessException
	 */
	Integer registrarArchivoLote(MultipartFile archivoLote) throws BusinessException;

	/**
	 * 
	 * Valida que un archivo lote cargado desde la aplicaci&oacute;n web cumpla lo
	 * siguiente:<br>
	 * <ul>
	 * <li>Que el archivo tenga la cantidad correcta de columnas</li>
	 * <li>Que cada columna tenga el tipo de dato correcto</li>
	 * <li>Que cada columna configurada como no nula tenga un dato</li>
	 * <li>Que detecte &oacute;rdenes de trabajo duplicadas</li>
	 * <li>Si incumple alguno de los tres primeros puntos, clasificar el archivo con
	 * resultado fallido en su validaci&oacute;n arrojando una excepci&oacute;n</li>
	 * </ul>
	 * 
	 * @throws BusinessException
	 */

	void validarArchivoLote() throws BusinessException;

	/**
	 * Toma el contenido de un archivo validado y por cada l&iacute;nea inserta una
	 * orden de servicio en la BD.
	 * 
	 * @throws BusinessException
	 */
	void procesarArchivoLote() throws BusinessException;

	/**
	 * Actualiza el estatus y conteos de ODS en un archivo lote.
	 * 
	 * @throws BusinessException
	 */
	void actualizarArchivoLote() throws BusinessException;

	/**
	 * Recupera la informaci&oacute;n de un archivo lote
	 * 
	 * @return ArchivoLoteVO
	 * @throws BusinessException
	 */
	ArchivoLoteVO getArchivoLote(Long idArchivoLote) throws BusinessException;

}
