package mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;

@Repository
public class ProcesoEncuestaDAOImpl extends BaseDaoHibernate<ProcesoEncuestaDTO> implements ProcesoEncuestaDAO {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcesoEncuestaDTO> obtenerEncuestasProceso(Long idProceso) {
		Criteria c = getCurrentSession().createCriteria(ProcesoEncuestaDTO.class);
		c.createAlias("idProceso", "proceso");
		c.add(Restrictions.eq("proceso.idProceso", idProceso));
		c.add(Restrictions.eq("stActivo", true));
		return (List<ProcesoEncuestaDTO>) c.list();
	}

}
