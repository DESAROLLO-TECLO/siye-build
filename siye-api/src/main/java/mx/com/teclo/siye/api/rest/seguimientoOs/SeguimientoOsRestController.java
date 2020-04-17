package mx.com.teclo.siye.api.rest.seguimientoOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.negocio.service.seguimientoOs.SeguimientoOsService;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServicioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesoDetalleVO;
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
			                                                                 @RequestParam(value ="colOmitidas", required=false) List<String> colOmitidas,
			                                                                 @RequestParam(value ="fechaInicio") String fechaInicio,
			                                                                 @RequestParam(value ="fechaFin") String fechaFin) throws NotFoundException{	
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<SeguimientoOrdenServicioVO> respuesta = seguimientoService.getSeguimientoOrdenServicio(usuario.getId(), columnas, colOmitidas, fechaInicio, fechaFin);
		if(respuesta.isEmpty()) {
			throw new NotFoundException("No hay información");
		}
		
		return new ResponseEntity<List<SeguimientoOrdenServicioVO>>(respuesta, HttpStatus.OK);
	};
	

	@GetMapping(value="/getProcesos")
	//@PreAuthorize("hasAnyAuthority('GET_SEGUIMIENTO_OS')")
	public ResponseEntity<OrdenServicioDetalleVO> getDetalleByEtapas(@RequestParam(value ="idOrden") Long nurdenServicio) throws NotFoundException{	
		OrdenServicioDetalleVO respuesta = seguimientoService.getDetalleByEtapas(nurdenServicio);
		if(respuesta==null) {
			throw new NotFoundException("No hay información");
		}
		
		return new ResponseEntity<OrdenServicioDetalleVO>(respuesta, HttpStatus.OK);
	};
	
	
	@GetMapping(value="/getDetalleProceso")
	//@PreAuthorize("hasAnyAuthority('GET_SEGUIMIENTO_OS')")
	public ResponseEntity<ProcesoDetalleVO> getDetalleProceso(@RequestParam(value ="idOrden") Long idOrdenServicio,
															  @RequestParam(value ="idProceso") Long idProceso) throws NotFoundException{	
		ProcesoDetalleVO respuesta = seguimientoService.getDetalleProceso(idOrdenServicio, idProceso);
		if(respuesta==null) {
			throw new NotFoundException("No hay información");
		}
		
		return new ResponseEntity<ProcesoDetalleVO>(respuesta, HttpStatus.OK);
	};
	
	
	

	@GetMapping(value="/getMonIncidencias")
	public ResponseEntity<String> getIncidenciasOs(
		@RequestParam(value ="fechaInicio") String fechaInicio,
		@RequestParam(value ="fechaFin") String fechaFin
	) throws Exception, BusinessException, NotFoundException {
		String mensajeErr = "";
		try {
			String respuesta = null;
			return new ResponseEntity<String>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	};
	
}
