package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoVehiculoVO> getCatTipoVehiculo() {
		Criteria criteria = getCurrentSession().createCriteria(TipoVehiculoDTO.class, "vehiculo");
		criteria.add(Restrictions.eq("vehiculo.stActivo", Boolean.TRUE.booleanValue()));
		criteria.addOrder(Order.asc("vehiculo.nuOrden"));
		
		criteria.setProjection(
				Projections.projectionList()
						.add(Projections.property("vehiculo.idTipoVehiculo").as("idTipoVehiculo"))
						.add(Projections.property("vehiculo.cdTipoVehiculo").as("cdTipoVehiculo"))
						.add(Projections.property("vehiculo.nbTipoVehiculo").as("nbTipoVehiculo")));
		criteria.addOrder(Order.asc("vehiculo.nuOrden"));		
		criteria.setResultTransformer(Transformers.aliasToBean(TipoVehiculoVO.class));		
		return (List<TipoVehiculoVO>)criteria.list();
	}
}
