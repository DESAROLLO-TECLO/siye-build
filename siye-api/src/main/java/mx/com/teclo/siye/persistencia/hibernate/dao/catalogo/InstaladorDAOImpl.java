package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;

@Repository
public class InstaladorDAOImpl extends BaseDaoHibernate<InstaladorDTO> implements InstaladorDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InstaladorDTO> getTecnicos() {
		Criteria criteria = getCurrentSession().createCriteria(InstaladorDTO.class);
		criteria.add(Restrictions.eq("stActivo", 1));
		criteria.addOrder(Order.asc("nuOrden"));
		
		return (List<InstaladorDTO>)criteria.list();
	}
}
