package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;

@Repository
public class StEncuestaDAOImpl extends BaseDaoHibernate<StEncuestaDTO> implements StEncuestaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<StEncuestaDTO> stEncuesta() {
		Criteria c= getCurrentSession().createCriteria(StEncuestaDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("nuOrden"));
		return (List<StEncuestaDTO>)c.list();
	}
	
	@Override
	public StEncuestaDTO encuesta(String cdStEncuesta) {
		Criteria c = getCurrentSession().createCriteria(StEncuestaDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("cdStEncuesta", cdStEncuesta));
		return (StEncuestaDTO) c.uniqueResult();
	}

}
