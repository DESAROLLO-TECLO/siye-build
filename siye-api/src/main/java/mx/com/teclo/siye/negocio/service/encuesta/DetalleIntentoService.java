package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.List;


import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTO;
import mx.com.teclo.siye.persistencia.vo.encuesta.PreguntaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaRespuestaVO;


public interface DetalleIntentoService {

	
	/**
	 * @Descripción: Método para filtarr las prespuestad del usuario
	 * @author davidGuerra
	 * @return List<UsuarioEncuestaRespuestaVO>
	 */
	public List<UsuarioEncuestaRespuestaVO> fitroUsuarioRespuesta(List<UsuaroEncuestaRespuestaDTO> uerlistDTO);

	
	/**
	 * Metodo para ordenar los elementos del array 
	 * mediante del numero de orden de la pregunta
	 * @author davidGuerra
	 * @return List<PreguntaVO>
	 */
	public List<UsuarioEncuestaRespuestaVO> orderListRespuesta(List<UsuarioEncuestaRespuestaVO> uerListVO);
	
	/**
	 * Método para ordenar las preguntas que pertenecen auna sección
	 * @author davidGuerra
	 * @return List<PreguntaVO>
	 */
	public List<PreguntaVO> orderList(List<PreguntaVO> pListVO);

}
