package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenDetailVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;

public interface MonitoreoIncidenciaService {
	
	public List<MonitoreoIncidenciasVO> getMonIncidencias(Long idSupervisor, String fechaInicio, String fechaFin, Integer tipoBusqueda, String valor, Integer opcion, Integer idCentroInstalacion, String mensajeErr) throws Exception, BusinessException, NotFoundException;
	/*@Author Manuel
	 *@param  idCentroInstalacion
	 *@param  tipoBusqueda
	 *@param  valor
	 *@param  fechaInicio
	 *@param  fechaFin
	 *@return OrdenIncidenciaDetalleVO
	 *Metodo para incidencias por tipo de busqueda  */
	public List<IncidenDetailVO> getListIncidenciaByIdOrden(Long idCentroInstalacion,Integer tipoBusqueda,String valor ,String fechaInicio,String fechaFin)  throws Exception, BusinessException, NotFoundException;
	
	/*@Author Manuel
	 *@param  idOrden
	 *@param  idPlan
	 *@return OrdenIncidenciaDetalleVO
	 *Metodo para arbol de incidecnias por orden proceso y encuestas o etapas  */
	public OrdenIncidenciaDetalleVO getDetalleIncidenciasOS(Long idOrden,Long idPlan) throws NotFoundException, Exception, BusinessException;
	
	/*@Author Manuel
	 *@param  idIncidencia
	 *@return List<ImagenVO>
	 *Metodo para obtener lista de imagenes de incidencia  */
	public List<ImagenVO> getExpedienteByIdIncidencia(Long idIncidencia) throws Exception, BusinessException, NotFoundException;

}
