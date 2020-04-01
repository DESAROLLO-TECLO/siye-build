package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.InstaladorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;


public interface CatalogoService {
	
	public List<StEncuestaVO> estatusEncuesta() throws NotFoundException;
	
	public List<TipoVehiculoVO> tipoVehiculo() throws NotFoundException;
	
	public List<ConductorVO> getTransportistas() throws NotFoundException;
	
	public List<InstaladorVO> getTecnicos() throws NotFoundException;
	
	public CatalogosOrdenProcesoVO getCatalogosOrdenProceso() throws NotFoundException;

	List<StSeguimientoVO> obtenerStSeguimientoByCdTpSeguimiento(String cdTpSeguimiento) throws NotFoundException;
	
	
	

}
