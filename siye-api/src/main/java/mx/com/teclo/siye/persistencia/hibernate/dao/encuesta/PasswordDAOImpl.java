package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.comun.PasswordDTO;

@Repository
public class PasswordDAOImpl extends BaseDaoHibernate<PasswordDTO> implements PasswordDAO{
	
	@Override
	public Boolean validarContraseniaTransportista(String password){
		Criteria criteria = getCurrentSession().createCriteria(PasswordDTO.class);
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.add(Restrictions.eq("stActual", true));
		criteria.add(Restrictions.eq("txValor", password));
		//criteria.addOrder(Order.asc("nuOrden"));
		
		Boolean result = false ;
		if(criteria.list().size() > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public PasswordDTO getClaveActual() {
		Criteria criteria = getCurrentSession().createCriteria(PasswordDTO.class);
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.add(Restrictions.eq("stActual", true));

		return (PasswordDTO) criteria.uniqueResult();
	}
}
