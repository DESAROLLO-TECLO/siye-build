package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.OdsIncidenciaDTO;

public interface OdsIncidenciaDAO extends BaseDao<OdsIncidenciaDTO>{
	
	List<OdsIncidenciaDTO> getInsidencisByIdOrdenAndIdEncuesta(Long idOrden,Long idEncuesta);
	
	List<OdsIncidenciaDTO> getInsidencisByIdOrden(Long idOrden);

}
