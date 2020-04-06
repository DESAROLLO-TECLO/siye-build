package mx.com.teclo.siye.persistencia.mybatis.dao.proceso;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoDTO;

@Repository
public class ProcesoDAOImpl extends BaseDaoHibernate<ProcesoDTO> implements ProcesoDAO {
	
	@Override
	public ProcesoDTO obtenerProceso(Long idProceso) {
		Criteria c = getCurrentSession().createCriteria(ProcesoDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idProceso", idProceso));
		return (ProcesoDTO) c.uniqueResult();
	}

}
