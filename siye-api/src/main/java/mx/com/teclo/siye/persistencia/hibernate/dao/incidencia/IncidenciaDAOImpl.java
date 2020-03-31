package mx.com.teclo.siye.persistencia.hibernate.dao.incidencia;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;

@Repository
public class IncidenciaDAOImpl extends BaseDaoHibernate<IncidenciaDTO> implements IncidenciaDAO {

	@Override
	public IncidenciaDTO getIncidenciabycdIncidencia(String cdIncidencia) {
		Criteria c = getCurrentSession().createCriteria(IncidenciaDTO.class);
		c.add(Restrictions.eq("stActivo", 1L));
		c.add(Restrictions.eq("cdIncidencia", cdIncidencia));
		return (IncidenciaDTO) c.uniqueResult();
	}


}
