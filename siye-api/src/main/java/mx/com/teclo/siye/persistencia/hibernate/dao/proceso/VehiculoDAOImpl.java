package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.filtro.FiltroVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;

@Repository
public class VehiculoDAOImpl extends BaseDaoHibernate<VehiculoDTO> implements VehiculoDAO {

	@Override
	public VehiculoVO obtenerVehiculo(Long idVehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehiculoVO> buscarVehiculos(FiltroVehiculoVO filtro) {
		Criteria criteria = getCurrentSession().createCriteria(VehiculoDTO.class, "vehiculo");
		criteria.createAlias("vehiculo.tipoVehiculo", "tipo", JoinType.LEFT_OUTER_JOIN);
		if(StringUtils.isNotBlank(filtro.getCdPlacaVehiculo())) {
			criteria.add(Restrictions.eq("vehiculo.cdPlacaVehiculo", filtro.getCdPlacaVehiculo()));
		}	
		if(StringUtils.isNotBlank(filtro.getCdVin())) {
			criteria.add(Restrictions.eq("vehiculo.cdVin", filtro.getCdVin()));
		}
		if(filtro.getStActivo() != null) {
			criteria.add(Restrictions.eq("vehiculo.stActivo", filtro.getStActivo()));
		}
		
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("vehiculo.idVehiculo").as("idVehiculo"))
						.add(Projections.property("vehiculo.cdPlacaVehiculo").as("cdPlacaVehiculo"))
						.add(Projections.property("vehiculo.cdVin").as("cdVin"))
						.add(Projections.property("vehiculo.cdTarjetaDeCirculacion").as("cdTarjetaDeCirculacion"))
						.add(Projections.property("tipo.idTipoVehiculo").as("idTipoVehiculo"))
						.add(Projections.property("tipo.cdTipoVehiculo").as("cdTipoVehiculo"))
						.add(Projections.property("tipo.nbTipoVehiculo").as("nbTipoVehiculo"))
						.add(Projections.property("vehiculo.nbMarca").as("nbMarca"))
						.add(Projections.property("vehiculo.nbSubMarca").as("nbSubMarca"))
						.add(Projections.property("vehiculo.cdModelo").as("cdModelo")));		
		criteria.addOrder(Order.asc("vehiculo.idVehiculo"));
		criteria.setResultTransformer(Transformers.aliasToBean(VehiculoVO.class));
		return criteria.list();

	}
}
