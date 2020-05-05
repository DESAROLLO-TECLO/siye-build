package mx.com.teclo.siye.persistencia.hibernate.dao.async;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.LayoutDTO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaArchivoVO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.TablaDestinoVO;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;

@Repository
public class LayoutDAOImpl extends BaseDaoHibernate<LayoutDTO> implements LayoutDAO {
	private static final String QUERY_GET_NBS_COLUMNAS = "SELECT d.NB_CAMPO AS nbColumna, NVL(l.NU_ORDEN_REGISTRO, 0) AS nuOrden, NVL(l.CD_TIPO_DATO, 'String') AS cdTipo, l.TX_VALOR_DEFECTO AS txValorDefecto, NVL(l.NU_LONGITUD_MAX, d.NU_LONGITUD) AS nuLongitudMax, CASE WHEN l.ST_CAMPO_FILTRO IS NULL THEN 0 ELSE 1 end AS stCampoFiltro, l.tx_mascara AS txMascara FROM TIE055C_IE_LAYOUT l RIGHT OUTER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO = d.ID_TABLA_DESTINO WHERE d.NB_TABLA = :nbTabla AND l.ID_TIPO_LAYOUT = :idTipoLayout AND d.ST_ACTIVO = 1 AND (d.ST_PERMITE_NULO = 0 OR l.ID_TABLA_DESTINO IS NOT NULL) ORDER BY d.ID_TABLA_DESTINO";
	private static final String QUERY_GET_COLUMNAS_EN_ARCHIVO = "SELECT l.ID_CAMPO AS idCampo, l.NB_CAMPO AS nbCampo,  NVL(l.NU_ORDEN_REGISTRO, 0) AS nuOrden, l.CD_TIPO_DATO AS tipoDato, l.NU_LONGITUD_MAX AS longMax, CASE WHEN l.ST_REQUERIDO = 1 THEN 1 ELSE 0 END AS isRequerido, l.TX_MASCARA AS txMascara FROM TIE055C_IE_LAYOUT l INNER JOIN TIE057C_IE_TIPO_LAYOUT t ON l.ID_TIPO_LAYOUT = t.ID_TIPO_LAYOUT LEFT OUTER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO = d.ID_TABLA_DESTINO WHERE t.ID_TIPO_LAYOUT = :idTipoLayout AND t.ST_VIGENTE = 1 AND t.ST_ACTIVO = 1 AND l.CD_INDICADOR_REG = 'D' AND l.ST_REQUERIDO IN (1,3) ORDER BY l.NU_ORDEN_REGISTRO";
	private static final String QUERY_GET_ORDEN_INSERCION = "SELECT t1.nb_tabla as nbTabla, t1.isReadOnly, t1.nu_orden_insercion as nuOrdenInsercion, nvl(t2.isTblBase,0) AS isTblBase FROM (SELECT d.NB_TABLA, NULL AS isTblBase, CASE WHEN ST_CAMPO_FILTRO = 1 THEN 1 ELSE 0 END AS isReadOnly, NU_ORDEN_INSERCION FROM TIE055C_IE_LAYOUT l INNER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO=d.ID_TABLA_DESTINO WHERE l.ID_TIPO_LAYOUT = :idTipoLayout AND l.ST_ACTIVO=1 and l.NU_ORDEN_INSERCION IS NOT NULL) t1 LEFT OUTER JOIN (SELECT DISTINCT d.NB_TABLA AS nb_tabla, 1 AS isTblBase, NULL AS isReadOnly, NULL AS stCampoFiltro, NULL AS NU_ORDEN_INSERCION FROM TIE055C_IE_LAYOUT l INNER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO=d.ID_TABLA_DESTINO WHERE l.ID_TIPO_LAYOUT = :idTipoLayout AND l.ST_ACTIVO=1 AND l.TX_VALOR_DEFECTO LIKE ':%') t2 ON t1.nb_tabla = t2.nb_tabla ORDER BY t1.nu_orden_insercion";

	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaVO> getLayout(Long idTipoLayout, String cdSeccion) {
		Criteria c = getCurrentSession().createCriteria(LayoutDTO.class, "layout");
		c.createAlias("layout.idTipoLayout", "tipoLayout");
		if (cdSeccion.equals(SeccionLayoutEnum.DETALLE.getCdIndicadorReg())) {
			c.createAlias("layout.idTablaDestino", "tbDestino");
		}

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
	public List<ColumnaVO> getNbsColumnas(Long idTipoLayout, String tabla) {
		SQLQuery query = (SQLQuery) getCurrentSession().createSQLQuery(QUERY_GET_NBS_COLUMNAS);
		query.setString("nbTabla", tabla);
		query.setLong("idTipoLayout", idTipoLayout);

		return query.addScalar("nbColumna", StringType.INSTANCE).addScalar("nuOrden", IntegerType.INSTANCE)
				.addScalar("cdTipo", StringType.INSTANCE).addScalar("txValorDefecto", StringType.INSTANCE)
				.addScalar("txMascara", StringType.INSTANCE)
				.addScalar("nuLongitudMax", IntegerType.INSTANCE).addScalar("stCampoFiltro", BooleanType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ColumnaVO.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaArchivoVO> getColumnasEnArchivo(Long idTipoLayout) {
		SQLQuery query = (SQLQuery) getCurrentSession().createSQLQuery(QUERY_GET_COLUMNAS_EN_ARCHIVO);
		query.setLong("idTipoLayout", idTipoLayout);

		return query.addScalar("idCampo", LongType.INSTANCE).addScalar("nbCampo", StringType.INSTANCE)
				.addScalar("nuOrden", IntegerType.INSTANCE).addScalar("tipoDato", StringType.INSTANCE)
				.addScalar("longMax", IntegerType.INSTANCE).addScalar("isRequerido", BooleanType.INSTANCE)
				.addScalar("txMascara", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ColumnaArchivoVO.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TablaDestinoVO> getOrdenInsercionTablas(Long idTipoLayout) {
		SQLQuery query = (SQLQuery) getCurrentSession().createSQLQuery(QUERY_GET_ORDEN_INSERCION);
		query.setLong("idTipoLayout", idTipoLayout);

		return query.addScalar("nbTabla", StringType.INSTANCE).addScalar("isReadOnly", BooleanType.INSTANCE)
				.addScalar("isTblBase", BooleanType.INSTANCE).addScalar("nuOrdenInsercion", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(TablaDestinoVO.class)).list();
	}

}
