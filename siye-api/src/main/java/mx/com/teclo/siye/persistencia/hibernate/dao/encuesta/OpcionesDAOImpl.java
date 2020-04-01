package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OpcionesDTO;

@Repository
public class OpcionesDAOImpl extends BaseDaoHibernate<OpcionesDTO> implements OpcionesDAO{

	@Override
	public OpcionesDTO opcion(Long idOpcion) {
		Criteria c = getCurrentSession().createCriteria(OpcionesDTO.class);
		c.add(Restrictions.eq("idOpcion", idOpcion));
		c.add(Restrictions.eq("stActivo", 1));
		return (OpcionesDTO)c.uniqueResult();
	}

}
