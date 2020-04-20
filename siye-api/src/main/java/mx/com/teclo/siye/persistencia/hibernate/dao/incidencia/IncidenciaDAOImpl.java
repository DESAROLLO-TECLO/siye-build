package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;

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
	public List<IncidenciaDTO> getIncidenciasByIdOrden(Long idOrden){
		
		String hql = "SELECT  inc "
				+ "FROM IncidenciaDTO as inc, "
				+ "OdsIncidenciaDTO as osinc "
				+ "WHERE inc.idIncidencia = osinc.idIncidencia.idIncidencia "
				+ "and osinc.idOrdenServicio.idOrdenServicio=:idOrden "
				+ "and inc.stActivo=1";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idOrden", idOrden);
		return query.list();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncidenciaDTO> getIncidenciasByIdEncuesta(Long idEncuesta) {
		Criteria c = getCurrentSession().createCriteria(IncidenciaDTO.class);
		c.createAlias("encuesta", "encuesta");
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("encuesta.idEncuesta", idEncuesta));
		return (List<IncidenciaDTO>)c.list();
	}
		

}
