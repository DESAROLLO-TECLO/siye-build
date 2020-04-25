package mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.EncuestaDetaVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.EncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.PreguntasDetalleVO;

@Repository
public class ProcesoEncuestaDAOImpl extends BaseDaoHibernate<ProcesoEncuestaDTO> implements ProcesoEncuestaDAO {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcesoEncuestaDTO> obtenerEncuestasProceso(Long idProceso) {
		Criteria c = getCurrentSession().createCriteria(ProcesoEncuestaDTO.class);
		c.createAlias("idProceso", "proceso");
		c.add(Restrictions.eq("proceso.idProceso", idProceso));
		c.addOrder(Order.asc("nuOrden"));
		c.add(Restrictions.eq("stActivo", true));
		return (List<ProcesoEncuestaDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteNivelEncuestaVO> getEncuestasByProcesoVO(Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT encuesta.ID_ENCUESTA  AS idEncuesta, encuesta.NB_ENCUESTA_ORIGEN AS cdEncuesta, encuesta.NU_MAX_IMAGENES AS nuMaxImg " + 
				"FROM TIE037D_IE_PROCESO_ENCUESTA pE" + 
				" INNER JOIN TIE001D_EE_ENCUESTAS encuesta ON (pE.ID_ENCUESTA = encuesta.ID_ENCUESTA)" + 
				"  WHERE encuesta.ST_ACTIVO = 1 AND pE.ID_PROCESO ="+idProceso +"ORDER BY encuesta.NU_ORDEN ASC");
		List<ExpedienteNivelEncuestaVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idEncuesta",LongType.INSTANCE)
				.addScalar("cdEncuesta",StringType.INSTANCE)
				.addScalar("nuMaxImg", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ExpedienteNivelEncuestaVO.class)).list();
		return respuesta;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EncuestaDetaVO> getEncuestaByIdOrden(Long idProceso) {
		String hql = "SELECT  enc.idEncuesta as idEncuesta,enc.cdEncuesta as cdEncuesta,enc.nbEncuesta as nbEncuesta,enc.txEncuesta as txEncuesta "
				+ "FROM EncuestaDetalleDTO as enc, "
				+ "ProcesoEncuestaDTO as proenc "
				+ "WHERE enc.idEncuesta = proenc.idEncuesta.idEncuesta "
				+ "and proenc.idProceso.idProceso=:idProceso "
				+ "and enc.stActivo=1 ORDER BY proenc.nuOrden ASC ";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idProceso", idProceso).setResultTransformer(Transformers.aliasToBean(EncuestaDetaVO.class));
		return query.list();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EncuestaDetalleVO> getDetalleEncuesta(Long idOrdenServicio, Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT tdipe.ID_ENCUESTA  AS idEncuesta, encuesta.NB_ENCUESTA AS nbEncuesta," 
	            +"tdeuei.FH_INICIO  AS fhInicio, tdeuei.FH_FIN AS fhFin, tdeuei.NU_PREGUNTAS||'/'||tdeuei.NU_PREGUNTAS_CORRECTAS AS preguntas," 
				+"st.NB_ST_ENCUESTA AS estatus,"
	            +"CASE tdeuei.ID_ST_CALIFICACION WHEN 3 THEN 0.0"
				+"  ELSE tdeuei.NU_PREGUNTAS /(tdeuei.NU_PREGUNTAS_CORRECTAS + tdeuei.NU_PREGUNTAS_INCORR + tdeuei.NU_PREGUNTAS_VACIAS)*100 END AS nuPorcentaje"
	            +" FROM TIE037D_IE_PROCESO_ENCUESTA tdipe" 
				+"    INNER JOIN TIE001D_EE_ENCUESTAS encuesta ON (tdipe.ID_ENCUESTA = encuesta.ID_ENCUESTA)" 
				+"    INNER JOIN TIE002D_EE_ODS_ENCUESTA tdeoe ON (encuesta.ID_ENCUESTA = tdeoe.ID_ENCUESTA)" 
				+"    INNER JOIN TIE006D_EE_USU_ENCU_INTEN tdeuei ON (tdeoe.ID_ODS_ENCUESTA  = tdeuei.ID_ODS_ENCUESTA)"
				+"    INNER JOIN TIE018C_EE_ST_ENCUESTAS st ON (tdeuei.ID_ST_CALIFICACION = st.ID_ST_ENCUESTA)"
				+" WHERE tdipe.ST_ACTIVO =1 AND encuesta.ST_ACTIVO =1 AND tdipe.ID_PROCESO =:idProceso AND tdeoe.ID_ORDEN_SERVICIO=:idOrdenServicio" 
				+"   ORDER BY tdipe.ID_ENCUESTA ");
		List<EncuestaDetalleVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idEncuesta",LongType.INSTANCE)
				.addScalar("nbEncuesta",StringType.INSTANCE)
				.addScalar("fhInicio", StringType.INSTANCE)
				.addScalar("fhFin", StringType.INSTANCE)
				.addScalar("preguntas", StringType.INSTANCE)
				.addScalar("estatus", StringType.INSTANCE)
				.addScalar("nuPorcentaje",DoubleType.INSTANCE)
				.setParameter("idOrdenServicio", idOrdenServicio)
				.setParameter("idProceso", idProceso)
				.setResultTransformer(Transformers.aliasToBean(EncuestaDetalleVO.class)).list();
		return respuesta;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PreguntasDetalleVO> getSeguimientoDetallePregunta(Long idOrdenServicio, Long idEncuesta) {
		StringBuilder consulta = new StringBuilder("SELECT "
				+" tdeoe.ID_ORDEN_SERVICIO AS idOrdenServicio,"
				+" tdeoe.ID_ENCUESTA  AS idEncuesta,"
				+" pre.ID_PREGUNTA  AS idPegunta,"
				+" pre.TX_PREGUNTA AS txPregunta,"
				+" opciones.TX_OPCION AS txRespuesta "
				+"FROM TIE002D_EE_ODS_ENCUESTA tdeoe"
				+"  INNER JOIN TIE006D_EE_USU_ENCU_INTEN intent ON (tdeoe.ID_ODS_ENCUESTA = intent.ID_ODS_ENCUESTA)"
				+"  INNER JOIN TIE003D_EE_USU_ENC_RESP resp ON (intent.ID_USU_ENCU_INTENTO = resp.ID_USU_ENCU_INTENTO)"
				+"  INNER JOIN TIE007D_EE_OPCIONES opciones ON (resp.ID_OPCION  = opciones.ID_OPCION)"
				+"  INNER JOIN TIE005D_EE_PREGUNTAS pre ON (resp.ID_PREGUNTA = pre.ID_PREGUNTA)"
				+"WHERE tdeoe.ST_ACTIVO =1 AND intent.ST_ACTIVO =1 AND resp.ST_ACTIVO =1 AND pre.ST_ACTIVO =1 "
				+"  AND tdeoe.ID_ORDEN_SERVICIO =:idOrdenServicio AND tdeoe.ID_ENCUESTA =:idEncuesta "
				+"ORDER BY pre.ID_PREGUNTA");
		List<PreguntasDetalleVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idOrdenServicio",LongType.INSTANCE)
				.addScalar("idEncuesta", LongType.INSTANCE)
				.addScalar("idPegunta", LongType.INSTANCE)
				.addScalar("txPregunta", StringType.INSTANCE)				
				.addScalar("txRespuesta", StringType.INSTANCE)
				.setParameter("idOrdenServicio", idOrdenServicio)
				.setParameter("idEncuesta", idEncuesta)
				.setResultTransformer(Transformers.aliasToBean(EncuestaDetalleVO.class)).list();
		return respuesta;
	}

	@Override
	public StEncuestaVO getstEncuestaByIdEncuestaIdOrden(Long idEncuesta,Long idOrden) {
		String hql = "SELECT  enc.stEncuesta.idStEncuesta as idStEncuesta,enc.stEncuesta.cdStEncuesta as cdStEncuesta,"
				+ "enc.stEncuesta.nbStEncuesta as nbStEncuesta,enc.stEncuesta.cdColor as cdColor "
				+ "FROM UsuarioEncuestaIntentosDTO as enc "
				+ "WHERE enc.usuarioEncuesta.encuesta.idEncuesta=:idEncuesta "
				+ "AND enc.usuarioEncuesta.ordenServicio.idOrdenServicio=:idOrden "
				+ "AND enc.stActivo=1";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idEncuesta", idEncuesta)
		.setParameter("idOrden", idOrden).setResultTransformer(Transformers.aliasToBean(StEncuestaVO.class));
		return (StEncuestaVO) query.uniqueResult();
	}

}
