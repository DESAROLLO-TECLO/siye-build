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
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenDetailVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenciaDetalleVO;

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
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("cdIncidencia", cdIncidenc));
		return (IncidenciaDTO)c.uniqueResult();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDetalleVO> getIncidenciasByIdEncuesta(Long idEncuesta){
		
		String hql = "SELECT  inc.idIncidencia as idIncidencia,inc.cdIncidencia as cdIncidencia ,"
				+ "inc.nbIncidencia as nbIncidencia,inc.txIncidencia as txIncidencia,inc.fhCreacion as fhCreacion,"
				+ "inc.stSeguimiento as stSeguimiento,inc.tpIncidencia as tpIncidencia,inc.stIncidencia as stIncidencia,"
				+ "inc.stAutorizacion as stAutorizacion,inc.prioridad as prioridad "
				+ "FROM IncidenciaDTO as inc "
				+ "WHERE inc.encuesta.idEncuesta =:idEncuesta "
				+ "and inc.iEproceso.idProceso IS NOT NULL "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idEncuesta", idEncuesta).setResultTransformer(Transformers.aliasToBean(IncidenciaDetalleVO.class));
		return query.list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDetalleVO> getIncidenciasByProceso(Long idProceso){
		
		String hql = "SELECT  inc.idIncidencia as idIncidencia,inc.cdIncidencia as cdIncidencia ,"
				+ "inc.nbIncidencia as nbIncidencia,inc.txIncidencia as txIncidencia,inc.fhCreacion as fhCreacion,"
				+ "inc.stSeguimiento as stSeguimiento,inc.tpIncidencia as tpIncidencia,inc.stIncidencia as stIncidencia,"
				+ "inc.stAutorizacion as stAutorizacion,inc.prioridad as prioridad "
				+ "FROM IncidenciaDTO as inc "
				+ "WHERE inc.iEproceso.idProceso=:idProceso "
				+ "and inc.encuesta.idEncuesta IS NULL "
				+ "and inc.stActivo=1 ORDER BY inc.fhCreacion DESC";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idProceso", idProceso).setResultTransformer(Transformers.aliasToBean(IncidenciaDetalleVO.class));
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
