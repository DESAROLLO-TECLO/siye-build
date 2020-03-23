package mx.com.teclo.siye.persistencia.hibernate.dao.perfil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilMenuDTO;

	

@SuppressWarnings("unchecked")
@Repository 
public class PerfilMenuDAOImpl extends BaseDaoHibernate<PerfilMenuDTO> implements PerfilMenuDAO {

	@Override
	public List<PerfilMenuDTO> findProfilesByIdPerfil(Long idPerfil) {		
		Criteria query = getCurrentSession().createCriteria(PerfilMenuDTO.class);		
		query.add(Restrictions.eq("stActivo", new Integer(1)));
		query.add(Restrictions.eq("perfil.idPerfil",idPerfil));

		return (ArrayList<PerfilMenuDTO>) query.list();		
	}
	
	@Override
	public PerfilMenuDTO findProfilesByIds(Long idPerfil, Long idMenu) {		
		Criteria query = getCurrentSession().createCriteria(PerfilMenuDTO.class);
		query.createAlias("menu", "menu");
		query.createAlias("perfil", "perfil");
		query.add(Restrictions.eq("menu.idMenu", idMenu));
		query.add(Restrictions.eq("perfil.idPerfil",idPerfil));

		return (PerfilMenuDTO) query.uniqueResult();		
	}
	
	@Override
	public Long findNextVal(){
		Criteria query = getCurrentSession().createCriteria(PerfilMenuDTO.class);
		query.setProjection(Projections.max("idPerfilMenu"));
		Long res = (Long)query.uniqueResult();
		
		return res != null? res+1:1;
	}


	 

}
