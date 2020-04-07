package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.EstatusCalificacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.SeccionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaIntentoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaRespuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EstatusCalificacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaIntentosVO;
import mx.com.teclo.siye.util.enumerados.CalificacionEnum;



@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	private UsuarioEncuestaIntentoDAO usuarioEncuestaIntentoDAO;
	@Autowired
	private UsuarioEncuestaRespuestaDAO usuarioEncuestaRespuestaDAO;
	@Autowired
	private EstatusCalificacionDAO estatusCalificacionDAO;
	@Autowired
	private StEncuestaDAO stEncuestaDAO;
	@Autowired
	private SeccionDAO seccionDAO;
	
	@Override
	@Transactional
	public UsuarioEncuestaIntentosVO calificarIntentoEncuesta(Long idIntentoEncuesta, Boolean mayorCalifIntento) {
		List<EstatusCalificacionDTO> listaEstatusCalifacaciones = estatusCalificacionDAO.calificacion();
		UsuarioEncuestaIntentosDTO usuarioEncuestaIntentosDTO = usuarioEncuestaIntentoDAO.getIntentoById(idIntentoEncuesta);
		UsuarioEncuestaIntentosVO encuestaIntentos = new UsuarioEncuestaIntentosVO();
		if (usuarioEncuestaIntentosDTO != null) {
			List<UsuaroEncuestaRespuestaDTO> usuaroEncuestaRespuestaDTO = usuarioEncuestaRespuestaDAO.getRespuestas(
					usuarioEncuestaIntentosDTO.getIdUsuEncuIntento(),usuarioEncuestaIntentosDTO.getUsuarioEncuesta().getEncuesta().getIdEncuesta());
			int respuestasCorrectas = 0;
			int respuestasNoRespondidas = 0;
			int totalPreguntas = usuaroEncuestaRespuestaDTO.size();
			int calAprobada= usuarioEncuestaIntentosDTO.getUsuarioEncuesta().getEncuesta().getNuCalificacionApro();
			
			for (UsuaroEncuestaRespuestaDTO uer : usuaroEncuestaRespuestaDTO) {
				
				if (uer.getOpcionesDTO() == null) {
					respuestasNoRespondidas++;
				} else if (uer.getOpcionesDTO().getStCorrecto()== null) {
					respuestasNoRespondidas++;
				} else if (uer.getOpcionesDTO().getStCorrecto().equals(1)) {
					uer.setStCorrecto(1);
					respuestasCorrectas++;
				}
			}
			encuestaIntentos.setNuPreguntasCorrectas(respuestasCorrectas);
			encuestaIntentos.setNuPreguntasIncorr(totalPreguntas - respuestasCorrectas);
			encuestaIntentos.setNuPreguntasVacias(respuestasNoRespondidas);
			encuestaIntentos.setNuCalificacion(respuestasCorrectas > 0 ? (respuestasCorrectas * 100) / totalPreguntas : 0 );
			
			usuarioEncuestaIntentosDTO.setNuCalificacion(encuestaIntentos.getNuCalificacion());
			usuarioEncuestaIntentosDTO.setNuPreguntasCorrectas(encuestaIntentos.getNuPreguntasCorrectas());
			usuarioEncuestaIntentosDTO.setNuPreguntasIncorr(encuestaIntentos.getNuPreguntasIncorr());
			usuarioEncuestaIntentosDTO.setNuPreguntasVacias(encuestaIntentos.getNuPreguntasVacias());
			StEncuestaDTO seDTO = stEncuestaDAO.encuesta("FIN");
			usuarioEncuestaIntentosDTO.setStEncuesta(seDTO);
			usuarioEncuestaIntentosDTO.setStCalificacion(getStatusCalificacion(calAprobada,encuestaIntentos.getNuCalificacion(),listaEstatusCalifacaciones));
 			usuarioEncuestaIntentosDTO.setNuSeccionesRsp( contarSecciones(usuarioEncuestaIntentosDTO.getIdUsuEncuIntento().intValue()));
 			usuarioEncuestaIntentoDAO.update(usuarioEncuestaIntentosDTO);
 			
 			if(mayorCalifIntento) {
	 			// DEBEMOS OBTENER LOS DEMÁS INTENTOS PARA VERIFICAR 
	 			// LAS CALIFICACIÓN MAS ALTA Y REALIZAR EL ORDENAMIENTO
	 			// SOBRE ESE DATO
	 			
	 			List<UsuarioEncuestaIntentosDTO> ueiListDTO = usuarioEncuestaIntentoDAO.intentoMismaEncuesta(usuarioEncuestaIntentosDTO.getUsuarioEncuesta().getIdUsuarioEncuesta());
	 			
	 			if(ueiListDTO.isEmpty()) {
	 				// SI LA LISTA DE RESULTADO ES VACÍO DEBEMOS SETEAR EL REGISTRO ACTUAL CON
	 				// ST_MOSTRAR = TRUE
	 				usuarioEncuestaIntentosDTO.setStMostrar(true);
	 			}else {
	 				// EXISTEN INTENTOS ACTUALES DE LA MISMA ENCUESTAS
	 				// LO CUAL SIFNIFICA CALCULAR LA CALIFICACIÓN MAS ALTA
	// 				determinarCalifAlta(ueiListDTO, usuarioEncuestaIntentosDTO);
	 				
	 				for(UsuarioEncuestaIntentosDTO ueiDTO: ueiListDTO){
	 					if(usuarioEncuestaIntentosDTO != null && ueiDTO.getIdUsuEncuIntento().equals(usuarioEncuestaIntentosDTO.getIdUsuEncuIntento())) {
	 						ueiDTO = usuarioEncuestaIntentosDTO;
	 						ueiDTO.setStMostrar(false);
	 						usuarioEncuestaIntentoDAO.update(ueiDTO);
	 						//break;
	 					}
	 				}
	 				determinarCalifAlta(ueiListDTO.get(0), usuarioEncuestaIntentosDTO);
	 			}
 			}
		}
 		return encuestaIntentos;
	}
	
	@Transactional
	@Override
	public Boolean determinarCalifAlta (List<UsuarioEncuestaIntentosDTO> ueiListDTO, UsuarioEncuestaIntentosDTO current) {
		for(UsuarioEncuestaIntentosDTO ueiDTO: ueiListDTO){
			if(current != null && ueiDTO.getIdUsuEncuIntento().equals(current.getIdUsuEncuIntento())) {
				ueiDTO = current;
				ueiDTO.setStMostrar(false);
				usuarioEncuestaIntentoDAO.update(ueiDTO);
				//break;
			}
		}
		ueiListDTO = orderList(ueiListDTO);
		// AGARRAMOS EL PRIMER ELEMENTO Y LE ACTUALIZAMOS EL ST_MOSTRAR
		ueiListDTO.get(ueiListDTO.size() - 1).setStMostrar(true);
		usuarioEncuestaIntentoDAO.update(ueiListDTO.get(ueiListDTO.size() - 1));
		return true;
	}
	
	@Transactional
	private Boolean determinarCalifAlta (UsuarioEncuestaIntentosDTO ueiDTO, UsuarioEncuestaIntentosDTO current) {
		ueiDTO.setStMostrar(true);
		return true;
	}
	
	private List<UsuarioEncuestaIntentosDTO> orderList(List<UsuarioEncuestaIntentosDTO> l){
		Collections.sort(l, new Comparator<UsuarioEncuestaIntentosDTO>(){
		  public int compare(UsuarioEncuestaIntentosDTO o1, UsuarioEncuestaIntentosDTO o2){
		     return o1.getNuCalificacion().compareTo(o2.getNuCalificacion());
		  }
		});
		return l;
	}
	
	
	private EstatusCalificacionDTO getStatusCalificacion(int calAprobada, int nuCalificacion,List<EstatusCalificacionDTO> listaEstatusCalifacaciones ) {
		EstatusCalificacionDTO calificacionDTO = null;
		if(nuCalificacion >= calAprobada) {
			for (EstatusCalificacionDTO  statusCalificacionDTO : listaEstatusCalifacaciones )
			{  
			   if (statusCalificacionDTO.getCdStCalificacion().equals( CalificacionEnum.APROBADO.getCdStCalificacion()))
				   calificacionDTO= statusCalificacionDTO;
			}
 			
		}else {
			for (EstatusCalificacionDTO  statusCalificacionDTO : listaEstatusCalifacaciones )
			{
			   if (statusCalificacionDTO.getCdStCalificacion().equals( CalificacionEnum.REPROBADO.getCdStCalificacion())) {
				   calificacionDTO= statusCalificacionDTO;
			   }
			}
		}
		return calificacionDTO;
	}
	
	@Override
	public Integer contarSecciones(Integer idEncuesta) {
		 List<Integer> listaSecciones= null;
		 Integer secciones = 0;
		 listaSecciones = seccionDAO.seccionesContestadasEncuesta(idEncuesta);
		 if(listaSecciones!= null)
			 secciones =    listaSecciones.size();
			
 		 return secciones;
	}
}
