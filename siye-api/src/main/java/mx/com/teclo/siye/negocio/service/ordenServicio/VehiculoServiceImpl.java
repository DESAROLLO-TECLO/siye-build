package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.VehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
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
	public VehiculoVO bucarVehiculoPlaca(String placa) throws NotFoundException  {
//		String mnsjError = "";
//		try{
		VehiculoDTO vehiculoDTO = vehiculoDAO.buscarVehiculoPorPlaca(placa);
		
		if(vehiculoDTO == null){
//			mnsjError= "No existe el vehiculo";
			throw new NotFoundException("No Existe Vehiculo");
		}
		VehiculoVO vehiVO = ResponseConverter.copiarPropiedadesFull(vehiculoDTO, VehiculoVO.class);
		
		return vehiVO;
		
//		}catch(Exception e){
//			if(mnsjError != null && !mnsjError.isEmpty() && !mnsjError.equals(null)) {
//				throw new NotFoundException(mnsjError);
//			} else {
////				e.printStackTrace();
//				throw new NotFoundException("¡Ha ocurrido un imprevisto!, porfavor contacte al administrador");
//			}
//			
//		}
		
	}
	

}
