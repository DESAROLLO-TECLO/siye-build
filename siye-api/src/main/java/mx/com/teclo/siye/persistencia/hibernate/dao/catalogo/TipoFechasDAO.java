package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoFechasDTO;

public interface TipoFechasDAO extends BaseDao<TipoFechasDTO>{
	
	/*@Author Maverick
	 * @return List<TipoFechasDTO>
	 * metodo para consultar el caalogo de rangos de fechas */
	public List<TipoFechasDTO> getCatTipoFechas();
}
