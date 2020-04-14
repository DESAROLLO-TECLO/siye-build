package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ParametrosFolioDTO;

@Repository
public class ParametrosFolioDAOImpl extends BaseDaoHibernate<ParametrosFolioDTO> implements ParametrosFolioDAO{

	@SuppressWarnings("unchecked")
	@Override
	public ParametrosFolioDTO obtenerFolio(String cdFolio) {
		Criteria c = getCurrentSession().createCriteria(ParametrosFolioDTO.class);
		c.add(Restrictions.eq("cdParametroFolio", cdFolio));
		c.add(Restrictions.eq("stActivo", true));
		return (ParametrosFolioDTO) c.uniqueResult();
		
	}
	
}
