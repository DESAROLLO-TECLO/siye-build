package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;

public interface PreguntasDAO  extends BaseDao<PreguntasDTO>{
	/**
	 * @Descripción: Método para consultar una preguntas por si identificador unico (op = true, considera st_activo = 1, op = false no considera el st_activo)
	 * @author Mannuel
	 * @return PreguntasDTO
	 * @param Long idPregunta, Boolean op
	 */
	public PreguntasDTO pregunta(Long idPregunta, Boolean op);
}
