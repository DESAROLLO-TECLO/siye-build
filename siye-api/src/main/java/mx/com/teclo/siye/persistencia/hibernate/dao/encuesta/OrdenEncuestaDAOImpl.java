package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;

@Repository
public class OrdenEncuestaDAOImpl extends BaseDaoHibernate<OrdenEncuestaDTO> implements OrdenEncuestaDAO{

	@Override
	public OrdenEncuestaDTO getOrdenEncuestaByIdOrdAndIdEnc(Long idOrden, Long idEncuesta) {
		Criteria c = getCurrentSession().createCriteria(OrdenEncuestaDTO.class);
		
		c.createAlias("ordenServicio", "ordenServicio");
		c.add(Restrictions.eq("ordenServicio.idOrdenServicio", idOrden));
		c.add(Restrictions.eq("ordenServicio.stActivo", true));
		
		c.createAlias("encuesta", "encuesta");
		c.add(Restrictions.eq("encuesta.idEncuesta", idEncuesta));
		c.add(Restrictions.eq("encuesta.stActivo", 1));
		
		c.add(Restrictions.eq("stActivo", true));
		
		return (OrdenEncuestaDTO)c.uniqueResult();
	}

}
