package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ConsecionarioDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConcesionariaVO;

public interface ConcesionariaDAO extends BaseDao<ConsecionarioDTO>{
	
	List<ConsecionarioDTO> getConcesionariaAll();

	public List<ConsecionarioDTO> obtenerConsecionarioVisible(Long value);
	
	List<ConcesionariaVO> getCatConcesionaria();

}
