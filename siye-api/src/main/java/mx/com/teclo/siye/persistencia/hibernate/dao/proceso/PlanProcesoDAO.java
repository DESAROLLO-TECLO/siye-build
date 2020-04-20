package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesoDetalleVO;


public interface PlanProcesoDAO extends BaseDao<PlanProcesoDTO> {
	
	/**
	 * Obtiene los procesos del plan
	 * 
	 * @param idPlan
	 * @return List<PlanProcesoDTO>
	 * David Guerra
	 */
	public List<PlanProcesoDTO> obtenerPorcesosPlan(Long idPlan);
		
	/**
	 * Obtiene los procesos del plan
	 * 
	 * @param idPlan
	 * @return List<ExpedienteNivelProcesoVO>
	 * Maverick
	 */
	public List<ExpedienteNivelProcesoVO> getProcesosPlanVO(Long idPlan);
	
	/**
	 * Obtiene los procesos del plan
	 * 
	 * @param idPlan
	 * @return List<ProcesoDetalleVO>
	 * Maverick
	 */
	public List<ProcesoDetalleVO> getEtapasParaSeguimiento(Long idPlan);
	
	
	/**
	 * Obtiene el avance del proceso para uno OS especifica 
	 * 
	 * @param idPlan
	 * @return List<ProcesoDetalleVO>
	 * Maverick
	 */
	public List<ProcesoDetalleVO> getDetalleProceso(Long idOrdenServicio, Long idProceso);

}
