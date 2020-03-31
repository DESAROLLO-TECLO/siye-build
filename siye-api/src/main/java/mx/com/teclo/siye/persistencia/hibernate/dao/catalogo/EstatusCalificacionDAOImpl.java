package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EstatusCalificacionDTO;


@Repository
public class EstatusCalificacionDAOImpl extends BaseDaoHibernate<EstatusCalificacionDTO> implements EstatusCalificacionDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<EstatusCalificacionDTO> calificacion() {
		Criteria c = getCurrentSession().createCriteria(EstatusCalificacionDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("nuOrden"));
		return (List<EstatusCalificacionDTO>)c.list();
	}

	@Override
	public EstatusCalificacionDTO calificacion(String cdStCalificacion) {
		Criteria c = getCurrentSession().createCriteria(EstatusCalificacionDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("cdStCalificacion", cdStCalificacion));
		return (EstatusCalificacionDTO) c.uniqueResult();
	}

	
	
}
