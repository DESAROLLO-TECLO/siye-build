package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;


public interface UsuarioEncuentaIntentoDAO extends BaseDao<UsuarioEncuestaIntentosDTO>{
	
	/**
	 * @author David Guerra
	 * @param idEncuesta Long
	 * @param idFolioOrden
	 * @return UsuarioEncuestaIntentosDTO
	 */
	
    UsuarioEncuestaIntentosDTO getEncuestaByUsuario(Long idEncuesta, Long idFolioOrden);

	
}
