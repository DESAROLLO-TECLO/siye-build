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
import mx.com.teclo.siye.persistencia.vo.catalogo.CatTipoFechasVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.OpcionCausaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TblCatalogosVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoKitVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.ordenServicio.CatalogosAltaOrdenServicioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ConsecionarioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioCatalogoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;
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
	
	@RequestMapping(value="/getTransportistasSinVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<ConductorVO>> getTransportistasSinVehiculo() throws NotFoundException{
		List<ConductorVO> listaConductorVO = catalogoService.getTransportistasSinVehiculo();
		return new ResponseEntity<List<ConductorVO>>(listaConductorVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTecnicos", method = RequestMethod.GET)
	public ResponseEntity<List<PersonaVO>> getTecnicos(
		@RequestParam("idTipoPersona") Long idTipoPersona
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
	public ResponseEntity<List<CentroInstalacionVO>> getModAten() throws NotFoundException {
		List<CentroInstalacionVO> listCentroInstalacionVO = new ArrayList<>(); 
		listCentroInstalacionVO.add(catalogoService.getModAten());
		return new ResponseEntity<List<CentroInstalacionVO>>(listCentroInstalacionVO, HttpStatus.OK);
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
	
	@RequestMapping(value="/buscarPersona", method = RequestMethod.GET)
	public ResponseEntity<PersonaGenericaVO> buscarPersona(@RequestParam("cdPersona") String cdPersona,@RequestParam("idTipoPersona") Long idTipoPersona) throws NotFoundException {
		PersonaGenericaVO personaVO = catalogoService.buscarPersona(cdPersona,idTipoPersona);
		if(personaVO == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		return new ResponseEntity<PersonaGenericaVO>(personaVO, HttpStatus.OK);
	}


	@RequestMapping(value = "/getCatTipoFechas", method =  RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('GET_CAT_RANGO_FECHAS')")
	public ResponseEntity<List<CatTipoFechasVO>> getCatTipoFechas()  throws NotFoundException {
		List<CatTipoFechasVO> fechas = catalogoService.getCatTipoFechas();
		if(fechas.isEmpty()) {
			throw new NotFoundException("No hay Rango de fechas Asignado");
		}
		return new ResponseEntity<List<CatTipoFechasVO>>(fechas, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/getNuMaxImgIncidencia", method =  RequestMethod.GET)
	public ResponseEntity<List<ConfiguracionVO>> getNuMaxImgIncidencia()  throws NotFoundException {
		List<ConfiguracionVO> listConfiguracionVO = catalogoService.configuracionIncidencia("TIE051D_NU_MAX_IMAGENES", "TIE051D_IMG_REQ");
		return new ResponseEntity<List<ConfiguracionVO>>(listConfiguracionVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTransportistasVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<ConductorVO>> getTransportistasVehiculo(@RequestParam("idVehiculo") Long idVehiculo)
		throws NotFoundException{
		List<ConductorVO> listaConductorVO = catalogoService.getTransportistasVehiculo(idVehiculo);
		return new ResponseEntity<List<ConductorVO>>(listaConductorVO, HttpStatus.OK);

     }
	
	@RequestMapping(value = "/buscaCatalogosActivos", method = RequestMethod.GET)
	public ResponseEntity<List<TblCatalogosVO>> buscaCatalogosActivos()  throws NotFoundException {
		List<TblCatalogosVO> listTblCatalogosVO = catalogoService.getTblCatalogos();
		return new ResponseEntity<List<TblCatalogosVO>>(listTblCatalogosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaCentroIntalacion", method = RequestMethod.GET)
	public ResponseEntity<List<CentroInstalacionVO>> consultaCentroIntalacion(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<CentroInstalacionVO> listCentroInstalacionVO = catalogoService.consultaCentroIntalacion(cdTipoBusqueda, valor);
		return new ResponseEntity<List<CentroInstalacionVO>>(listCentroInstalacionVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultaPersona", method = RequestMethod.GET)
	public ResponseEntity<List<PersonaVO>> consultalistPersona(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<PersonaVO> listPersonaVO = catalogoService.consultalistPersona(cdTipoBusqueda, valor);
		return new ResponseEntity<List<PersonaVO>>(listPersonaVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultaConductor", method = RequestMethod.GET)
	public ResponseEntity<List<ConductorVO>> consultaConductor(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<ConductorVO> listConductorVO = catalogoService.consultaConductor(cdTipoBusqueda, valor);
		return new ResponseEntity<List<ConductorVO>>(listConductorVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaProveedor", method = RequestMethod.GET)
	public ResponseEntity<List<ProveedorVO>> consultaProveedor(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<ProveedorVO> listProveedorVO = catalogoService.consultaProveedor(cdTipoBusqueda, valor);
		return new ResponseEntity<List<ProveedorVO>>(listProveedorVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultaConcesiones", method = RequestMethod.GET)
	public ResponseEntity<List<ConsecionarioVO>> consultaConcesiones(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<ConsecionarioVO> listConsecionarioVO = catalogoService.consultaConcesiones(cdTipoBusqueda, valor);
		return new ResponseEntity<List<ConsecionarioVO>>(listConsecionarioVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultaTipoVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<TipoVehiculoVO>> consultaTipoVehiculo(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<TipoVehiculoVO> listTipoVehiculoVO = catalogoService.consultaTipoVehiculo(cdTipoBusqueda, valor);
		return new ResponseEntity<List<TipoVehiculoVO>>(listTipoVehiculoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultaVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoVO>> consultaVehiculo(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") Long valor) throws NotFoundException {
		List<VehiculoVO> listVehiculoVO = catalogoService.consultaVehiculo(cdTipoBusqueda, valor);
		return new ResponseEntity<List<VehiculoVO>>(listVehiculoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaStSegumientoByTipo", method = RequestMethod.GET)
	public ResponseEntity<List<StSeguimientoVO>> consultaStSegumientoByTipo(@RequestParam(value="tipoSeguimiento") Long tipo) throws NotFoundException {
		List<StSeguimientoVO> stSeguimientoVO = catalogoService.consultaStSeguimientoByTipo(tipo);
		return new ResponseEntity<List<StSeguimientoVO>>(stSeguimientoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaTipoKit", method = RequestMethod.GET)
	public ResponseEntity<List<TipoKitVO>> consultaTipoKit() throws NotFoundException {
		List<TipoKitVO> TipoKitVO = catalogoService.consultaTipoKit();
		return new ResponseEntity<List<TipoKitVO>>(TipoKitVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaPlan", method = RequestMethod.GET)
	public ResponseEntity<List<PlanVO>> consultaPlan() throws NotFoundException {
		List<PlanVO> planVO = catalogoService.consultaPlan();
		return new ResponseEntity<List<PlanVO>>(planVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/alta/ordenServicio", method =  RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_CENTRO_INSTA')")
	public ResponseEntity<CatalogosAltaOrdenServicioVO> getCatalogosAltaOrdenServicio()  throws NotFoundException {
		CatalogosAltaOrdenServicioVO ciVO = catalogoService.getCatalogosAltaOrdenServicio();
		return new ResponseEntity<CatalogosAltaOrdenServicioVO>(ciVO, HttpStatus.OK);
	}
}
