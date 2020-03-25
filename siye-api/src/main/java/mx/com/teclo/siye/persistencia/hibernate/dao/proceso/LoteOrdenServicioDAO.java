package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.LoteOrdenServicioVO;

public interface LoteOrdenServicioDAO extends BaseDao<LoteOrdenServicioDTO> {

	/***
	 * Obtiene el lote por su identificador
	 * 
	 * @param idOrdenServicio
	 * @return LoteOrdenServicioVO
	 */
	public LoteOrdenServicioVO obtenerLote(Long idLote);

}
