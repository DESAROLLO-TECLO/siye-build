package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaTipoDTO;

public interface PersonaTipoDAO extends BaseDao<PersonaTipoDTO>{
	
	public List<PersonaTipoDTO> getTecnicos(Long idTipoPersona);
	
	public List<PersonaTipoDTO> getTecnicoByCd(String cdPersona);

	public PersonaTipoDTO getTecnicosXIdPersonaYIdTipoPersona(Long idPersona, Long idTipoPersona);
}
