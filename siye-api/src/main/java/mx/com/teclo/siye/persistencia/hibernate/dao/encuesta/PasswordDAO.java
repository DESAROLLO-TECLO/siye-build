package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.comun.PasswordDTO;

public interface PasswordDAO extends BaseDao<PasswordDTO>{
	
	public Boolean validarContraseniaTransportista(String password);
}
