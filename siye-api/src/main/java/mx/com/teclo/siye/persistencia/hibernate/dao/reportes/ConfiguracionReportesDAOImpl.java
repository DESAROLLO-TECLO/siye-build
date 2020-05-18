package mx.com.teclo.siye.persistencia.hibernate.dao.reportes;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.reportes.ConfiguracionReportesDTO;

@Repository
public class ConfiguracionReportesDAOImpl extends BaseDaoHibernate<ConfiguracionReportesDTO> implements ConfiguracionReportesDAO {

	@Override
	public ConfiguracionReportesDTO getConfigParametrosCdLlave(String cdLlaveConfig) {
		Criteria criteria = getCurrentSession().createCriteria(ConfiguracionReportesDTO.class);
		criteria.add(Restrictions.eq("cdLlaveConfig", cdLlaveConfig));

		return (ConfiguracionReportesDTO) criteria.uniqueResult();
	}
}
