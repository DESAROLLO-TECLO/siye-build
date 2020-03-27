package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;

public interface TipoKitDAO extends BaseDao<TipoKitDTO>{

	
	public List<TipoKitDTO> getTipoKit();
}
