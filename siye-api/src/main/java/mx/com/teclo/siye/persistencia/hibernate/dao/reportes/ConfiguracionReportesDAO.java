package mx.com.teclo.siye.persistencia.hibernate.dao.reportes;

import java.util.List;
import java.util.Map;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.reportes.ConfiguracionReportesDTO;

public interface ConfiguracionReportesDAO extends BaseDao<ConfiguracionReportesDTO>{

	ConfiguracionReportesDTO getConfigParametrosCdLlave(String cdLlaveConfig);
}
