package mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.EncuestaDetalleVO;

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
	public List<EncuestaDetalleVO> getDetalleEncuesta(Long idOrdenServicio, Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT tdipe.ID_ENCUESTA  AS idEncuesta, encuesta.NB_ENCUESTA AS nbEncuesta," 
	            +"tdeuei.FH_INICIO  AS fhInicio, tdeuei.FH_FIN AS fhFin, tdeuei.NU_PREGUNTAS||'/'||tdeuei.NU_PREGUNTAS_CORRECTAS AS preguntas," 
				+"st.NB_ST_ENCUESTA AS estatus" 
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
				.setParameter("idOrdenServicio", idOrdenServicio)
				.setParameter("idProceso", idProceso)
				.setResultTransformer(Transformers.aliasToBean(EncuestaDetalleVO.class)).list();
		return respuesta;
	}

}
