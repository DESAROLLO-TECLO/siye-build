package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.persistencia.hibernate.dto.configuracion.ConfiguracionDTO;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;


public interface ConfiguracionParamDAO extends BaseDao<ConfiguracionDTO>{

	/**
	 * @Descripción: Método para consultar una lista de configuraciones
	 * y restricciones
	 * @author Jorge Luis
	 * @return List<ConfiguracionDTO>
	 */
	public List<ConfiguracionDTO> configuracion();
	
	/**
	 * @Descripción: Método para consultar un parámetro 
	 * mediante su codigo identificador
	 * @author Jorge Luis
	 * @return ConfiguracionDTO
	 */
	public ConfiguracionDTO configuracion(String cdLlavePConfig);
	
}
