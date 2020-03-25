package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;

public interface InstaladorDAO  extends BaseDao<InstaladorDTO>{
	
	public List<InstaladorDTO> getTecnicos();
}
