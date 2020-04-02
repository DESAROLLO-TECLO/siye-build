package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.ConfiguracionVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.hibernate.dto.configuracion.ConfiguracionDTO;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConductorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConfiguracionParamDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.InstaladorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ProveedorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoKitDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoVehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.KitInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.InstaladorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoKitVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class CatalogoServiceImpl implements CatalogoService{
	
	@Autowired
	private StEncuestaDAO stEncuestaDAO;
	
	@Autowired
	private TipoVehiculoDAO tipoVehiculoDAO; 
	
	@Autowired
	private ConductorDAO conductorDAO;
	
	@Autowired
	private InstaladorDAO instaladorDAO;
	
	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;
	
	@Autowired
	private KitInstalacionDAO kitInstalacionDAO;
	
	@Autowired
	private PlanDAO planDAO;
	
	@Autowired
	private TipoKitDAO tipoKitDAO;
	
	@Autowired
	private ProveedorDAO proveedorDAO;

	@Autowired
	private StSeguimientoDAO stSeguimientoDAO;
	
	@Autowired
	private ConfiguracionParamDAO configuracionDAO;
	
	@Transactional
	@Override
	public List<StEncuestaVO> estatusEncuesta() throws NotFoundException {
		List<StEncuestaDTO> stEncuesta = stEncuestaDAO.stEncuesta();
		if(stEncuesta.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<StEncuestaVO> listStEncuesta = ResponseConverter.converterLista(new ArrayList<>(), stEncuesta, StEncuestaVO.class);
		return listStEncuesta;
	}
	
	@Transactional
	@Override
	public List<TipoVehiculoVO> tipoVehiculo() throws NotFoundException {
		List<TipoVehiculoDTO> tpVehiculo = tipoVehiculoDAO.tipoVehiculo();
		if(tpVehiculo.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<TipoVehiculoVO> listTpVehiculo = ResponseConverter.converterLista(new ArrayList<>(), tpVehiculo, TipoVehiculoVO.class);
		return listTpVehiculo;
	}
	
	@Transactional
	@Override
	public List<ConductorVO> getTransportistas() throws NotFoundException {
		List<ConductorDTO> listaConductorDTO = conductorDAO.getTransportistas();
		if(listaConductorDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<ConductorVO> listaConductorVO = ResponseConverter.converterLista(new ArrayList<>(), listaConductorDTO, ConductorVO.class);
		return listaConductorVO;
	}
	
	@Transactional
	@Override
	public List<InstaladorVO> getTecnicos() throws NotFoundException {
		List<InstaladorDTO> listaConductorDTO = instaladorDAO.getTecnicos();
		if(listaConductorDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<InstaladorVO> listaConductorVO = ResponseConverter.converterLista(new ArrayList<>(), listaConductorDTO, InstaladorVO.class);
		return listaConductorVO;
	}

	@Override
	@Transactional
	public CatalogosOrdenProcesoVO getCatalogosOrdenProceso()  throws NotFoundException {
		CatalogosOrdenProcesoVO copVO = new CatalogosOrdenProcesoVO();
		
		List<CentroInstalacionDTO> ciDTO = centroInstalacionDAO.obtenerCentroInstalacion();
		List<PlanDTO> pDTO = planDAO.getPlanAll();
		List<KitInstalacionDTO> kiDTO = kitInstalacionDAO.obtenerkitInstalacionAll();
		List<TipoKitDTO> tpKitDTO = tipoKitDAO.getTipoKit();
		List<ProveedorDTO> proveedorDTO = proveedorDAO.getListProveedor();

		
		
		
		List<CentroInstalacionVO> ciVO = ResponseConverter.converterLista(new ArrayList<>(),ciDTO , CentroInstalacionVO.class);
		
		List<KitInstalacionVO> kiVO = ResponseConverter.converterLista(new ArrayList<>(), kiDTO, KitInstalacionVO.class);
	
		List<PlanVO> pVO = ResponseConverter.converterLista(new ArrayList<>(), pDTO, PlanVO.class);	
		
		List<TipoKitVO> tpKitVO= ResponseConverter.converterLista(new ArrayList<>(), tpKitDTO, TipoKitVO.class);
		
		List<ProveedorVO> proveedorVO = ResponseConverter.converterLista(new ArrayList<>(), proveedorDTO, ProveedorVO.class); 
		
		List<TipoVehiculoVO> tvVO = this.tipoVehiculo();
		
		// Centros de Instalacion
		copVO.setCentrosInstalacion(ciVO);
		// Kit de instalacion
		copVO.setKitInstalacion(kiVO);
		// Plan
		copVO.setPlan(pVO);
		// tipo Vehiculo
		copVO.setTipoVehiculo(tvVO);
		//tipo Kit
		copVO.setTipoKit(tpKitVO);
		
		copVO.setProveedorVO(proveedorVO);
		
		return copVO;
	}
	
	@Transactional
	@Override
	public List<StSeguimientoVO> obtenerStSeguimientoByCdTpSeguimiento(String cdTpSeguimiento) throws NotFoundException {
		List<StSeguimientoDTO> listStSeguimientoDTO = stSeguimientoDAO.obtenerStSeguimientoByCdTpSeguimiento(cdTpSeguimiento);
		if(listStSeguimientoDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<StSeguimientoVO> listStSeguimientoVO = ResponseConverter.converterLista(new ArrayList<>(), listStSeguimientoDTO, StSeguimientoVO.class);
		return listStSeguimientoVO;
	}
	
	@Transactional
	@Override
	public ConfiguracionVO configuracion(String cdLlavePConfig) throws NotFoundException{
		ConfiguracionVO voReturn = null;
		ConfiguracionDTO a1 =  configuracionDAO.configuracion(cdLlavePConfig);
		if(a1 == null)
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		voReturn = new ConfiguracionVO();
		ResponseConverter.copiarPropriedades(voReturn, a1);
		return voReturn;
	}

}
