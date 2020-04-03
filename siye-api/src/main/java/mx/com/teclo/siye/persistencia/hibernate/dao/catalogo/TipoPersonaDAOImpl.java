package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoPersonaDTO;

@Repository
public class TipoPersonaDAOImpl extends BaseDaoHibernate<TipoPersonaDTO> implements TipoPersonaDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public TipoPersonaDTO getTipoPersonaXID(Integer idTipoPersona) {
		Criteria criteria = getCurrentSession().createCriteria(TipoPersonaDTO.class);
		criteria.add(Restrictions.eq("idTipoPersona", idTipoPersona));
		criteria.add(Restrictions.eq("stActivo", true));
		return (TipoPersonaDTO)criteria.list().get(0);
	}
}
