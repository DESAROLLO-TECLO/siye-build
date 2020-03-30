package mx.com.teclo.siye.api.rest.encuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.encuesta.EncuestaService;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;

@RestController
@RequestMapping("/encuesta")
public class EncuestaRestController {
	
	@Autowired
	private EncuestaService encuestaService;
	
	
	@RequestMapping(value="/detalle", method = RequestMethod.GET)
	public ResponseEntity<UsuarioEncuestaDetalleVO> encuestaDetalle (
			@RequestParam(value="idEncuesta") Long idEncuesta, 
			@RequestParam(value="idOrdenServicio") Long idOrdenServicio) throws NotFoundException{
		UsuarioEncuestaDetalleVO encuesta = encuestaService.encuestaDetalle(idEncuesta, idOrdenServicio);
		return new ResponseEntity<UsuarioEncuestaDetalleVO>(encuesta, HttpStatus.OK);
		
		
	}
	


}
	