package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;

public interface TipoVehiculoDAO extends BaseDao<TipoVehiculoDTO>{
	
	public List<TipoVehiculoDTO> tipoVehiculo();

}
