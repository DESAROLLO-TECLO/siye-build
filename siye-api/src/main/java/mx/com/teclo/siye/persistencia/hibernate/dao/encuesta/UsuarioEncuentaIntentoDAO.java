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
    
	/**
	 * 
	 * @author David Guerra
	 * @param idEncuesta
	 * @return
	 */
	public UsuarioEncuestaIntentosDTO buscaUsuEncuIntento(Long idUsuEncuIntento);
	
	/**
	 * @author David Guerra
	 * @param idUsuEncuIntento
	 * @return List<UsuarioEncuestaIntentosDTO>
	 */
	public List<UsuarioEncuestaIntentosDTO> usuarioEncuesta(Long idUsuarioEncuesta);
	
	UsuarioEncuestaIntentosDTO getIntentoById(Long idIntentoEncuesta);
	/**Método para obtener los registros del usuario de la misma encuesta
	 * estos para determinar la calificación mas alta obtenida
	 * @author jorgeluis
	 * @return List<UsuarioEncuestaIntentosDTO>
	 */
	public List<UsuarioEncuestaIntentosDTO> intentoMismaEncuesta(Long idUsuarioEncuesta);

	
}
