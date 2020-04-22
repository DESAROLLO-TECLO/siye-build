package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TblCatalogosDTO;

@Repository
public class TblCatalogosDAOImpl extends BaseDaoHibernate<TblCatalogosDTO> implements TblCatalogosDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCatalogosDTO> getTblCatalogos(){
		Criteria c = getCurrentSession().createCriteria(TblCatalogosDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stMostrar", true));
		c.addOrder(Order.asc("nuOrden"));
		return (List<TblCatalogosDTO>)c.list();
		
	}

}
