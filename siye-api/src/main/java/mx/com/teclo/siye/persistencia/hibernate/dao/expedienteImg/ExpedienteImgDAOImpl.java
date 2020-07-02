package mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

@Repository
public class ExpedienteImgDAOImpl extends BaseDaoHibernate<ExpedientesImgDTO> implements ExpedienteImgDAO {
	
	private static Boolean ACTIVO = true;

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesImgDTO> getAllExpedientesDTO(Long idOrdenServicio) {
		Criteria c= getCurrentSession().createCriteria(ExpedientesImgDTO.class);
		c.createAlias("idOrdenServicio", "OrdenServicioDTO");
		c.add(Restrictions.eq("OrdenServicioDTO.idOrdenServicio", idOrdenServicio));
		c.add(Restrictions.eq("stActivo", ACTIVO));
		c.addOrder(Order.desc("nuOrden"));
		return (List<ExpedientesImgDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getAllExpedientesImgVO(List<Long> idOrdenServicio) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT  TIE050.ID_EXPEDIENTE_ODS as idExpedienteODS,");
		consulta.append("TIE050.ID_ORDEN_SERVICIO as idOrdenServicio,TIE050.ID_ODS_ENCUESTA as idOdsEncuesta,");
		consulta.append("TIE001.ID_ENCUESTA as idEncuesta,");
		consulta.append("TIE050.ID_PROCESO  as idProceso,TIE050.ID_PREGUNTA as idPregunta,");
		consulta.append("TIE050.ID_TIPO_EXPEDIENTE as idTipoExpediente,TIE050.NB_EXPEDIENTE_ODS as nbExpedienteODS,");
		consulta.append("TIE050.CD_TIPO_ARCHIVO as cdTipoArchivo,TIE050.LB_EXPEDIENTE_ODS as lbExpedienteODS,");
		consulta.append("TIE050.FH_CREACION as fhCreacion, (CASE");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NULL AND TIE050.ID_ODS_ENCUESTA IS NULL AND TIE050.ID_PREGUNTA IS NULL");
		consulta.append(" THEN 'Orden de Servicio'");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NOT NULL AND TIE050.ID_ODS_ENCUESTA IS NULL AND TIE050.ID_PREGUNTA IS NULL");
		consulta.append(" THEN ('Proceso/' || TIE035.NB_PROCESO)");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NOT NULL AND TIE050.ID_ODS_ENCUESTA IS NOT NULL AND TIE050.ID_PREGUNTA IS NULL");
		consulta.append(" THEN ('Encuesta/' || TIE001.NB_ENCUESTA)");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NOT NULL AND TIE050.ID_ODS_ENCUESTA IS NOT NULL AND TIE050.ID_PREGUNTA IS NOT NULL");
		consulta.append(" THEN (TIE001.NB_ENCUESTA || '/' || TIE005.TX_PREGUNTA)");
		consulta.append(" ELSE 'Sin clasificación' END) AS nbNivel, ");
		consulta.append("(CASE WHEN TIE050.ID_PROCESO IS NULL AND TIE050.ID_ODS_ENCUESTA IS NULL AND TIE050.ID_PREGUNTA IS NULL");
		consulta.append(" THEN 'OS'");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NOT NULL AND TIE050.ID_ODS_ENCUESTA IS NULL AND TIE050.ID_PREGUNTA IS NULL");
		consulta.append(" THEN 'PRC'");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NOT NULL AND TIE050.ID_ODS_ENCUESTA IS NOT NULL AND TIE050.ID_PREGUNTA IS NULL");
		consulta.append(" THEN 'ENC'");
		consulta.append(" WHEN TIE050.ID_PROCESO IS NOT NULL AND TIE050.ID_ODS_ENCUESTA IS NOT NULL AND TIE050.ID_PREGUNTA IS NOT NULL");
		consulta.append(" THEN 'PREG'");
		consulta.append(" ELSE 'N/A' END) AS cdNivel,");
		consulta.append(" TIE054.NB_TIPO_EXPEDIENTE AS nbTpDocumneto,");
		consulta.append(" TIE050.NU_ORDEN AS nuOrden, TIE050.ID_INCIDENCIA AS idIncidencia");
		consulta.append(" FROM TIE050D_IE_EXPEDIENTES_IMG TIE050 JOIN TIE026D_IE_ORDEN_SERVICIOS TIE026");
		consulta.append(" ON TIE050.ID_ORDEN_SERVICIO = TIE026.ID_ORDEN_SERVICIO LEFT JOIN TIE035C_IE_PROCESOS TIE035");
		consulta.append(" ON TIE035.ID_PROCESO = TIE050.ID_PROCESO AND TIE035.ST_ACTIVO = 1 LEFT JOIN TIE002D_EE_ODS_ENCUESTA TIE002");
		consulta.append(" ON TIE002.ID_ORDEN_SERVICIO =TIE026.ID_ORDEN_SERVICIO AND TIE002.ID_ODS_ENCUESTA = TIE050.ID_ODS_ENCUESTA");
		consulta.append(" AND TIE002.ST_ACTIVO = 1 LEFT JOIN TIE001D_EE_ENCUESTAS TIE001 ON TIE001.ID_ENCUESTA = TIE002.ID_ENCUESTA AND TIE001.ST_ACTIVO = 1 ");
		consulta.append(" LEFT JOIN TIE005D_EE_PREGUNTAS TIE005  ON TIE005.ID_PREGUNTA = TIE050.ID_PREGUNTA AND TIE005.ST_ACTIVO = 1 ");
		consulta.append(" LEFT JOIN TIE054C_IE_TIPO_EXPEDIENTE TIE054 ON TIE054.ID_TIPO_EXPEDIENTE = TIE050.ID_TIPO_EXPEDIENTE");
		consulta.append(" WHERE TIE026.ID_ORDEN_SERVICIO IN (:idOrdenServicio) AND TIE050.ST_ACTIVO=1");
		
		consulta.append(" ORDER BY TIE050.ID_EXPEDIENTE_ODS ASC");

		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idEncuesta", LongType.INSTANCE)				 
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .addScalar("fhCreacion",DateType.INSTANCE)
				 .addScalar("nbNivel",StringType.INSTANCE)
				 .addScalar("cdNivel",StringType.INSTANCE)
				 .addScalar("nuOrden",LongType.INSTANCE)
				 .addScalar("idIncidencia",LongType.INSTANCE)
				 .addScalar("nbTpDocumneto",StringType.INSTANCE)
				 .setParameterList("idOrdenServicio", idOrdenServicio)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImagenVO> getImgByProceso(Long OrdenServicio, Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " + 
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente"+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO =:OrdenServicio AND ID_PROCESO =:idProceso AND ST_ACTIVO=1 AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL "+
				"ORDER BY NU_ORDEN ASC");
		List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setParameter("idProceso", idProceso)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImagenVO> getImgByEncuesta(Long OrdenServicio, Long idEncuesta) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " +
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio AND ID_ODS_ENCUESTA=:idEncuesta AND ID_PREGUNTA IS NULL AND ST_ACTIVO=1 "+
				"ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setParameter("idEncuesta", idEncuesta)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImagenVO> getImgByPregunta(Long OrdenServicio, Long idPregunta) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " +
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio AND ID_PREGUNTA=:idPregunta AND ST_ACTIVO=1 "+
				"ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setParameter("idPregunta", idPregunta)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getImgByOrdenServicio(Long OrdenServicio) {//AND ID_PROCESO_ENCUESTA=NULL
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " + 
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio  AND ST_ACTIVO=1 AND ID_PROCESO IS NULL AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL " +
				"ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getImagenOS(Long OrdenServicio, String restriccion) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " + 
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio  AND ST_ACTIVO=1 AND ID_PROCESO IS NULL AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL ");
		consulta.append(restriccion);
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getImagenesByIdIncidencia(Long idIncidencia) {		
			String hql = "SELECT ex.idExpedienteODS as idExpedienteODS,ex.nbExpedienteODS as nbExpedienteODS,"
					+ "ex.cdTipoArchivo as cdTipoArchivo,ex.lbExpedienteODS as lbExpedienteODS "
					+ "FROM ExpedientesImgDTO as ex "
					+ "WHERE ex.incidencia.idIncidencia=:idIncidencia "
					+ "and ex.stActivo=1 ORDER BY ex.nuOrden DESC";
			Query query = getCurrentSession().createQuery(hql);
			query.setParameter("idIncidencia", idIncidencia).setResultTransformer(Transformers.aliasToBean(ImagenVO.class));
			return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getImagenPorNivel(StringBuilder consulta) {
		List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("idIncidencia",LongType.INSTANCE)
				 .addScalar("txIncidencia",StringType.INSTANCE)
				 .addScalar("colorPrioridad",StringType.INSTANCE)
				 .addScalar("nbPrioridad",StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@Override
	public Integer getNumeroImagenesByPregunta(Long idOrden, Long idProceso, Long idEncuesta, Long idPregunta) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("select COUNT(TIE050.ID_EXPEDIENTE_ODS) ");
		consulta.append("from TIE050D_IE_EXPEDIENTES_IMG TIE050 ");
		consulta.append("join TIE002D_EE_ODS_ENCUESTA TIE002 ");
		consulta.append("on TIE002.ID_ORDEN_SERVICIO = TIE050.ID_ORDEN_SERVICIO ");
		consulta.append("and TIE002.ID_ODS_ENCUESTA = TIE050.ID_ODS_ENCUESTA ");
		consulta.append("WHERE TIE050.ID_ORDEN_SERVICIO=:idOrdenServicio AND TIE050.ID_PROCESO=:idProceso AND TIE002.ID_ENCUESTA=:idOsEncuesta ");
		consulta.append("AND TIE050.ID_PREGUNTA = :idPregunta AND TIE050.ST_ACTIVO=1");
		
		SQLQuery query =getCurrentSession().createSQLQuery(consulta.toString());
		query.setParameter("idOrdenServicio", idOrden);
		query.setParameter("idProceso", idProceso);
		query.setParameter("idOsEncuesta", idEncuesta);
		query.setParameter("idPregunta", idPregunta);
		BigDecimal bd=(BigDecimal)query.uniqueResult();
		Integer respuesta  = bd.intValue();
		
		return respuesta;
	}

}
