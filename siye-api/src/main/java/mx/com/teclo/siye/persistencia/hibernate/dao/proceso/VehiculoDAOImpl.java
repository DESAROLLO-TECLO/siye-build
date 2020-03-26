package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;

@Repository
public class VehiculoDAOImpl extends BaseDaoHibernate<VehiculoDTO> implements VehiculoDAO {

	@Override
	public VehiculoVO obtenerVehiculo(Long idVehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

}
