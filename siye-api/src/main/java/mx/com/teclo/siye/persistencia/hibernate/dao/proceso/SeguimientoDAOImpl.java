package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.SeguimientoVO;

@Repository
public class SeguimientoDAOImpl extends BaseDaoHibernate<StSeguimientoDTO> implements SeguimientoDAO {

	@Override
	public SeguimientoVO obtenerSeguimiento(Long idSeguimiento) {
		return null;
	}

	@Override
	public StSeguimientoDTO obtenerSeguimientoDos(Long idSeg) {
		Criteria c = getCurrentSession().createCriteria(StSeguimientoDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idStSeguimiento", idSeg));
		return (StSeguimientoDTO)c.uniqueResult();
	}

}
