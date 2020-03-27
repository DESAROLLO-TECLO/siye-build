package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;

@Repository
public class TipoKitDAOImpl extends BaseDaoHibernate<TipoKitDTO> implements TipoKitDAO{


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoKitDTO> getTipoKit() {
		Criteria c= getCurrentSession().createCriteria(TipoKitDTO.class);
		c.add(Restrictions.eq("stActivo", Boolean.TRUE));
		c.addOrder(Order.asc("nuOrden"));
		return (List<TipoKitDTO>) c.list();
	}

}
