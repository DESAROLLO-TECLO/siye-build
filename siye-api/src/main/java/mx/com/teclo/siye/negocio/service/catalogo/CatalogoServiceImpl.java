package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class CatalogoServiceImpl implements CatalogoService{
	
	private StEncuestaDAO stEncuestaDAO;

	@Transactional
	@Override
	public List<StEncuestaVO> estatusEncuesta() throws NotFoundException {
		List<StEncuestaDTO> stEncuesta = stEncuestaDAO.stEncuesta();
		if(stEncuesta.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<StEncuestaVO> listStEncuesta = ResponseConverter.converterLista(new ArrayList<>(), stEncuesta, StEncuestaVO.class);
		return listStEncuesta;
	}

}
