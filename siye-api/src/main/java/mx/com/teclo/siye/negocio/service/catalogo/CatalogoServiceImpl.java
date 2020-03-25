package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.ConductorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.InstaladorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.TipoVehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.InstaladorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
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
}
