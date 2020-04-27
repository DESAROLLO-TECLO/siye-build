package mx.com.teclo.siye.persistencia.hibernate.dao.async;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.TipoLayoutDTO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigLayoutVO;

/**
 * Recupera la configuraci&oacute; del layout o estructura que se espera recibir
 * en el archivo lote
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface TipoLayoutDAO extends BaseDao<TipoLayoutDTO> {
	ConfigLayoutVO getLayoutVigente();
	ConfigLayoutVO getTipoLayoutById(Long idTipoLayout);
	
}
