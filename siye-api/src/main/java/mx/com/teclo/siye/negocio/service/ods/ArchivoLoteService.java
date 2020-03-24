/**
 * 
 */
package mx.com.teclo.siye.negocio.service.ods;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.ods.ArchivoLoteVO;

/**
 * Administra la carga masiva de ordenes de servicios desde un archivo lote
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface ArchivoLoteService {

	/**
	 * Despu&eacute;s de determinar si el nombre y tamanio del archivo es correcto o
	 * incorrecto se registra el nombre en base de datos junto con la ruta temporal
	 * donde se resguard&aacute; para ser validado.
	 * 
	 * @return Long ID del lote
	 * @throws BusinessException
	 */
	Long registrarArchivoLote() throws BusinessException;

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
