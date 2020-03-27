package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.GerenteSupervisorDTO;

@Repository
public class GerenteSupervisorDAOImpl extends BaseDaoHibernate<GerenteSupervisorDTO> implements GerenteSupervisorDAO{

	@Override
	public GerenteSupervisorDTO consultaGerenteSupervisorBySupervisor(Long supervisor) {
		Criteria c = getCurrentSession().createCriteria(GerenteSupervisorDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("supervisor", supervisor));
		return (GerenteSupervisorDTO) c.uniqueResult();
	}




}
