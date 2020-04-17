package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitDispositivoDTO;


public interface DispositivosDAO extends BaseDao<KitDispositivoDTO>{
	
	public List<KitDispositivoDTO> getListDispositivos(Long idTipoKit);
	
	public KitDispositivoDTO getByDispositivo(Long idDisp);

}
