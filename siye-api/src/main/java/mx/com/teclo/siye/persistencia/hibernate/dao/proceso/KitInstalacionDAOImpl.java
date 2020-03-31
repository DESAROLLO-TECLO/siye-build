package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstalacionVO;

@Repository
public class KitInstalacionDAOImpl extends BaseDaoHibernate<KitInstalacionDTO> implements KitInstalacionDAO {

	@Override
	public KitInstalacionVO obtenerKitInstalacion(Long idKitInstalacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KitInstalacionDTO> obtenerkitInstalacionAll() throws NotFoundException {
		Criteria c = getCurrentSession().createCriteria(KitInstalacionDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		return (List<KitInstalacionDTO>) c.list();
	}

	@Override
	public KitInstalacionDTO kitIns(String cdKitIns) {
		Criteria c = getCurrentSession().createCriteria(KitInstalacionDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("cdKitInstalacion", cdKitIns));
		return (KitInstalacionDTO)c.uniqueResult();
	}

}
