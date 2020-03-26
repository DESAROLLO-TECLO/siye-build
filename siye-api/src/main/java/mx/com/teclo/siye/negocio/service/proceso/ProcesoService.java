package mx.com.teclo.siye.negocio.service.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;



public interface ProcesoService {
	
	/**
     * Consulta la informacion general de la orden de servicio para iniciar el proceso. 
     * @param Long folioSolictud
     * @return OrdenServicioDTO
     * David Guerra
     */
	OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud);
	
	/**
	 * Valida datos requeridos y en el formato correcto para guardar en la base de
	 * datos
	 * 
	 * @param ordenServicioVO
	 * @return Long idOrdenServicio
	 * @throws BusinessException
	 */
	Long crearOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException;

	/**
	 * Valida datos requeridos y en el formato correcto para actualizar la orden de
	 * servicio
	 * 
	 * @param ordenServicioVO
	 * @throws BusinessException
	 */
	void actualizarOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException;

	/**
	 * Recupera informaci&oacute;n de la orden de servicio
	 * @param idOrdenServicio
	 * @return
	 * @throws BusinessException
	 */
	OrdenServicioVO obtenerOrdenServicio(Long idOrdenServicio) throws BusinessException;
	
	/**
     * Consulta plan a seguir de acuerdo a la orden de servicio. 
     * @param Long idPlan
     * @return List<PlanProcesoDTO>
     * David Guerra
     */
	List<PlanProcesoDTO> getPlanOrdenServicio(Long idPlan);
	
	/**
     * Consulta las encuestas a llenar de acuerdo al proceso. 
     * @param Long idProceso
     * @return List<ProcesoEncuestaDTO>
     * David Guerra
     */
	List<ProcesoEncuestaDTO> getEncuestasProceso(Long idProceso);


}
