package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;

public interface LoteOrdenServicioDAO extends BaseDao<LoteOrdenServicioDTO> {

	/***
	 * Obtiene el lote por su identificador
	 * 
	 * @param idOrdenServicio
	 * @return ArchivoLoteVO
	 */
	public ArchivoLoteVO obtenerLote(Long idLote);

}
