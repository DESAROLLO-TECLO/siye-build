package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesoDetalleVO;

@Repository
public class PlanProcesoDAOImpl extends BaseDaoHibernate<PlanProcesoDTO> implements PlanProcesoDAO {


	@SuppressWarnings("unchecked")
	@Override
	public List<PlanProcesoDTO> obtenerPorcesosPlan(Long idPlan) {
		Criteria c = getCurrentSession().createCriteria(PlanProcesoDTO.class);
		c.createAlias("plan", "plan");
		c.add(Restrictions.eq("plan.idPlan", idPlan));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("nuorden"));
		return (List<PlanProcesoDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteNivelProcesoVO> getProcesosPlanVO(List<Long> idPlan) {
		StringBuilder consulta = new StringBuilder("SELECT pp.ID_PROCESO AS idProceso, proceso.TX_PROCESO AS cdProceso, proceso.NU_MAX_IMAGENES AS nuMaxImg, " + 
				" pp.ID_PLAN as idPlan"+
				"   FROM TIE036D_IE_PLAN_PROCESO pp" + 
				"     INNER JOIN TIE035C_IE_PROCESOS proceso ON (pp.ID_PROCESO  = proceso.ID_PROCESO)" + 
				"     WHERE pp.ID_PLAN IN (:idPlan) AND pp.ST_ACTIVO =1 AND proceso.ST_ACTIVO =1 ORDER BY pp.NU_ORDEN ASC");
		List<ExpedienteNivelProcesoVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idProceso",LongType.INSTANCE)
				.addScalar("cdProceso",StringType.INSTANCE)
				.addScalar("nuMaxImg",LongType.INSTANCE)
				.addScalar("idPlan",LongType.INSTANCE)
				.setParameterList("idPlan", idPlan)
				.setResultTransformer(Transformers.aliasToBean(ExpedienteNivelProcesoVO.class)).list();
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcesoDetalleVO> getEtapasParaSeguimiento(Long idOrdenServicio) {
		StringBuilder consulta = new StringBuilder("SELECT "
				+" tdop.ID_ODS_PROCESO AS idOdsProceso," 
				+" tdop.FH_INI_PROCESO AS fhInicio," 
				+" tdop.FH_FIN_PROCESO AS fhFin,"
				+" proceso.ID_PROCESO AS idProceso," 
				+" proceso.TX_PROCESO AS nbProceso "
				+"FROM TIE065D_ODS_PROCESOS tdop" 
				+"   INNER JOIN TIE026D_IE_ORDEN_SERVICIOS os ON (tdop.ID_ORDEN_SERVICIO = os.ID_ORDEN_SERVICIO)"
				+"   INNER JOIN TIE035C_IE_PROCESOS proceso ON (tdop.ID_PROCESO  = proceso.ID_PROCESO) "
				+"WHERE tdop.ST_ACTIVO =1 AND os.ST_ACTIVO =1 AND proceso.ST_ACTIVO =1 AND os.ID_ORDEN_SERVICIO =:idOrdenServicio" 
				+" ORDER BY proceso.ID_PROCESO");
		List<ProcesoDetalleVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idOdsProceso",LongType.INSTANCE)
				.addScalar("fhInicio",StringType.INSTANCE)
				.addScalar("fhFin",StringType.INSTANCE)
				.addScalar("idProceso",LongType.INSTANCE)
				.addScalar("nbProceso",StringType.INSTANCE)
				.setParameter("idOrdenServicio", idOrdenServicio)
				.setResultTransformer(Transformers.aliasToBean(ProcesoDetalleVO.class)).list();
		return respuesta;
	}

	@Override
	public ProcesoDetalleVO getDetalleProceso(Long idOrdenServicio, Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT tdop.ID_PROCESO AS idProceso, FH_INI_PROCESO AS fhInicio, FH_FIN_PROCESO AS fhFin,"
				+"tcip.TX_PROCESO  AS nbProceso"
				+" FROM TIE065D_ODS_PROCESOS tdop " 
				+"  INNER JOIN TIE035C_IE_PROCESOS tcip ON (tdop.ID_PROCESO = tcip.ID_PROCESO)"
				+" WHERE tdop.ID_ORDEN_SERVICIO =:idOrdenServicio AND tdop.ID_PROCESO =:idProceso AND tdop.ST_ACTIVO =1");
		ProcesoDetalleVO respuesta = (ProcesoDetalleVO) getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idProceso",LongType.INSTANCE)
				.addScalar("fhInicio",StringType.INSTANCE)
				.addScalar("fhFin",StringType.INSTANCE)
				.addScalar("nbProceso",StringType.INSTANCE)
				.setParameter("idOrdenServicio", idOrdenServicio)
				.setParameter("idProceso", idProceso)
				.setResultTransformer(Transformers.aliasToBean(ProcesoDetalleVO.class)).uniqueResult();
		return respuesta;
	}

}
