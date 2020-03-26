package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoEncuestaDTO;

public interface ProcesoEncuestaDAO extends BaseDao<ProcesoEncuestaDTO> {
	
	/**
	 * Obtiene las encuestas del proceso
	 * 
	 * @param idProceso
	 * @return List<ProcesoEncuestaDTO>
	 * David Guerra
	 */
	public List<ProcesoEncuestaDTO> obtenerEncuestasProceso(Long idProceso);

}
