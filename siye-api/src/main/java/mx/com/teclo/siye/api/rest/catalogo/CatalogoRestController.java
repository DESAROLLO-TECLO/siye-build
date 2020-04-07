package mx.com.teclo.siye.api.rest.catalogo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.catalogo.CatalogoService;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.OpcionCausaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.OpcionCausaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioCatalogoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;


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
	public ResponseEntity<List<PersonaVO>> getTecnicos(
		@RequestParam("idTipoPersona") Integer idTipoPersona
	) throws NotFoundException{
		List<PersonaVO> listaInstaladorVO = catalogoService.getTecnicos(idTipoPersona);
		return new ResponseEntity<List<PersonaVO>>(listaInstaladorVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCatOrdenProceso", method =  RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_CENTRO_INSTA')")
	public ResponseEntity<CatalogosOrdenProcesoVO> getCentroInstalacion()  throws NotFoundException {
		CatalogosOrdenProcesoVO ciVO = catalogoService.getCatalogosOrdenProceso();
		return new ResponseEntity<CatalogosOrdenProcesoVO>(ciVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTpIncidencia", method =  RequestMethod.GET)
	public ResponseEntity<List<StSeguimientoVO>> getTpIncidencia()  throws NotFoundException {
		List<StSeguimientoVO> listTpSeguimientoVO = catalogoService.obtenerStSeguimientoByCdTpSeguimiento("ID_TP_INCIDENCIA");
		return new ResponseEntity<List<StSeguimientoVO>>(listTpSeguimientoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPrioridad", method =  RequestMethod.GET)
	public ResponseEntity<List<StSeguimientoVO>> getPrioridad()  throws NotFoundException {
		List<StSeguimientoVO> listStSeguimientoVO = catalogoService.obtenerStSeguimientoByCdTpSeguimiento("ID_PRIORIDAD");
		return new ResponseEntity<List<StSeguimientoVO>>(listStSeguimientoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getOrdenServicio", method = RequestMethod.GET)
	public ResponseEntity<List<OrdenServicioCatalogoVO>> getOrdenServicio() throws NotFoundException {
		List<OrdenServicioCatalogoVO> listOrdenServicioCatalogoVO = catalogoService.getOrdenServicio();
		return new ResponseEntity<List<OrdenServicioCatalogoVO>>(listOrdenServicioCatalogoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/parametroCd", method = RequestMethod.GET)
	public ResponseEntity<ConfiguracionVO> parametro(@RequestParam("cdParametro") String cdParametro) throws NotFoundException {
		ConfiguracionVO listToReturn = catalogoService.configuracion(cdParametro);
		if(listToReturn == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		return new ResponseEntity<ConfiguracionVO>(listToReturn, HttpStatus.OK);
	}
	

	@RequestMapping(value="/getModAten", method = RequestMethod.GET)
	public ResponseEntity<CentroInstalacionVO> getModAten() throws NotFoundException {
		CentroInstalacionVO centroInstalacionVO = catalogoService.getModAten();
		return new ResponseEntity<CentroInstalacionVO>(centroInstalacionVO, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/catCuasas", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<List<OpcionCausaVO>> buscarCatalogo(@RequestParam("idOpcion") Long idOpcion) {
		List<OpcionCausaDTO> opcionCausa = new ArrayList<OpcionCausaDTO>();
		opcionCausa = catalogoService.getCatalogoCausas(idOpcion);
		List<OpcionCausaVO> causas = ResponseConverter.converterLista(new ArrayList<>(), opcionCausa,
				OpcionCausaVO.class);
		return new ResponseEntity<List<OpcionCausaVO>>(causas, HttpStatus.OK);
	}

}
