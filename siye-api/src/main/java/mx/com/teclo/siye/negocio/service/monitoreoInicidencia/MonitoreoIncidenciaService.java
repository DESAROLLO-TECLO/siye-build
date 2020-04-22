package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;

public interface MonitoreoIncidenciaService {
	
	public List<MonitoreoIncidenciasVO> getMonIncidencias(Long idSupervisor, String fechaInicio, String fechaFin, Integer tipoBusqueda, String valor, Integer opcion, Integer idCentroInstalacion, String mensajeErr) throws Exception, BusinessException, NotFoundException;
//	public List<IncidencDetalleVO> listIncidenciaByIdOrden(Long idOrden) throws NotFoundException;

	public OrdenIncidenciaDetalleVO getDetalleIncidenciasOS(Long idOrden,Long idPlan) throws NotFoundException, Exception, BusinessException;
	
	/*@Author Manuel
	 *@param  List<ImagenVO>
	 *@param  idIncidencia
	 *@return List<ImagenVO>
	 *Metodo para obtener lista de imagenes de incidencia  */
	public List<ImagenVO> getExpedienteByIdIncidencia(Long idIncidencia) throws Exception, BusinessException, NotFoundException;

}
