package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoFechasDTO;

@Repository
public class TipoFechasDAOImpl extends BaseDaoHibernate<TipoFechasDTO> implements TipoFechasDAO {

	private static Boolean ACTIVO = true;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoFechasDTO> getCatTipoFechas() {
		Criteria q = getCurrentSession().createCriteria(TipoFechasDTO.class);
		q.add(Restrictions.eq("stActivo", ACTIVO));
		q.addOrder(Order.asc("nuOrden"));
		return (List<TipoFechasDTO>)q.list();
	}
	

}
