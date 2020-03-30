package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.SeccionDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelPreguntaVO;

public interface SeccionDAO  extends BaseDao<SeccionDTO> {

	/*@Author Maverick
	 * @param idEncuesta
	 * @return List<ExpedienteNivelPreguntaVO>
	 * metodo para consultar las preguntas y numero maximo de imagens que se puede cargar en cada una , por encuesta 
	 * */
	public List<ExpedienteNivelPreguntaVO> getPreguntasByEncuestaVO(Long idEncuesta);
}
