package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.VehiculoConductorDTO;

@Repository
public class VehiculoConductorDAOImpl extends BaseDaoHibernate<VehiculoConductorDTO>
		implements
			VehiculoConductorDAO {



	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoConductorDTO> getTransportistas(Long idVehiculo) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoConductorDTO.class);
		criteria.createAlias("vehiculo", "vehiculo");
		criteria.add(Restrictions.eq("vehiculo.idVehiculo", idVehiculo));
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.addOrder(Order.desc("nuOrden"));
		return (List<VehiculoConductorDTO>)criteria.list();

	}

}
