package mx.com.teclo.siye.persistencia.hibernate.dao.tipoExpediente;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.tipoexpediente.TipoExpedienteDTO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

public interface TipoExpedienteDAO extends BaseDao<TipoExpedienteDTO> {
	
	
	/*@author Maverick
	 * @Return List<tipoExpedienteVO>
	 * metodo para consultar tipos de expedientes */
	public List<TipoExpedienteVO> getTipoExpedientes();

}
