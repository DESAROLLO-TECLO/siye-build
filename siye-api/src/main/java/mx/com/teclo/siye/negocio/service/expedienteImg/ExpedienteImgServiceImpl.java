package mx.com.teclo.siye.negocio.service.expedienteImg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PreguntasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.SeccionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaDAO;
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
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.tipoexpediente.TipoExpedienteDTO;
import mx.com.teclo.siye.persistencia.mybatis.dao.proceso.ProcesoDAO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
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
	

	private static Boolean ACTIVO = true, BORRAR = false;
	private static String PLACA = "PLACA", OS = "ORDEN_SERVICIO", VIN = "VIN";
	private final String CDOS = "ORDEN_SERVICIO", CDPLAN = "PLAN", CDENCUESTA = "ENCUESTA", CDPREGUNTA = "PREGUNTA";

	@Override
	@Transactional
	public List<CargaExpedienteImgVO> getInformacionExpediente(String tipoBusqueda, String valor) {
		List<OrdenServicioDTO> OSDTO = null;
		List<CargaExpedienteImgVO> respuesta = new ArrayList<CargaExpedienteImgVO>();
		List<Long> expedientes = new ArrayList<Long>();

		ConfiguracionOSDTO config = configuracionDAO.findOne(1L);
		Long numeroMaximo = Long.parseLong(config.getCdValorConfig());

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
				OSVO.setCdClasif(os.getIdOrdenServicio() + "OS");
				expedientes.add(os.getIdOrdenServicio());

				if (os.getPlan().getIdPlan() != null) {
					List<ExpedienteNivelProcesoVO> procesosByOS = planProcesoDAO.getProcesosPlanVO(os.getPlan().getIdPlan());
					if (!procesosByOS.isEmpty()) {
						for (ExpedienteNivelProcesoVO proceso : procesosByOS) {
							proceso.setNbNivel("Proceso");
							proceso.setCdClasif(proceso.getIdProceso() + "PRO");
							proceso.setName(proceso.getCdProceso());
							List<ExpedienteNivelEncuestaVO> LisEncuestas = procesoEncuestaDAO.getEncuestasByProcesoVO(proceso.getIdProceso());
							if (!LisEncuestas.isEmpty()) {
								for (ExpedienteNivelEncuestaVO encuesta : LisEncuestas) {
									encuesta.setNbNivel("Encuesta");
									encuesta.setCdClasif(encuesta.getIdEncuesta() + "ENC");
									encuesta.setName(encuesta.getCdEncuesta());
									List<ExpedienteNivelPreguntaVO> listPreguntas = seccionDAO.getPreguntasByEncuestaVO(encuesta.getIdEncuesta());
									for (ExpedienteNivelPreguntaVO pregunta : listPreguntas) {
										pregunta.setNbNivel("Pregunta");
										pregunta.setName(pregunta.getCdPregunta());
										pregunta.setCdClasif(
												encuesta.getIdEncuesta() + "" + pregunta.getIdPregunta() + "PREG");
									}
									encuesta.setListImageClasif(listPreguntas);
								}
							}
							proceso.setListImageClasif(LisEncuestas);
						}
					}
					OSVO.setProcesos(procesosByOS);
				}
				respuesta.add(OSVO);
			}
		}

		// Consulta De imegenes y fintrado por OS
		if(expedientes.size()>0) {
			List<ImagenVO> listImg = expedienteImgDAO.getAllExpedientesImgVO(expedientes);
			if (!listImg.isEmpty()) {
				for (ImagenVO img : listImg) {
					CargaExpedienteImgVO expediente = null;
					for (CargaExpedienteImgVO exp : respuesta) {
						if (img.getIdOrdenServicio() == exp.getIdOrdenServicio()) {
							expediente = exp;
							break;
						}
					}
					if(img.getIdOrdenServicio()!= null  && img.getIdProceso()==null && img.getIdOdsEncuesta()==null && img.getIdPregunta()==null) {
						///nivel Orden Servicio
						List<ImagenVO> imagenes = expediente.getImagenes()!=null ? expediente.getImagenes() : new ArrayList<ImagenVO>() ;
						imagenes.add(img);
						expediente.setImagenes(imagenes);
						
					}else if(img.getIdOrdenServicio()!= null && img.getIdProceso()!=null && img.getIdOdsEncuesta()==null && img.getIdPregunta()==null) {
						// nivel proceso
						expediente.setProcesos(addImgProceso(expediente.getProcesos(), img));
						
					}else if(img.getIdOrdenServicio()!= null && img.getIdOdsEncuesta()!=null && img.getIdPregunta()==null) {
						// nivel Encuesta
						expediente.setProcesos(addImgEncuestaPregunta(expediente.getProcesos(), img));
						
					}else if(img.getIdOrdenServicio()!= null && img.getIdProceso()!=null && img.getIdOdsEncuesta()!=null && img.getIdPregunta()!=null) {
						//nivel pregunta
						expediente.setProcesos(addImgEncuestaPregunta(expediente.getProcesos(), img));	
					}
				}
			}
		}
		return respuesta;
	}

	// Agregar imagen nivelProceso
	public List<ExpedienteNivelProcesoVO> addImgProceso(List<ExpedienteNivelProcesoVO> procesos, ImagenVO imagen) {
		List<ImagenVO> imagenes = new ArrayList<ImagenVO>();
		for (ExpedienteNivelProcesoVO proceso : procesos) {
			if (imagen.getIdProceso().equals(proceso.getIdProceso())) {
				imagenes = proceso.getImagenes()!=null ? proceso.getImagenes() : new ArrayList<ImagenVO>() ;
				imagenes.add(imagen);
				proceso.setImagenes(imagenes);
				break;
			}
		}
		return procesos;
	};

	public List<ExpedienteNivelProcesoVO> addImgEncuestaPregunta(List<ExpedienteNivelProcesoVO> procesos,
			ImagenVO imagen) {
		List<ImagenVO> imagenes = null;
		Boolean encontre = false;
		for (ExpedienteNivelProcesoVO pro : procesos) {
			if (encontre) {
				break;
			} else {
				for (ExpedienteNivelEncuestaVO encuesta : pro.getListImageClasif()) {
					if (imagen.getIdOdsEncuesta().equals(encuesta.getIdEncuesta())) {
						if (imagen.getIdPregunta() != null) {
							encuesta.setListImageClasif(addImgPregunta(encuesta.getListImageClasif(), imagen));
							encontre = true;
							break;
						} else {
							imagenes = encuesta.getImagenes() != null ? encuesta.getImagenes(): new ArrayList<ImagenVO>();
							imagenes.add(imagen);
							encuesta.setImagenes(imagenes);
							encontre = true;
							break;
						}
					}
				}
			}
		}
		return procesos;
	};

	public List<ExpedienteNivelPreguntaVO> addImgPregunta(List<ExpedienteNivelPreguntaVO> preguntas, ImagenVO imagen) {
		List<ImagenVO> imagenes = new ArrayList<ImagenVO>();
		for (ExpedienteNivelPreguntaVO pregunta : preguntas) {
			if (imagen.getIdPregunta().equals(pregunta.getIdPregunta())) {
				imagenes = pregunta.getImagenes() !=null ? pregunta.getImagenes(): new ArrayList<ImagenVO>();
				imagenes.add(imagen);
				pregunta.setImagenes(imagenes);
				break;
			}
		}
		return preguntas;
	}

	@Transactional
	@Override
	public List<ImagenVO> saveExpediente(List<ImagenVO> expedientes, Long idUsuario) {
		Date fechaCarga = new Date();
		ConfiguracionOSDTO config = configuracionDAO.findOne(1L);
		Long numeroMaximo = Long.parseLong(config.getCdValorConfig());

		// Clasificar el nivel de las miamgenes
		ImagenVO expediente = expedientes.get(0);
		
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

		return expedientes;
	};

	public List<ImagenVO> saveImagenEvidencia(List<ImagenVO> expedientes, Long idUsuario, Date fechaCarga,Long nuMaximoImagenes) {
		
		if (expedientes.size() <= nuMaximoImagenes) {
			ImagenVO imagen = expedientes.get(0);
			OrdenServicioDTO ordenServicioDTO = ordenServicioDAO.findOne(imagen.getIdOrdenServicio());
			OrdenEncuestaDTO ordenEncuestaDTO = null;
			ProcesoDTO procesoDTO = null;
			PreguntasDTO preguntaDTO = null;
			IncidenciaDTO incidenciaDTO = null;
			TipoExpedienteDTO tipoExpedienteDTO = null;
			
			if(imagen.getIdOdsEncuesta()!=null) {
				 ordenEncuestaDTO = usuarioEncuesta.findOne(imagen.getIdOdsEncuesta());
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
				if (archivo.getIdExpedienteODS() != null) {
					 registro  = expedienteImgDAO.findOne(archivo.getIdExpedienteODS());
						registro.setIdOrdenServicio(ordenServicioDTO);
						registro.setIdOdsEncuesta(ordenEncuestaDTO !=null ? ordenEncuestaDTO : null);
						registro.setIdPregunta(preguntaDTO!=null ? preguntaDTO : null);
						registro.setIncidencia(incidenciaDTO!=null ? incidenciaDTO: null);
						registro.setTipoExpediente(tipoExpedienteDTO!=null ? tipoExpedienteDTO : null);	
						registro.setIdProceso(procesoDTO!=null ? procesoDTO : null);
						registro.setNuOrden(null);
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
						registro.setNuOrden(null);
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
		} else {
			// exepcion
			expedientes= null;

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
			}
		}
		return expedientes;
	}

	@Override
	@Transactional
	public List<ImagenVO> getInfoExpedienteByNivel(Long nuOrderServicio, Long idValorBuscar, String cdNivel) {
		List<ImagenVO> respuesta =null;;
		switch (cdNivel) {
		case CDOS:
			respuesta = expedienteImgDAO.getImgByOrdenServicio(nuOrderServicio);
			break;

		case CDPLAN:
			respuesta = expedienteImgDAO.getImgByPlan(nuOrderServicio, idValorBuscar);
			break;

		case CDENCUESTA:
			respuesta = expedienteImgDAO.getImgByEncuesta(nuOrderServicio, idValorBuscar);
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

}
