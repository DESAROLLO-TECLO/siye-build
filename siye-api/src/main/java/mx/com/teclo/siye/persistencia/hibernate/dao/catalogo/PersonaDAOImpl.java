package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaDTO;

@Repository
public class PersonaDAOImpl extends BaseDaoHibernate<PersonaDTO> implements PersonaDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaDTO> getTecnicos() {
		Criteria criteria = getCurrentSession().createCriteria(PersonaDTO.class);
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.addOrder(Order.asc("nuOrden"));
		
		return (List<PersonaDTO>)criteria.list();
	}

	@Override
	public List<PersonaDTO> getInstaladorXNombre(String nombre, String aPaterno, String aMaterno) {
		Criteria criteria = getCurrentSession().createCriteria(PersonaDTO.class);
		criteria.add(Restrictions.eq("nbPersona", nombre));
		criteria.add(Restrictions.eq("nbPatPersona", aPaterno));
		criteria.add(Restrictions.eq("nbMatPersona", aMaterno));
		criteria.add(Restrictions.eq("stActivo", true));
		
		return (List<PersonaDTO>)criteria.list();
	}
}