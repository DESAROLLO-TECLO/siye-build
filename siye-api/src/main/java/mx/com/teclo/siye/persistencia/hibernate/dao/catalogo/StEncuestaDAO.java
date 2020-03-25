package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;

public interface StEncuestaDAO extends BaseDao<StEncuestaDTO>{
	
	public List<StEncuestaDTO> stEncuesta();

}
