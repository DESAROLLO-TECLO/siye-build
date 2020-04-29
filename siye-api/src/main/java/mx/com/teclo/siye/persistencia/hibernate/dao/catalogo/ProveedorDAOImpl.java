package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;

@Repository
public class ProveedorDAOImpl extends BaseDaoHibernate<ProveedorDTO> implements ProveedorDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorDTO> getListProveedor() {
		Criteria c = getCurrentSession().createCriteria(ProveedorDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("nuOrden"));
		return (List<ProveedorDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorDTO> obtenerProveedorVisible(Long value) {
		Criteria c = getCurrentSession().createCriteria(ProveedorDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stProveedor", value));
		return (List<ProveedorDTO>) c.list();
	}

}
