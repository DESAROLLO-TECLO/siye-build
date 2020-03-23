package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;

@Repository
public class AplicacionesDAOImpl extends BaseDaoHibernate<AplicacionDTO> implements AplicacionDAO{

	
	@Override
	public AplicacionDTO findAplicationById(String cdAplicacion){
		Criteria query = getCurrentSession().createCriteria(AplicacionDTO.class);
		query.add(Restrictions.eq("cdAplicacion", cdAplicacion));
		query.add(Restrictions.eq("stActivo", 1));
		
		return (AplicacionDTO) query.uniqueResult();
	}
}
