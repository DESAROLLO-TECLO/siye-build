package mx.com.teclo.siye.negocio.service.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.DispositivosVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanProcesoVO;

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
	
	List<DispositivosVO> getKitDispositivo( Long idTpKit) throws NotFoundException;
	
	List<UsuarioEncuestaDTO> obtenerEncuestas(Long idOrden);
	
	List<PlanProcesoVO> revisarEncuestasCompletas(List<UsuarioEncuestaDTO> encuestasByUsuario,List<PlanProcesoVO> plan, Long idEncuesta);

}
