package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;

@Repository
public class TipoVehiculoDAOImpl extends BaseDaoHibernate<TipoVehiculoDTO> implements TipoVehiculoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoVehiculoDTO> tipoVehiculo() {
		Criteria criteria = getCurrentSession().createCriteria(TipoVehiculoDTO.class);
		criteria.add(Restrictions.eq("stActivo", Boolean.TRUE));
		criteria.addOrder(Order.asc("nuOrden"));
		
		return (List<TipoVehiculoDTO>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoVehiculoDTO> obtenerTipoVehiculoVisible(Long value) {
		Criteria c = getCurrentSession().createCriteria(TipoVehiculoDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stTipoVehiculo", value));
		return (List<TipoVehiculoDTO>) c.list();
	}
}
