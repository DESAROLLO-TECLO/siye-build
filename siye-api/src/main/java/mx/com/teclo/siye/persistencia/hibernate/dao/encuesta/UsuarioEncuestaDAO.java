package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;



import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDTO;



public interface UsuarioEncuestaDAO extends BaseDao<UsuarioEncuestaDTO>{
	
	
	/**
	 * @author David Guerra
	 * @param idEncuesta Long
	 * @param idFolioOrden
	 * @return UsuarioEncuestaIntentosDTO
	 */
	
    List<UsuarioEncuestaDTO> getEncuestasPorOrden(Long idOrden);
	
    public List<UsuarioEncuestaDTO> consultaOrdenByOrdenServicio(String valor);
}
