package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;

public interface IncidenciaDAO extends BaseDao<IncidenciaDTO> {
	
	/**
     * Descripción: Obtener el registro de incidencia
     * @author Eric Rivera
     * @param  cdIncidencia
     * @return IncidenciaDTO
     * */
	
	public IncidenciaDTO getIncidenciabycdIncidencia(String cdIncidencia);
	
	/** 
	 * Descripción: Obtener la lista de incidencias
     * @author Estephanie Chavez
	 * @return Long
	 */
	public Long getUltimoId();

}
