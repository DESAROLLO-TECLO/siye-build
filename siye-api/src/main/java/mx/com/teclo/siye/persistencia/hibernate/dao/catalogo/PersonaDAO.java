package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaDTO;

public interface PersonaDAO  extends BaseDao<PersonaDTO>{
	
	public List<PersonaDTO> getTecnicos();
	
	public List<PersonaDTO> getInstaladorXNombre(String nombre, String aPaterno, String aMaterno);
}
