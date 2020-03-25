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
     * Descripción: Obtener el registro de orden de 
     * servicio 
     * @author Estephanie Chavez
     * @param idOrdenServicio
     * @return OrdenServicioDTO
     * */
	public List<OrdenServicioDTO> consultaOrden();
	

}
