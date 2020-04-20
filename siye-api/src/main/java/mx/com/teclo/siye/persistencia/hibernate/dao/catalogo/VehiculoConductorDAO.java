package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.VehiculoConductorDTO;

public interface VehiculoConductorDAO extends BaseDao<VehiculoConductorDTO> {
	
	
	public List<VehiculoConductorDTO> getTransportistas(Long idVehiculo);

}
