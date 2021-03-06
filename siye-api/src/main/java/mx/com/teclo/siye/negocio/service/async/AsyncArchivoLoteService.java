/**
 * 
 */
package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;
import mx.com.teclo.siye.persistencia.vo.async.ResponseDowloadFileVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;

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
	Long registrarArchivoLote(MultipartFile archivoLote) throws BusinessException;

	/**
	 * 
	 * Procesa cada linea y verifica lo siguiente:<br>
	 * <ul>
	 * <li>Que el archivo tenga la cantidad correcta de columnas</li>
	 * <li>Que cada columna tenga el tipo de dato correcto</li>
	 * <li>Que cada columna configurada como no nula tenga un dato</li>
	 * <li>Que detecte &oacute;rdenes de trabajo duplicadas</li>
	 * <li>Si incumple alguno de los tres primeros puntos, clasificar el archivo con
	 * resultado fallido en su validaci&oacute;n arrojando una excepci&oacute;n</li>
	 * </ul>
	 * @param Long idArchivoLote
	 * @throws BusinessException
	 */

	void cargarArchivoLote(Long idArchivoLote) throws BusinessException;

	/**
	 * Actualiza el seguimiento del archivo
	 * 
	 * @throws BusinessException
	 */
	void actualizarSeguimiento(Long idArchivoLote, ArchivoSeguimientoEnum seguimiento, String txLoteOdsError ) throws BusinessException;

	/**
	 * Recupera la informaci&oacute;n de un archivo lote
	 * 
	 * @return ArchivoLoteVO
	 * @throws BusinessException
	 */
	ArchivoLoteVO obtenerArchivoLote(Long idArchivoLote) throws BusinessException;

	/**
	 * Recupera la informaci&oacute;n del lote
	 * @param nombreFinal
	 * @param error
	 * @return
	 */
	Long crearLote(String nombreFinal, String error) ;
	
	/**
	 * @Descripcion: Obtiene los archivos cargados por dia del usuario en sesi�n
	 * @author DanielUnitis
	 * @return List<ArchivoLoteVO>
	 */
	List<ArchivoLoteVO> getFilesUploadToDay();
	
	/**
	 * @Descripcion regresa el arreglo de byte para descragar el archivo del lote por id
	 * @param idLote
	 * @return  ResponseDowloadFileVO
	 */
	ResponseDowloadFileVO dowloaderFileByIdLote(Long idLote) throws NotFoundException,IOException,BusinessException;
}
