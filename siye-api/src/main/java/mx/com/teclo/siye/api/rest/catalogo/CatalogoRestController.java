package mx.com.teclo.siye.api.rest.catalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.catalogo.CatalogoService;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.InstaladorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;


@RestController
@RequestMapping("/catalogo")
public class CatalogoRestController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	
	
	@RequestMapping(value="/encuesta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_ENCUESTA')")
	public ResponseEntity<List<StEncuestaVO>>encuesta() throws NotFoundException{
		List<StEncuestaVO> listCatalogoReturn = catalogoService.estatusEncuesta();
		return new ResponseEntity<List<StEncuestaVO>>(listCatalogoReturn, HttpStatus.OK);
		}
	
	@RequestMapping(value="/tipoVehiculo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_VEHICULO')")
	public ResponseEntity<List<TipoVehiculoVO>> tipoVehiculo() throws NotFoundException{
		List<TipoVehiculoVO> listTpVehiculoReturn = catalogoService.tipoVehiculo();
		return new ResponseEntity<List<TipoVehiculoVO>>(listTpVehiculoReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTransportistas", method = RequestMethod.GET)
	public ResponseEntity<List<ConductorVO>> getTransportistas() throws NotFoundException{
		List<ConductorVO> listaConductorVO = catalogoService.getTransportistas();
		return new ResponseEntity<List<ConductorVO>>(listaConductorVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTecnicos", method = RequestMethod.GET)
	public ResponseEntity<List<InstaladorVO>> getTecnicos() throws NotFoundException{
		List<InstaladorVO> listaInstaladorVO = catalogoService.getTecnicos();
		return new ResponseEntity<List<InstaladorVO>>(listaInstaladorVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCatOrdenProceso", method =  RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_CENTRO_INSTA')")
	public ResponseEntity<CatalogosOrdenProcesoVO> getCentroInstalacion()  throws NotFoundException {
		CatalogosOrdenProcesoVO ciVO = catalogoService.getCatalogosOrdenProceso();
		return new ResponseEntity<CatalogosOrdenProcesoVO>(ciVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTpSeguimiento", method =  RequestMethod.GET)
	public ResponseEntity<List<StSeguimientoVO>> getTpSeguimiento()  throws NotFoundException {
		List<StSeguimientoVO> listTpSeguimientoVO = catalogoService.obtenerStSeguimientoByCdTpSeguimiento("ID_TP_INCIDENCIA");
		return new ResponseEntity<List<StSeguimientoVO>>(listTpSeguimientoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPrioridad", method =  RequestMethod.GET)
	public ResponseEntity<List<StSeguimientoVO>> getPrioridad()  throws NotFoundException {
		List<StSeguimientoVO> listStSeguimientoVO = catalogoService.obtenerStSeguimientoByCdTpSeguimiento("ID_PRIORIDAD");
		return new ResponseEntity<List<StSeguimientoVO>>(listStSeguimientoVO, HttpStatus.OK);
	}

}
