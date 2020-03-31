package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;

@Repository
public class EncuestaDAOImpl extends BaseDaoHibernate<EncuestasDTO> implements EncuestasDAO {

	@Override
	public EncuestasDTO encuestaIntento(Long idEncuesta) {
		Criteria c = getCurrentSession().createCriteria(EncuestasDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("idEncuesta", idEncuesta));
		return (EncuestasDTO)c.uniqueResult();
	}

}
