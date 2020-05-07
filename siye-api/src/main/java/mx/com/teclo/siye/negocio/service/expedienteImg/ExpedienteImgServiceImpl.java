package mx.com.teclo.siye.negocio.service.expedienteImg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.responsehttp.BadRequestHttpResponse;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.catalogo.CatalogoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PreguntasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.SeccionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.CompresorImgConfigDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.ExpedienteImgDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesos.IEProcesosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.tipoExpediente.TipoExpedienteDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.CompresorImgConfigDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.tipoexpediente.TipoExpedienteDTO;
import mx.com.teclo.siye.persistencia.mybatis.dao.proceso.ProcesoDAO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CompresorImgConfigVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelPreguntaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

@Service
public class ExpedienteImgServiceImpl implements ExpedienteImgService {

	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Autowired
	private PlanProcesoDAO planProcesoDAO;

	@Autowired
	private ProcesoEncuestaDAO procesoEncuestaDAO;

	@Autowired
	private SeccionDAO seccionDAO;

	@Autowired
	private ExpedienteImgDAO expedienteImgDAO;

	@Autowired
	private IEProcesosDAO ieProcesosDAO;

	@Autowired
	private PreguntasDAO preguntaDAO;

	@Autowired
	private EncuestasDAO encuestasDAO;

	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Autowired
	private TipoExpedienteDAO tipoExpedienteDAO;
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	@Autowired
	private UsuarioEncuestaDAO usuarioEncuesta;
	
	@Autowired
	private ProcesoDAO procesoDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private CompresorImgConfigDAO compresorImgConfigDAO;

	private static Boolean ACTIVO = true, BORRAR = false;
	private static String PLACA = "PLACA", OS = "ORDEN_SERVICIO", VIN = "VIN";
	private final String CDOS = "ORDEN_SERVICIO", CDPROCESO = "PROCESO", CDENCUESTA = "ENCUESTA", CDPREGUNTA = "PREGUNTA";

