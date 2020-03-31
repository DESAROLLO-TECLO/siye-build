package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.filtro.FiltroVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;

/**
 * Administra la alta, consulta, actualizaci&oacute;n y baja l&oacute;gica de
 * veh&iacute;culo
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface VehiculoService {
	/**
	 * Obtiene un veh&iacute;culo por su identificador
	 * 
	 * @param idVehiculo
	 * @return
	 */
	VehiculoVO obtenerVehiculo(Long idVehiculo);

	/**
	 * Busca los veh&iacute;culos que coincidan con el filtro
	 * 
	 * @param filtro
	 * @return List<VehiculoVO>
	 */
	List<VehiculoVO> buscarVehiculos(FiltroVehiculoVO filtro);
	
	VehiculoVO bucarVehiculoPlaca(String placa)throws BusinessException;
}
