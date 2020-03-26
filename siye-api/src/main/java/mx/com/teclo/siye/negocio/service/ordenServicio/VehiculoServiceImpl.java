package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.VehiculoDAO;
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

}
