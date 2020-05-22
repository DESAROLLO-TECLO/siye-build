package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.ordenServicio.OrdenServicioService;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConductorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConfiguracionParamDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.OpcionCausasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ParametrosFolioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.PersonaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.PersonaTipoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ProveedorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TblCatalogosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoFechasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoKitDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoVehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.VehiculoConductorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.ConcesionariaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.KitInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.VehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConfiguracionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.OpcionCausaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ParametrosFolioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaTipoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TblCatalogosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoFechasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.VehiculoConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ConsecionarioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.GerenteSupervisorDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.CatTipoFechasVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConcesionariaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaCompVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaTipoVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TblCatalogosVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoKitVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.VehiculoConductorVO;
import mx.com.teclo.siye.persistencia.vo.ordenServicio.CatalogosAltaOrdenServicioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ConsecionarioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioCatalogoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;
import mx.com.teclo.siye.util.comun.RutinasTiempoImpl;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class CatalogoServiceImpl implements CatalogoService{
	
	@Autowired
	private StEncuestaDAO stEncuestaDAO;
	
	@Autowired
	private TipoVehiculoDAO tipoVehiculoDAO; 
	
	@Autowired
	private ConductorDAO conductorDAO;
	
	@Autowired
	private PersonaTipoDAO personaTipoDAO;
	
	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;
	
	@Autowired
	private KitInstalacionDAO kitInstalacionDAO;
	
	@Autowired
	private PlanDAO planDAO;
	
	@Autowired
	private TipoKitDAO tipoKitDAO;
	
	@Autowired
	private ProveedorDAO proveedorDAO;

	@Autowired
	private StSeguimientoDAO stSeguimientoDAO;
	
	
	@Autowired
	private ConcesionariaDAO  concesionariaDAO;

	@Autowired
	private ConfiguracionParamDAO configuracionDAO;
	
	@Autowired
	private OpcionCausasDAO opcionCausasDAO;

	@Autowired
	private OrdenServicioService ordenServicioService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private GerenteSupervisorDAO gerenteSupervisorDAO;
	
	@Autowired
	private TipoFechasDAO tipoFechaDAO;
	
	@Autowired
	private ParametrosFolioDAO parametrosFolioDAO;
	
	@Autowired
	private VehiculoConductorDAO vehiculoConductorDAO;

	@Autowired
	private TblCatalogosDAO tblCatalogosDAO;
	
	@Autowired
	private PersonaDAO personaDAO;
	
	@Autowired
	private VehiculoDAO vehiculoDAO;
	
	
	@Transactional
	@Override
	public List<StEncuestaVO> estatusEncuesta() throws NotFoundException {
		List<StEncuestaDTO> stEncuesta = stEncuestaDAO.stEncuesta();
		if(stEncuesta.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<StEncuestaVO> listStEncuesta = ResponseConverter.converterLista(new ArrayList<>(), stEncuesta, StEncuestaVO.class);
		return listStEncuesta;
	}
	
	@Transactional
	@Override
	public List<TipoVehiculoVO> tipoVehiculo() throws NotFoundException {
		List<TipoVehiculoDTO> tpVehiculo = tipoVehiculoDAO.tipoVehiculo();
		if(tpVehiculo.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<TipoVehiculoVO> listTpVehiculo = ResponseConverter.converterLista(new ArrayList<>(), tpVehiculo, TipoVehiculoVO.class);
		return listTpVehiculo;
	}
	
	@Transactional
	@Override
	public List<ConductorVO> getTransportistas() throws NotFoundException {
		List<ConductorDTO> listaConductorDTO = conductorDAO.getTransportistas();
		if(listaConductorDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<ConductorVO> listaConductorVO = ResponseConverter.converterLista(new ArrayList<>(), listaConductorDTO, ConductorVO.class);
		return listaConductorVO;
	}
	
	@Transactional
	@Override
	public List<ConductorVO> getTransportistasSinVehiculo() throws NotFoundException{
		List<ConductorVO> listaConductorVO = conductorDAO.getTransportistasSinVehiculo();
		if(listaConductorVO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		return listaConductorVO;
	}
	
	@Transactional
	@Override
	public List<PersonaVO> getTecnicos(Integer idTipoPersona) throws NotFoundException {
		List<PersonaTipoDTO> listaPersonaTipoDTO = personaTipoDAO.getTecnicos(idTipoPersona);
		if(listaPersonaTipoDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<PersonaTipoVO> listaPersonaTipoVO = ResponseConverter.converterLista(new ArrayList<>(), listaPersonaTipoDTO, PersonaTipoVO.class);
		List<PersonaVO> listaPersonaVO = new ArrayList<PersonaVO>();
		for (PersonaTipoVO personaTipoVO : listaPersonaTipoVO) {
			PersonaCompVO personaCompVO = personaTipoVO.getPersona();
			PersonaVO personaVO = new PersonaVO();
			personaVO.setIdPersona(personaCompVO.getIdPersona());
			personaVO.setNbPersona(personaCompVO.getNbPersona());
			personaVO.setCdPersona(personaCompVO.getCdPersona());
			personaVO.setNbPatPersona(personaCompVO.getNbPatPersona());
			personaVO.setNbMatPersona(personaCompVO.getNbMatPersona());
			
			listaPersonaVO.add(personaVO);
		}
		
		return listaPersonaVO;
	}

	@Override
	@Transactional
	public CatalogosOrdenProcesoVO getCatalogosOrdenProceso()  throws NotFoundException {
		CatalogosOrdenProcesoVO copVO = new CatalogosOrdenProcesoVO();
		
		List<CentroInstalacionDTO> ciDTO = centroInstalacionDAO.obtenerCentroInstalacion();
		List<PlanDTO> pDTO = planDAO.getPlanAll();
		List<KitInstalacionDTO> kiDTO = kitInstalacionDAO.obtenerkitInstalacionAll();
		List<TipoKitDTO> tpKitDTO = tipoKitDAO.getTipoKit();
		List<ProveedorDTO> proveedorDTO = proveedorDAO.getListProveedor();
		List<ConsecionarioDTO> consecionarioDTO = concesionariaDAO.getConcesionariaAll();

		
		
		
		List<CentroInstalacionVO> ciVO = ResponseConverter.converterLista(new ArrayList<>(),ciDTO , CentroInstalacionVO.class);
		
		List<KitInstalacionVO> kiVO = ResponseConverter.converterLista(new ArrayList<>(), kiDTO, KitInstalacionVO.class);
	
		List<PlanVO> pVO = ResponseConverter.converterLista(new ArrayList<>(), pDTO, PlanVO.class);	
		
		List<TipoKitVO> tpKitVO= ResponseConverter.converterLista(new ArrayList<>(), tpKitDTO, TipoKitVO.class);
		
		List<ProveedorVO> proveedorVO = ResponseConverter.converterLista(new ArrayList<>(), proveedorDTO, ProveedorVO.class); 
		
		List<ConcesionariaVO> concesionarioVO = ResponseConverter.converterLista(new ArrayList<>(), consecionarioDTO, ConcesionariaVO.class);
		List<TipoVehiculoVO> tvVO = this.tipoVehiculo();
		
		// Centros de Instalacion
		copVO.setCentrosInstalacion(ciVO);
		// Kit de instalacion
		copVO.setKitInstalacion(kiVO);
		// Plan
		copVO.setPlan(pVO);
		// tipo Vehiculo
		copVO.setTipoVehiculo(tvVO);
		//tipo Kit
		copVO.setTipoKit(tpKitVO);
		
		copVO.setProveedorVO(proveedorVO);
		//catalogo de concesiones
		copVO.setConcesionariaVO(concesionarioVO);
		
		return copVO;
	}
	
	@Transactional
	@Override
	public List<StSeguimientoVO> obtenerStSeguimientoByCdTpSeguimiento(String cdTpSeguimiento) throws NotFoundException {
		List<StSeguimientoDTO> listStSeguimientoDTO = stSeguimientoDAO.obtenerStSeguimientoByCdTpSeguimiento(cdTpSeguimiento);
		if(listStSeguimientoDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<StSeguimientoVO> listStSeguimientoVO = ResponseConverter.converterLista(new ArrayList<>(), listStSeguimientoDTO, StSeguimientoVO.class);
		return listStSeguimientoVO;
	}
	
	@Transactional
	@Override
	public ConfiguracionVO configuracion(String cdLlavePConfig) throws NotFoundException{
		ConfiguracionVO voReturn = null;
		ConfiguracionDTO a1 =  configuracionDAO.configuracion(cdLlavePConfig);
		if(a1 == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		voReturn = new ConfiguracionVO();
		ResponseConverter.copiarPropriedades(voReturn, a1);
		return voReturn;
	}
	

	

	@Transactional
	@Override
	public List<OrdenServicioCatalogoVO> getOrdenServicio() throws NotFoundException{
		List<OrdenServicioCatalogoVO> listOrdenServicioCatalogoVO =  new ArrayList<>();
		List<OrdenServicioVO> listOrdenServicioVO = ordenServicioService.consultaOrdenServicioAll();
		
		for (int i = 0; i < listOrdenServicioVO.size(); i++) {
			OrdenServicioCatalogoVO ordenServicioCatalogoVO = new OrdenServicioCatalogoVO();
			ordenServicioCatalogoVO.setIdOrdenServicio(listOrdenServicioVO.get(i).getIdOrdenServicio());
			ordenServicioCatalogoVO.setCdOrdenServicio(listOrdenServicioVO.get(i).getCdOrdenServicio());
			listOrdenServicioCatalogoVO.add(ordenServicioCatalogoVO);
		}
		if(listOrdenServicioCatalogoVO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		
		return listOrdenServicioCatalogoVO;
	}

	@Transactional
	@Override
	public CentroInstalacionVO getModAten() throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		GerenteSupervisorDTO gerenteSupervisorDTO = gerenteSupervisorDAO.consultaGerenteSupervisorBySupervisor(usuario.getId());
		if(gerenteSupervisorDTO == null)
			throw new NotFoundException("No se encontró el centro de instalación, favor de reportar al administrador del sistema.");
		
		CentroInstalacionDTO centroInstalacionDTO = centroInstalacionDAO.centroIns(gerenteSupervisorDTO.getCentroInstalacion().getIdCentroInstalacion());
		if(centroInstalacionDTO == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		
		CentroInstalacionVO centroInstalacionVO = new CentroInstalacionVO(); 
		ResponseConverter.copiarPropriedades(centroInstalacionVO, centroInstalacionDTO);
		return centroInstalacionVO;
	}

	@Override
	@Transactional
	public List<OpcionCausaDTO> getCatalogoCausas(Long idOpcion) {
		return opcionCausasDAO.getCausasByidOpcion(idOpcion);

	}

	@Override
	@Transactional
	public PersonaGenericaVO buscarPersona(String cdPersona, Integer idTipoPersona) throws NotFoundException {
		PersonaGenericaVO personaVO=new PersonaGenericaVO();
		List<PersonaTipoDTO> listpersonaTipoDTO =personaTipoDAO.getTecnicoByCd(cdPersona);
		PersonaTipoDTO personaTipoDTO=new PersonaTipoDTO();
		Boolean existe=false;
		if(listpersonaTipoDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
			for (PersonaTipoDTO personaTipoDTO2 : listpersonaTipoDTO) {
			if (personaTipoDTO2.getTipoPersona().getIdTipoPersona()==idTipoPersona){ 
				existe=true;
				}
			}
			personaVO.setExistia(existe);
			personaVO.setCdPersona(listpersonaTipoDTO.get(0).getPersona().getCdPersona());
			personaVO.setIdPersona(listpersonaTipoDTO.get(0).getPersona().getIdPersona());
			personaVO.setaMaterno(listpersonaTipoDTO.get(0).getPersona().getNbMatPersona());
			personaVO.setaPaterno(listpersonaTipoDTO.get(0).getPersona().getNbPatPersona());
			personaVO.setNombre(listpersonaTipoDTO.get(0).getPersona().getNbPersona());
			return personaVO;
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public String generaFolioEmpl(Integer idPersona) throws NotFoundException {
		Formatter fmt = new Formatter();
		String folioEmpleado="";
		ConfiguracionDTO conf =  configuracionDAO.configuracion("CODIGO_PERSONA");
		if(conf == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
			String codigo=conf.getCdValorPConfig();
			String[] parts = codigo.split("-");
			String formato = parts[1];
			String prefijo = parts[0];
	        folioEmpleado =prefijo+fmt.format("%"+formato+"d",idPersona).toString();     
		return folioEmpleado;
	}

	@Override
	@Transactional
	public List<CatTipoFechasVO> getCatTipoFechas() {
		List<CatTipoFechasVO> respuesta = new ArrayList<CatTipoFechasVO>();
		List<TipoFechasDTO> fechas = tipoFechaDAO.getCatTipoFechas();
		if(!fechas.isEmpty()) {
			RutinasTiempoImpl rutinas =new RutinasTiempoImpl();	
			for(TipoFechasDTO fecha: fechas) {
				CatTipoFechasVO e = new CatTipoFechasVO();
				e.setIdPeriodo(fecha.getIdTipoFecha());
				e.setCdTipoFecha(fecha.getCdTipoFecha());
				e.setNbTipoFecha(fecha.getNbTipoFecha());
				List<String> rango = rutinas.generaRangoFechas(fecha.getIdTipoFecha());
				if(!rango.isEmpty()) {
					e.setFechaInicio(rango.get(0));
					e.setFechaFin(rango.get(1));					
				}
				respuesta.add(e);
			}
		}
		
		return respuesta;
	}
	
	@Override
	@Transactional
	public String generaFolio(String cdParametro) {
		String folioGenerado="";
		Formatter fmt = new Formatter();
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		 ParametrosFolioDTO parametroDTO = new ParametrosFolioDTO();
		 parametroDTO= parametrosFolioDAO.obtenerFolio(cdParametro);
		if (parametroDTO!=null) {
			Long numeroFolio=parametroDTO.getNuFolios()+1;
			String prefijo=parametroDTO.getNbPrefijo();
			folioGenerado =prefijo+fmt.format("%04d",numeroFolio).toString();  
			//ParametrosFolioDTO parametrosFolioDTO = parametrosFolioDAO.findOne(idParametro);
			parametroDTO.setFhModificacion(new Date());
			parametroDTO.setIdUsrModifica(usuario);
			parametroDTO.setNuFolios(numeroFolio);
			parametrosFolioDAO.update(parametroDTO);
		}
		return folioGenerado;
	}
	
	@Transactional
	@Override
	public List<ConfiguracionVO> configuracionIncidencia(String cdLlavePConfig1, String cdLlavePConfig2) throws NotFoundException{
		ConfiguracionVO voReturn1 = null;
		ConfiguracionVO voReturn2 = null;
		ConfiguracionDTO a1 =  configuracionDAO.configuracion(cdLlavePConfig1);
		if(a1 == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		
		ConfiguracionDTO a2 =  configuracionDAO.configuracion(cdLlavePConfig2);
		if(a2 == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		
	
		voReturn1 = new ConfiguracionVO();
		ResponseConverter.copiarPropriedades(voReturn1, a1);
		voReturn2 = new ConfiguracionVO();
		ResponseConverter.copiarPropriedades(voReturn2, a2);
		
		List<ConfiguracionVO> listConfiguracionVO = new ArrayList<ConfiguracionVO>();
		listConfiguracionVO.add(voReturn1);
		listConfiguracionVO.add(voReturn2);
		return listConfiguracionVO;
	}
	
	@Transactional
	@Override
	public List<ConductorVO> getTransportistasVehiculo(Long idVehiculo) throws NotFoundException {

		
		List<VehiculoConductorDTO> vehiculoConductor = vehiculoConductorDAO.getTransportistas(idVehiculo);
		if(vehiculoConductor.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<VehiculoConductorVO> listaVehiculoConductor = ResponseConverter.converterLista(new ArrayList<>(), vehiculoConductor, VehiculoConductorVO.class);
		List<ConductorVO> listaConductorVO = new ArrayList<ConductorVO>();	
		for (VehiculoConductorVO actual : listaVehiculoConductor) {
			ConductorVO conductorVO = new ConductorVO();
			conductorVO.setIdConductor(actual.getConductor().getIdConductor());
			conductorVO.setNbConductor(actual.getConductor().getNbConductor());
			conductorVO.setNbApepatConductor(actual.getConductor().getNbApepatConductor());
			conductorVO.setNbApematConductor(actual.getConductor().getNbApematConductor());
			conductorVO.setIdVehiculoConductor(actual.getIdVehiculoConductor());
			
			listaConductorVO.add(conductorVO);
		}
		
		
		return listaConductorVO;
	}
	
	@Transactional
	@Override
	public List<TblCatalogosVO> getTblCatalogos() throws NotFoundException{
		List<TblCatalogosDTO> listTblCatalogosDTO =  tblCatalogosDAO.getTblCatalogos();
		if(listTblCatalogosDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<TblCatalogosVO> listTblCatalogosVO = ResponseConverter.converterLista(new ArrayList<>(), listTblCatalogosDTO, TblCatalogosVO.class);
		return listTblCatalogosVO;
	}

	@Transactional
	@Override
	public List<CentroInstalacionVO>consultaCentroIntalacion(String cdTipoBusqueda,Long valor) throws NotFoundException {
		List<CentroInstalacionDTO> listCentroInstalacionDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listCentroInstalacionDTO = centroInstalacionDAO.obtenerCentroInstalacion();
				break;
			case "INACTIVOS":
				listCentroInstalacionDTO = centroInstalacionDAO.obtenerCentroInstalacionVisible(valor);
				break;
			
			case "ACTIVOS":
				listCentroInstalacionDTO = centroInstalacionDAO.obtenerCentroInstalacionVisible(valor);
				break;
			}
		
		if(listCentroInstalacionDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<CentroInstalacionVO> listCentroInstalacionVO = ResponseConverter.converterLista(new ArrayList<>(), listCentroInstalacionDTO, CentroInstalacionVO.class);
		return listCentroInstalacionVO;
	}
	
	@Transactional
	@Override
	public List<PersonaVO>consultalistPersona(String cdTipoBusqueda,Long valor) throws NotFoundException {
		List<PersonaDTO> listPersonaDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listPersonaDTO = personaDAO.getTecnicos();
				break;
			case "INACTIVOS":
				listPersonaDTO = personaDAO.obtenerPersonaVisible(valor);
				break;
			
			case "ACTIVOS":
				listPersonaDTO = personaDAO.obtenerPersonaVisible(valor);
				break;
			}
		
		if(listPersonaDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<PersonaVO> listPersonaVO = ResponseConverter.converterLista(new ArrayList<>(), listPersonaDTO, PersonaVO.class);
		return listPersonaVO;
	}
	
	@Transactional
	@Override
	public List<ConductorVO>consultaConductor(String cdTipoBusqueda,Long valor) throws NotFoundException {
		List<ConductorDTO> listConductorDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listConductorDTO = conductorDAO.getTransportistas();
				break;
			case "INACTIVOS":
				listConductorDTO = conductorDAO.obtenerConductorVisible(valor);
				break;
			
			case "ACTIVOS":
				listConductorDTO = conductorDAO.obtenerConductorVisible(valor);
				break;
			}
		
		if(listConductorDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<ConductorVO> listConductorVO = ResponseConverter.converterLista(new ArrayList<>(), listConductorDTO, ConductorVO.class);
		return listConductorVO;
	}
	
	@Transactional
	@Override
	public List<ProveedorVO>consultaProveedor(String cdTipoBusqueda,Long valor) throws NotFoundException {
		List<ProveedorDTO> listProveedorDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listProveedorDTO = proveedorDAO.getListProveedor();
				break;
			case "INACTIVOS":
				listProveedorDTO = proveedorDAO.obtenerProveedorVisible(valor);
				break;
			
			case "ACTIVOS":
				listProveedorDTO = proveedorDAO.obtenerProveedorVisible(valor);
				break;
			}
		
		if(listProveedorDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<ProveedorVO> listProveedorVO = ResponseConverter.converterLista(new ArrayList<>(), listProveedorDTO, ProveedorVO.class);
		return listProveedorVO;
	}
	
	@Transactional
	@Override
	public List<TipoVehiculoVO>consultaTipoVehiculo(String cdTipoBusqueda, Long valor) throws NotFoundException {
		List<TipoVehiculoDTO> listTipoVehiculoDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listTipoVehiculoDTO = tipoVehiculoDAO.tipoVehiculo();
				break;
			case "INACTIVOS":
				listTipoVehiculoDTO = tipoVehiculoDAO.obtenerTipoVehiculoVisible(valor);
				break;
			
			case "ACTIVOS":
				listTipoVehiculoDTO = tipoVehiculoDAO.obtenerTipoVehiculoVisible(valor);
				break;
			}
		
		if(listTipoVehiculoDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<TipoVehiculoVO> listTipoVehiculoVO = ResponseConverter.converterLista(new ArrayList<>(), listTipoVehiculoDTO, TipoVehiculoVO.class);
		return listTipoVehiculoVO;
	}
	
	@Transactional
	@Override
	public List<VehiculoVO>consultaVehiculo(String cdTipoBusqueda,Long valor) throws NotFoundException {
		List<VehiculoDTO> listVehiculoDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listVehiculoDTO = vehiculoDAO.buscarVehiculo();
				break;
			case "INACTIVOS":
				listVehiculoDTO = vehiculoDAO.obtenerVehiculoVisible(valor);
				break;
			
			case "ACTIVOS":
				listVehiculoDTO = vehiculoDAO.obtenerVehiculoVisible(valor);
				break;
			}
		
		if(listVehiculoDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<VehiculoVO> listVehiculoVO = ResponseConverter.converterLista(new ArrayList<>(), listVehiculoDTO, VehiculoVO.class);
		return listVehiculoVO;
	}
	
	@Transactional
	@Override
	public List<ConsecionarioVO>consultaConcesiones(String cdTipoBusqueda,Long valor) throws NotFoundException {
		List<ConsecionarioDTO> listConsecionarioDTO = new ArrayList<>(); 
		if(cdTipoBusqueda == null)
			throw new NotFoundException("No se encontró el valor de busqueda.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listConsecionarioDTO = concesionariaDAO.getConcesionariaAll();
				break;
			case "INACTIVOS":
				listConsecionarioDTO = concesionariaDAO.obtenerConsecionarioVisible(valor);
				break;
			
			case "ACTIVOS":
				listConsecionarioDTO = concesionariaDAO.obtenerConsecionarioVisible(valor);
				break;
			}
		
		if(listConsecionarioDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<ConsecionarioVO> listConsecionarioVO = ResponseConverter.converterLista(new ArrayList<>(), listConsecionarioDTO, ConsecionarioVO.class);
		return listConsecionarioVO;
	}
	
	
	@Transactional
	@Override
	public List<StSeguimientoVO> consultaStSeguimientoByTipo(Long tipo) throws NotFoundException {
		List<StSeguimientoDTO> listSeguimiento = new ArrayList<>(); 
		listSeguimiento=stSeguimientoDAO.obtenerStSeguimientoByTipo(tipo);
		if(listSeguimiento.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<StSeguimientoVO> listSeguimientoVO = ResponseConverter.converterLista(new ArrayList<>(), listSeguimiento, StSeguimientoVO.class);
		return listSeguimientoVO;
	}
	
	@Transactional
	@Override
	public List<TipoKitVO> consultaTipoKit() throws NotFoundException {
		List<TipoKitDTO> tipoKitDTO = new ArrayList<>(); 
		tipoKitDTO=tipoKitDAO.getTipoKit();
		if(tipoKitDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<TipoKitVO> tipoKitVO = ResponseConverter.converterLista(new ArrayList<>(), tipoKitDTO, TipoKitVO.class);
		return tipoKitVO;
	}
	
	@Transactional
	@Override
	public List<PlanVO> consultaPlan() throws NotFoundException {
		List<PlanDTO> planDTO = new ArrayList<>(); 
		planDTO=planDAO.getPlanAll();
		if(planDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<PlanVO> planVO = ResponseConverter.converterLista(new ArrayList<>(), planDTO, PlanVO.class);
		return planVO;
	}

	@Override
	@Transactional(readOnly = true)
	public CatalogosAltaOrdenServicioVO getCatalogosAltaOrdenServicio() throws NotFoundException {
		CatalogosAltaOrdenServicioVO copVO = new CatalogosAltaOrdenServicioVO();			
		copVO.setCentrosInstalacion(centroInstalacionDAO.getCatCentroInstalacion()); // Centros de Instalacion		
		copVO.setPlan(planDAO.getCatPlan()); // Plan		
		copVO.setTipoVehiculo(tipoVehiculoDAO.getCatTipoVehiculo()); // tipo Vehiculo		
		copVO.setTipoKit(tipoKitDAO.getCatTipoKit()); //tipo Kit
		copVO.setProveedorVO(proveedorDAO.getCatProveedor());		
		copVO.setConcesionariaVO(concesionariaDAO.getCatConcesionaria()); //catalogo de concesiones
		
		return copVO;
	}
	
	
}
