package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;

@Repository
public class PlanDAOImpl extends BaseDaoHibernate<PlanDTO> implements PlanDAO {

	@Override
	public PlanVO obtenerKitInstalacion(Long idPlan) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanDTO> getPlanAll() throws NotFoundException {
		Criteria c = getCurrentSession().createCriteria(PlanDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		return (List<PlanDTO>) c.list();
	}

}
