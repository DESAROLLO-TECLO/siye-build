package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaTipoDTO;

@Repository
public class PersonaTipoDAOImpl extends BaseDaoHibernate<PersonaTipoDTO> implements PersonaTipoDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaTipoDTO> getTecnicos(Long idTipoPersona) {
		Criteria criteria = getCurrentSession().createCriteria(PersonaTipoDTO.class);
		criteria.createAlias("tipoPersona", "tipoPersona");
		criteria.createAlias("persona", "persona");
		criteria.add(Restrictions.eq("tipoPersona.idTipoPersona", idTipoPersona));
		criteria.add(Restrictions.eq("persona.stActivo", true));
		criteria.addOrder(Order.asc("persona.nuOrden"));
		return (List<PersonaTipoDTO>)criteria.list();
	}
	
	@Override
	public PersonaTipoDTO getTecnicosXIdPersonaYIdTipoPersona(Long idPersona, Long idTipoPersona) {
		Criteria criteria = getCurrentSession().createCriteria(PersonaTipoDTO.class);
		criteria.createAlias("tipoPersona", "tipoPersona");
		criteria.createAlias("persona", "persona");
		criteria.add(Restrictions.eq("tipoPersona.idTipoPersona", idTipoPersona));
		criteria.add(Restrictions.eq("persona.idPersona", idPersona));
		criteria.add(Restrictions.eq("tipoPersona.stActivo", true));
		criteria.add(Restrictions.eq("persona.stActivo", true));
		
		if(criteria.list().size() > 0) {
			return (PersonaTipoDTO)criteria.list().get(0);
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaTipoDTO> getTecnicoByCd(String cdPersona) {
		Criteria criteria = getCurrentSession().createCriteria(PersonaTipoDTO.class);
		criteria.createAlias("tipoPersona", "tipoPersona");
		criteria.createAlias("persona", "persona");
		criteria.add(Restrictions.eq("persona.cdPersona", cdPersona));
		criteria.add(Restrictions.eq("tipoPersona.stActivo", true));
		criteria.add(Restrictions.eq("persona.stActivo", true));
		return (List<PersonaTipoDTO>)criteria.list();
	}

	
}
