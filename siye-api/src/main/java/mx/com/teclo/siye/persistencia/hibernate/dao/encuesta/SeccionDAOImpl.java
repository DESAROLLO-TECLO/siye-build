package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
				 .addScalar("idPregunta",LongType.INSTANCE)
					.addScalar("cdPregunta",StringType.INSTANCE)
					.addScalar("nuMaxImg", LongType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ExpedienteNivelPreguntaVO.class)).list();
		return respuesta;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> seccionesContestadasEncuesta(Integer idEncuesta) {
		List<Integer> listaIdSecciones = null;
		StringBuilder strQuery = new StringBuilder(
						" SELECT "+
						" 	uer.ID_SECCION AS idSeccion "+
						" FROM "+
						" 	TEE003D_EE_USU_ENC_RESP uer "+
						" WHERE "+
						" 	uer.ID_USU_ENCU_INTENTO= "+ idEncuesta +
						" 	AND ID_SECCION NOT IN( "+
						" 	SELECT "+
						" 		uerTwo.ID_SECCION "+
						" 	FROM "+
						" 		TEE003D_EE_USU_ENC_RESP uerTwo "+
						" 	WHERE "+
						" 		uerTwo.ID_USU_ENCU_INTENTO= "+ idEncuesta +
						" 		AND uerTwo.ID_OPCION IS NULL "+
						" 	GROUP BY "+
						" 		(uerTwo.ID_SECCION, "+
						" 		uerTwo.ID_OPCION) ) "+
						" 	GROUP BY (uer.ID_SECCION) ");
		SQLQuery resultado = getCurrentSession().createSQLQuery(strQuery.toString());
		listaIdSecciones = (List<Integer>) resultado.list();

		return listaIdSecciones;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SeccionDTO> seccionesEncuesta(Long idEncuesta) {
		Criteria c = getCurrentSession().createCriteria(SeccionDTO.class);
		c.createAlias("idEncuesta", "e");
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("e.stActivo", 1));
		c.add(Restrictions.eq("e.idEncuesta", idEncuesta));
		c.addOrder(Order.asc("idSeccion"));
		return (List<SeccionDTO>) c.list();
	}

	@Override
	public SeccionDTO seccion(Long idSeccion) {
		Criteria c = getCurrentSession().createCriteria(SeccionDTO.class);
		c.add(Restrictions.eq("idSeccion", idSeccion));
		c.add(Restrictions.eq("stActivo", 1));
		return (SeccionDTO) c.uniqueResult();
	}

}
