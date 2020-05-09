package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenProcesoDTO;

@Repository
public class OrdenProcesoDAOImpl extends BaseDaoHibernate<OrdenProcesoDTO>
		implements
			OrdenProcesoDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenProcesoDTO> getProcesoPorOrden(Long idOrden)
	{
		Criteria c = getCurrentSession().createCriteria(OrdenProcesoDTO.class);
		c.createAlias("ordenServicio", "orden");
		c.add(Restrictions.eq("orden.idOrdenServicio", idOrden));
		c.add(Restrictions.eq("stActivo", true));
		return (List<OrdenProcesoDTO>) c.list();
	}
	

	@Override
	public OrdenProcesoDTO getProceso(Long idOrden,Long idProceso)
	{
		Criteria c = getCurrentSession().createCriteria(OrdenProcesoDTO.class);
		c.createAlias("ordenServicio", "orden");
		c.createAlias("proceso", "proceso");
		c.add(Restrictions.eq("orden.idOrdenServicio", idOrden));
		c.add(Restrictions.eq("proceso.idProceso", idProceso));
		c.add(Restrictions.eq("stActivo", true));
		return (OrdenProcesoDTO) c.uniqueResult();
	}



}
