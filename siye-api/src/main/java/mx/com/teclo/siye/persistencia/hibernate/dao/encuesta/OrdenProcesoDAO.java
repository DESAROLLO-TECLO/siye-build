package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenProcesoDTO;

public interface OrdenProcesoDAO extends BaseDao<OrdenProcesoDTO> {
	
	
	/**
	 * @author David Guerra
	 * @param idOrden
	 * @return List<OrdenProcesoDTO>
	 */
	
    List<OrdenProcesoDTO> getProcesoPorOrden(Long idOrden);
    
    OrdenProcesoDTO getProceso(Long idOrden,Long idProceso);

}
