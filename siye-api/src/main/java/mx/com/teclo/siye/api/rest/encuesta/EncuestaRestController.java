package mx.com.teclo.siye.api.rest.encuesta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.encuesta.EncuestaService;
import mx.com.teclo.siye.negocio.service.usuario.UsuarioService;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaVO;



@RestController
@RequestMapping("/encuesta")
public class EncuestaRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EncuestaService encuestaService;
		
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
	
	@RequestMapping(value="/consultaEncuestasSatisfaccion", method = RequestMethod.POST)
	public ResponseEntity<List<UsuarioEncuestaVO>> encuestaDetalle (
		@RequestParam(value="tipoBusqueda") Integer tipoBusqueda,
		@RequestParam(value="valor") String valor, 
		@RequestParam(value="password") String password) 
		throws Exception, BusinessException, NotFoundException{
		String passwordEnc = usuarioService.toggleEncryption(password, "encrypt");
		List<UsuarioEncuestaVO> listaUsuarioEncuestaDTO = encuestaService.consultaEncuestasSatisfaccion(tipoBusqueda, valor, passwordEnc);
		return new ResponseEntity<List<UsuarioEncuestaVO>>(listaUsuarioEncuestaDTO, HttpStatus.OK);
	}
	

	
}
	