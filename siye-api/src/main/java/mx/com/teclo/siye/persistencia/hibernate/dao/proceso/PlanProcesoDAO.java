package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;


public interface PlanProcesoDAO extends BaseDao<PlanProcesoDTO> {
	
	/**
	 * Obtiene los procesos del plan
	 * 
	 * @param idPlan
	 * @return List<PlanProcesoDTO>
	 * David Guerra
	 */
	public List<PlanProcesoDTO> obtenerPorcesosPlan(Long idPlan);

}
