package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;

@Repository
public class StSeguimientoDAOImpl extends BaseDaoHibernate<StSeguimientoDTO> implements StSeguimientoDAO {

	@Override
	public StSeguimientoVO obtenerSeguimiento(Long idSeguimiento) {
		return null;
	}
	
	

	@Override
	public StSeguimientoDTO obtenerSeguimientoDos(Long idSeg) {
		Criteria c = getCurrentSession().createCriteria(StSeguimientoDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idStSeguimiento", idSeg));
		return (StSeguimientoDTO)c.uniqueResult();
	}
	
	@Override
	public StSeguimientoDTO obtenerStSeguimientoByCodigo(String cdStSeguimiento) {
		Criteria c = getCurrentSession().createCriteria(StSeguimientoDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("cdStSeguimiento", cdStSeguimiento));
		return (StSeguimientoDTO)c.uniqueResult();
	}
	

	@Override
	public List<StSeguimientoDTO> obtenerStSeguimientoByCdTpSeguimiento(String cdTpSeguimiento) {
		Criteria c = getCurrentSession().createCriteria(StSeguimientoDTO.class);
		c.createAlias("tipoSeguimiento", "tipoSeguimiento");
		c.add(Restrictions.eq("tipoSeguimiento.cdTpSeguimiento", cdTpSeguimiento));
		c.add(Restrictions.eq("stActivo", true));
		return (List<StSeguimientoDTO>)c.list();
	}


}
