package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;

@Repository
public class PlanDAOImpl extends BaseDaoHibernate<PlanDTO> implements PlanDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanDTO> getPlanAll() throws NotFoundException {
		Criteria c = getCurrentSession().createCriteria(PlanDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		return (List<PlanDTO>) c.list();
	}

	@Override
	public PlanDTO getId(Long idPlan)  {
		Criteria c = getCurrentSession().createCriteria(PlanDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idPlan", idPlan));
		return (PlanDTO)c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanVO> getCatPlan() {
		Criteria c = getCurrentSession().createCriteria(PlanDTO.class, "plan");
		c.add(Restrictions.eq("plan.stActivo", true));
		c.setProjection(
				Projections.projectionList()
						.add(Projections.property("plan.idPlan").as("idPlan"))
						.add(Projections.property("plan.cdPlan").as("cdPlan"))
						.add(Projections.property("plan.nbPlan").as("nbPlan"))
						.add(Projections.property("plan.txPlan").as("txPlan")));
		c.addOrder(Order.asc("plan.idPlan"));		
		c.setResultTransformer(Transformers.aliasToBean(PlanVO.class));		
		return (List<PlanVO>) c.list();
	}

}
