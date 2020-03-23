package mx.com.teclo.siye.persistencia.hibernate.dao.perfil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilDTO;

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
@Repository 
public class PerfilDAOImpl extends BaseDaoHibernate<PerfilDTO> implements PerfilDAO {

	@Override
	public Long findNextValue(){
		Criteria query = getCurrentSession().createCriteria(PerfilDTO.class);
		query.setProjection(Projections.max("idPerfil"));
		Long id = (Long)query.uniqueResult();
		
		return id!= null ? id+1L:0L;
	}
	
	@Override
	public List<PerfilDTO> findProfilesByApp(String cdApplication) {
		ArrayList<PerfilDTO> profiles = null;
		Criteria query = getCurrentSession().createCriteria(PerfilDTO.class);
		query.createAlias("aplicacion", "aplicacion");
		query.add(Restrictions.eq("stActivo", new Integer(1)));
		query.add(Restrictions.eq("aplicacion.cdAplicacion",cdApplication));

		profiles = (ArrayList<PerfilDTO>) query.list();

		return profiles;
	}
	
	@Override
	public List<PerfilDTO> findAllProfilesByApp(String cdApplication) {
		ArrayList<PerfilDTO> profiles = null;
		Criteria query = getCurrentSession().createCriteria(PerfilDTO.class);
		query.createAlias("aplicacion", "aplicacion");
		query.add(Restrictions.eq("aplicacion.cdAplicacion",cdApplication));

		profiles = (ArrayList<PerfilDTO>) query.list();

		return profiles;
	}

	@Override
	public PerfilDTO findProfileById(Long id, String cdApplication, boolean isByApplication) {

		PerfilDTO perfilDTO = null;
		Criteria query = getCurrentSession().createCriteria(PerfilDTO.class);

		// Alias para alcanzar la aplicacion al que pertenece el perfil
 		query.createAlias("aplicacion", "aplicacion");

		query.add(Restrictions.eq("stActivo", new Integer(1)));
 		query.add(Restrictions.eq("idPerfil", id));

		if(isByApplication) {			
			query.add(Restrictions.eq("aplicacion.cdAplicacion", cdApplication));
		}

		perfilDTO = (PerfilDTO) query.uniqueResult();

		return perfilDTO;
	}
	
	@Override
	public PerfilDTO findUniqueProfileById(Long id, String cdApplication, boolean isByApplication) {

		PerfilDTO perfilDTO = null;
		Criteria query = getCurrentSession().createCriteria(PerfilDTO.class);

		// Alias para alcanzar la aplicacion al que pertenece el perfil
 		query.createAlias("aplicacion", "aplicacion");

 		query.add(Restrictions.eq("idPerfil", id));

		if(isByApplication) {			
			query.add(Restrictions.eq("aplicacion.cdAplicacion", cdApplication));
		}

		perfilDTO = (PerfilDTO) query.uniqueResult();

		return perfilDTO;
	}

	 

}