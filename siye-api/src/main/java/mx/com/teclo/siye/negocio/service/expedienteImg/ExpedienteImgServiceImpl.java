package mx.com.teclo.siye.negocio.service.expedienteImg;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PreguntasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.ExpedienteImgDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesos.IEProcesosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

@Service
public class ExpedienteImgServiceImpl implements ExpedienteImgService {
	
	@Autowired
	private OrdenServicioDAO ordenServicioDAO;
	
	@Autowired 
	private ProcesoEncuestaDAO procesoEncuestaDAO;
	
	@Autowired
	private IEProcesosDAO ieProcesosDAO;
	
	@Autowired 
	private ExpedienteImgDAO expedienteImgDAO;
	
	@Autowired
	private PreguntasDAO preguntaDAO;
	
	@Autowired
	private EncuestasDAO encuestasDAO;
	
	
	private static Boolean ACTIVO=true;

	@Override
	@Transactional
	public List<ExpedienteImgVO> saveExpediente(List<ExpedienteImgVO> expedientes, Long idUsuario) {
		Date fechaCarga = new Date();
		
		for(ExpedienteImgVO expediente : expedientes){
			Long numeroMaximoImagenes = 0l;
			
			if(expediente.getIdOrdenServicio()!=null) {
				if(expediente.getIdProcesoEncuesta()!=null) {
					if(expediente.getIdOdsEncuesta()!=null) {
						if(expediente.getIdPregunta()!= null) {
							//Nivel Pregunta
							PreguntasDTO numax = preguntaDAO.findOne(expediente.getIdPregunta());
							expediente = saveImagenEvidencia(expediente, idUsuario, fechaCarga, numax.getNuMaxImagenes());				
						}else {
							//Nivel Encuesta
							EncuestasDTO numx = encuestasDAO.findOne(expediente.getIdOdsEncuesta());
							expediente = saveImagenEvidencia(expediente, idUsuario, fechaCarga, numeroMaximoImagenes);
						}
					}else {
						//Nivel Proceso
						IEprocesosDTO numax = ieProcesosDAO.findOne(expediente.getIdProcesoEncuesta());
						expediente = saveImagenEvidencia(expediente, idUsuario, fechaCarga, numax.getNuMaxImagenes());
					}
					
				}else {
					//Nivel OS
					expediente = saveImagenEvidencia(expediente, idUsuario, fechaCarga, numeroMaximoImagenes);
					
				}
			}else {
				// Incidencias OrdenServicioDTO ordenServicioDTO = ordenServicioDAO.findOne(expediente.getIdOrdenServicio());
			}
		}
		return expedientes;
	};
	
	public ExpedienteImgVO saveImagenEvidencia(ExpedienteImgVO expediente, Long idUsuario, Date fechaCarga, Long nuMaximoImagenes) {
		if(expediente.getArchivos().size() <= nuMaximoImagenes) {
			OrdenServicioDTO ordenServicioDTO = ordenServicioDAO.findOne(expediente.getIdOrdenServicio());
			ProcesoEncuestaDTO PEDTO = new ProcesoEncuestaDTO();
			if(expediente.getIdProcesoEncuesta()!=null){
				PEDTO = procesoEncuestaDAO.findOne(expediente.getIdProcesoEncuesta());		
			}
			
			for(ImagenVO archivo: expediente.getArchivos()) {
				ExpedientesImgDTO registro = null;	
				if(archivo.getIdExpedienteODS()!=null) {
					//edicion
					registro = expedienteImgDAO.findOne(archivo.getIdExpedienteODS());
					if(registro!=null) {
						registro.setCdTipoArchivo(archivo.getCdTipoArchivo());
						registro.setNbExpedienteODS(archivo.getNbExpedienteODS());
						registro.setLbExpedienteODS(archivo.getLbExpedienteODS());
						registro.setFhModifica(fechaCarga);
						registro.setIdUsrModifica(idUsuario);
						expedienteImgDAO.update(registro);
					}
				}else {
					//Alta
					registro = new ExpedientesImgDTO();
					registro.setIdOrdenServicio(ordenServicioDTO);
					registro.setIdOdsEncuesta(expediente.getIdOdsEncuesta());
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
			//Excepcion
		}
		return expediente;
	};
	
	
	

}
