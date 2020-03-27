package mx.com.teclo.siye.persistencia.mybatis.dao.configuracion;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConfiguracionBDMyBatisDAO {

	String PARAMETROS_TODOS = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TIE019P_CONFIGURACION WHERE ST_ACTIVO = 1";
	String PARAMETROS_PARAMETRO_POR_IDPCONFIG = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TIE019P_CONFIGURACION WHERE ST_ACTIVO = 1 AND ID_P_CONFIG = #{valor}";
	String PARAMETROS_PARAMETRO_POR_CDCONFIG = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TIE019P_CONFIGURACION WHERE ST_ACTIVO = 1 AND CD_LLAVE_P_CONFIG = #{valor}";
		
	@Select(PARAMETROS_TODOS)
	public List<Map<String, String>> getParametrosTodos();
	
	@Select(PARAMETROS_PARAMETRO_POR_IDPCONFIG)
	public List<Map<String, String>> getParametrosPorNbConfig(
			@Param("valor") String valor);
	
	@Select(PARAMETROS_PARAMETRO_POR_CDCONFIG)
	public List<Map<String, String>> getParametrosPorCdConfig(
			@Param("valor") String valor);
}
