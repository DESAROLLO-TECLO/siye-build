package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;

public interface ProveedorDAO extends BaseDao<ProveedorDTO>{
	
	public List<ProveedorDTO> getListProveedor();

	public List<ProveedorDTO> obtenerProveedorVisible(Long value);
	
	List<ProveedorVO> getCatProveedor();

}
