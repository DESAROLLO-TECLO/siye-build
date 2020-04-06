package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.List;

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaIntentosVO;



public interface RespuestaService {
	
	/**
	 * Califica las respuestas de cada intento 
	 * 
	 * @author Fjmb
	 * @param idUsuario Long
	 * @return UsuarioEncuestaIntentosVO
	 */
	UsuarioEncuestaIntentosVO calificarIntentoEncuesta(Long idIntentoEncuesta, Boolean mayorCalifIntento);
	
	Integer contarSecciones ( Integer idEncuesta);
	
	/**
	 * Determian el resultado mas alto
	 * 
	 * @author Fjmb
	 * @param idUsuario Long
	 * @return UsuarioEncuestaIntentosVO
	 */
	public Boolean determinarCalifAlta (List<UsuarioEncuestaIntentosDTO> ueiListDTO, UsuarioEncuestaIntentosDTO current);
}
