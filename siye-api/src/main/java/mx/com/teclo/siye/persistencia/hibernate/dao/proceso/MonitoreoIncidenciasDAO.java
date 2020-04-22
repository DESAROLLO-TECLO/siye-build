package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;

public interface MonitoreoIncidenciasDAO extends BaseDao<OrdenServicioDTO>{
	
	public List<MonitoreoIncidenciasVO> getInfoSeguimientoGeneral(String fechaInicio, String fechaFin, List<Long> CentroInstalacion);
	
	public List<MonitoreoIncidenciasVO> getInfoSeguimientoXModulo(String fechaInicio, String fechaFin, Integer tipoBusqueda, String valor, Integer idCentroInstalacion);
}
