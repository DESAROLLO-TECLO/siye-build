package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoPersonaDTO;

public interface TipoPersonaDAO extends BaseDao<TipoPersonaDTO>{
	
	public TipoPersonaDTO getTipoPersonaXID(Long idTipoPersona);
}
