package mx.com.teclo.siye.persistencia.hibernate.dao.perfil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilUsuarioDTO;


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

@Repository
public class PerfilUsuarioDAOImpl extends BaseDaoHibernate<PerfilUsuarioDTO> implements PerfilUsuarioDAO {

	@Override
	public PerfilUsuarioDTO getPerfilUsuario(Long idUsuario, Long idPerfil) {
		Criteria c =getCurrentSession().createCriteria(PerfilUsuarioDTO.class);
		c.createAlias("perfil", "p");
		c.createAlias("usuario", "usr");
		
		c.add(Restrictions.eq("usr.idUsuario", idUsuario));
		c.add(Restrictions.eq("p.idPerfil", idPerfil));
		
		return (PerfilUsuarioDTO)c.uniqueResult();
	}

}
