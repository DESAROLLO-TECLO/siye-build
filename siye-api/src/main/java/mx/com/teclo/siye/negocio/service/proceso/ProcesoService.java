package mx.com.teclo.siye.negocio.service.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.DispositivosVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ProcesoEncuestaVO;

public interface ProcesoService {

	/**
	 * Consulta la informacion general de la orden de servicio para iniciar el
	 * proceso.
	 * 
	 * @param Long folioSolictud
	 * @return OrdenServicioDTO David Guerra
	 */
	OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud);

	/**
	 * Consulta plan a seguir de acuerdo a la orden de servicio.
	 * 
	 * @param Long idPlan
	 * @return List<PlanProcesoDTO> David Guerra
	 */
	List<PlanProcesoDTO> getPlanOrdenServicio(Long idPlan);

	/**
	 * Consulta las encuestas a llenar de acuerdo al proceso.
	 * 
	 * @param Long idProceso
	 * @return List<ProcesoEncuestaDTO> David Guerra
	 */

	List<ProcesoEncuestaDTO> getEncuestasProceso(Long idProceso);
	
	List<DispositivosVO> getKitDispositivo( Long idTpKit) throws BusinessException;
	
	List<OrdenEncuestaDTO> obtenerEncuestas(Long idOrden);
	
	List<PlanProcesoVO> revisarEncuestasCompletas(List<OrdenEncuestaDTO> encuestasByUsuario,List<PlanProcesoVO> plan, Long idSolicitud);
	
	List<ProcesoEncuestaVO> revisarEncuestasCompletas2(List<OrdenEncuestaDTO> encuestasByUsuario,List<ProcesoEncuestaVO> encuestasByProceso);
	
	Boolean inicarProcesoOrdenServicio (Long idOrdenServicio);
	
	Boolean finalizarProceso (Long idOrdenServicio);
	
	Boolean avanzarProcesoOrden (Long idOrdenServicio, Long idProceso);




}
