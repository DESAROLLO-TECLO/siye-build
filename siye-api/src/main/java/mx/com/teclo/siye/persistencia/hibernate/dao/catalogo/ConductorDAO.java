package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;

public interface ConductorDAO extends BaseDao<ConductorDTO>{
	
	public List<ConductorDTO> getTransportistas();
	
	public List<ConductorVO> getTransportistasSinVehiculo();
	
	public List<ConductorDTO> getConductorXNombre(String nombre, String aPaterno, String aMaterno);

	public List<ConductorDTO> obtenerConductorVisible(Long value);
}
