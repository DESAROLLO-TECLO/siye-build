package mx.com.teclo.siye.api.rest.seguimientoOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.negocio.service.seguimientoOs.SeguimientoOsService;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

@RestController
@RequestMapping("/monitoreo")
public class SeguimientoOsRestController {
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private SeguimientoOsService seguimientoService;
	
	
	
	@GetMapping(value="/getSeguimientoOS")
	//@PreAuthorize("hasAnyAuthority('GET_SEGUIMIENTO_OS')")
	public ResponseEntity<List<SeguimientoOrdenServicioVO>> getSeguimientoOs(@RequestParam(value ="columnas") List<String> columnas,
														               @RequestParam(value ="fechaInicio") String fechaInicio,
														               @RequestParam(value ="fechaFin") String fechaFin) throws NotFoundException{	
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<SeguimientoOrdenServicioVO> respuesta = seguimientoService.getSeguimientoOrdenServicio(usuario.getId(), columnas, fechaInicio, fechaFin);
		if(respuesta.isEmpty()) {
			throw new NotFoundException("No hay información");
		}
		
		return new ResponseEntity<List<SeguimientoOrdenServicioVO>>(respuesta, HttpStatus.OK);
	};

}
