package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.encuesta.UserRespuestaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaIntentosVO;
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
	
	/**
	 * Metodo para actualizar como finalizado el intento de contestar un cuestionario o evaluación
	 * 
	 * @param idUsuEncuIntento
	 * @return
	 * @throws NotFoundException
	 */
	public UsuarioEncuestaIntentosVO finalizarIntento(Long idUsuEncuIntento, Boolean b, Boolean finEnc) throws BusinessException;
	
	/**
	 * Finaliza una encuesta actualizando su estatus y la fecha de termino
	 * 
	 * @author David Guerra
	 * @param idEncuesta Long
	 * @param idUsuario Long
	 */
	public void finalizarEncuesta(UsuarioEncuestaIntentosVO encuestaIntentosVO);
	
	/**
	 * Consulta el detalle del intento en base de datos mediante el ID
	 * 
	 * @author jorgeluis
	 * @return UsuarioEncuestaIntentosVO
	 */
	public UsuarioEncuestaIntentosVO detalle(Long idUsuEncuIntento, Boolean finalizar) throws NotFoundException;
	
	/**
	 * @Descripción: Se guardan las repuestas marcadas por el usuario
	 * @author Mannuel Dirsio
	 * @param List<UserRespuestaVO>
	 * @return Boolean 
	 */
	public Boolean guardarRespuestas(List<UserRespuestaVO> l)throws BusinessException;
	
	public void cargarEncuesta(Long idEncuesta, Long idOrdenServicio) throws Exception, BusinessException, NotFoundException;
	
	public Boolean activarODesactivarSatisfaccion(Long idEncuesta, Long idOrdenServicio, Boolean nuevoValor);

	public void actualizaOrdenServFhParcial(Long idUsuEncuIntento);
	
	public void actualizaSuperTransInst(Long idTransVehiculo, Integer idInstalador,Long idUsuEncuIntento);
	
	/**
	 * @Descripcion: Se actualiza la tabla intermedia orden de servicio y encuesta
	 * @param idOrden
	 * @param idEncuesta
	 * @param inProceso
	 * @param idUser
	 */
	public Boolean updateEncuestaEnProceso(Long idOrden,Long idEncuesta,boolean inProceso,Long idUser);
}
