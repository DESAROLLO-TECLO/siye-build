package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.VehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.filtro.FiltroVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	@Autowired
	private VehiculoDAO vehiculoDAO;

	@Override
	@Transactional(readOnly = true)
	public VehiculoVO obtenerVehiculo(Long idVehiculo) {
		return vehiculoDAO.obtenerVehiculo(idVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<VehiculoVO> buscarVehiculos(FiltroVehiculoVO filtro) {
		return vehiculoDAO.buscarVehiculos(filtro);
	}

	@Override
	@Transactional(readOnly = true)
	public VehiculoVO bucarVehiculoPlaca(String placa)  {
		VehiculoDTO vehiculoDTO = vehiculoDAO.buscarVehiculoPorPlaca(placa);
		
		VehiculoVO vehiVO = ResponseConverter.copiarPropiedadesFull(vehiculoDTO, VehiculoVO.class);
				//converterLista(new ArrayList<>(), vehiculoDTO, VehiculoVO.class);
		
		return vehiVO;
	}
	

}
