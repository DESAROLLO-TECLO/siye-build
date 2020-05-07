package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.IeStUsuEncuIntenDTO;

@Repository
public class IeStUsuEncuIntenDAOImpl extends BaseDaoHibernate<IeStUsuEncuIntenDTO>
		implements
			IeStUsuEncuIntenDAO {
	
	@Override
	public IeStUsuEncuIntenDTO getInfoByUsuEncInt(Long usuEncInte) {
		Criteria c = getCurrentSession().createCriteria(IeStUsuEncuIntenDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.createAlias("idUsuEncuIntento", "idUsuEncuIntento");
		c.add(Restrictions.eq("idUsuEncuIntento.idUsuEncuIntento", usuEncInte));
		return (IeStUsuEncuIntenDTO) c.uniqueResult();
	}



}
