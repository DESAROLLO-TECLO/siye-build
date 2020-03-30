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
import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.ExpedienteImgDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesos.IEProcesosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.tipoExpediente.TipoExpedienteDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;
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

	private static Boolean ACTIVO = true, BORRAR = false;
	private static String PLACA = "PLACA", OS = "ORDEN DE SERVICIO", VIN = "VIN";

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

		if (OSDTO != null) {
			for (OrdenServicioDTO os : OSDTO) {
				CargaExpedienteImgVO OSVO = new CargaExpedienteImgVO();
				OSVO.setNameConsesionario(os.getVehiculo().getConsecionario().getNbConsecion());
				OSVO.setNumvim(os.getVehiculo().getCdVin());
				OSVO.setPlaca(os.getVehiculo().getCdPlacaVehiculo());
				OSVO.setCdOrdenServicio(os.getCdOrdenServicio());
				OSVO.setNuMaxImg(numeroMaximo);
				OSVO.setIdOrdenServicio(os.getIdOrdenServicio());
				expedientes.add(os.getIdOrdenServicio());

				if (os.getPlan().getIdPlan() != null) {
					List<ExpedienteNivelProcesoVO> procesosByOS = planProcesoDAO.getProcesosPlanVO(os.getPlan().getIdPlan());
					if (!procesosByOS.isEmpty()) {
						for (ExpedienteNivelProcesoVO proceso : procesosByOS) {
							List<ExpedienteNivelEncuestaVO> LisEncuestas = procesoEncuestaDAO.getEncuestasByProcesoVO(proceso.getIdProceso());
							if (!LisEncuestas.isEmpty()) {
								for (ExpedienteNivelEncuestaVO encuesta : LisEncuestas) {								
									List<ExpedienteNivelPreguntaVO> listPreguntas = seccionDAO.getPreguntasByEncuestaVO(encuesta.getIdEncuesta());
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
		
		//Consulta De imegenes y fintrado por OS 
		List<ImagenVO> listImg = expedienteImgDAO.getAllExpedientesImgVO(expedientes);
		if(!listImg.isEmpty()) {
			for(ImagenVO img : listImg) {
				CargaExpedienteImgVO expediente = null;				
				for(CargaExpedienteImgVO exp: respuesta) {
					if(img.getIdOrdenServicio() == exp.getIdOrdenServicio()) {
						expediente= exp;
						break;
					}
				}
				
				if(img.getIdProcesoEncuesta()!=null) {
					if(img.getIdOdsEncuesta()!=null) {
						expediente.setProcesos(addImgEncuestaPregunta(expediente.getProcesos(),img));
					}else {
						expediente.setProcesos(addImgProceso(expediente.getProcesos(), img));
					}		
				}else {
					List<ImagenVO> imagenes = expediente.getImagenes();
					imagenes.add(img);
				}
			}
		}
		
		return respuesta;
	}

	//Agregar imagen nivelProceso
	public List<ExpedienteNivelProcesoVO> addImgProceso(List<ExpedienteNivelProcesoVO> procesos,ImagenVO imagen) {
		List<ImagenVO> imagenes = new ArrayList<ImagenVO>();	
		for(ExpedienteNivelProcesoVO proceso: procesos) {
			if(imagen.getIdProcesoEncuesta().equals(proceso.getIdProceso())) {
				imagenes = proceso.getImagenes();
				imagenes.add(imagen);
				break;
			}
		}
		return procesos;
	};
	
	public List<ExpedienteNivelProcesoVO> addImgEncuestaPregunta(List<ExpedienteNivelProcesoVO> procesos, ImagenVO imagen){
		List<ImagenVO> imagenes = new ArrayList<ImagenVO>();
		Boolean encontre = false;
		for(ExpedienteNivelProcesoVO pro: procesos) {
			if(!encontre) {
				break;
			}else {
				for(ExpedienteNivelEncuestaVO encuesta: pro.getListImageClasif()) {
					if(imagen.getIdOdsEncuesta().equals(encuesta.getIdEncuesta())) {
						if(imagen.getIdPregunta()!=null) {
							encuesta.setListImageClasif(addImgPregunta(encuesta.getListImageClasif(), imagen));
							encontre = true;
							break;
						}else {
							imagenes = encuesta.getImagenes();
							imagenes.add(imagen);
							encontre = true;
							break;
						}
					}
				}
			}
		}
		return procesos;
	};
	
	public List<ExpedienteNivelPreguntaVO> addImgPregunta(List<ExpedienteNivelPreguntaVO> preguntas, ImagenVO imagen){
		List<ImagenVO> imagenes = new ArrayList<ImagenVO>();
		for(ExpedienteNivelPreguntaVO pregunta: preguntas) {
			if(imagen.getIdPregunta().equals(pregunta.getIdPregunta())) {
				imagenes = pregunta.getImagenes();
				imagenes.add(imagen);
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
		
		//Clasificar el nivel de las miamgenes
		ImagenVO expediente = expedientes.get(0);
		
			if(expediente.getIdOrdenServicio()!=null) {
				if(expediente.getIdProcesoEncuesta()!=null) {
					if(expediente.getIdOdsEncuesta()!=null) {
						if(expediente.getIdPregunta()!= null) {
							//Nivel Pregunta
							PreguntasDTO numax = preguntaDAO.findOne(expediente.getIdPregunta());	
							expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numax.getNuMaxImagenes());
						}else {
							//Nivel Encuesta
							EncuestasDTO numax = encuestasDAO.findOne(expediente.getIdOdsEncuesta());
							expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numax.getNuMaxImagenes());
						}
					}else {
						//Nivel Proceso
						IEprocesosDTO numax = ieProcesosDAO.findOne(expediente.getIdProcesoEncuesta());
						expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numax.getNuMaxImagenes());
					}
				}else {
					//Nivel OS
					expedientes = saveImagenEvidencia(expedientes, idUsuario, fechaCarga, numeroMaximo);
				}
			}else { 
				// Incidencias
			}
		
		
		return expedientes;
	};
	
	public List<ImagenVO> saveImagenEvidencia(List<ImagenVO> expedientes, Long idUsuario, Date fechaCarga, Long nuMaximoImagenes) {
		ProcesoEncuestaDTO PEDTO = new ProcesoEncuestaDTO();
		
		if(expedientes.size()<= nuMaximoImagenes) {
			ImagenVO imganeGral = expedientes.get(0);
			OrdenServicioDTO ordenServicioDTO = ordenServicioDAO.findOne(imganeGral.getIdOrdenServicio());
			if(imganeGral.getIdProcesoEncuesta()!=null){
				PEDTO = procesoEncuestaDAO.findOne(imganeGral.getIdProcesoEncuesta());		
			}
			
			for(ImagenVO archivo: expedientes) {
				ExpedientesImgDTO registro = new ExpedientesImgDTO();
				if(archivo.getIdExpedienteODS()!=null) {
					registro = expedienteImgDAO.findOne(archivo.getIdExpedienteODS());
					registro.setLbExpedienteODS(archivo.getLbExpedienteODS());
					registro.setFhModifica(fechaCarga);
					registro.setIdUsrModifica(idUsuario);
					expedienteImgDAO.update(registro);					
				}else {
					registro = new ExpedientesImgDTO();
					registro.setIdOrdenServicio(ordenServicioDTO);
					registro.setIdOdsEncuesta(archivo.getIdOdsEncuesta());
					registro.setIdProcesoEncuesta(PEDTO);
					registro.setIdPregunta(registro.getIdPregunta());
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
					expedienteImgDAO.save(registro);
					archivo.setIdExpedienteODS(registro.getIdExpedienteODS());
				}					
			   }
			}else {
				//exepcion 
				
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
		for(ImagenVO imagen: expedientes) {
			ExpedientesImgDTO registro = expedienteImgDAO.findOne(imagen.getIdExpedienteODS());
			if(registro!=null) {
				registro.setStActivo(BORRAR);
				registro.setFhModifica(fechaEliminacion);
				registro.setIdUsrModifica(idUsuario);
				expedienteImgDAO.save(registro);
				imagen.setLbExpedienteODS(null);
			}	
		}
		return expedientes;
	}


}
