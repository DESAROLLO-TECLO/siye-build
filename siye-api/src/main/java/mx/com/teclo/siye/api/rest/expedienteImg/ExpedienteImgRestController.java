package mx.com.teclo.siye.api.rest.expedienteImg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.negocio.service.expedienteImg.ExpedienteImgService;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteImgVO;

@RestController
@RequestMapping("/expediente")
public class ExpedienteImgRestController {
	
	@Autowired
	private ExpedienteImgService expedienteImg;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	
	@PostMapping(value="/saveEvidencia")
	public ResponseEntity<List<ExpedienteImgVO>> saveImgExpediente(@RequestBody List<ExpedienteImgVO> expedientes) throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		
		List<ExpedienteImgVO> respuesta = expedienteImg.saveExpediente(expedientes, usuario.getId());
		return new ResponseEntity<List<ExpedienteImgVO>>(respuesta, HttpStatus.OK);
	}

}
