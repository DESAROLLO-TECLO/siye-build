package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ConsecionarioDTO;

public interface ConcesionariaDAO extends BaseDao<ConsecionarioDTO>{
	
	List<ConsecionarioDTO> getConcesionariaAll();

}
