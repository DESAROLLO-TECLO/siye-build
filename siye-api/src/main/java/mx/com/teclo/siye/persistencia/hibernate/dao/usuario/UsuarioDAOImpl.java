package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsuarioDTO;
import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;
import mx.com.teclo.siye.util.enumerados.PerfilesEnum;

/**
 *  Copyright (c) 2018, Teclo Mexicana. 
 * 
 *  Descripcion					: UsuarioService
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Diciembre/2018
 */

@SuppressWarnings("unchecked")
@Repository("usuariosDAO")
public class UsuarioDAOImpl extends BaseDaoHibernate<UsuarioDTO> implements UsuarioDAO {

	@Override
	public List<UsuarioDTO> findUsersByParams(String parameter, String value, String cdApplication,boolean isByApplication) {

		ArrayList<UsuarioDTO> users = null;

		Criteria query = getCurrentSession().createCriteria(UsuarioDTO.class);

		// Alias para alcanzar la aplicacion del usuario
		query.createAlias("usuarioAplicacion", "usuarioAplicacion");
		query.createAlias("usuarioAplicacion.aplicacion", "aplicacion");

		if (parameter.equals("USERNAME")) {
			query.add(Restrictions.like("cdUsuario", "%" + value + "%").ignoreCase());
		}
		else if (parameter.equals("APELLIDO")) {			
			query.add(Restrictions.like("nbApaterno", "%" + value + "%").ignoreCase());
		}
		else if (parameter.equals("NOMBRE")) {
			query.add(Restrictions.like("nbUsuario", "%" + value + "%").ignoreCase());			
		}
		else if (parameter.equals("TODOS") && value.equals("ACTIVES")) {
			query.add(Restrictions.like("stActivo", new Integer(1)));
		}
		else if (parameter.equals("TODOS") && value.equals("INACTIVES")) {
			query.add(Restrictions.like("stActivo", new Integer(0)));
		}

		if(isByApplication){
			query.add(Restrictions.eq("aplicacion.cdAplicacion", cdApplication));
		}

		query.addOrder(Order.asc("nbUsuario"));

		users = (ArrayList<UsuarioDTO>) query.list();

		return users;
	}

	@Override
	public UsuarioDTO findUserById(Long id, String cdApplication) {

		UsuarioDTO usuario = null;
		Criteria query = getCurrentSession().createCriteria(UsuarioDTO.class);
		query.createAlias("perfilUsuario", "perfilUsuario");
		query.createAlias("perfilUsuario.perfil", "perfil");
		query.createAlias("perfil.aplicacion", "perfilAplicacion");

		// Alias para alcanzar la aplicacion del usuario
		query.createAlias("usuarioAplicacion", "usuarioAplicacion");
		query.createAlias("usuarioAplicacion.aplicacion", "aplicacion");

		query.add(Restrictions.eq("stActivo", new Integer(1)));
		query.add(Restrictions.eq("perfilAplicacion.cdAplicacion", cdApplication));
		query.add(Restrictions.eq("aplicacion.cdAplicacion", cdApplication));
		query.add(Restrictions.eq("idUsuario", id));

		usuario = (UsuarioDTO) query.uniqueResult();

		return usuario;
	}

	@Override
	public List<UsuarioDTO> findExistentUser(Map<String, Object> parameters) {

		ArrayList<UsuarioDTO> users = null;
		Criteria query = getCurrentSession().createCriteria(UsuarioDTO.class);

		Iterator<String> iterator = parameters.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			query.add(Restrictions.eq(key, parameters.get(key)));
		}
		users = (ArrayList<UsuarioDTO>) query.list();

		return users;
	}

	@Override
	public boolean isUserExist(UsuarioVO usuarioVO, String action) {

		boolean isExist = false;
		List<UsuarioDTO> user = null;

		Criteria query = getCurrentSession().createCriteria(UsuarioDTO.class);

		Criterion nameUser = Restrictions.and(
			Restrictions.eq("nbUsuario", usuarioVO.getNbUsuario()),
			Restrictions.eq("nbApaterno", usuarioVO.getNbApaterno()),
			Restrictions.eq("nbAmaterno", usuarioVO.getNbAmaterno())
		);
		
		if(action.equals("new")) {			
			query.add(Restrictions.or(nameUser, Restrictions.eq("cdUsuario", usuarioVO.getCdUsuario())));
		} else {
			query.add(nameUser);
		}

		user = (List<UsuarioDTO>) query.list();

		isExist = user.size() != 0 ? true : false;

		return isExist;
	}

	@Override
	public UsuarioDTO findUserByUserName(String username, String cdApplication, boolean isByApplication, boolean isActivo) {
		UsuarioDTO usuario = null;
		Criteria query = getCurrentSession().createCriteria(UsuarioDTO.class);
		query.createAlias("perfilUsuario", "perfilUsuario");
		query.createAlias("perfilUsuario.perfil", "perfil");
		query.createAlias("perfil.aplicacion", "perfilAplicacion");

		// Alias para alcanzar la aplicacion del usuario
		query.createAlias("usuarioAplicacion", "usuarioAplicacion");
		query.createAlias("usuarioAplicacion.aplicacion", "aplicacion");

		query.add(Restrictions.like("cdUsuario", "%" + username + "%").ignoreCase());
		if(isByApplication){
			query.add(Restrictions.eq("perfilAplicacion.cdAplicacion", cdApplication));
			query.add(Restrictions.eq("aplicacion.cdAplicacion", cdApplication));
		}
		if(isActivo)
			query.add(Restrictions.eq("stActivo", new Integer(1)));

		usuario = (UsuarioDTO) query.uniqueResult();

		return usuario;
	}

	@Override
	public List<UsuarioDTO> findUsersByProfile(String cdProfile, String cdApplication, boolean isByApplication) {

		ArrayList<UsuarioDTO> users = null;

		Criteria query = getCurrentSession().createCriteria(UsuarioDTO.class);

		// Alias para alcanzar la aplicacion del perfil
		query.createAlias("perfilUsuario", "perfilUsuario");
		query.createAlias("perfilUsuario.perfil", "perfil");
		query.createAlias("perfil.aplicacion", "perfilAplicacion");

		// Alias para alcanzar la aplicacion del usuario
		query.createAlias("usuarioAplicacion", "usuarioAplicacion");
		query.createAlias("usuarioAplicacion.aplicacion", "aplicacion");
		
		if(!cdProfile.equals(PerfilesEnum.SIN_PERFIL.getCdPerfil())) {
			query.add(Restrictions.eq("perfil.cdPerfil", cdProfile));
			query.add(Restrictions.eq("perfilUsuario.stActivo", new Integer(1)));
		}

		if(isByApplication){
			query.add(Restrictions.eq("aplicacion.cdAplicacion", cdApplication));
		}

		query.addOrder(Order.asc("nbUsuario"));

		users = (ArrayList<UsuarioDTO>) query.list();

		return users;
 	}

	@Override
	public UsuarioDTO getUserByCd(String cdUsuario) {
		Criteria c = getCurrentSession().createCriteria(UsuarioDTO.class);
		c.add(Restrictions.eq("cdUsuario", cdUsuario));
		c.add(Restrictions.eq("stActivo", 1));
		return (UsuarioDTO)c.uniqueResult();
	}

}
