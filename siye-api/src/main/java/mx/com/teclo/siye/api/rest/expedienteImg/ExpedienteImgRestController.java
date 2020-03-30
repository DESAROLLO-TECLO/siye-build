package mx.com.teclo.siye.api.rest.expedienteImg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.negocio.service.expedienteImg.ExpedienteImgService;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

@RestController
@RequestMapping("/expediente")
public class ExpedienteImgRestController {
	
	@Autowired
	private ExpedienteImgService expedienteImg;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	
	@GetMapping(value="/getInfoOS")
	//@PreAuthorize("hasAnyAuthority('GET_STATUS_CARGA_EVIDENCIAS')")
	public ResponseEntity<List<CargaExpedienteImgVO>> getImgExpediente(@RequestParam(value ="tipoBusqueda") String tipoBusqueda,
														   @RequestParam(value ="valor") String valor) throws NotFoundException{	
		List<CargaExpedienteImgVO> respuesta = expedienteImg.getInformacionExpediente(tipoBusqueda, valor);
		return new ResponseEntity<List<CargaExpedienteImgVO>>(respuesta, HttpStatus.OK);
	};
	
	@PostMapping(value="/saveEvidencia")
	//@PreAuthorize("hasAnyAuthority('GUARDAR_ALTA_EVIDENCIA')")
	public ResponseEntity<List<ImagenVO>> saveImgExpediente(@RequestBody List<ImagenVO> expedientes) throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO(); 		
		List<ImagenVO> respuesta = expedienteImg.saveExpediente(expedientes, usuario.getId());
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	};
	
	@GetMapping(value="/getTipoExpediente")
	//@PreAuthorize("hasAnyAuthority('GET_TIPO_EXPEDIENTE')")
	public ResponseEntity<List<TipoExpedienteVO>> getImgExpediente() throws NotFoundException{	
		List<TipoExpedienteVO> respuesta = expedienteImg.getTipoExpediente();
		return new ResponseEntity<List<TipoExpedienteVO>>(respuesta, HttpStatus.OK);
	};
	
	@DeleteMapping(value="/delEvidencia")
	//@PreAuthorize("hasAnyAuthority('DELETE_EVIDENCIA')")
	public ResponseEntity<List<ImagenVO>> deleteEvidencia(@RequestBody List<ImagenVO> expedientes) throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO(); 		
		List<ImagenVO> respuesta = expedienteImg.saveExpediente(expedientes, usuario.getId());
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping(value="/getInfoExpByNivel")
	//@PreAuthorize("hasAnyAuthority('GET_STATUS_CARGA_EVIDENCIAS')")
	public ResponseEntity<List<ImagenVO>> getInfoExpedienteByNivel(@RequestParam(value ="nuOrdenServicio") Long nuOrdenServicio,
														           @RequestParam(value ="cdNivel") String cdNivel,
														           @RequestParam(value="idValor") Long idparamBusqueda) throws NotFoundException{	
		List<ImagenVO> respuesta = expedienteImg.getInformacionExpediente(nuOrdenServicio, idparamBusqueda, cdNivel);
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	};
}
