package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

@Repository
public class CentroInstalacionDAOImpl extends BaseDaoHibernate<CentroInstalacionDTO> implements CentroInstalacionDAO {

	@Override
	public CentroInstalacionVO obtenerCentroInstalacion(Long idCentroInstalacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CentroInstalacionDTO> obtenerCentroInstalacion() {
		Criteria c = getCurrentSession().createCriteria(CentroInstalacionDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		return (List<CentroInstalacionDTO>) c.list();
	}

	@Override
	public CentroInstalacionDTO centroIns(Long centroIn) {
		Criteria c = getCurrentSession().createCriteria(CentroInstalacionDTO.class);
		c.add(Restrictions.eq("idCentroInstalacion", centroIn));
		return (CentroInstalacionDTO)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CentroInstalacionDTO> obtenerCentroInstalacionVisible(Long value) {
		Criteria c = getCurrentSession().createCriteria(CentroInstalacionDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stCentroInstalacion", value));
		return (List<CentroInstalacionDTO>) c.list();
	}

}
