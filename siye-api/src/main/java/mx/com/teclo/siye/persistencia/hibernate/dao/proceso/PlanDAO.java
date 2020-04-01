package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;

/**
 * Administra la alta, actualizaci&oacute;n, consulta y baja l&oacute:gica de un
 * plan de instalaci&oacute;n
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface PlanDAO extends BaseDao<PlanDTO> {

	/**
	 * Obtiene un plan de instalaci&oacute;n por su identificador
	 * 
	 * @param idPlan
	 * @return PlanVO
	 */
	public PlanVO obtenerKitInstalacion(Long idPlan);
	
	
	/**
	 * Obtiene todos los planes
	 * 
	 * @return List<PlanVO>
	 */
	public List<PlanDTO> getPlanAll() throws NotFoundException;
	
	public PlanDTO getId(Long idPlan);
	
	
	

}
