/**
 * 
 */
package mx.com.teclo.siye.negocio.service.ods;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

/**
 * Administra la alta, actualizaci&oacute;n y borrado l&oacute;gico de la orden
 * de servicio
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface OrdenServicioService {

	/**
	 * Valida datos requeridos y en el formato correcto para guardar en la base de
	 * datos
	 * 
	 * @param ordenServicioVO
	 * @return Long idOrdenServicio
	 * @throws BusinessException
	 */
	Long crearOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException;

	/**
	 * Valida datos requeridos y en el formato correcto para actualizar la orden de
	 * servicio
	 * 
	 * @param ordenServicioVO
	 * @throws BusinessException
	 */
	void actualizarOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException;

	/**
	 * Recupera informaci&oacute;n de la orden de servicio
	 * @param idOrdenServicio
	 * @return
	 * @throws BusinessException
	 */
	OrdenServicioVO obtenerOrdenServicio(Long idOrdenServicio) throws BusinessException;


}
