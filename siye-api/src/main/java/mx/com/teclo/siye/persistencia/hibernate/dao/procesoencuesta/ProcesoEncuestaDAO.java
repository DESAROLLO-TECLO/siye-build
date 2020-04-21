package mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.EncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.PreguntasDetalleVO;

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
	
	/* @Author Maverick
	 * @param idProceso
	 * @param idOrdenSerivicio
	 * @return List<EncuestaDetalleVO>
	 *metodo para consultar el avance de las encuestas de una os y proceso en especifico
	 * */
	public List<EncuestaDetalleVO> getDetalleEncuesta(Long idOrdenServicio, Long idProceso);
	

	/**
	 * Descripciónn: Obtiene una lista de encuestas por id de proceso
	 * @author Mannuel
	 * @param idProceso
	 * @return List<EncuestaDetalleDTO> 
	 * */
	public List<EncuestaDetalleDTO> getEncuestaByIdOrden(Long idProceso);
	
	
	/**
	 * Descripción: obtiene las preguntas por encuesta con su respuesta 
	 * @author Maverick
	 * @param idOrdenServicio
	 * @param idEncuestaa
	 * @return List<PreguntasDetalleVO> 
	 * */
	public List<PreguntasDetalleVO> getSeguimientoDetallePregunta(Long idOrdenServicio, Long idEncuesta);

}
