package mx.com.teclo.siye.api.rest.seguimientoOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.negocio.service.monitoreoInicidencia.MonitoreoIncidenciaService;
import mx.com.teclo.siye.negocio.service.seguimientoOs.SeguimientoOsService;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenDetailVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.DetalleIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.PreguntasDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesoDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesosOrdenServicioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

@RestController
@RequestMapping("/monitoreo")
public class SeguimientoOsRestController {
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private SeguimientoOsService seguimientoService;
	
	@Autowired
	private MonitoreoIncidenciaService monitoreoIncidenciaService;
	
	
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
	public ResponseEntity<ProcesosOrdenServicioDetalleVO> getDetalleByEtapas(@RequestParam(value ="idOrden") Long nurdenServicio) throws NotFoundException{	
		ProcesosOrdenServicioDetalleVO respuesta = seguimientoService.getDetalleByEtapas(nurdenServicio);
		if(respuesta==null) {
			throw new NotFoundException("No hay información");
		}
		
		return new ResponseEntity<ProcesosOrdenServicioDetalleVO>(respuesta, HttpStatus.OK);
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
	public ResponseEntity<List<MonitoreoIncidenciasVO>> getIncidenciasOs(
		@RequestParam(value ="fechaInicio") String fechaInicio,
		@RequestParam(value ="fechaFin") String fechaFin,
		@RequestParam(value ="tipoBusqueda") Integer tipoBusqueda,
		@RequestParam(value ="valor") String valor,
		@RequestParam(value ="opcion") Integer opcion,
		@RequestParam(value ="idCentroInstalacion") Integer idCentroInstalacion
	) throws Exception, BusinessException, NotFoundException {
		String mensajeErr = "";
		try {
			UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
			List<MonitoreoIncidenciasVO> respuesta = monitoreoIncidenciaService.getMonIncidencias(usuario.getId(), fechaInicio, fechaFin, tipoBusqueda, valor, opcion, idCentroInstalacion, mensajeErr);
			return new ResponseEntity<List<MonitoreoIncidenciasVO>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	};
	
	@RequestMapping(value = "/getDetalleIncidenciasOS", method = RequestMethod.GET)
	public ResponseEntity<OrdenIncidenciaDetalleVO> getIncidenciasByIdOrden(@RequestParam(value="idOrden", required=true) Long idOrden,@RequestParam(value="idPlan", required=true) Long idPlan) throws BusinessException, Exception{
		OrdenIncidenciaDetalleVO incidenciaDTO = monitoreoIncidenciaService.getDetalleIncidenciasOS(idOrden, idPlan);
		return new ResponseEntity<OrdenIncidenciaDetalleVO>(incidenciaDTO, HttpStatus.OK);
	}
	

	
	@GetMapping(value="/getDetallePregunta")
	//@PreAuthorize("hasAnyAuthority('GET_SEGUIMIENTO_OS')")
	public ResponseEntity<List<PreguntasDetalleVO>> getDetallePregunta(@RequestParam(value ="idOrden") Long idOrdenServicio,
															         @RequestParam(value ="idEncuesta") Long idEncuesta) throws NotFoundException{	
		List<PreguntasDetalleVO> respuesta = seguimientoService.getDetallePregunta(idOrdenServicio, idEncuesta);
		if(respuesta.isEmpty()) {
			throw new NotFoundException("No hay preguntas con respuesta");
		}
		return new ResponseEntity<List<PreguntasDetalleVO>>(respuesta, HttpStatus.OK);
	};

	
	@RequestMapping(value = "/getExpedienteIncidencia", method = RequestMethod.GET)
	public ResponseEntity<List<ImagenVO>> getExpedienteIncidencia(@RequestParam(value="idIncidencia", required=true) Long idIncidencia) throws BusinessException, Exception{
	List<ImagenVO> listImagenes = monitoreoIncidenciaService.getExpedienteByIdIncidencia(idIncidencia);
		return new ResponseEntity<List<ImagenVO>>(listImagenes, HttpStatus.OK);
	};
	
	@GetMapping(value="/getImgSeguimiento")
	//@PreAuthorize("hasAnyAuthority('GET_SEGUIMIENTO_OS')")
	public ResponseEntity<List<ImagenVO>> getImagenSeguimiento(@RequestParam(value ="idOrdenServicio") Long idOrdenServicio,
															   @RequestParam(value ="valor") Long valor,
															   @RequestParam(value ="nivel") String nivel,
															   @RequestParam(value ="clase") String clase) throws NotFoundException{	
		List<ImagenVO> respuesta = seguimientoService.getDetalleImagenByNivel(idOrdenServicio, valor, nivel, clase);
		if(respuesta.isEmpty()) {
			throw new NotFoundException("No hay imagenes de "+clase);
		}
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	};
	
	@GetMapping(value="/getDetalleIncidencia")
	//@PreAuthorize("hasAnyAuthority('GET_SEGUIMIENTO_OS')")
	public ResponseEntity<List<DetalleIncidenciaVO>> getImagenSeguimiento(@RequestParam(value ="idOrdenServicio") Long idOrdenServicio) throws NotFoundException{	
		
		List<DetalleIncidenciaVO> respuesta = seguimientoService.getDetalleSeguimientoIncidencia(idOrdenServicio);
		if(respuesta.isEmpty()) {
			throw new NotFoundException("Ocurrio un error al consultar el detalle de la incidencia");
		}
		return new ResponseEntity<List<DetalleIncidenciaVO>>(respuesta, HttpStatus.OK);
	};

	
	@RequestMapping(value = "/getIncidenciasByTipobusqueda", method = RequestMethod.GET)
	public ResponseEntity<List<IncidenDetailVO>> listIncidenciaByIdOrden(@RequestParam(value="idCentroInstalacion", required=true) Long idCentroInstalacion,
			@RequestParam(value="tipoBusqueda", required=true) Integer tipoBusqueda,
			@RequestParam(value="valor", required=true) String valor,
			@RequestParam(value="fechaInicio", required=true) String fechaInicio,
			@RequestParam(value="fechaFin", required=true) String fechaFin
			) throws BusinessException, Exception{
		List<IncidenDetailVO> listImagenes = monitoreoIncidenciaService.getListIncidenciaByIdOrden(idCentroInstalacion, tipoBusqueda, valor, fechaInicio, fechaFin);
		return new ResponseEntity<List<IncidenDetailVO>>(listImagenes, HttpStatus.OK);
	}


	
}
