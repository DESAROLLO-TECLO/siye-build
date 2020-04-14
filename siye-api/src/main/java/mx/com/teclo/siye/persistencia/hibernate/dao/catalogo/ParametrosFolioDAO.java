package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ParametrosFolioDTO;

public interface  ParametrosFolioDAO extends BaseDao<ParametrosFolioDTO> {
	
	public ParametrosFolioDTO obtenerFolio(String cdFolio);
}
