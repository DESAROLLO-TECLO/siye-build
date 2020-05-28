package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.OdsIncidenciaDTO;

@Repository
public class OdsIncidenciaDAOImpl extends BaseDaoHibernate<OdsIncidenciaDTO> implements OdsIncidenciaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<OdsIncidenciaDTO> getInsidencisByIdOrdenAndIdEncuesta(Long idOrden,Long idEncuesta){
		Criteria c = getCurrentSession().createCriteria(OdsIncidenciaDTO.class);
		c.createAlias("idOrdenServicio", "idOrdenServicio");
		c.createAlias("idIncidencia", "idIncidencia");
		c.createAlias("idIncidencia.encuesta", "encuesta");
		c.add(Restrictions.eq("idOrdenServicio.idOrdenServicio", idOrden));
		c.add(Restrictions.eq("encuesta.idEncuesta", idEncuesta));
		return (List<OdsIncidenciaDTO>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OdsIncidenciaDTO> getInsidencisByIdOrden(Long idOrden){
		Criteria c = getCurrentSession().createCriteria(OdsIncidenciaDTO.class);
		c.createAlias("idOrdenServicio", "idOrdenServicio");
		c.createAlias("idIncidencia", "idIncidencia");
		c.add(Restrictions.eq("idOrdenServicio.idOrdenServicio", idOrden));
		c.add(Restrictions.isNull("idIncidencia.encuesta"));
		c.add(Restrictions.isNull("idIncidencia.iEproceso"));
		return (List<OdsIncidenciaDTO>) c.list();
	}
	
}
