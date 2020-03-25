package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;

/**
 * Administra la alta, actualizaci&oacute;n, consulta y baja l&oacute:gica de un
 * veh&iacute;culo
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface VehiculoDAO extends BaseDao<VehiculoDTO> {

	/**
	 * Obtiene un veh&iacute;culo por su identificador
	 * 
	 * @param idVehiculo
	 * @return
	 */
	public VehiculoVO obtenerVehiculo(Long idVehiculo);

}
