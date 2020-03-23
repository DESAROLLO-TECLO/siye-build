package mx.com.teclo.siye.persistencia.hibernate.dao.usraplicacion;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsrAplicacionDTO;

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
public class UsrAplicacionDAOImpl extends BaseDaoHibernate<UsrAplicacionDTO> implements UsrAplicacionDAO {

	@Override
	public UsrAplicacionDTO findUserAplication(String cdApplication, Long idUsuario) {
		Criteria c = getCurrentSession().createCriteria(UsrAplicacionDTO.class);
		c.createAlias("aplicacion", "app");
		c.createAlias("usuario", "usr");
		
		c.add(Restrictions.eq("app.cdAplicacion", cdApplication));
		c.add(Restrictions.eq("usr.idUsuario", idUsuario));
		
		return  (UsrAplicacionDTO)c.uniqueResult();
	}

	@Override
	public AplicacionDTO getApplicationByCode(String cdApplication) {

		Criteria query = getCurrentSession().createCriteria(AplicacionDTO.class);

		query.add(Restrictions.eq("cdAplicacion", cdApplication));

		return (AplicacionDTO) query.uniqueResult();
	}

}
