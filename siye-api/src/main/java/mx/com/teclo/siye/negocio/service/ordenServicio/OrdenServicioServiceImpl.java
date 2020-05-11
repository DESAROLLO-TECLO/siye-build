package mx.com.teclo.siye.negocio.service.ordenServicio;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConfiguracionParamDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ProveedorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoVehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.OdsIncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.ConcesionariaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.DispositivosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.KitInstDispDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.KitInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OdsDetalleCambioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.VehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConfiguracionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.OdsIncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ConsecionarioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitDispositivoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDispDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OdsDetalleCambioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.GerenteSupervisorDTO;
import mx.com.teclo.siye.persistencia.vo.ordenServicio.OrdenServiVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class OrdenServicioServiceImpl implements OrdenServicioService{
	
	private static final String MSG_ERROR_ORDEN_NULA = "La orden de servicio esta vac\u00EDa";
	private static final String MSG_ERROR_ORDEN_INCOMPLETA = "La orden de servicio est\u00E1 incompleta";
	private static final String MSG_ERROR_ORDEN_CON_REFERENCIAS_NULAS = "La orden de servicio hace referencia a datos nulos";

	@Autowired
	private OrdenServicioDAO ordenServicioDAO;
	
	@Autowired
	private VehiculoDAO vehiculoDAO;

	@Autowired
	private LoteOrdenServicioDAO loteDAO;

	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;

	@Autowired
	private KitInstalacionDAO kitDAO;

	@Autowired
	private PlanDAO planDAO;

	@Autowired
	private UsuarioFirmadoService contexto;

	@Autowired
	private StSeguimientoDAO seguimientoDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private GerenteSupervisorDAO gerenteSupervisorDAO;
	
	@Autowired
	private OdsIncidenciaDAO odsIncidenciaDAO;
	
	@Autowired
	private OdsDetalleCambioDAO odsDetalleCambioDAO;
	
	@Autowired
	private TipoVehiculoDAO tpVehiculoDAO;
	
	@Autowired
	private ConcesionariaDAO concesionariaDAO;
	
	@Autowired
	private ProveedorDAO proveedorDAO;
	
	@Autowired
	private DispositivosDAO dispositivoDAO;
	
	@Autowired
	private KitInstDispDAO kitInstDispDAO;
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	@Autowired
	private  ConfiguracionParamDAO configuracionDAO;
	
	@Transactional
	@Override
	public List<OrdenServicioVO> consultaOrdenServicioAll() throws NotFoundException {
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		GerenteSupervisorDTO gerenteSupervisorDTO = gerenteSupervisorDAO.consultaGerenteSupervisorBySupervisor(usuario.getId());
		List<OrdenServicioDTO> listOrdenServicioDTO =new ArrayList<OrdenServicioDTO>();
		if(gerenteSupervisorDTO == null)
		{
			 listOrdenServicioDTO =  ordenServicioDAO.consultaTodasOrdenes(); 
			
		}
		else
		{
	        listOrdenServicioDTO =  ordenServicioDAO.consultaOrdenByFhCita(gerenteSupervisorDTO.getCentroInstalacion().getIdCentroInstalacion()); 
		}
		
		if(listOrdenServicioDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<OrdenServicioVO> listOrdenServicioVO = ResponseConverter.converterLista(new ArrayList<>(), listOrdenServicioDTO, OrdenServicioVO.class);
		return listOrdenServicioVO;
	}

	
	@Transactional
	@Override
	public List<OrdenServicioVO> consultaOrden(String cdTipoBusqueda, String valor) throws NotFoundException {
		List<OrdenServicioDTO> listOrdenServicioDTO = new ArrayList<>(); 
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		GerenteSupervisorDTO gerenteSupervisorDTO = gerenteSupervisorDAO.consultaGerenteSupervisorBySupervisor(usuario.getId());
		if(gerenteSupervisorDTO == null)
			throw new NotFoundException("No se encontró el centro de instalación, favor de reportar al administrador del sistema.");
		switch(cdTipoBusqueda) {
			case "TODO":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByFhCita(gerenteSupervisorDTO.getCentroInstalacion().getIdCentroInstalacion());
				break;
			case "PLACA":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByPlaca(valor, gerenteSupervisorDTO.getCentroInstalacion().getIdCentroInstalacion());
				break;
			
			case "ORDEN_SERVICIO":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByOrdenServicio(valor, gerenteSupervisorDTO.getCentroInstalacion().getIdCentroInstalacion());
				break;
			
			case "VIN":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByVin(valor, gerenteSupervisorDTO.getCentroInstalacion().getIdCentroInstalacion());
				break;
			}
		
		if(listOrdenServicioDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<OrdenServicioVO> listOrdenServicioVO = ResponseConverter.converterLista(new ArrayList<>(), listOrdenServicioDTO, OrdenServicioVO.class);
		return listOrdenServicioVO;
	}

	@Transactional
	@Override
	public Boolean actualizaOrdenServicio(OrdenServicioVO osVO) throws NotFoundException, BusinessException{
		OrdenServicioDTO osDTO = ordenServicioDAO.obtenerOrdenServicio(osVO.getIdOrdenServicio());
		if(osDTO == null)
			throw new NotFoundException("El registro que intenta actualizar no existe");
		
		if(osVO.getLoteOrdenServicio() != null) {
			LoteOrdenServicioDTO losDTO = new LoteOrdenServicioDTO();
			losDTO.setIdLoteOds(osVO.getLoteOrdenServicio().getIdLoteOds());
			osDTO.setLoteOrdenServicio(losDTO);
		}
		if(osVO.getVehiculo() != null) {
			VehiculoDTO vDTO = osDTO.getVehiculo();
			vDTO.setIdVehiculo(osVO.getVehiculo().getIdVehiculo());
		
			// Campos Vehiculo 
		if (osVO.getVehiculo().getTipoVehiculo() != null) {
				// vDTO.getTipoVehiculo().setIdTipoVehiculo(osVO.getVehiculo().getTipoVehiculo().getIdTipoVehiculo());
			TipoVehiculoDTO tvDTO = new TipoVehiculoDTO();
			// tvDTO.setIdTipoVehiculo(osVO.getVehiculo().getTipoVehiculo().getIdTipoVehiculo());
			ResponseConverter.copiarPropriedades(tvDTO, osVO.getVehiculo().getTipoVehiculo());
			vDTO.setTipoVehiculo(tvDTO);
		}
		
			vDTO.setCdPlacaVehiculo(osVO.getVehiculo().getCdPlacaVehiculo());
			vDTO.setCdVin(osVO.getVehiculo().getCdVin());
			vDTO.setCdTarjetaDeCirculacion(osVO.getVehiculo().getCdTarjetaDeCirculacion());
			vDTO.setNbMarca(osVO.getVehiculo().getNbMarca());
			vDTO.setNbSubMarca(osVO.getVehiculo().getNbSubMarca());
			vDTO.setCdModelo(osVO.getVehiculo().getCdModelo());
			
			vehiculoDAO.update(vDTO);
			
			osDTO.setVehiculo(vDTO);
		}
		
		
		
		if(osVO.getCentroInstalacion() != null) {
			CentroInstalacionDTO ciDTO = new CentroInstalacionDTO();
			ciDTO.setIdCentroInstalacion(osVO.getCentroInstalacion().getIdCentroInstalacion());
			osDTO.setCentroInstalacion(ciDTO);
		}
		
		if(osVO.getKitInstalacion() != null) {
			KitInstalacionDTO kiDTO = new KitInstalacionDTO();
			kiDTO.setIdKitInstalacion(osVO.getKitInstalacion().getIdKitInstalacion());
			osDTO.setKitInstalacion(kiDTO);
		}
		
		if(osVO.getPlan() != null) {
			PlanDTO pDTO = new PlanDTO();
			pDTO.setIdPlan(osVO.getPlan().getIdPlan());
			osDTO.setPlan(pDTO);
		}
		
		// Actualizacion de Campos
		//osDTO.setHrCita(osVO.getHrCita());
		osDTO.setFhCita(osVO.getFhCita());
		osDTO.setFhAtencionFin(osVO.getFhAtencionFin());
		osDTO.setFhAtencionIni(osVO.getFhAtencionIni());
		osDTO.setIdOrigenOds(1L);
	
		if (osVO.getIncidencia() != null) {
				OdsIncidenciaDTO oiDTO = new OdsIncidenciaDTO();
				IncidenciaDTO iDTO = new IncidenciaDTO();
				iDTO = ResponseConverter.copiarPropiedadesFull(osVO.getIncidencia(), IncidenciaDTO.class);
				oiDTO.setIdOrdenServicio(osDTO);
				oiDTO.setIdIncidencia(iDTO);
				
			OdsDetalleCambioDTO dcDTO = new OdsDetalleCambioDTO();	
			
			dcDTO = ResponseConverter.copiarPropiedadesFull(osDTO, OdsDetalleCambioDTO.class);
			dcDTO.setOrdenServicio2(osDTO);				
			odsDetalleCambioDAO.save(dcDTO);	
			odsIncidenciaDAO.save(oiDTO);
			ordenServicioDAO.update(osDTO);	
		} else {
			throw new NotFoundException("La incidencia se encuentra vacia.");
		}

	return true;
	}

	@Override
	@Transactional
	public OrdenServicioVO findOrdenServicio(Long idOrdenServico) throws NotFoundException, BusinessException {
		OrdenServicioVO osVo = new OrdenServicioVO();
		OrdenServicioDTO osDto = ordenServicioDAO.obtenerOrdenServicio(idOrdenServico);
		osVo = ResponseConverter.copiarPropiedadesFull(osDto, OrdenServicioVO.class);
		return osVo;
	}
	
	
	
	@Override
	@Transactional
	public OrdenServicioVO findOrdenServiciobyCD_ORDEN_SERVICIO(String  cdOrdenServicio) throws NotFoundException, BusinessException {
		OrdenServicioVO osVo = new OrdenServicioVO();
		OrdenServicioDTO osDto = ordenServicioDAO.obtenerOrdenServicioCD_ORDEN_SERVICIO(cdOrdenServicio);
		osVo = ResponseConverter.copiarPropiedadesFull(osDto, OrdenServicioVO.class);
		return osVo;
	}
	
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param ordenServicioVO
	 * 
	 */
	@Override
	@Transactional
	public Long crearOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException {
		return (Long) ordenServicioDAO.save(generarOrdenServicioDTO(ordenServicioVO));
	}

	/**
	 * Valida una orden de servicio previo a su registro en el sistema.
	 * 
	 * @param ordenServicioVO
	 * @return OrdenServicioDTO
	 * @throws BusinessException
	 */
	private OrdenServicioDTO generarOrdenServicioDTO(OrdenServicioVO ordenServicioVO) throws BusinessException {

		validarOrdenServicio(ordenServicioVO);
		OrdenServicioDTO ordenServicioDTO = new OrdenServicioDTO();
		ordenServicioDTO.setCdOrdenServicio(ordenServicioVO.getCdOrdenServicio());
		ordenServicioDTO.setLoteOrdenServicio(loteDAO.findOne(ordenServicioVO.getLoteOrdenServicio().getIdLoteOds()));
		ordenServicioDTO.setVehiculo(vehiculoDAO.findOne(ordenServicioVO.getVehiculo().getIdVehiculo()));
		ordenServicioDTO.setCentroInstalacion(
				centroInstalacionDAO.findOne(ordenServicioVO.getCentroInstalacion().getIdCentroInstalacion()));
		ordenServicioDTO.setKitInstalacion(kitDAO.findOne(ordenServicioVO.getKitInstalacion().getIdKitInstalacion()));
		ordenServicioDTO.setPlan(planDAO.findOne(ordenServicioVO.getPlan().getIdPlan()));
		ordenServicioDTO
				.setStSeguimiento(seguimientoDAO.findOne(ordenServicioVO.getStSeguimiento().getIdStSeguimiento()));
		ordenServicioDTO.setStActivo(Boolean.TRUE.booleanValue());
		ordenServicioDTO.setIdUsrCreacion(contexto.getUsuarioFirmadoVO().getId());
		ordenServicioDTO.setFhCreacion(new Date());
		ordenServicioDTO.setIdUsrModifica(contexto.getUsuarioFirmadoVO().getId());

		return ordenServicioDTO;
	}

	/**
	 * Verifica que los datos incluidos en la orden de servicio existan en el
	 * sistema.
	 * 
	 * @param ordenServicioVO
	 * @throws BusinessException
	 */
	private void validarOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException {
		boolean isConDatosRequeridos = false;
		boolean isConIDsRequeridas = false;

		if (ordenServicioVO == null) {
			throw new BusinessException(MSG_ERROR_ORDEN_NULA);
		}

		if (ordenServicioVO.getLoteOrdenServicio() != null)
			if (ordenServicioVO.getVehiculo() != null)
				if (ordenServicioVO.getCentroInstalacion() != null)
					if (ordenServicioVO.getKitInstalacion() != null)
						if (ordenServicioVO.getPlan() != null)
							if (ordenServicioVO.getStSeguimiento() != null)
								isConDatosRequeridos = true;
		if (!isConDatosRequeridos) {
			throw new BusinessException(MSG_ERROR_ORDEN_INCOMPLETA);
		}

		if (!StringUtils.isBlank(ordenServicioVO.getCdOrdenServicio()))
			if (ordenServicioVO.getLoteOrdenServicio().getIdLoteOds() != null)
				if (ordenServicioVO.getVehiculo().getIdVehiculo() != null)
					if (ordenServicioVO.getCentroInstalacion().getIdCentroInstalacion() != null)
						if (ordenServicioVO.getKitInstalacion().getIdKitInstalacion() != null)
							if (ordenServicioVO.getPlan().getIdPlan() != null)
								if (ordenServicioVO.getStSeguimiento().getIdStSeguimiento() != null)
									isConIDsRequeridas = true;

		if (!isConIDsRequeridas) {
			throw new BusinessException(MSG_ERROR_ORDEN_CON_REFERENCIAS_NULAS);
		}

	}

	@Override
	@Transactional
	public void saveOrdenServicio(OrdenServiVO ordenServiVO) {
		
		OrdenServicioDTO ordenServiDTO = new OrdenServicioDTO(); // TIE026_ORDEN_SERVICIO
		OdsIncidenciaDTO odsIncidencDTO = new OdsIncidenciaDTO();
		IncidenciaDTO incidenciaDTO = new IncidenciaDTO();
		KitInstalacionDTO kitInstalacion  = new KitInstalacionDTO();
		 // TIE027_VEHICULO
		
		VehiculoDTO vehiculo = vehiculoDAO.buscarVehiculoPorPlaca(ordenServiVO.getVehiculoVO().getPlaca());
		
		if(vehiculo == null){
			
			vehiculo = new VehiculoDTO();
		
			TipoVehiculoDTO tpVehiculoDTO = tpVehiculoDAO.findOne(ordenServiVO.getVehiculoVO().getTpVehiculo().getIdTipoVehiculo());
			ConsecionarioDTO concesinarioDTO = concesionariaDAO.findOne(ordenServiVO.getVehiculoVO().getConcesionaria().getIdConsecion());
			
			vehiculo.setCdPlacaVehiculo(ordenServiVO.getVehiculoVO().getPlaca());
			vehiculo.setCdVin(ordenServiVO.getVehiculoVO().getCdVIN());
			vehiculo.setCdTarjetaDeCirculacion(ordenServiVO.getVehiculoVO().getTjtCirculacion());
			vehiculo.setTipoVehiculo(tpVehiculoDTO);
			vehiculo.setNbMarca(ordenServiVO.getVehiculoVO().getMarca());
			vehiculo.setNbSubMarca(ordenServiVO.getVehiculoVO().getSubMarca());
			vehiculo.setCdModelo(ordenServiVO.getVehiculoVO().getCdModelo());
			vehiculo.setConsecionario(concesinarioDTO);
			vehiculo.setStActivo(true);
			vehiculo.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			vehiculo.setFhCreacion(new Date());
			vehiculo.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			vehiculo.setFhModificacion(new Date());
			vehiculoDAO.save(vehiculo);
		}
		
		if(ordenServiVO.getCdKitIntalacion() == null){
			
			kitInstalacion.setStActivo(true);
			kitInstalacion.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			kitInstalacion.setFhCreacion(new Date());
			kitInstalacion.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			kitInstalacion.setFhModificacion(new Date());
			kitDAO.save(kitInstalacion);
			
		}
		else{
			
			kitInstalacion.setCdKitInstalacion(ordenServiVO.getCdKitIntalacion());
			kitInstalacion.setStActivo(true);
			kitInstalacion.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			kitInstalacion.setFhCreacion(new Date());
			kitInstalacion.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			kitInstalacion.setFhModificacion(new Date());
			kitDAO.save(kitInstalacion);
		}
		
		kitInstalacion = kitDAO.ultimoId();
		
		

		
		vehiculo = vehiculoDAO.buscarVehiculoPorPlaca(ordenServiVO.getVehiculoVO().getPlaca());
		CentroInstalacionDTO centroInst = centroInstalacionDAO.findOne(ordenServiVO.getCentroI());
		
		PlanDTO planDTO = planDAO.getId(ordenServiVO.getPlan());
		StSeguimientoDTO stSeguimiento =seguimientoDAO.obtenerSeguimientoDos(1l);
		
		ordenServiDTO.setCdOrdenServicio(ordenServiVO.getCdOrden());
		ordenServiDTO.setVehiculo(vehiculo);
		ordenServiDTO.setCentroInstalacion(centroInst);
		ordenServiDTO.setKitInstalacion(kitInstalacion);
		ordenServiDTO.setPlan(planDTO);
		ordenServiDTO.setStSeguimiento(stSeguimiento);
		ordenServiDTO.setIdOrigenOds(2l);
		//contemplar para front
		ordenServiDTO.setFhCita(ordenServiVO.getFhCita());
		ordenServiDTO.setStActivo(true);
		ordenServiDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		ordenServiDTO.setFhCreacion(new Date());
		ordenServiDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		ordenServiDTO.setFhModificacion(new Date());
		ordenServicioDAO.save(ordenServiDTO);
		
	
		for(int i=0; i<ordenServiVO.getKitInstalacionVO().size(); i++){
			
			KitInstalacionDispDTO kitInsDipDTO = new KitInstalacionDispDTO();
			
			KitDispositivoDTO dispDTO = dispositivoDAO.getByDispositivo(ordenServiVO.getKitInstalacionVO().get(i).getIdDispositivo());
//			kitInstalacion = kitDAO.kitIns(ordenServiVO.getCdKitIntalacion());
			ProveedorDTO provee = proveedorDAO.findOne(ordenServiVO.getKitInstalacionVO().get(i).getProveedor());
			
			
			kitInsDipDTO.setKitInstalacion(kitInstalacion);
			kitInsDipDTO.setKitDispositivo(dispDTO);
			kitInsDipDTO.setProveedor(provee);
			kitInsDipDTO.setCdKitDispositivo(ordenServiVO.getKitInstalacionVO().get(i).getSerie());
			kitInsDipDTO.setStActivo(true);
			kitInstDispDAO.save(kitInsDipDTO);	
		}	
		
		ordenServiDTO = ordenServicioDAO.obtenerOrdenServicioCD_ORDEN_SERVICIO(ordenServiVO.getCdOrden());
		incidenciaDTO = incidenciaDAO.findOne(ordenServiVO.getIdIncidencia());
		odsIncidencDTO.setIdOrdenServicio(ordenServiDTO);
		odsIncidencDTO.setIdIncidencia(incidenciaDTO);
		odsIncidenciaDAO.save(odsIncidencDTO);
		
		
		StSeguimientoDTO stSeguimiento3 =seguimientoDAO.obtenerSeguimientoDos(3l);
		incidenciaDTO.setStIncidencia(stSeguimiento3);
		incidenciaDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		incidenciaDTO.setFhModificacion(new Date());
		incidenciaDAO.update(incidenciaDTO);
		
	}
	
	@Transactional
	@Override
	public List<OrdenServicioVO> consultaHistorica(Boolean busquedaAvanzada,String cdTipoBusqueda, String valorBusqueda, String fhInicio, String fhFin,String centroInstalacion,String estatusSeguimiento,
		Boolean isLote, Boolean isIncidencia, String valorLoteIncidencia, String tipoKit, String tipoPlan) throws NotFoundException {
		
		List<OrdenServicioDTO> listOrdenServicioDTO = new ArrayList<>(); 

		if(!busquedaAvanzada){
			switch(cdTipoBusqueda) {
				case "TODO":
					Integer registrosAMostrar;
					ConfiguracionDTO a1 =  configuracionDAO.configuracion("NUM_MAX_REGISTROS_MOSTRAR");
					registrosAMostrar=Integer.parseInt(a1.getCdValorPConfig());
					listOrdenServicioDTO = ordenServicioDAO.todos(registrosAMostrar);
					break;
				case "PLACA":
					listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByPlacaTodo(valorBusqueda);
					break;
				
				case "ORDEN_SERVICIO":
					listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByOrdenServicioTodo(valorBusqueda);
					break;
				
				case "VIN":
					listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByVinTodo(valorBusqueda);
					break;
					
				case "LOTE":
					listOrdenServicioDTO = ordenServicioDAO.getOrdenServicioByLote(valorBusqueda);
					break;
					
				case "INCIDENCIA":
					listOrdenServicioDTO = ordenServicioDAO.getOrdenServicioByIncidecnia(valorBusqueda);
					break;
				}
			
			if(listOrdenServicioDTO.isEmpty())
				throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
			List<OrdenServicioVO> listOrdenServicioVO = ResponseConverter.converterLista(new ArrayList<>(), listOrdenServicioDTO, OrdenServicioVO.class);
			return listOrdenServicioVO;
		}else
		{
			Integer registrosAMostrar;
			ConfiguracionDTO a1 =  configuracionDAO.configuracion("NUM_MAX_REGISTROS_MOSTRAR");
			registrosAMostrar=Integer.parseInt(a1.getCdValorPConfig());
			listOrdenServicioDTO=ordenServicioDAO.consultaAvanzada(busquedaAvanzada, cdTipoBusqueda, valorBusqueda, fhInicio, fhFin, centroInstalacion, estatusSeguimiento, isLote, isIncidencia, valorLoteIncidencia, tipoKit, tipoPlan,registrosAMostrar);
			
			if(listOrdenServicioDTO.isEmpty())
				throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
			List<OrdenServicioVO> listOrdenServicioVO = ResponseConverter.converterLista(new ArrayList<>(), listOrdenServicioDTO, OrdenServicioVO.class);
			return listOrdenServicioVO;
		}
		}
		


}
