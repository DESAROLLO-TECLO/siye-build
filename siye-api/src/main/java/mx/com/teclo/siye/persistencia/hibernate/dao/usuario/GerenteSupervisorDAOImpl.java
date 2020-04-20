package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdCentroInstalacion(Long idSupervisor) {
		Criteria c = getCurrentSession().createCriteria(GerenteSupervisorDTO.class);
		c.createAlias("centroInstalacion", "CI");
		Criterion gerente = Restrictions.eq("supervisor", idSupervisor);
        Criterion supervisor = Restrictions.eq("name", idSupervisor);
        LogicalExpression orExp = Restrictions.or(gerente, supervisor);
        c.add(orExp);
		c.add(Restrictions.eq("stActivo", Boolean.TRUE));
		c.add(Restrictions.eq("CI.stActivo", Boolean.TRUE));
		c.setProjection(Projections.distinct(Projections.property("CI.idCentroInstalacion")));
		return (List<Long>)c.list();

	}




}
