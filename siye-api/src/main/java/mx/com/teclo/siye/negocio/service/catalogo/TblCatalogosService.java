package mx.com.teclo.siye.negocio.service.catalogo;



import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

public interface TblCatalogosService {
	
	public String altaCentrodeInstalacion(CentroInstalacionVO centroInstalacionVO) throws BusinessException;
	

}
