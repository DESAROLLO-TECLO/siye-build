package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConfiguracionDTO;

@Repository
public class ConfiguracionParamDAOImpl extends BaseDaoHibernate<ConfiguracionDTO> implements ConfiguracionParamDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfiguracionDTO> configuracion() {
		Criteria c = getCurrentSession().createCriteria(ConfiguracionDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("idPConfig"));
		return (List<ConfiguracionDTO>) c.list();
	}

	@Override
	public ConfiguracionDTO configuracion(String cdLlavePConfig) {
		Criteria c = getCurrentSession().createCriteria(ConfiguracionDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("cdLlavePConfig", cdLlavePConfig));
		c.addOrder(Order.asc("idPConfig"));
		return (ConfiguracionDTO) c.uniqueResult();
	}

}
