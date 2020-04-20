package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import java.util.List;

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
	
	/**
	 * Descripciónn: Obtiene el registro mediante el código
	 * @author VAPD1226
	 * @param cdInciden
	 * @return IncidenciaDTO
	 */
	public IncidenciaDTO incidenciaBycdIncidencia(String cdInciden);
	/**
	 * Descripciónn: Obtiene una lista de incidencias por id de orden
	 * @author Mannuel
	 * @param idOrden
	 * @return List<IncidenciaDTO> 
	 * */
	public List<IncidenciaDTO> getIncidenciasByIdOrden(Long idOrden);
	/**
	 * Descripciónn: Obtiene una lista de incidencias por id de encuesta
	 * @author Mannuel
	 * @param idOrden
	 * @return List<IncidenciaDTO> 
	 * */
	
	public List<IncidenciaDTO> getIncidenciasByIdEncuesta(Long idEncuesta);


}
