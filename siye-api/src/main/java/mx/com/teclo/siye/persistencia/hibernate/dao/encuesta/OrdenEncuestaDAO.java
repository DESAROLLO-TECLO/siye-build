package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;

public interface OrdenEncuestaDAO extends BaseDao<OrdenEncuestaDTO>{

	/**
	 * 
	 * @param idOrden
	 * @param idEncuesta
	 * @return OrdenEncuestaDTO
	 */
	OrdenEncuestaDTO getOrdenEncuestaByIdOrdAndIdEnc(Long idOrden,Long idEncuesta);
	
}
