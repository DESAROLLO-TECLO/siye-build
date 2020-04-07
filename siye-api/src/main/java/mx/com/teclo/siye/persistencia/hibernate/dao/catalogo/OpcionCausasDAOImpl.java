package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.OpcionCausaDTO;

@Repository
public class OpcionCausasDAOImpl extends BaseDaoHibernate<OpcionCausaDTO>
		implements
			OpcionCausasDAO {


	@SuppressWarnings("unchecked")
	@Override
	public List<OpcionCausaDTO> getCausasByidOpcion(Long idOpcion) {
		Criteria c= getCurrentSession().createCriteria(OpcionCausaDTO.class);
		c.createAlias("opcion", "opcion");
		c.add(Restrictions.eq("opcion.idOpcion", idOpcion));;
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("nuOrden"));
		return (List<OpcionCausaDTO>)c.list();
	}

}
