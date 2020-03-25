package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.springframework.stereotype.Repository;

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

}
