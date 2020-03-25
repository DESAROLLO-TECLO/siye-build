package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;

public interface ConductorDAO extends BaseDao<ConductorDTO>{
	
	public List<ConductorDTO> getTransportistas();
}
