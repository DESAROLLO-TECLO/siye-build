package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.ordenServicio.OrdenServiVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

public interface OrdenServicioService {
	
	public List<OrdenServicioVO> consultaOrden(String cdTipoBusqueda, String valor) throws NotFoundException;

	/**
     * Descripción: Método para actualizar los datos del 
     * orden de servicio
     * @author Jorge Luis
     * @param OrdenServiVO
     * @return Boolean
     * */
	public Boolean actualizaOrdenServicio(OrdenServicioVO osVO) throws NotFoundException, BusinessException;
	
	public OrdenServicioVO findOrdenServicio(Long idOrdenServico) throws NotFoundException, BusinessException;
	
	public OrdenServicioVO findOrdenServiciobyCD_ORDEN_SERVICIO(String  cdOrdenServicio) throws NotFoundException, BusinessException;
	
	/**
	 * Valida y registra en modo "express" una orden de servicio en el sistema.<br>
	 * Esto aplica cuando el veh&iacute;lo no fu&eacute; encontrado en el sistema y tiene
	 * una cita, por lo que se capturar&aacute; la orden junto con una incidencia.
	 * 
	 * @author beatriz.orosio@unitis.com.mx
	 * @param ordenServicioVO orden de servicio capturada desde el sistema
	 * @return Long idOrdenServicio
	 * @throws BusinessException
	 */
	Long crearOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException;
	
	public void saveOrdenServicio(OrdenServiVO ordenServiVO);

	List<OrdenServicioVO> consultaOrdenServicioAll() throws NotFoundException; 
	
	List<OrdenServicioVO> consultaHistorica(Boolean busquedaAvanzada,String cdTipoBusqueda,
		    String valorBusqueda, String fhInicio, String fhFin,Long centroInstalacion,Long estatusSeguimiento,
		    Boolean isLote, Boolean isIncidencia,
			String valorLoteIncidencia, Long tipoKit,
            Long tipoPlan) throws NotFoundException; 

}