	@Override
	@Transactional(readOnly=true)
	public List<CargaExpedienteImgVO> getInformacionExpediente(String tipoBusqueda, String valor) {
		List<OrdenServicioDTO> OSDTO = null;
		List<CargaExpedienteImgVO> respuesta = new ArrayList<CargaExpedienteImgVO>();

		ConfiguracionOSDTO config = configuracionDAO.getConfigByCdConfig("TIE026_NU_MAX_IMAGENES");
		Long numeroMaximo = config != null ? Long.parseLong(config.getCdValorConfig()) : null;

		if (tipoBusqueda.equals(OS)) {
			OSDTO = ordenServicioDAO.consultaOrdenByOrdenServicio(valor);
		} else if (tipoBusqueda.equals(PLACA)) {
			OSDTO = ordenServicioDAO.consultaOrdenByPlaca(valor);
		} else if (tipoBusqueda.equals(VIN)) {
			OSDTO = ordenServicioDAO.consultaOrdenByVin(valor);
		}

		if (!OSDTO.isEmpty() && OSDTO!=null) {
			for (OrdenServicioDTO os : OSDTO) {
				CargaExpedienteImgVO OSVO = new CargaExpedienteImgVO();
				OSVO.setNameConsesionario(os.getVehiculo().getConsecionario().getNbConsecion());
				OSVO.setNumvim(os.getVehiculo().getCdVin());
				OSVO.setPlaca(os.getVehiculo().getCdPlacaVehiculo());
				OSVO.setCdOrdenServicio(os.getCdOrdenServicio());
				OSVO.setNuMaxImg(numeroMaximo);
				OSVO.setIdOrdenServicio(os.getIdOrdenServicio());
				OSVO.setNbNivel("Orden de Servicio");
				OSVO.setCdNivel("OS");
				OSVO.setFechaInicio(os.getFhAtencionIni());
				OSVO.setFechaFin(os.getFhAtencionFin());

				if (os.getPlan().getIdPlan() != null) {
					List<ExpedienteNivelProcesoVO> procesosByOS = planProcesoDAO.getProcesosPlanVO(os.getPlan().getIdPlan());
					if (!procesosByOS.isEmpty()) {
						for (ExpedienteNivelProcesoVO proceso : procesosByOS) {
							proceso.setNbNivel("Proceso");
							proceso.setCdNivel("PRC");
							proceso.setName(proceso.getCdProceso());
							List<ExpedienteNivelEncuestaVO> LisEncuestas = procesoEncuestaDAO.getEncuestasByProcesoVO(proceso.getIdProceso());
							if (!LisEncuestas.isEmpty()) {
								for (ExpedienteNivelEncuestaVO encuesta : LisEncuestas) {
									encuesta.setNbNivel("Encuesta");
									encuesta.setCdNivel("ENC");
									encuesta.setName(encuesta.getCdEncuesta());
									List<ExpedienteNivelPreguntaVO> listPreguntas = seccionDAO.getPreguntasByEncuestaVO(encuesta.getIdEncuesta());
									for (ExpedienteNivelPreguntaVO pregunta : listPreguntas) {
										pregunta.setNbNivel("Pregunta");
										pregunta.setName(pregunta.getCdPregunta());
										pregunta.setCdNivel("PREG");
									}
									encuesta.setListPreguntas(listPreguntas);
								}
								
							}	
							proceso.setListEncuestas(LisEncuestas);
						}
					}
					OSVO.setProcesos(procesosByOS);
				}
				
				List<ExpedientesImgDTO> listDTO=expedienteImgDAO.getAllExpedientesDTO(os.getIdOrdenServicio());
				List<ImagenVO> listVO= new ArrayList<>();
				ImagenVO imgVO=null;
				
				for(ExpedientesImgDTO dto: listDTO){
					imgVO = new ImagenVO();
					imgVO.setIdOrdenServicio(os.getIdOrdenServicio());
					imgVO.setCdTipoArchivo(dto.getCdTipoArchivo());
					imgVO.setFhCreacion(dto.getFhCreacion());
					imgVO.setIdExpedienteODS(dto.getIdExpedienteODS());
					imgVO.setLbExpedienteODS(dto.getLbExpedienteODS());
					imgVO.setNbExpedienteODS(dto.getNbExpedienteODS());
					
					if(dto.getTipoExpediente() != null){
						TipoExpedienteVO tpExpVO=new TipoExpedienteVO();
						tpExpVO.setIdTipoExpediente(dto.getTipoExpediente().getIdDispositivo());
						tpExpVO.setCdTipoExpediente(dto.getTipoExpediente().getCdTipoExpediente());
						tpExpVO.setNbTipoExpediente(dto.getTipoExpediente().getNbTipoExpediente());
						imgVO.setTipoExpediente(tpExpVO);
						imgVO.setIdTipoExpediente(dto.getTipoExpediente().getIdDispositivo());
					}
					
					//Clasificacion de imagenes
					if(dto.getIdOrdenServicio()!=null && dto.getIdProceso()==null && dto.getIdOdsEncuesta()==null && dto.getIdPregunta()==null) {
						//Nivel OS
						imgVO.setCdNivel("OS");
						imgVO.setNbNivel("Orden de Servicio");
						
					}else if(dto.getIdOrdenServicio()!=null && dto.getIdProceso()!=null && dto.getIdOdsEncuesta()==null && dto.getIdPregunta()==null) {
						//Nivel Procso 
						imgVO.setCdNivel("PRC");
						imgVO.setNbNivel("Proceso/"+dto.getIdProceso().getNbProceso());
						imgVO.setIdProceso(dto.getIdProceso().getIdProceso());
					}else if(dto.getIdOrdenServicio()!=null && dto.getIdProceso()!=null && dto.getIdOdsEncuesta()!=null && dto.getIdPregunta()==null) {
						//Nivel Encuesta 
						imgVO.setCdNivel("ENC");
						imgVO.setNbNivel("Encuesta/"+dto.getIdOdsEncuesta().getEncuesta().getNbEncuesta());
						imgVO.setIdProceso(dto.getIdProceso().getIdProceso());
						imgVO.setIdOdsEncuesta(dto.getIdOdsEncuesta().getEncuesta().getIdEncuesta());
					}else if(dto.getIdOrdenServicio()!=null && dto.getIdProceso()!=null && dto.getIdOdsEncuesta()!=null && dto.getIdPregunta()!=null) {
						//Nivel Pregunta
						imgVO.setCdNivel("PREG");
						String nbEncuest=dto.getIdOdsEncuesta() == null ? "Pregunta" : dto.getIdOdsEncuesta().getEncuesta().getNbEncuesta();
						imgVO.setNbNivel(nbEncuest+"/"+dto.getIdPregunta().getTxPregunta());
						imgVO.setIdProceso(dto.getIdProceso().getIdProceso());
						imgVO.setIdOdsEncuesta(dto.getIdOdsEncuesta().getEncuesta().getIdEncuesta());
						imgVO.setIdPregunta(dto.getIdPregunta().getIdPregunta());
					}
					
					listVO.add(imgVO);
				}
				OSVO.setImagenes(listVO);
				
				
				
				respuesta.add(OSVO);
			}
		}

		// Consulta De imegenes y filtrado por OS
		
		return respuesta;
	}

