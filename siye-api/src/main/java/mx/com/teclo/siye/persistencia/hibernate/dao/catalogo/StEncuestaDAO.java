package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;

public interface StEncuestaDAO extends BaseDao<StEncuestaDTO>{
	
	public List<StEncuestaDTO> stEncuesta();
	
	/**
	 * @Descripción: Método para consultar un esttatus 
	 * de encuesta por su identificador unico
	 * @author David Guerra
	 * @return List<StEncuestaDTO>
	 */
	public StEncuestaDTO encuesta(String cdStEncuesta);

}
