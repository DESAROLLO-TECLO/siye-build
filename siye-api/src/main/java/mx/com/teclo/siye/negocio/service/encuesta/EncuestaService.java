package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaVO;


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
	
	public Boolean nuevoIntento(UsuarioEncuestaVO vo) throws BusinessException;
	
	public List<UsuarioEncuestaVO> consultaEncuestasSatisfaccion(Integer tipoBusqueda, String valor, String password) throws Exception, BusinessException, NotFoundException;
}
