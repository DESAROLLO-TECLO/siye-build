package mx.com.teclo.siye.persistencia.hibernate.dao.async;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.LayoutDTO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;

@Repository
public class LayoutDAOImpl extends BaseDaoHibernate<LayoutDTO> implements LayoutDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaVO> getLayout(Long idTipoLayout, String cdSeccion) {
		Criteria c = getCurrentSession().createCriteria(LayoutDTO.class, "layout");
		c.createAlias("layout.idTipoLayout", "tipoLayout");
		c.createAlias("layout.idTablaDestino", "tbDestino");

		c.add(Restrictions.eq("tipoLayout.idTipoLayout", idTipoLayout));
		c.add(Restrictions.eq("layout.cdIndicadorReg", cdSeccion));
		c.add(Restrictions.eq("layout.stActivo", Boolean.TRUE.booleanValue()));

		c.setProjection(Projections.projectionList().add(Projections.property("layout.nbCampo").as("nbColumna"))
				.add(Projections.property("layout.cdTipoDato").as("cdTipo")));
		c.addOrder(Order.asc("layout.nuOrdenRegistro"));

		c.setResultTransformer(Transformers.aliasToBean(ColumnaVO.class));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaVO> getNbsColumnas(String tabla) {

		Criteria c = getCurrentSession().createCriteria(LayoutDTO.class, "layout");
		c.createAlias("layout.idTablaDestino", "tbDestino", JoinType.RIGHT_OUTER_JOIN);
		c.add(Restrictions.eq("tbDestino.nbTabla", tabla.toUpperCase()));
		c.add(Restrictions.eq("tbDestino.stActivo", Boolean.TRUE.booleanValue()));
		Criterion rest1 = Restrictions.eq("tbDestino.stPermiteNulo", Boolean.FALSE.booleanValue());
		Criterion rest2 = Restrictions.isNotNull("layout.idTablaDestino");
		c.add(Restrictions.or(rest1, rest2));

		c.setProjection(Projections.projectionList().add(Projections.property("tbDestino.nbCampo").as("nbColumna"))
				.add(Projections.property("layout.nuOrdenRegistro").as("nuOrden"))
				.add(Projections.property("tbDestino.txValorDefecto").as("txValorDefecto"))
				.add(Projections.property("layout.nuLongitudMax").as("nuLongitudMax")));
		c.addOrder(Order.asc("tbDestino.idTablaDestino"));

		c.setResultTransformer(Transformers.aliasToBean(ColumnaVO.class));
		return c.list();
	}

	@Override
	public Long ejecutarQueryConcatenado(String query) {
		Query q = getCurrentSession().createSQLQuery(query);
		Long id = (long) q.executeUpdate();
		return id;
	}

}
