package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class OrdenServicioServiceImpl implements OrdenServicioService{
	
	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Transactional
	@Override
	public List<OrdenServicioVO> consultaOrden(String cdTipoBusqueda, String valor) throws NotFoundException {
		
		List<OrdenServicioDTO> listOrdenServicioDTO = ordenServicioDAO.consultaOrden();
		if(listOrdenServicioDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<OrdenServicioVO> listOrdenServicioVO = ResponseConverter.converterLista(new ArrayList<>(), listOrdenServicioDTO, OrdenServicioVO.class);
		return listOrdenServicioVO;
	}

	@Transactional
	@Override
	public Boolean actualizaOrdenServicio(OrdenServicioVO osVO) throws NotFoundException, BusinessException{
		OrdenServicioDTO osDTO = ordenServicioDAO.obtenerOrdenServicio(osVO.getIdOrdenServicio());
		if(osDTO == null)
			throw new NotFoundException("El registro que intenta actualizar no existe");
		
		if(osVO.getIdLoteOds() != null) {
			LoteOrdenServicioDTO losDTO = new LoteOrdenServicioDTO();
			losDTO.setIdLoteOds(osVO.getIdLoteOds().getIdLoteOds());
			osDTO.setLoteOrdenServicio(losDTO);
		}
		if(osVO.getIdVehiculo() != null) {
			VehiculoDTO vDTO = new VehiculoDTO();
			vDTO.setIdVehiculo(osVO.getIdVehiculo().getIdVehiculo());
			osDTO.setVehiculo(vDTO);
		}
		
		if(osVO.getIdCentroInstalacion() != null) {
			CentroInstalacionDTO ciDTO = new CentroInstalacionDTO();
			ciDTO.setIdCentroInstalacion(ciDTO.getIdCentroInstalacion());
			osDTO.setCentroInstalacion(ciDTO);
		}
		
		if(osVO.getIdKitInstalacion() != null) {
			KitInstalacionDTO kiDTO = new KitInstalacionDTO();
			kiDTO.setIdKitInstalacion(osVO.getIdKitInstalacion().getIdKitInstalacion());
			osDTO.setKitInstalacion(kiDTO);
		}
		
		if(osVO.getIdPlan() != null) {
			PlanDTO pDTO = new PlanDTO();
			pDTO.setIdPlan(osVO.getIdPlan().getIdPlan());
			osDTO.setPlan(pDTO);
		}
		
		
		
		
		return null;
	}

}
