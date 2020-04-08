package mx.com.teclo.siye.api.rest.encuesta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.encuesta.EncuestaService;
import mx.com.teclo.siye.negocio.service.encuesta.RespuestaService;
import mx.com.teclo.siye.negocio.service.usuario.UsuarioService;
import mx.com.teclo.siye.persistencia.vo.encuesta.UserRespuestaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaIntentosVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaVO;





@RestController
@RequestMapping("/encuesta")
public class EncuestaRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EncuestaService encuestaService;
	
	@Autowired
	private RespuestaService respuestaService;
	
		
	@RequestMapping(value="/detalle", method = RequestMethod.GET)
	public ResponseEntity<UsuarioEncuestaDetalleVO> encuestaDetalle (
			@RequestParam(value="idEncuesta") Long idEncuesta, 
			@RequestParam(value="idOrdenServicio") Long idOrdenServicio) throws NotFoundException{
		UsuarioEncuestaDetalleVO encuesta = encuestaService.encuestaDetalle(idEncuesta, idOrdenServicio);
		return new ResponseEntity<UsuarioEncuestaDetalleVO>(encuesta, HttpStatus.OK);	
	}
	
	@RequestMapping(value="/intento", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('SERVICE13_ENC_INTENTO')")
	public ResponseEntity<Boolean> nuevo(@RequestBody UsuarioEncuestaVO vo) throws NotFoundException, BusinessException{
		Boolean b = encuestaService.nuevoIntento(vo);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="/consultaEncuestasSatisfaccion", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioEncuestaVO>> encuestaDetalle (
		@RequestParam(value="tipoBusqueda") Integer tipoBusqueda,
		@RequestParam(value="valor") String valor, 
		@RequestParam(value="password") String password) 
		throws Exception, BusinessException, NotFoundException{
		String passwordEnc = usuarioService.toggleEncryption(password, "encrypt");
		List<UsuarioEncuestaVO> listaUsuarioEncuestaDTO = encuestaService.consultaEncuestasSatisfaccion(tipoBusqueda, valor, passwordEnc);
		return new ResponseEntity<List<UsuarioEncuestaVO>>(listaUsuarioEncuestaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/finalizar", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioEncuestaIntentosVO> finalizarEncuesta(
		@RequestBody UsuarioEncuestaIntentosVO usuarioEncuestaIntentosVO
			) throws NotFoundException {
		UsuarioEncuestaIntentosVO encuestaIntentosVO =null;
		try {
			//1.- Finalizar intento
			encuestaIntentosVO = encuestaService.finalizarIntento(usuarioEncuestaIntentosVO.getIdUsuEncuIntento(), false, false);
			//2.- Calificar intento
			respuestaService.calificarIntentoEncuesta(usuarioEncuestaIntentosVO.getIdUsuEncuIntento(), true);
			//3.- Actuaizar a falso el campo aplicar encuesta y contar total de intentos 
			encuestaService.finalizarEncuesta(encuestaIntentosVO);
			encuestaIntentosVO.setIdUsuEncuIntento(null);
			encuestaIntentosVO.setUsuarioEncuesta(null);
			encuestaIntentosVO.setEsProcesoExitoso(true);
			encuestaService.actualizaOrdenServFhParcial(usuarioEncuestaIntentosVO.getIdUsuEncuIntento());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			encuestaIntentosVO=new UsuarioEncuestaIntentosVO();
			encuestaIntentosVO.setEsProcesoExitoso(false);
		}
		
		if(encuestaIntentosVO != null) {
			// Consultamos los datoas del itento por el ID 
			encuestaIntentosVO = encuestaService.detalle(usuarioEncuestaIntentosVO.getIdUsuEncuIntento(), true);
		}
		return new ResponseEntity<UsuarioEncuestaIntentosVO>(encuestaIntentosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/respuestas", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('SERVICE14_ENC_RESPUESTAS')")
	public ResponseEntity<Boolean> respuestas(@RequestBody List<UserRespuestaVO> l) throws NotFoundException, BusinessException {
		Boolean b = encuestaService.guardarRespuestas(l);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);

	}
	
	@RequestMapping(value="/cargar", method = RequestMethod.GET)
	public ResponseEntity<Boolean> cargarEncuesta (
		@RequestParam(value="idEncuesta") long idEncuesta,
		@RequestParam(value="idOrdenServicio") long idOrdenServicio
		) throws Exception, BusinessException, NotFoundException {
		try {
			encuestaService.cargarEncuesta(idEncuesta, idOrdenServicio);
		}catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto!, porfavor contacte al administrador");
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value="/activarEncuestaSatis", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('SERVICE13_ENC_INTENTO')")
	public ResponseEntity<Boolean> activar(@RequestParam(value="idEncuesta") long idEncuesta,
			@RequestParam(value="idOrdenServicio") long idOrdenServicio,
			@RequestParam(value="nuevoValor") Boolean nuevoValor) throws NotFoundException, BusinessException{
		Boolean b = encuestaService.activarODesactivarSatisfaccion(idEncuesta, idOrdenServicio, nuevoValor);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

	@RequestMapping(value="/finalizarEncuestaS", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioEncuestaIntentosVO> finalizarEncuestaS(
		@RequestBody UsuarioEncuestaIntentosVO usuarioEncuestaIntentosVO
	) throws NotFoundException {
		UsuarioEncuestaIntentosVO encuestaIntentosVO = null;
		try {
			//1.- Finalizar intento
			encuestaIntentosVO = encuestaService.finalizarIntento(usuarioEncuestaIntentosVO.getIdUsuEncuIntento(), false, true);
			//2.- Calificar intento
			respuestaService.calificarIntentoEncuesta(usuarioEncuestaIntentosVO.getIdUsuEncuIntento(), false);
			//3.- Actuaizar a falso el campo aplicar encuesta y contar total de intentos 
			encuestaService.finalizarEncuesta(encuestaIntentosVO);
			encuestaIntentosVO.setIdUsuEncuIntento(null);
			encuestaIntentosVO.setUsuarioEncuesta(null);
			encuestaIntentosVO.setEsProcesoExitoso(true);
			encuestaService.actualizaOrdenServFhParcial(usuarioEncuestaIntentosVO.getIdUsuEncuIntento());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			encuestaIntentosVO=new UsuarioEncuestaIntentosVO();
			encuestaIntentosVO.setEsProcesoExitoso(false);
		}
		
		if(encuestaIntentosVO != null) {
			// Consultamos los datoas del itento por el ID 
			encuestaIntentosVO = encuestaService.detalle(usuarioEncuestaIntentosVO.getIdUsuEncuIntento(), true);
		}
		return new ResponseEntity<UsuarioEncuestaIntentosVO>(encuestaIntentosVO, HttpStatus.OK);
	}
}
	