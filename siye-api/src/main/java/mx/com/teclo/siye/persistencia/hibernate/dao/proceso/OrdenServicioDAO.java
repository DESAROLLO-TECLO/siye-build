package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;

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
	


}
