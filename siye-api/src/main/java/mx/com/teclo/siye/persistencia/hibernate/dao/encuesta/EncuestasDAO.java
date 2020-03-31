package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;

public interface EncuestasDAO extends BaseDao<EncuestasDTO> {
	
	
	/**
	 * @Descripción: Método para consultar la encuesta por su identificador
	 * único
	 * @author Mannuel
	 * @return EncuestasDTO
	 */
	public EncuestasDTO encuestaIntento(Long idEncuesta);

}
