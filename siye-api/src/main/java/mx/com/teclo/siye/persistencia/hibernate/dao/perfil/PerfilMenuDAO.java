package mx.com.teclo.siye.persistencia.hibernate.dao.perfil;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilMenuDTO;

public interface PerfilMenuDAO extends BaseDao<PerfilMenuDTO> {

	public Long findNextVal();
	public PerfilMenuDTO findProfilesByIds(Long idPerfil, Long idMenu);
	public List<PerfilMenuDTO> findProfilesByIdPerfil(Long idPerfil);
}
