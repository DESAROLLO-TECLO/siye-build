package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.SeccionDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelPreguntaVO;

@Repository
public class SeccionDAOImpl extends BaseDaoHibernate<SeccionDTO> implements SeccionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteNivelPreguntaVO> getPreguntasByEncuestaVO(Long idEncuesta) {
		StringBuilder consulta = new StringBuilder("SELECT pregunta.ID_PREGUNTA  AS idPregunta, pregunta.TX_PREGUNTA  AS cdPregunta, pregunta.NU_MAX_IMAGENES  AS nuMaxImg" + 
				" FROM TIE004D_EE_SECCION seccion" + 
				"  INNER JOIN TIE005D_EE_PREGUNTAS pregunta ON (seccion.ID_SECCION = pregunta.ID_SECCION)" + 
				"   WHERE seccion.ID_ENCUESTA ="+ idEncuesta+" AND pregunta.ST_ACTIVO = 1");
		List<ExpedienteNivelPreguntaVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idEncuesta",LongType.INSTANCE)
					.addScalar("cdEncuesta",StringType.INSTANCE)
					.addScalar("nuMaxImg", LongType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ExpedienteNivelPreguntaVO.class)).list();
		return respuesta;
	}

}