	@Transactional
	@Override
	public List<ImagenVO> saveExpediente(List<ImagenVO> expedientes, Long idUsuario) throws BusinessException,BadRequestHttpResponse{
		Date fechaCarga = new Date();
		ConfiguracionOSDTO config = configuracionDAO.findOne(1L);
		Long numeroMaximo = Long.parseLong(config.getCdValorConfig());

		// Clasificar el nivel de las miamgenes
		ImagenVO expediente = expedientes.get(0);
		
		if(expediente.getIdOrdenServicio() == null)
			throw new BadRequestHttpResponse("La orden de servicio es requerida para guardar la imagen");
		
		try{
			if(expediente.getIdOrdenServicio()!=null && expediente.getIdProceso()==null && expediente.getIdOdsEncuesta()==null && expediente.getIdPregunta()==null) {
				//Nivel OS
				expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numeroMaximo);
				
			}else if(expediente.getIdOrdenServicio()!=null && expediente.getIdProceso()!=null && expediente.getIdOdsEncuesta()==null && expediente.getIdPregunta()==null) {
				//Nivel Procso 
				IEprocesosDTO numax = ieProcesosDAO.findOne(expediente.getIdProceso());
				expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numax.getNuMaxImagenes());
				
			}else if(expediente.getIdOrdenServicio()!=null && expediente.getIdProceso()!=null && expediente.getIdOdsEncuesta()!=null && expediente.getIdPregunta()==null) {
				//Nivel Encuesta 
				EncuestasDTO numax = encuestasDAO.findOne(expediente.getIdOdsEncuesta());
				expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numax.getNuMaxImagenes());
				
			}else if(expediente.getIdOrdenServicio()!=null && expediente.getIdProceso()!=null && expediente.getIdOdsEncuesta()!=null && expediente.getIdPregunta()!=null) {
				//Nivel Pregunta
				PreguntasDTO numax = preguntaDAO.findOne(expediente.getIdPregunta());
				expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numax.getNuMaxImagenes());
			}
		}catch(BusinessException bs){
			 throw new BusinessException(bs.getMessage());
		}
		
		return expedientes;
	};

	public List<ImagenVO> saveImagenEvidencia(List<ImagenVO> expedientes, Long idUsuario, 
			Date fechaCarga,Long nuMaximoImagenes) throws BusinessException{
		Long nuOrden =0L;
		
		if(expedientes.size() > nuMaximoImagenes)
			throw new BusinessException("Ha llegado al número ("+nuMaximoImagenes+") maxímo de imagenes admitidas");
		
		ImagenVO imagen = expedientes.get(0);
		OrdenServicioDTO ordenServicioDTO = ordenServicioDAO.findOne(imagen.getIdOrdenServicio());
		
		if(ordenServicioDTO == null)
			throw new BusinessException("No se ha encontrado la orden de servicio para asignar la imagen");
		
		OrdenEncuestaDTO ordenEncuestaDTO = null;
		ProcesoDTO procesoDTO = null;
		PreguntasDTO preguntaDTO = null;
		IncidenciaDTO incidenciaDTO = null;
		TipoExpedienteDTO tipoExpedienteDTO = null;
		
		if(imagen.getIdOdsEncuesta()!=null) {
			 ordenEncuestaDTO = usuarioEncuesta.consultaByIdOrdernIdEncuesta(imagen.getIdOrdenServicio(), imagen.getIdOdsEncuesta());
		}
		if(imagen.getIdPregunta()!=null) {
			preguntaDTO = preguntaDAO.findOne(imagen.getIdPregunta());
		}
		if(imagen.getIdProceso()!=null) {
			procesoDTO =  procesoDAO.findOne(imagen.getIdProceso());
		}
		if(imagen.getIdIncidencia()!=null) {
			incidenciaDTO = incidenciaDAO.findOne(imagen.getIdIncidencia());
		}
		if(imagen.getIdTipoExpediente()!=null) {
			tipoExpedienteDTO = tipoExpedienteDAO.findOne(imagen.getIdTipoExpediente());
		}
		

		for (ImagenVO archivo : expedientes) {
			ExpedientesImgDTO registro = null;
			
			if(archivo.getNuOrden()!=null) {
				nuOrden = archivo.getNuOrden();
			}else {
				nuOrden++;
			}
			
			if (archivo.getIdExpedienteODS() != null) {
				 registro  = expedienteImgDAO.findOne(archivo.getIdExpedienteODS());
					registro.setIdOrdenServicio(ordenServicioDTO);
					registro.setIdOdsEncuesta(ordenEncuestaDTO !=null ? ordenEncuestaDTO : null);
					registro.setIdPregunta(preguntaDTO!=null ? preguntaDTO : null);
					registro.setIncidencia(incidenciaDTO!=null ? incidenciaDTO: null);
					registro.setTipoExpediente(tipoExpedienteDTO!=null ? tipoExpedienteDTO : null);	
					registro.setIdProceso(procesoDTO!=null ? procesoDTO : null);
					registro.setNuOrden(nuOrden);
					registro.setNbExpedienteODS(archivo.getNbExpedienteODS());
					registro.setCdTipoArchivo(archivo.getCdTipoArchivo());
					registro.setLbExpedienteODS(archivo.getLbExpedienteODS());
					registro.setTxRutaExpedienteODS(null);
					registro.setStActivo(ACTIVO);
					registro.setFhCreacion(fechaCarga);
					registro.setIdUsrCreacion(idUsuario);
					registro.setFhModifica(fechaCarga);
					registro.setIdUsrModifica(idUsuario);
					expedienteImgDAO.saveOrUpdate(registro);
			} else {
				 registro = new ExpedientesImgDTO();
					registro.setIdOrdenServicio(ordenServicioDTO);
					registro.setIdOdsEncuesta(ordenEncuestaDTO !=null ? ordenEncuestaDTO : null);
					registro.setIdPregunta(preguntaDTO!=null ? preguntaDTO : null);
					registro.setIncidencia(incidenciaDTO!=null ? incidenciaDTO: null);
					registro.setTipoExpediente(tipoExpedienteDTO!=null ? tipoExpedienteDTO : null);			
					registro.setIdProceso(procesoDTO!=null ? procesoDTO : null);
					registro.setNuOrden(nuOrden);
					registro.setNbExpedienteODS(archivo.getNbExpedienteODS());
					registro.setCdTipoArchivo(archivo.getCdTipoArchivo());
					registro.setLbExpedienteODS(archivo.getLbExpedienteODS());
					registro.setTxRutaExpedienteODS(null);
					registro.setStActivo(ACTIVO);
					registro.setFhCreacion(fechaCarga);
					registro.setIdUsrCreacion(idUsuario);
					registro.setFhModifica(fechaCarga);
					registro.setIdUsrModifica(idUsuario);
					expedienteImgDAO.saveOrUpdate(registro);
					archivo.setIdExpedienteODS(registro.getIdExpedienteODS());
					
			}
		}

		return expedientes;
	}

	@Override
	@Transactional
	public List<TipoExpedienteVO> getTipoExpediente() {
		List<TipoExpedienteVO> respuesta = tipoExpedienteDAO.getTipoExpedientes();
		return respuesta;
	}

	@Override
	@Transactional
	public List<ImagenVO> delListEvidencia(List<ImagenVO> expedientes, Long idUsuario) { 
		Date fechaEliminacion = new Date();
		for (ImagenVO imagen : expedientes) {
			ExpedientesImgDTO registro = expedienteImgDAO.findOne(imagen.getIdExpedienteODS());
			if (registro != null) {
				registro.setStActivo(BORRAR);
				registro.setFhModifica(fechaEliminacion);
				registro.setIdUsrModifica(idUsuario);
				expedienteImgDAO.update(registro);
				imagen.setLbExpedienteODS(null);
			}else {
				expedientes = null;
				break;
			}
		}
		return expedientes;
	}

	@Override
	@Transactional
	public List<ImagenVO> getInfoExpedienteByNivel(Long nuOrderServicio, Long idValorBuscar, String cdNivel) {
		List<ImagenVO> respuesta =null;
		switch (cdNivel) {
		case CDOS:
			respuesta = expedienteImgDAO.getImgByOrdenServicio(nuOrderServicio);
			break;

		case CDPROCESO:
			respuesta = expedienteImgDAO.getImgByProceso(nuOrderServicio, idValorBuscar);
			break;

		case CDENCUESTA:
			OrdenEncuestaDTO ordEncuest=usuarioEncuesta.consultaByIdOrdernIdEncuesta(nuOrderServicio, idValorBuscar);
			Long idOrdenEncuesta=ordEncuest.getIdUsuarioEncuesta();
			respuesta = expedienteImgDAO.getImgByEncuesta(nuOrderServicio, idOrdenEncuesta);
			break;

		case CDPREGUNTA:
			respuesta = expedienteImgDAO.getImgByPregunta(nuOrderServicio, idValorBuscar);
			break;
			
		default:
			respuesta = new ArrayList<ImagenVO>();
			break;
		}

		return respuesta;
	}
	
	@Override
	@Transactional
	public String saveImagenIncidencia(List<ImagenVO> listImagenVO, IncidenciaDTO incidenciaDTO) throws BusinessException {
		String respuesta = "";
		ConfiguracionVO configuracionVO;
		try {
			configuracionVO = catalogoService.configuracion("TIE051D_NU_MAX_IMAGENES");
			int nuMaximoImagenes = Integer.parseInt(configuracionVO.getCdValorPConfig());
			
			if(listImagenVO.size() > nuMaximoImagenes)
				throw new BusinessException("Ha llegado al número ("+nuMaximoImagenes+") maxímo de imagenes admitidas");
			
			for(ImagenVO imagenVO : listImagenVO){
				if (imagenVO != null) {
					if (imagenVO.getLbExpedienteODS() != null && imagenVO.getNbExpedienteODS() != null && imagenVO.getNbExpedienteODS() != "" &&
							imagenVO.getCdTipoArchivo() != null	&& imagenVO.getCdTipoArchivo() != "") {
						UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
						ExpedientesImgDTO expedientesImgDTO = new ExpedientesImgDTO();
						expedientesImgDTO.setNbExpedienteODS(imagenVO.getNbExpedienteODS());
						expedientesImgDTO.setCdTipoArchivo(imagenVO.getCdTipoArchivo());
						expedientesImgDTO.setLbExpedienteODS(imagenVO.getLbExpedienteODS());
						expedientesImgDTO.setIncidencia(incidenciaDTO);
						expedientesImgDTO.setNuOrden(null);
						expedientesImgDTO.setCdTipoArchivo(imagenVO.getCdTipoArchivo());
						expedientesImgDTO.setStActivo(true);
						expedientesImgDTO.setFhCreacion(new Date());
						expedientesImgDTO.setIdUsrCreacion(usuario.getId());
						expedientesImgDTO.setFhModifica(new Date());
						expedientesImgDTO.setIdUsrModifica(usuario.getId());
						TipoExpedienteDTO tipoExpedienteDTO = tipoExpedienteDAO.findOne(imagenVO.getIdTipoExpediente());
//						tipoExpedienteDTO = ResponseConverter.copiarPropiedadesFull(imagenVO.getTipoExpediente(), TipoExpedienteDTO.class);
						expedientesImgDTO.setTipoExpediente(tipoExpedienteDTO);
						try {
							expedienteImgDAO.save(expedientesImgDTO);
							respuesta = "";
						} catch (Exception e) {
							return "Error al guardar la imagen incidencia. ";
						}
					}
				}
			}
			return respuesta;
		} catch (NotFoundException e1) {
			e1.printStackTrace();
		}
		
		return respuesta;
	}
	
	@Transactional(readOnly=true)
	public List<CompresorImgConfigVO> getAllConfigCompress(){
		List<CompresorImgConfigDTO> listConfDTO=compresorImgConfigDAO.getAllConfoCompress();
		List<CompresorImgConfigVO> listConfi=ResponseConverter.converterLista(new ArrayList<>(), listConfDTO,CompresorImgConfigVO.class);
		
		return listConfi;
	}
}
