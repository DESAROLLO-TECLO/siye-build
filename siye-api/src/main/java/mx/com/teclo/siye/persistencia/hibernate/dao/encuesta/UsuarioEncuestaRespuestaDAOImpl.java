package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuaroEncuestaRespuestaDTO;

@Repository
public class UsuarioEncuestaRespuestaDAOImpl extends BaseDaoHibernate<UsuaroEncuestaRespuestaDTO> implements UsuarioEncuestaRespuestaDAO{


	@SuppressWarnings("unchecked")
	@Override
	public List<UsuaroEncuestaRespuestaDTO> repuestas(Long idUsuEncuIntento) {
		Criteria c = getCurrentSession().createCriteria(UsuaroEncuestaRespuestaDTO.class);
		c.add(Restrictions.eq("id.idUsuEncuIntento", idUsuEncuIntento));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<UsuaroEncuestaRespuestaDTO>) c.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UsuaroEncuestaRespuestaDTO> getRespuestas(Long idUsuEncuIntento, Long idEncuesta) {
		Criteria criteria = getCurrentSession().createCriteria(UsuaroEncuestaRespuestaDTO.class);
		criteria.add(Restrictions.eq("id.idUsuEncuIntento", idUsuEncuIntento));
		criteria.add(Restrictions.eq("id.idEncuesta", idEncuesta));
		criteria.add(Restrictions.eq("stActivo", 1));
		return criteria.list();
	}

	@Override
	public UsuaroEncuestaRespuestaDTO userEncuestaRespuesta(Long idUsuEncuIntento, Long idEncuesta, Long idSeccion,
			Long idPregunta) {
		
		Criteria c = getCurrentSession().createCriteria(UsuaroEncuestaRespuestaDTO.class);
		c.add(Restrictions.eq("id.idUsuEncuIntento", idUsuEncuIntento));
		c.add(Restrictions.eq("id.idEncuesta", idEncuesta));
		c.add(Restrictions.eq("id.idSeccion", idSeccion));
		c.add(Restrictions.eq("id.idPregunta", idPregunta));
		c.add(Restrictions.eq("stActivo", 1));
		return (UsuaroEncuestaRespuestaDTO) c.uniqueResult();
	}

	 
	
}
