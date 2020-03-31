package mx.com.teclo.siye.negocio.service.encuesta;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;


public interface EncuestaService {
	
	/**
	 * @author David Guerra
	 * @param idUsuario Long
	 * @param idOrdenServicio Long
	 * @return EncuestaVO
	 * 
	 * Busca la encuesta por aplicar y regresa la encuesta, segmentos y preguntas
	 */
	UsuarioEncuestaDetalleVO encuestaDetalle(Long idEncuesta, Long idOrdenServicio) throws NotFoundException;

}
