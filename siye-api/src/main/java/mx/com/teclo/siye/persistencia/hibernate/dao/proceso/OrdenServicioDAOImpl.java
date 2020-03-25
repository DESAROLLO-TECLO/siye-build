package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;

@Repository
public class OrdenServicioDAOImpl extends BaseDaoHibernate<OrdenServicioDTO> implements OrdenServicioDAO{

	@Override
	public OrdenServicioDTO obtenerOrdenServicio(Long idOrdenServicio) {
		Criteria c = getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idOrdenServicio", idOrdenServicio));
		return (OrdenServicioDTO) c.uniqueResult();
	}

}
package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;

@Repository
public class OrdenServicioDAOImpl extends BaseDaoHibernate<OrdenServicioDTO> implements OrdenServicioDAO{

	@Override
	public OrdenServicioDTO obtenerOrdenServicio(Long idOrdenServicio) {
		Criteria c = getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idOrdenServicio", idOrdenServicio));
		return (OrdenServicioDTO) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrden() {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("nuOrden"));
		return (List<OrdenServicioDTO>)c.list();
	}
}
