package mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;

public interface ProcesoEncuestaDAO extends BaseDao<ProcesoEncuestaDTO>{
	

	/*@Author Maverick
	 * @param idProceso
	 * @return List<ExpedienteNivelEncuestaVO>
	 * metodo que retorna una lista con los datos de las encuestas que pertenecen al proceso 
	 * */
	public List<ExpedienteNivelEncuestaVO> getEncuestasByProcesoVO(Long idProceso);

	/**
	 * Obtiene las encuestas del proceso
	 * 
	 * @param idProceso
	 * @return List<PlanProcesoDTO>
	 * David Guerra
	 */
	public List<ProcesoEncuestaDTO> obtenerEncuestasProceso(Long idProceso);


}
