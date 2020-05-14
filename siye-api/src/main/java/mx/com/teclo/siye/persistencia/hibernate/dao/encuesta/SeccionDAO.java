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
	public List<ExpedienteNivelPreguntaVO> getPreguntasByEncuestaVO(List<Long> idEncuesta);
	
	/**
	 * @Descripción: Método para consultar las secciones respondidas de cada encuesta
	 * @author Fjmb
	 * @return List<Long>
	 */
	public List<Integer> seccionesContestadasEncuesta(Integer idEncuesta);
	
	/**
	 * @Descripción: Método para consultar las secciones que pertenecen
	 * a cierta encuesta por su identificador
	 * @author David Guerra
	 * @return List<SeccionDTO>
	 */
	public List<SeccionDTO> seccionesEncuesta(Long idEncuesta);
	
	
	/**
	 * @Descripción: Método para consultar la sección medienta
	 * su identificador unico
	 * @author Mannuel
	 * @return SeccionDTO
	 */
	public SeccionDTO seccion(Long idSeccion);
}
