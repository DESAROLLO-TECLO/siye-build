package mx.com.teclo.siye.persistencia.hibernate.dao.configuracion;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;

@Repository
public class ConfiguracionOSDAOImpl extends BaseDaoHibernate<ConfiguracionOSDTO> implements ConfiguracionOSDAO {

	@Override
	public ConfiguracionOSDTO getConfigByCdConfig(String cdConfig) {
		Criteria criteria = getCurrentSession().createCriteria(ConfiguracionOSDTO.class);
		criteria.add(Restrictions.eq("cdLlaveConfig", cdConfig));
		criteria.add(Restrictions.eq("stActivo", true));
		return (ConfiguracionOSDTO)criteria.uniqueResult();
	}

}
