package mx.com.teclo.siye.persistencia.hibernate.dao.configuracion;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;

public interface ConfiguracionOSDAO extends BaseDao<ConfiguracionOSDTO>{
	public ConfiguracionOSDTO getConfigByCdConfig(String cdConfig);
}
