package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.SeguimientoVO;

public interface StSeguimientoDAO extends BaseDao<StSeguimientoDTO> {

	/**
	 * Obtiene el siguiento por su identificador
	 * @param idSeguimiento
	 * @return
	 */
	public SeguimientoVO obtenerSeguimiento(Long idSeguimiento);

}
