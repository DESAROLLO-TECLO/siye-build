package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;

@Repository
public class ConductorDAOImpl extends BaseDaoHibernate<ConductorDTO> implements ConductorDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConductorDTO> getTransportistas() {
		Criteria criteria = getCurrentSession().createCriteria(ConductorDTO.class);
		criteria.add(Restrictions.eq("stActivo", 1));
		criteria.addOrder(Order.asc("nuOrden"));
		
		return (List<ConductorDTO>)criteria.list();
	}
}
