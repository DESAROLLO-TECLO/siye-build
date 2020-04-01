package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.PreguntasDTO;


@Repository
public class PreguntasDAOImpl extends BaseDaoHibernate<PreguntasDTO> implements PreguntasDAO {

	@Override
	public PreguntasDTO pregunta(Long idPregunta, Boolean op) {
		Criteria c = getCurrentSession().createCriteria(PreguntasDTO.class);
		c.add(Restrictions.eq("idPregunta", idPregunta));
		if(op)
			c.add(Restrictions.eq("stActivo", 1));
		return (PreguntasDTO)c.uniqueResult();
	}

}
