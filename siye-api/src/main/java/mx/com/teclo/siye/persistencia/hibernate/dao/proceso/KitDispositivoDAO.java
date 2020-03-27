package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitDispositivoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitDispositivoVO;

public interface KitDispositivoDAO extends BaseDao<KitDispositivoDTO> {
	/**
	 * Obtiene los dispositivos configurados para un tipo de kit
	 * 
	 * @param idTipoKit
	 * @return
	 */
	List<KitDispositivoVO> getListDispositivos(Long idTipoKit);

}
