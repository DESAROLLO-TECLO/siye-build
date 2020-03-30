package mx.com.teclo.siye.negocio.service.encuesta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.PreguntasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTO;
import mx.com.teclo.siye.persistencia.vo.encuesta.OpcionVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.PreguntaVO;
import mx.com.teclo.siye.persistencia.vo.encuesta.UsuarioEncuestaRespuestaVO;

@Service
public class DetalleIntentoServiceImpl implements DetalleIntentoService{
	
	@Autowired
	private PreguntasDAO preguntasDAO;
	


	@Transactional
	@Override
	public List<UsuarioEncuestaRespuestaVO> fitroUsuarioRespuesta(List<UsuaroEncuestaRespuestaDTO> uerListDTO) {
		List<UsuarioEncuestaRespuestaVO> uerListVO = null;
		UsuarioEncuestaRespuestaVO uerVO = null;
		PreguntaVO pVO = null;
		if(!uerListDTO.isEmpty()) {
			uerListVO = new ArrayList<>();
			for(UsuaroEncuestaRespuestaDTO uerDTO: uerListDTO) {
				uerVO = ResponseConverter.copiarPropiedadesFull(uerDTO, UsuarioEncuestaRespuestaVO.class);
				PreguntasDTO pDTO = preguntasDAO.findOne(uerVO.getId().getIdPregunta());
				if(pDTO != null) {
					pVO = ResponseConverter.copiarPropiedadesFull(pDTO, PreguntaVO.class);
					if(pVO != null) {
						for(OpcionVO oVO: pVO.getOpciones()) {
							if(uerDTO.getOpcionesDTO() != null && oVO.getIdOpcion().equals(uerDTO.getOpcionesDTO().getIdOpcion())) {
								oVO.setStMarcado(1);
							}else {
								oVO.setStMarcado(0);
							}
							oVO.setStCorrecto(uerDTO.getStCorrecto());
						}
					}
					uerVO.setPregunta(pVO);
				}
				uerListVO.add(uerVO);
			}
			//
			return orderListRespuesta(uerListVO);
		}
		return null;
	}
	
	@Override
	public List<UsuarioEncuestaRespuestaVO> orderListRespuesta(List<UsuarioEncuestaRespuestaVO> uerListVO){
		Collections.sort(uerListVO, new Comparator<UsuarioEncuestaRespuestaVO>(){
		  public int compare(UsuarioEncuestaRespuestaVO o1, UsuarioEncuestaRespuestaVO o2){
		     return o1.getPregunta().getNuOrden().compareTo(o2.getPregunta().getNuOrden());
		  }
		});
		return uerListVO;
	} 
	
	@Override
	public List<PreguntaVO> orderList(List<PreguntaVO> pListVO){
		Collections.sort(pListVO, new Comparator<PreguntaVO>(){
		  public int compare(PreguntaVO o1, PreguntaVO o2){
		     return o1.getNuOrden().compareTo(o2.getNuOrden());
		  }
		});
		for(PreguntaVO p : pListVO) {
			p.setOpciones(orderOpciones(p.getOpciones()));
		}
		return pListVO;




        }
	
	private List<OpcionVO> orderOpciones(List<OpcionVO> l){
		Collections.sort(l, new Comparator<OpcionVO>(){
			  public int compare(OpcionVO o1, OpcionVO o2){
			     return o1.getNuOrden().compareTo(o2.getNuOrden());
			  }
			});
		return l;
	}
}
