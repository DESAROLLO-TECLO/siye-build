package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;

public interface MonitoreoIncidenciaService {
	
	public void getMonIncidencias(String mensajeErr) throws Exception, BusinessException, NotFoundException;
//	public List<IncidencDetalleVO> listIncidenciaByIdOrden(Long idOrden) throws NotFoundException;

	public OrdenIncidenciaDetalleVO incidenciaByOS(Long idOrden,Long idPlan) throws NotFoundException;

}
