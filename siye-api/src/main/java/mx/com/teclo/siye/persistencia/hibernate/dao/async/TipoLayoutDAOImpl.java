package mx.com.teclo.siye.persistencia.hibernate.dao.async;

import java.math.BigDecimal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.TipoLayoutDTO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigLayoutVO;

@Repository
public class TipoLayoutDAOImpl extends BaseDaoHibernate<TipoLayoutDTO> implements TipoLayoutDAO {

	@Override
	public ConfigLayoutVO getLayoutVigente() {

		Criteria criteria = getCurrentSession().createCriteria(TipoLayoutDTO.class, "tipoLayout");

		criteria.add(Restrictions.eq("tipoLayout.stActivo", Boolean.TRUE.booleanValue()));
		criteria.add(Restrictions.eq("tipoLayout.stVigente", Boolean.TRUE.booleanValue()));

		criteria.setProjection(
				Projections.projectionList().add(Projections.property("tipoLayout.idTipoLayout").as("idTipoLayout"))
						.add(Projections.property("tipoLayout.cdTipoLayout").as("cdTipoLayout"))
						.add(Projections.property("tipoLayout.nbTipoLayout").as("nbTipoLayout"))
						.add(Projections.property("tipoLayout.nuMaxRegistros").as("nuMaxRegistros"))
						.add(Projections.property("tipoLayout.idTipoArchivo").as("idTipoArchivo"))
						.add(Projections.property("tipoLayout.txMascara").as("txMascara"))
						.add(Projections.property("tipoLayout.cdTamanioMax").as("cdTamanioMax"))
						.add(Projections.property("tipoLayout.nbDirectorio").as("nbDirectorio"))
						.add(Projections.property("tipoLayout.stCargaParcial").as("stCargaParcial"))
						.add(Projections.property("tipoLayout.stVigente").as("stVigente")));
		criteria.addOrder(Order.desc("tipoLayout.idTipoLayout"));
		criteria.setMaxResults(BigDecimal.ONE.intValue());
		criteria.setResultTransformer(Transformers.aliasToBean(ConfigLayoutVO.class));
		return (ConfigLayoutVO) criteria.uniqueResult();
	}
	
	
	@Override
	public ConfigLayoutVO getTipoLayoutById(Long idTipoLayout) {

		Criteria criteria = getCurrentSession().createCriteria(TipoLayoutDTO.class, "tipoLayout");

		criteria.add(Restrictions.eq("tipoLayout.stActivo", Boolean.TRUE.booleanValue()));		
		criteria.add(Restrictions.eq("tipoLayout.idTipoLayout", idTipoLayout));

		criteria.setProjection(
				Projections.projectionList().add(Projections.property("tipoLayout.idTipoLayout").as("idTipoLayout"))
						.add(Projections.property("tipoLayout.cdTipoLayout").as("cdTipoLayout"))
						.add(Projections.property("tipoLayout.nbTipoLayout").as("nbTipoLayout"))
						.add(Projections.property("tipoLayout.nuMaxRegistros").as("nuMaxRegistros"))
						.add(Projections.property("tipoLayout.idTipoArchivo").as("idTipoArchivo"))
						.add(Projections.property("tipoLayout.txMascara").as("txMascara"))
						.add(Projections.property("tipoLayout.cdTamanioMax").as("cdTamanioMax"))
						.add(Projections.property("tipoLayout.nbDirectorio").as("nbDirectorio"))
						.add(Projections.property("tipoLayout.stCargaParcial").as("stCargaParcial"))
						.add(Projections.property("tipoLayout.stVigente").as("stVigente")));
		criteria.addOrder(Order.desc("tipoLayout.idTipoLayout"));
		criteria.setMaxResults(BigDecimal.ONE.intValue());
		criteria.setResultTransformer(Transformers.aliasToBean(ConfigLayoutVO.class));
		return (ConfigLayoutVO) criteria.uniqueResult();
	}

}
