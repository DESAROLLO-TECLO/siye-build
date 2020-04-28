package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenDetailVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenciaDetalleVO;

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
	 * @param idEncuesta
	 * @return List<IncidenciaDetalleVO> 
	 * */
	public List<IncidenciaDetalleVO> getIncidenciasByIdEncuesta(Long idOrden,Long idEncuesta);
	/**
	 * Descripciónn: Obtiene una lista de incidencias por id ecnuesta
	 * @author Mannuel
	 * @param idEncuesta
	 * @return List<IncidenciaDTO> 
	 * */
	public List<IncidenciaDTO> getIncidenciasByIdEn(Long idEncuesta);
	
	/**
	 * Descripciónn: Obtiene una lista de incidencias por id de encuesta
	 * @author Mannuel
	 * @param idProceso
	 * @return List<IncidenciaDetalleVO> 
	 * */
	public List<IncidenciaDetalleVO> getIncidenciasByProceso(Long idOrden,Long idProceso);

	/**
	 * Descripciónn: Obtiene una lista de incidencias por id de orden
	 * @author Mannuel
	 * @param idOrden
	 * @return List<IncidenciaDetalleVO> 
	 * */
	public List<IncidenciaDetalleVO> getIncidenciasByIdOrden(Long idOrden);

	/**
	 * Descripciónn: Obtiene una lista de incidencias por id de orden,fechaIni fecha fin
	 * @author Mannuel
	 * @param idOrden
	 * @param fechaIni
	 * @param fechaFin
	 * @return List<IncidenDetailVO> 
	 * */
	public List<IncidenciaDTO> getIncidenciasByIdOrdenFechas(Long idCentroInstalacion,Long idOrden,String fhInici,String fhFin);
	
	/**
	 * Descripciónn: Obtiene una lista de incidencias sin orden de serivicio y con centro de instalacion y fechaIni fecha fin
	 * @author Mannuel
	 * @param idCentroInstalacion
	 * @param fechaIni
	 * @param fechaFin
	 * @return List<IncidenDetailVO> 
	 * @throws ParseException 
	 * */
	public List<IncidenciaDTO> getIncidenciasByIdCentroFechas(Long idCentroInstalacion,String fhInici,String fhFin) throws ParseException;

	/**
	 * Descripciónn: Obtiene una lista de incidencias por id de orden,fechaIni fecha fin
	 * @author Mannuel
	 * @param idIncidencia
	 * @param fechaIni
	 * @param fechaFin
	 * @return List<IncidenDetailVO> 
	 * */
	public List<IncidenciaDTO> getIncidenciasByIdIncidenciaFechas(Long idCentroInstalacion,Long idIncidencia,String fhInici,String fhFin);
}
