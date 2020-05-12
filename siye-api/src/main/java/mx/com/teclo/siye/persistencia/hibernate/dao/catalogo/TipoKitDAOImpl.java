package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoKitVO;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoKitVO> getCatTipoKit() {	
		Criteria c = getCurrentSession().createCriteria(TipoKitDTO.class, "tipoKit");
		c.add(Restrictions.eq("tipoKit.stActivo", true));
		c.setProjection(
				Projections.projectionList()
						.add(Projections.property("tipoKit.idTipoKit").as("idTipoKit"))
						.add(Projections.property("tipoKit.cdTipoKit").as("cdTipoKit"))
						.add(Projections.property("tipoKit.nbTipoKit").as("nbTipoKit")));
		c.addOrder(Order.desc("tipoKit.nuOrden"));		
		c.setResultTransformer(Transformers.aliasToBean(TipoKitVO.class));		
		return (List<TipoKitVO>) c.list();
	}

}
