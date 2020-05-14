package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenServicioDetVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServcioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

public interface OrdenServicioDAO extends BaseDao<OrdenServicioDTO>{

	/**
     * Descripción: Obtener el registro de orden de 
     * servicio mediante su identificador unico
     * @author Jorge Luis
     * @param idOrdenServicio
     * @return OrdenServicioDTO
     * */
	public OrdenServicioDTO obtenerOrdenServicio(Long idOrdenServicio);
	
	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio mediante la placa
     * @author Estephanie Chavez
	 * @param valor
	 * @param centroInstalacion
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByPlaca(String valor, Long centroInstalacion);

	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio mediante el codigo de orden de servicio 
     * @author Estephanie Chavez
	 * @param valor
	 * @param centroInstalacion
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByOrdenServicio(String valor, Long centroInstalacion);
	
	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio mediante el VIN
     * @author Estephanie Chavez
	 * @param valor
	 * @param centroInstalacion
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByVin(String valor, Long centroInstalacion);


	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio del dia
     * @author Estephanie Chavez
	 * @param centroInstalacion
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByFhCita(Long centroInstalacion);
	
	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio mediante la placa
     * @author Maverick
	 * @param valor
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByPlaca(String valor);

	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio mediante el codigo de orden de servicio 
     * @author Maverick
	 * @param valor
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByOrdenServicio(String valor);
	
	/**
     * Descripción: Obtener lista de los registros de orden de 
     * servicio mediante el VIN
     * @author Maverick
	 * @param valor
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByVin(String valor);
	
	
	/**
     * Descripción: Obtener el registro de orden de 
     * servicio mediante CD_ORDEN_SERVICIO
     * @author Eric Rivera
     * @param idOrdenServicio
     * @return OrdenServicioDTO
     * */
	public OrdenServicioDTO obtenerOrdenServicioCD_ORDEN_SERVICIO(String  cdOrdenServicio);
	
	/**
     * Descripción: Detalle de la OS, por centro de instalacion y fecha de la cita 
     * @author Maverick
     * @param List<Long> idCentroInstalacion
     * @return fechaInicio, fechaFin
     * @return List<OrdenServcioDetalleVO>
     * */
	public List<OrdenServcioDetalleVO> getDetalleOS(Long idCentroInstalacion, String fechaInicio, String fechaFin);
	
	/**
     * Descripción:Totales de operacion por OS 
     * @author Maverick
     * @param String consulta
     * @param String fechaInicio
     * @param Strin fechaFin
     * @param List<Long> idCentroInstalacion
     * @return List<SeguimientoOrdenServicioVO>
     * */
	public List<SeguimientoOrdenServicioVO> getInfoSeguimientoGeneral(StringBuilder consulta, List<Long> CentroInstalacion, List<String> columnas);
	

	/**
     * Descripción: Obtener el registro de orden de 
     * servicio mediante su identificador unico
     * @author Manuel
     * @param idOrdenServicio
     * @return OrdenServicioDetVO
     * */
	public OrdenServicioDetVO getOrdenServicioByIdOrden(Long idOrdenServicio);

	
	/**
     * Descripción: Obtener lista de todos los registros
     * @author David Guerra
	 * @param centroInstalacion
	 * @param numMostrar
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> todos(Integer numMostrar);
	
	
	/**
     * Descripción: Obtener lista de todos los registros de un lote
     * @author David Guerra
	 * @param centroInstalacion
	 * @param valor
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> getOrdenServicioByLote(String valor);
	
	/**
     * Descripción: Obtener lista de todos los registros de un lote
     * @author David Guerra
	 * @param centroInstalacion
	 * @param valor
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> getOrdenServicioByIncidecnia(String valor);
	
	public List<OrdenServicioDTO> consultaOrdenByPlacaTodo(String valor);
	
	public List<OrdenServicioDTO> consultaOrdenByOrdenServicioTodo(String valor);
	
	public List<OrdenServicioDTO> consultaOrdenByVinTodo(String valor);
	
	public List<OrdenServicioDTO> consultaAvanzada(Boolean busquedaAvanzada,String cdTipoBusqueda,
		    String valorBusqueda, String fhInicio, String fhFin,String centroInstalacion,String estatusSeguimiento,
		    Boolean isLote, Boolean isIncidencia,
			String valorLoteIncidencia, String tipoKit,
			String tipoPlan,Integer nuMaxMostrar);
	
	public List<OrdenServicioDTO> consultaTodasOrdenes();
	
	/**
     * Descripción: Cancelar Orden de servicio sin atender al dia 
     * @author Maverick
	 * @param fechaCorte
	 * @param idCentroInstalacion
	 */
	public List<OrdenServicioDTO> hacerCorteDiarioOS(String fechaCorte, List<Long> idCentroInstalacion);
	
	/**
	 * @Descripcion: Regresa una lista de ordenes de servicio mediante el unico filtro de vin
	 * @author DanielUnitis
	 * @param vin
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByVinOnly(String vin);
	
	/**
	 * @Descripcion: Regresa una lista de ordenes de servicio mediante el unico filtro de placa
	 * @author DanielUnitis
	 * @param vin
	 * @return List<OrdenServicioDTO>
	 */
	public List<OrdenServicioDTO> consultaOrdenByPlacaOnly(String placa);
}
