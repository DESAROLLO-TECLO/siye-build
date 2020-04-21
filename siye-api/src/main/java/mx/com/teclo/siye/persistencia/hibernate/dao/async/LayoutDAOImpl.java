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
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;

@Repository
public class LayoutDAOImpl extends BaseDaoHibernate<LayoutDTO> implements LayoutDAO {
	private static final String QUERY_GET_NBS_COLUMNAS = "SELECT d.NB_CAMPO AS nbColumna, NVL(l.NU_ORDEN_REGISTRO, 0) AS nuOrden, NVL(l.CD_TIPO_DATO, 'String') AS cdTipo, l.TX_VALOR_DEFECTO AS txValorDefecto, NVL(l.NU_LONGITUD_MAX, d.NU_LONGITUD) AS nuLongitudMax, CASE WHEN l.ST_CAMPO_FILTRO IS NULL THEN 0 ELSE 1 end AS stCampoFiltro FROM TIE055C_IE_LAYOUT l RIGHT OUTER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO = d.ID_TABLA_DESTINO WHERE d.NB_TABLA = :nbTabla AND l.ID_TIPO_LAYOUT = :idTipoLayout AND d.ST_ACTIVO = 1 AND (d.ST_PERMITE_NULO = 0 OR l.ID_TABLA_DESTINO IS NOT NULL) ORDER BY d.ID_TABLA_DESTINO";
	private static final String QUERY_GET_COLUMNAS_EN_ARCHIVO = "SELECT l.ID_CAMPO AS idCampo, l.NB_CAMPO AS nbCampo, l.NU_ORDEN_REGISTRO AS nuOrden, l.CD_TIPO_DATO AS tipoDato, l.NU_LONGITUD_MAX AS longMax, CASE WHEN l.ST_REQUERIDO = 1 THEN 1 ELSE 0 END AS isRequerido, l.TX_MASCARA AS txMascara FROM TIE055C_IE_LAYOUT l INNER JOIN TIE057C_IE_TIPO_LAYOUT t ON l.ID_TIPO_LAYOUT = t.ID_TIPO_LAYOUT LEFT OUTER JOIN TIE056C_IE_TABLA_DESTINO d ON l.ID_TABLA_DESTINO = d.ID_TABLA_DESTINO WHERE t.ST_VIGENTE = 1 AND t.ST_ACTIVO = 1 AND l.CD_INDICADOR_REG = 'D' AND l.ST_REQUERIDO IN (1,3) ORDER BY l.NU_ORDEN_REGISTRO";
	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaVO> getLayout(Long idTipoLayout, String cdSeccion) {
		Criteria c = getCurrentSession().createCriteria(LayoutDTO.class, "layout");
		c.createAlias("layout.idTipoLayout", "tipoLayout");
		if(cdSeccion.equals(SeccionLayoutEnum.DETALLE.getCdIndicadorReg())) {
			c.createAlias("layout.idTablaDestino", "tbDestino");
		}	

		c.add(Restrictions.eq("tipoLayout.idTipoLayout", idTipoLayout));
		c.add(Restrictions.eq("layout.cdIndicadorReg", cdSeccion));
		c.add(Restrictions.eq("layout.stActivo", Boolean.TRUE.booleanValue()));

		c.setProjection(Projections.projectionList().add(Projections.property("layout.nbCampo").as("nbColumna"))
				.add(Projections.property("layout.cdTipoDato").as("cdTipo")));
		c.addOrder(Order.asc("layout.nuOrdenRegistro"));

		c.setResultTransformer(Transformers.aliasToBean(ColumnaVO.class));
		return  c.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaVO> getNbsColumnas(Long idTipoLayout, String tabla) {
		SQLQuery query = (SQLQuery) getCurrentSession().createSQLQuery(QUERY_GET_NBS_COLUMNAS);
		query.setString("nbTabla", tabla);
		query.setLong("idTipoLayout", idTipoLayout);
		
		return query.addScalar("nbColumna", StringType.INSTANCE).addScalar("nuOrden", IntegerType.INSTANCE)
				.addScalar("cdTipo", StringType.INSTANCE).addScalar("txValorDefecto", StringType.INSTANCE)
				.addScalar("nuLongitudMax", IntegerType.INSTANCE)
				.addScalar("stCampoFiltro", BooleanType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ColumnaVO.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnaArchivoVO> getColumnasEnArchivo() {
		SQLQuery query = (SQLQuery) getCurrentSession().createSQLQuery(QUERY_GET_COLUMNAS_EN_ARCHIVO);
		
		return query.addScalar("idCampo", LongType.INSTANCE)
				.addScalar("nbCampo", StringType.INSTANCE)
				.addScalar("nuOrden", IntegerType.INSTANCE)
				.addScalar("tipoDato", StringType.INSTANCE)
				.addScalar("longMax", IntegerType.INSTANCE)
				.addScalar("isRequerido", BooleanType.INSTANCE)
				.addScalar("txMascara", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ColumnaArchivoVO.class)).list();
	}



}
