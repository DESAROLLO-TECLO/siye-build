package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;

public interface MonitoreoIncidenciaService {
	
	public List<MonitoreoIncidenciasVO> getMonIncidencias(Long idSupervisor, String fechaInicio, String fechaFin, Integer tipoBusqueda, String valor, Integer opcion, String mensajeErr) throws Exception, BusinessException, NotFoundException;
//	public List<IncidencDetalleVO> listIncidenciaByIdOrden(Long idOrden) throws NotFoundException;

	public OrdenIncidenciaDetalleVO incidenciaByOS(Long idOrden,Long idPlan) throws NotFoundException;

}
