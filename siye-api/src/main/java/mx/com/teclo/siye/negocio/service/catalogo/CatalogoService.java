package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;

public interface CatalogoService {
	
	public List<StEncuestaVO> estatusEncuesta() throws NotFoundException;

}
