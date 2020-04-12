package mx.com.teclo.siye.persistencia.hibernate.dao.async;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.LayoutDTO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;

@Repository
public class LayoutDAOImpl extends BaseDaoHibernate<LayoutDTO> implements LayoutDAO {
	private static final String QUERY_GET_NBS_COLUMNAS = "SELECT d.NB_CAMPO as nbColumna, NVL(l.NU_ORDEN_REGISTRO, 0) AS nuOrden, NVL(l.CD_TIPO_DATO, CASE WHEN D.NB_CAMPO LIKE 'ST%' THEN 'Boolean' WHEN D.NB_CAMPO LIKE 'ID%' THEN 'Long' WHEN D.NB_CAMPO LIKE 'FH%' THEN 'Date' ELSE 'String' END) AS cdTipo, d.TX_VALOR_DEFECTO as txValorDefecto, NVL(l.NU_LONGITUD_MAX, d.NU_LONGITUD) AS nuLongitudMax FROM TIE055C_IE_LAYOUT l RIGHT OUTER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO = d.ID_TABLA_DESTINO WHERE d.NB_TABLA = :nbTabla AND d.ST_ACTIVO = 1 AND (d.ST_PERMITE_NULO = 0 OR l.ID_TABLA_DESTINO IS NOT NULL)";

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
		SQLQuery query = (SQLQuery) getCurrentSession().createSQLQuery(QUERY_GET_NBS_COLUMNAS);
		query.setString("nbTabla", tabla);
		return query.addScalar("nbColumna", StringType.INSTANCE).addScalar("nuOrden", LongType.INSTANCE)
				.addScalar("cdTipo", StringType.INSTANCE).addScalar("txValorDefecto", StringType.INSTANCE)
				.addScalar("nuLongitudMax", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ColumnaVO.class)).list();

	}

	@Override
	public Long ejecutarQueryConcatenado(String query) {
		Query q = getCurrentSession().createSQLQuery(query);
		Long id = (long) q.executeUpdate();
		return id;
	}

}
