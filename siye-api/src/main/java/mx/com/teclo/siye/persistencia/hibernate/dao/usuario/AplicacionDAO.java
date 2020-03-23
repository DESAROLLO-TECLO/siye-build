package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;

public interface AplicacionDAO extends BaseDao<AplicacionDTO>{
	public AplicacionDTO findAplicationById(String cdAplicacion);
}
