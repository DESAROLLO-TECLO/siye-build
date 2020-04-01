package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.EstatusCalificacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestaDetalleDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.OpcionesDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PasswordDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PreguntasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.SeccionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuentaIntentoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaRespuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EstatusCalificacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OpcionesDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.SeccionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTOPK;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.IntentoDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.OpcionVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.PreguntaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.SeccionVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UserRespuestaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaIntentosVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaRespuestaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class EncuestaServiceImpl implements EncuestaService {
	
	@Autowired
	private UsuarioFirmadoService userSession;
	
	@Autowired
	private EncuestaDetalleDAO encuestaDetalleDAO;
	
	@Autowired
	private UsuarioEncuentaIntentoDAO usuarioEncuentaIntentoDAO;
	
	@Autowired
	private UsuarioEncuestaRespuestaDAO usuarioEncuestaRespuestaDAO;
	
	@Autowired
	private DetalleIntentoService detalleIntentoService;
		
	@Autowired
	private RespuestaService respuestaService;
	
	@Autowired
	private StEncuestaDAO stEncuestaDAO;
	
	@Autowired
	private EstatusCalificacionDAO estatusCalificacionDAO;
	
	@Autowired
	private SeccionDAO seccionDAO;
	
	@Autowired
	private PasswordDAO passwordDAO;
	
	@Autowired
	private UsuarioEncuestaDAO usuarioEncuestaDAO;
	
	@Autowired 
	private EncuestasDAO encuestasDAO;
	
	@Autowired
	private PreguntasDAO preguntasDAO;
	
	@Autowired 
	private OpcionesDAO  opcionesDAO;
	
	@Autowired
	private UsuarioEncuestaRespuestaDAO usuaroEncuestaRespuestaDAO;
	
	@Override
	@Transactional
	public UsuarioEncuestaDetalleVO encuestaDetalle(Long idEncuesta,
			Long idOrdenServicio) throws NotFoundException {
		
        Long idUsuario = userSession.getUsuarioFirmadoVO().getId();
		
		UsuarioEncuestaDetalleVO uedVO = new UsuarioEncuestaDetalleVO();
		UsuarioEncuestaDetalleDTO uedDTO = encuestaDetalleDAO.getEncuestaDetalle(idEncuesta,idOrdenServicio);
		if(uedDTO == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		uedVO = ResponseConverter.copiarPropiedadesFull(uedDTO, UsuarioEncuestaDetalleVO.class);
		
		// Obtener el detalle del intento actual
		UsuarioEncuestaIntentosDTO ueiDTO = usuarioEncuentaIntentoDAO.getEncuestaByUsuario(uedDTO.getEncuesta().getIdEncuesta(), idOrdenServicio);
		if(ueiDTO != null) {
			StEncuestaVO steVO = null;
			IntentoDetalleVO idVO = new IntentoDetalleVO();
			if(ueiDTO.getStEncuesta() != null) {
				steVO = new StEncuestaVO();
				ResponseConverter.copiarPropriedades(steVO, ueiDTO.getStEncuesta());
				idVO.setStEncuesta(steVO);
				//idVO.setNuMinConsumidos(ueiDTO.getNuMinConsumidos());
				idVO.setIdUsuEncuIntento(ueiDTO.getIdUsuEncuIntento());
			}
			List<UsuaroEncuestaRespuestaDTO> uerListDTO = usuarioEncuestaRespuestaDAO.repuestas(ueiDTO.getIdUsuEncuIntento());
			List<UsuarioEncuestaRespuestaVO> uerListVO = detalleIntentoService.fitroUsuarioRespuesta(uerListDTO);
			
			// Ordenar los elementos por secciones
			List<SeccionVO> snewListVO = new ArrayList<>(obtenerPreguntas(uerListVO,uedVO.getEncuesta().getSecciones()));
			uedVO.getEncuesta().setSecciones(quitarSeccionesInactivas(snewListVO));
			idVO.setResumenRespuesta(uerListVO);
			uedVO.setIntentoDetalleVO(idVO);
			
//			List<SeccionVO> listPp = new ArrayList<SeccionVO>(uedVO.getEncuesta().getSecciones());
//			listPp = quitarSeccionesInactivas(listPp);
//			uedVO.getEncuesta().setSecciones(listPp);
		}
		return uedVO;


	}
	
	
	private List<SeccionVO> obtenerPreguntas (List<UsuarioEncuestaRespuestaVO> uerListVO,List<SeccionVO> sListVO) {
		for(SeccionVO sVO: sListVO){
			List<PreguntaVO> pListVO = new ArrayList<>(sVO.getPreguntas());
			if(uerListVO == null) {
				sVO.setPreguntas(detalleIntentoService.orderList(pListVO));
				break;
			}
				
			for(UsuarioEncuestaRespuestaVO uerVO: uerListVO) {
				for(PreguntaVO pVO: pListVO) {
					if(uerVO.getId().getIdPregunta().equals( pVO.getIdPregunta() )) {
						pVO.setOpciones(uerVO.getPregunta().getOpciones());
						break;
					}
				}
			}
			sVO.setPreguntas(detalleIntentoService.orderList(pListVO));
		}
		return orderList(sListVO);
	}
	
	private List<SeccionVO> quitarSeccionesInactivas(List<SeccionVO> secciones){
		List<SeccionVO> seccionStAct = new ArrayList<SeccionVO>();
		for(SeccionVO s : secciones) {
			if(s.getStActivo()==1) {
				List<PreguntaVO> preguntas = s.getPreguntas();
				List<PreguntaVO> preguntasStAct = new ArrayList<PreguntaVO>();
				for(PreguntaVO p : preguntas) {
					if(p.getStActivo()==1) {
						List<OpcionVO> opciones = p.getOpciones();
						List<OpcionVO> opcionesStActivo = new ArrayList<OpcionVO>();
						for(OpcionVO o : opciones) {
							if(o.getStActivo()==1) {
								opcionesStActivo.add(o);
							}
						}
						p.setOpciones(opcionesStActivo);
						preguntasStAct.add(p);
					}
				}
				s.setPreguntas(preguntasStAct);
				seccionStAct.add(s);
			}
		}
		return seccionStAct;
	}
	
	private List<SeccionVO> orderList(List<SeccionVO> l){
		Collections.sort(l, new Comparator<SeccionVO>(){
		  public int compare(SeccionVO o1, SeccionVO o2){
		     return o1.getIdSeccion().compareTo(o2.getIdSeccion());
		  }
		});
		return l;
		
	}
	
	@Transactional
	@Override
	public Boolean nuevoIntento(UsuarioEncuestaVO vo) throws BusinessException{
		OrdenEncuestaDTO ueDTO = usuarioEncuestaDAO.findOne(vo.getIdUsuarioEncuesta());
		Long idUsr = userSession.getUsuarioFirmadoVO().getId();
		if(ueDTO == null)
			throw new BusinessException(RespuestaHttp.CONFLICT.getMessage());
		
		if(vo.getValor() == 0) {
			UsuarioEncuestaIntentosDTO ueiDTO = usuarioEncuentaIntentoDAO.buscaUsuEncuIntento(vo.getIntento());
			if(ueiDTO.getStEncuesta().getCdStEncuesta().equals("FIN"))
				throw new BusinessException("Esta encuesta ya fue finalizada, favor de validar.");
		}
		
			if(ueDTO.getStAplicaEncuesta() == false) {
				/*List<UsuarioEncuestaIntentosDTO> list = usuarioEncuentaIntentoDAO.buscaPendientes(ueDTO.getIdUsuarioEncuesta());
				if(!list.isEmpty()) {
					ueDTO.setStAplicaEncuesta(false);
					decactivaIntentosActuales(ueDTO.getIdUsuarioEncuesta());
					return true;
				}*/
				
				
				UsuarioEncuestaIntentosDTO ueiDTO = new UsuarioEncuestaIntentosDTO();
					
				// DESCATIVAMOS LOS REGISTROS ACTUALES
				ueDTO.setStAplicaEncuesta(true);
				usuarioEncuestaDAO.update(ueDTO);
				
				decactivaIntentosActuales(ueDTO.getIdUsuarioEncuesta());
				
				// Se está activando un nuevo intento con un estatus NO INICIADA
				//pendiente ueiDTO.setUsuarioEncuesta(ueDTO);
				ueiDTO.setFhInicio(null);
				ueiDTO.setFhFin(null);
				StEncuestaDTO seDTO = stEncuestaDAO.encuesta("NI");
				ueiDTO.setStEncuesta(seDTO);
				ueiDTO.setNuCalificacion(0);
				// obtenemos el estatus de la calificación inicial
				EstatusCalificacionDTO ecDTO = estatusCalificacionDAO.calificacion("SC");
				ueiDTO.setStCalificacion(ecDTO);
				ueiDTO.setStMostrar(true);
				ueiDTO.setNuPreguntasCorrectas(0);
				ueiDTO.setNuPreguntasIncorr(0);
				ueiDTO.setStActivo(true);
				ueiDTO.setIdUsrCreacion(idUsr);
				ueiDTO.setFhCreacion(new Date());
				ueiDTO.setIdUsrModifica(idUsr);
				ueiDTO.setFhModificacion(new Date());
				ueiDTO.setNuSeccionesRsp(0);
				ueiDTO.setNuPreguntasVacias(0);
				
				// CONTAR SECCIONE SY PREGUNTAS TOTALES
				Map<String, Object> map = contarPreguntasSecciones(ueDTO.getEncuesta());
				Integer nuSecciones = (Integer) map.get("SECCIONES");
				Integer nuPreguntas = (Integer) map.get("PREGUNTAS");	
				
				
				ueiDTO.setNuSeccionesTot(nuSecciones);// numero de secciones
				ueiDTO.setNuPreguntas(nuPreguntas);// contar
				usuarioEncuentaIntentoDAO.save(ueiDTO);
			}else {
				ueDTO.setStAplicaEncuesta(false);
				decactivaIntentosActuales(ueDTO.getIdUsuarioEncuesta());
			}
			return true;
	}
	
	
	@Transactional
	private Boolean decactivaIntentosActuales(Long idUsuEncuIntento) throws BusinessException {
		List<UsuarioEncuestaIntentosDTO> ueiListDTO = usuarioEncuentaIntentoDAO.usuarioEncuesta(idUsuEncuIntento);
		Long idUsr = userSession.getUsuarioFirmadoVO().getId();
		if(!ueiListDTO.isEmpty()) {
			for(UsuarioEncuestaIntentosDTO ueiDTO : ueiListDTO) {
				ueiDTO.setStMostrar(false);
				ueiDTO.setFhModificacion(new Date());
				ueiDTO.setIdUsrModifica(idUsr);
				if(ueiDTO.getFhInicio() !=null && ueiDTO.getFhFin() ==null) {
					//1.- Finalizar intento
					@SuppressWarnings("unused")
					UsuarioEncuestaIntentosVO encuestaIntentosVO = finalizarIntento(ueiDTO.getIdUsuEncuIntento(), false);
					//2.- Calificar intento
					respuestaService.calificarIntentoEncuesta(ueiDTO.getIdUsuEncuIntento());
					//3.- Actuaizar a falso el campo aplicar encuesta y contar total de intentos 
					//finalizarEncuesta(encuestaIntentosVO);
				}
				if(ueiDTO.getFhInicio()==null && ueiDTO.getFhFin()==null){
					StEncuestaDTO seDTO = stEncuestaDAO.encuesta("CAN");
					ueiDTO.setStEncuesta(seDTO);
					List<UsuarioEncuestaIntentosDTO> listaDOs = usuarioEncuentaIntentoDAO.intentoMismaEncuesta(idUsuEncuIntento);
					respuestaService.determinarCalifAlta(listaDOs, null);
				}
//				ueiDTO.getUsuarioEncuesta().setStAplicaEncuesta(false);
				ueiDTO.setStMostrar(false);
				usuarioEncuentaIntentoDAO.update(ueiDTO);
			}
			//
		}
		return true;
	}
	
	@Override
	@Transactional
	public UsuarioEncuestaIntentosVO finalizarIntento(Long idUsuEncuIntento, Boolean b) throws BusinessException{
		
		UsuarioEncuestaIntentosDTO usuarioEncuestaIntentosDTO = null;
		//JLGD
		usuarioEncuestaIntentosDTO = usuarioEncuentaIntentoDAO.buscaUsuEncuIntento(idUsuEncuIntento);
		
		if(b) {
			if(usuarioEncuestaIntentosDTO.getStEncuesta().getCdStEncuesta().equals("FIN"))
				throw new BusinessException("Esta encuesta ya fue finalizada, favor de validar.");
		}else {
			usuarioEncuestaIntentosDTO.setStMostrar(true);
			usuarioEncuestaIntentosDTO.setFhFin(new Date());
			usuarioEncuestaIntentosDTO.setFhModificacion(new Date());
			usuarioEncuestaIntentosDTO.setIdUsrModifica(userSession.getUsuarioFirmadoVO().getId());
			usuarioEncuentaIntentoDAO.update(usuarioEncuestaIntentosDTO);
			List<UsuarioEncuestaIntentosDTO> encuestaIntentosDTOs=new ArrayList<>();
			encuestaIntentosDTOs.add(usuarioEncuestaIntentosDTO);
			List<UsuarioEncuestaIntentosVO> listReturn =ResponseConverter.converterLista(new ArrayList<>(), encuestaIntentosDTOs, UsuarioEncuestaIntentosVO.class);
			return listReturn.get(0);
		}
		return null;
	}
	
	@Transactional
	private Map<String, Object> contarPreguntasSecciones (EncuestasDTO e) {
		Map<String, Object> mapReturn = new HashMap<>();
		if(e == null)
			return mapReturn;
		
		List<SeccionDTO> sListDTO = seccionDAO.seccionesEncuesta(e.getIdEncuesta());
		if(sListDTO.isEmpty()) {
			mapReturn.put("PREGUNTAS", 0);
			mapReturn.put("SECCIONES", 0);
		}else {
			int nuSecciones = sListDTO.size();
			int nuPreguntas = 0;
			for(SeccionDTO sDTO: sListDTO) {
				nuPreguntas += preguntas(sDTO.getPreguntas());
			}
			mapReturn.put("PREGUNTAS", nuPreguntas);
			mapReturn.put("SECCIONES", nuSecciones);
		}
		return mapReturn;
	}
	
	@Transactional
	private Long preguntas (List<PreguntasDTO> p) {
		Long nuPreguntas = new Long(p.size());
		return nuPreguntas;
	}
	
	@Transactional
	@Override
	public List<UsuarioEncuestaVO> consultaEncuestasSatisfaccion(Integer tipoBusqueda, String valor, String passwordEnc) 
		throws Exception, BusinessException, NotFoundException {
		String mensajeErr = "";
		try {
			Boolean contraseniaValida = passwordDAO.validarContraseniaTransportista(passwordEnc);
			
//			List<OrdenServicioDTO> listaOrdenServicioDTO = new ArrayList<OrdenServicioDTO>(); 
			
			if(contraseniaValida == true) {
//				switch(tipoBusqueda) {
//					case 1: //Por Orden de Servicio
//						listaOrdenServicioDTO = ordenServicioDAO.consultaOrdenByOrdenServicio(valor);
//					break;
//					case 2: //Por Placa Vehicular
//						listaOrdenServicioDTO = ordenServicioDAO.consultaOrdenByPlaca(valor);
//					break;
//					case 3: //Por VIN
//						listaOrdenServicioDTO = ordenServicioDAO.consultaOrdenByVin(valor);
//					break;
//					default:
//					break;
//				}
				
				
				List<OrdenEncuestaDTO> listaUsuarioEncuestaDTO = usuarioEncuestaDAO.consultaOrdenByOrdenServicio(valor);
				
				String s ="";
				
				List<UsuarioEncuestaVO> listaUsuarioEncuestaVO = ResponseConverter.converterLista(new ArrayList<>(), listaUsuarioEncuestaDTO, UsuarioEncuestaVO.class);
				return listaUsuarioEncuestaVO;
			}else {
				mensajeErr = "La contraseña no es válida, porfavor solicitarla a un supervisor.";
				throw new NotFoundException("");
			}
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto!, porfavor contacte al administrador");
			}
		}
	}
	
	@Transactional
	@Override
	public void finalizarEncuesta(UsuarioEncuestaIntentosVO encuestaIntentosVO) {
		//Convertir de UsuarioEncuestaIntentosVO a UsuarioEncuestaDTO
		List<UsuarioEncuestaVO> encuestaIntentosVOs =new ArrayList<>();
		encuestaIntentosVOs.add(encuestaIntentosVO.getUsuarioEncuesta());
		List<OrdenEncuestaDTO> listReturn =ResponseConverter.converterLista(new ArrayList<>(), encuestaIntentosVOs,OrdenEncuestaDTO.class);
	    listReturn.get(0).setStAplicaEncuesta(false);
//	    listReturn.get(0).setNuIntegerentos(listReturn.get(0).getNuIntegerentos() + 1);
		//userDTO = ResponseConverter.copiarPropiedadesFull(userVO, UsuarioDTO.class);
	    usuarioEncuestaDAO.update(listReturn.get(0));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public UsuarioEncuestaIntentosVO detalle(Long idUsuEncuIntento, Boolean finalizar) throws NotFoundException{
		UsuarioEncuestaIntentosDTO ueiDTO = usuarioEncuentaIntentoDAO.getIntentoById(idUsuEncuIntento);
		if(ueiDTO == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		UsuarioEncuestaIntentosVO objectReturn = ResponseConverter.copiarPropiedadesFull(ueiDTO, UsuarioEncuestaIntentosVO.class);
		List<UsuaroEncuestaRespuestaDTO> uerListDTO = usuarioEncuestaRespuestaDAO.repuestas(ueiDTO.getIdUsuEncuIntento());
					
		objectReturn.setDetalleRespuesta(detalleIntentoService.fitroUsuarioRespuesta(uerListDTO));
		
		if(finalizar != null && finalizar) {
			Map<String, Object> mapSeciones = detalleIntentoService.detalleFinalizar(objectReturn.getDetalleRespuesta());
			if(mapSeciones.get("secciones") != null) {
				List<SeccionVO> sListVO = (List<SeccionVO>) mapSeciones.get("secciones"); 
				objectReturn.setSeccionesListVO(sListVO);
			}
		}
		return objectReturn;
	}


	@Override
	public Boolean guardarRespuestas(List<UserRespuestaVO> l) throws BusinessException {
	
		if(l.isEmpty())// No se guardar ni procesa nada
			return true;
		Long idIntento = l.isEmpty() ? 0L: l.get(0).getIdIntento();		
		UsuarioEncuestaIntentosDTO ueDTO = usuarioEncuentaIntentoDAO.getIntentoById(idIntento);	
		// Obtenemos la encuesta actual del usuario
		if(ueDTO.getStEncuesta().getCdStEncuesta().equals("FIN"))
			throw new BusinessException("Esta encuesta ya fue finalizada, favor de validar.");
		
		if(ueDTO != null)
		for(UserRespuestaVO urVO: l) {
			// Obtenemos la encuesta actual
			EncuestasDTO eDTO = encuestasDAO.encuestaIntento(urVO.getIdEncuesta());
			
			// Obtener la sección actual
			SeccionDTO sDTO = seccionDAO.seccion(urVO.getIdSeccion());
			
			// Obtener la prelgunta actual
			PreguntasDTO pDTO = preguntasDAO.pregunta(urVO.getIdPregunta(), true);
			
			UsuaroEncuestaRespuestaDTO uDTO = usuarioEncuestaRespuestaDAO.userEncuestaRespuesta(ueDTO.getIdUsuEncuIntento(), eDTO.getIdEncuesta(), sDTO.getIdSeccion(), pDTO.getIdPregunta());
			if(uDTO != null) {
				if(urVO.getIdOpcion() != null) {
					// Obtenemos la opción medianta su identificador unico recibido
					OpcionesDTO oDTO = opcionesDAO.opcion(urVO.getIdOpcion());
					uDTO.setFhRespuesta(new Date());
					uDTO.setOpcionesDTO(oDTO);
				}
				uDTO.setFhLectura(new Date());
				usuarioEncuestaRespuestaDAO.update(uDTO);	
			}
		}
		return true;	
	}
	
	@Transactional
	@Override
	public void cargarEncuesta(
			Long idEncuesta, Long idOrdenServicio
		) throws Exception, BusinessException, NotFoundException {
		Long idUsuario = idOrdenServicio;
		UsuarioEncuestaIntentosDTO usuarioEncuestaIntentosDTO = null;
		usuarioEncuestaIntentosDTO = usuarioEncuentaIntentoDAO.getEncuestaByUsuario(idEncuesta, idUsuario);
		StEncuestaDTO seDTO = stEncuestaDAO.encuesta("EC");
		List<StEncuestaDTO> seDTOw = stEncuestaDAO.stEncuesta();
		usuarioEncuestaIntentosDTO.setStEncuesta(seDTO);
		UsuarioEncuestaDetalleDTO usuarioEncuestaDetalleDTO = encuestaDetalleDAO.getEncuestaDetalle(idEncuesta, idUsuario);
		
		List<SeccionDTO> secciones =  usuarioEncuestaDetalleDTO.getEncuesta().getSecciones();
		List<UsuaroEncuestaRespuestaDTO> respuestas = new ArrayList<UsuaroEncuestaRespuestaDTO>();
		
		for(SeccionDTO seccion: secciones) {
			UsuaroEncuestaRespuestaDTO respuesta= null;
			
			List<PreguntasDTO> preguntas = seccion.getPreguntas();
			for(PreguntasDTO pregunta : preguntas) {
				if(pregunta.getStActivo()==1) {
					UsuaroEncuestaRespuestaDTOPK respuestaPK= null;
					respuestaPK = new UsuaroEncuestaRespuestaDTOPK();
					respuestaPK.setIdUsuEncuIntento(usuarioEncuestaIntentosDTO.getIdUsuEncuIntento());
					respuestaPK.setIdEncuesta(usuarioEncuestaDetalleDTO.getEncuesta().getIdEncuesta());
					respuestaPK.setIdSeccion(seccion.getIdSeccion());
					respuesta = new UsuaroEncuestaRespuestaDTO();
					respuestaPK.setIdPregunta(pregunta.getIdPregunta());
					respuesta.setUsuaroEncuestaRespuestaPK(respuestaPK);
					respuesta.setNuIntentos(0);
					respuesta.setStCorrecto(0);
					respuesta.setStActivo(1);
					respuesta.setIdUsrCreacion(idUsuario);
					respuesta.setIdUsrModifica(idUsuario);
					respuesta.setFhCreacion(new Date());
					respuesta.setFhModificacion(new Date());
	 				respuestas.add(respuesta);
				}
			}
		}
		
		for(UsuaroEncuestaRespuestaDTO respuesta: respuestas) {
			usuaroEncuestaRespuestaDAO.save(respuesta);
		}
		
		usuarioEncuestaIntentosDTO.setFhInicio(new Date());
		usuarioEncuestaIntentosDTO.setFhModificacion(new Date());
		usuarioEncuestaIntentosDTO.setIdUsrModifica(-1L);
		usuarioEncuestaIntentosDTO.getUsuarioEncuesta().setNuIntegerentos(usuarioEncuestaIntentosDTO.getUsuarioEncuesta().getNuIntegerentos() + 1);

		usuarioEncuentaIntentoDAO.update(usuarioEncuestaIntentosDTO);
	}
}
