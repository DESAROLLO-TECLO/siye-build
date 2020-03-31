package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.TipoLayoutDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

public interface IncidenciaDAO extends BaseDao<IncidenciaDTO> {
	
	/**
     * Descripción: Obtener el registro de incidencia
     * @author Eric Rivera
     * @param  cdIncidencia
     * @return IncidenciaDTO
     * */
	
	public IncidenciaDTO getIncidenciabycdIncidencia(String cdIncidencia);
	
}
