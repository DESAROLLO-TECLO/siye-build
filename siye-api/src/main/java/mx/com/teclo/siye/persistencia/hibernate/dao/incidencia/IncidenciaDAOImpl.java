package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenDetailVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.DetalleIncidenciaVO;

@Repository
public class IncidenciaDAOImpl extends BaseDaoHibernate<IncidenciaDTO> implements IncidenciaDAO {

	@Override
	public IncidenciaDTO getIncidenciabycdIncidencia(String cdIncidencia) {
		Criteria c = getCurrentSession().createCriteria(IncidenciaDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("cdIncidencia", cdIncidencia));
		return (IncidenciaDTO) c.uniqueResult();
	}
	
	@Override
	public Long getUltimoId(){
		Criteria c = getCurrentSession().createCriteria(IncidenciaDTO.class);
		c.addOrder(Order.desc("idIncidencia"));
		IncidenciaDTO incidenciaDTO = (IncidenciaDTO)c.list().get(0);
		return incidenciaDTO.getIdIncidencia();
	}
	
	@Override
	public  IncidenciaDTO incidenciaBycdIncidencia(String cdIncidenc){
		Criteria c = getCurrentSession().createCriteria(IncidenciaDTO.class);
		c.createAlias("stIncidencia", "stIncidencia");
		
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("cdIncidencia", cdIncidenc));
		c.add(Restrictions.ne("stIncidencia.idStSeguimiento", 3l));
		return (IncidenciaDTO)c.uniqueResult();
		
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDetalleVO> getIncidenciasByIdEncuesta(Long idOrden,Long idEncuesta){
		
		String hql = "SELECT  inc.idIncidencia as idIncidencia,inc.cdIncidencia as cdIncidencia ,"
				+ "inc.nbIncidencia as nbIncidencia,inc.txIncidencia as txIncidencia,inc.fhCreacion as fhCreacion,"
				+ "inc.stSeguimiento as stSeguimiento,inc.tpIncidencia as tpIncidencia,inc.stIncidencia as stIncidencia,"
				+ "inc.stAutorizacion as stAutorizacion,inc.prioridad as prioridad "
				+ "FROM IncidenciaDTO as inc, "
				+ "OdsIncidenciaDTO as osinc "
				+ "WHERE inc.idIncidencia = osinc.idIncidencia.idIncidencia "
				+ "and osinc.idOrdenServicio.idOrdenServicio=:idOrden "
				+ "and inc.encuesta.idEncuesta =:idEncuesta "
				+ "and inc.iEproceso.idProceso IS NOT NULL "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idEncuesta", idEncuesta).
		setParameter("idOrden", idOrden).setResultTransformer(Transformers.aliasToBean(IncidenciaDetalleVO.class));
		return query.list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDetalleVO> getIncidenciasByProceso(Long idOrden,Long idProceso){
		
		String hql = "SELECT  inc.idIncidencia as idIncidencia,inc.cdIncidencia as cdIncidencia ,"
				+ "inc.nbIncidencia as nbIncidencia,inc.txIncidencia as txIncidencia,inc.fhCreacion as fhCreacion,"
				+ "inc.stSeguimiento as stSeguimiento,inc.tpIncidencia as tpIncidencia,inc.stIncidencia as stIncidencia,"
				+ "inc.stAutorizacion as stAutorizacion,inc.prioridad as prioridad "
				+ "FROM IncidenciaDTO as inc, "
				+ "OdsIncidenciaDTO as osinc "
				+ "WHERE inc.idIncidencia = osinc.idIncidencia.idIncidencia "
				+ "and osinc.idOrdenServicio.idOrdenServicio=:idOrden "
				+ "and inc.iEproceso.idProceso=:idProceso "
				+ "and inc.encuesta.idEncuesta IS NULL "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idProceso", idProceso).
		setParameter("idOrden", idOrden)
		.setResultTransformer(Transformers.aliasToBean(IncidenciaDetalleVO.class));
		return query.list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDetalleVO> getIncidenciasByIdOrden(Long idOrden){
		
		String hql = "SELECT  inc.idIncidencia as idIncidencia,inc.cdIncidencia as cdIncidencia ,"
				+ "inc.nbIncidencia as nbIncidencia,inc.txIncidencia as txIncidencia,inc.fhCreacion as fhCreacion,"
				+ "inc.stSeguimiento as stSeguimiento,inc.tpIncidencia as tpIncidencia,inc.stIncidencia as stIncidencia,"
				+ "inc.stAutorizacion as stAutorizacion,inc.prioridad as prioridad "
				+ "FROM IncidenciaDTO as inc, "
				+ "OdsIncidenciaDTO as osinc "
				+ "WHERE inc.idIncidencia = osinc.idIncidencia.idIncidencia "
				+ "and osinc.idOrdenServicio.idOrdenServicio=:idOrden "
				+ "and inc.encuesta.idEncuesta IS NULL "
				+ "and inc.iEproceso.idProceso IS NULL "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idOrden", idOrden).setResultTransformer(Transformers.aliasToBean(IncidenciaDetalleVO.class));
		return query.list();		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDTO> getIncidenciasByIdEn(Long idEncuesta) {
		Criteria c = getCurrentSession().createCriteria(IncidenciaDTO.class);
		c.createAlias("encuesta", "encuesta");
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("encuesta.idEncuesta", idEncuesta));
		return (List<IncidenciaDTO>)c.list();
	}


	@Override
    @SuppressWarnings("unchecked")
	public List<DetalleIncidenciaVO> getDetalleIncidencia(Long idOrden) {
		StringBuilder consulta = new StringBuilder("SELECT "
				+"tdioi.ID_ODS_INCIDENCIA AS idOSIncidencia,"
				+"tdioi.ID_ORDEN_SERVICIO  AS idOrdenServicio,"
				+"tdii.ID_INCIDENCIA AS idIncidencia,"
				+"tdii.CD_INCIDENCIA AS cdIncidencia,"
				+"tdii.NB_INCIDENCIA  AS nbIncidencia,"
				+"tdii.TX_INCIDENCIA AS txIncidencia,"
				+"tciss.ID_ST_SEGUIMIENTO AS idStSeguimiento,"
				+"tciss.NB_ST_SEGUIMIENTO AS txNbSeguimiento,"
				+"tciss.CD_COLOR AS txColor,"
				+"tpr.NB_ST_SEGUIMIENTO AS prioridad,"
				+"tinci.NB_ST_SEGUIMIENTO AS tipoIncidencia,"
				+"tauntoriza.NB_ST_SEGUIMIENTO AS nbStAutoriza,"
				+"stIncidencia.NB_ST_SEGUIMIENTO AS stIncidencia,"
				+"tcip.TX_PROCESO AS nbProceso,"
				+"tdee.NB_ENCUESTA AS nbEncuesta"
				+"	FROM TIE058D_IE_ODS_INCIDENCIA tdioi"
				+" INNER JOIN TIE051D_IE_INCIDENCIA tdii ON (tdioi.ID_INCIDENCIA = tdii.ID_INCIDENCIA) "
				+" INNER JOIN TIE048C_IE_ST_SEGUIMIENTO tciss ON (tdii.ID_ST_INCIDENCIA = tciss.ID_ST_SEGUIMIENTO) "
				+" INNER JOIN TIE048C_IE_ST_SEGUIMIENTO tpr ON (tdii.ID_PRIORIDAD = tpr.ID_ST_SEGUIMIENTO ) "
				+" INNER JOIN TIE048C_IE_ST_SEGUIMIENTO tinci ON (tdii.ID_TP_INCIDENCIA = tinci.ID_ST_SEGUIMIENTO) "
				+" INNER JOIN TIE048C_IE_ST_SEGUIMIENTO tauntoriza ON (tdii.ID_ST_AUTORIZACION = tauntoriza.ID_ST_SEGUIMIENTO) "
				+" INNER JOIN TIE048C_IE_ST_SEGUIMIENTO stIncidencia ON (tdii.ID_ST_INCIDENCIA = stIncidencia.ID_ST_SEGUIMIENTO) "
				+" INNER JOIN TIE035C_IE_PROCESOS tcip ON (tdii.ID_PROCESO = tcip.ID_PROCESO) "
				+" INNER JOIN TIE001D_EE_ENCUESTAS tdee ON (tdii.ID_ENCUESTA = tdee.ID_ENCUESTA) "
				+"  WHERE tdioi.ID_ORDEN_SERVICIO=:idOrdenServicio");
		List<DetalleIncidenciaVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idOSIncidencia",LongType.INSTANCE)
				.addScalar("idOrdenServicio",LongType.INSTANCE)
				.addScalar("idIncidencia",LongType.INSTANCE)
				.addScalar("cdIncidencia",StringType.INSTANCE)
				.addScalar("nbIncidencia",StringType.INSTANCE)
				.addScalar("txIncidencia",StringType.INSTANCE)
				.addScalar("txColor",StringType.INSTANCE)
				.addScalar("prioridad",StringType.INSTANCE)
				.addScalar("tipoIncidencia",StringType.INSTANCE)
				.addScalar("nbStAutoriza",StringType.INSTANCE)
				.addScalar("stIncidencia",StringType.INSTANCE)
				.addScalar("nbProceso",StringType.INSTANCE)
				.addScalar("nbEncuesta",StringType.INSTANCE)
				.setParameter("idOrdenServicio", idOrden)
				.setResultTransformer(Transformers.aliasToBean(DetalleIncidenciaVO.class)).list();
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDTO> getIncidenciasByIdOrdenFechas(Long idCentroInstalacion,Long idOrden, String fhInici, String fhFin) {
		String hql = "SELECT  inc "
				+ "FROM IncidenciaDTO as inc, "
				+ "OdsIncidenciaDTO as osinc "
				+ "WHERE inc.idIncidencia = osinc.idIncidencia.idIncidencia "
				+ "and osinc.idOrdenServicio.idOrdenServicio=:idOrden "
				+ "AND inc.centroInstalacion.idCentroInstalacion=:idCentroInstalacion "
				+ "and TRUNC(TO_DATE(inc.fhCreacion)) BETWEEN TO_DATE('#{fhInici}','dd/MM/yyyy') AND TO_DATE('#{fhFin}','dd/MM/yyyy')"
				+ "and inc.stActivo=1";		
		hql = StringUtils.replace(hql, "#{fhInici}", fhInici);
		hql = StringUtils.replace(hql, "#{fhFin}", fhFin);
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idOrden", idOrden);
		query.setParameter("idCentroInstalacion", idCentroInstalacion);
		return query.list();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDTO> getIncidenciasByIdCentroFechas(Long idCentroInstalacion, String fhInici, String fhFin) throws ParseException {
		
		String hql = "SELECT  inc "
				+ "FROM IncidenciaDTO as inc "
				+ "WHERE inc.idIncidencia NOT IN (SELECT DISTINCT inci.idIncidencia from OdsIncidenciaDTO as inci) "
				+ "AND inc.centroInstalacion.idCentroInstalacion=:idCentroInstalacion "
				+ "and TRUNC(TO_DATE(inc.fhCreacion)) BETWEEN TO_DATE('#{fhInici}','dd/MM/yyyy') AND TO_DATE('#{fhFin}','dd/MM/yyyy') "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
		
		hql = StringUtils.replace(hql, "#{fhInici}", fhInici);
		hql = StringUtils.replace(hql, "#{fhFin}", fhFin);
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idCentroInstalacion", idCentroInstalacion);
		return query.list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDTO> getIncidenciasByIdIncidenciaFechas(Long idCentroInstalacion,Long idIncidencia, String fhInici, String fhFin) {
		String hql = "SELECT  inc "
				+ "FROM IncidenciaDTO as inc "
				+ "WHERE inc.idIncidencia=:idIncidencia "
				+ "AND inc.centroInstalacion.idCentroInstalacion=:idCentroInstalacion "
				+ "and TRUNC(TO_DATE(inc.fhCreacion)) BETWEEN TO_DATE('#{fhInici}','dd/MM/yyyy') AND TO_DATE('#{fhFin}','dd/MM/yyyy') "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
		
		hql = StringUtils.replace(hql, "#{fhInici}", fhInici);
		hql = StringUtils.replace(hql, "#{fhFin}", fhFin);
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idIncidencia", idIncidencia);
		query.setParameter("idCentroInstalacion", idCentroInstalacion);
		return query.list();
	}

}
