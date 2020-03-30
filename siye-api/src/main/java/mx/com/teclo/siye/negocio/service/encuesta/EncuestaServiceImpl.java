package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestaDetalleDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuentaIntentoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaRespuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.IntentoDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.OpcionVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.PreguntaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.SeccionVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaRespuestaVO;
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
	
	
	

	@Override
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
				idVO.setNuMinConsumidos(ueiDTO.getNuMinConsumidos());
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
	
	

}
