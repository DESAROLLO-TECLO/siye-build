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
		List<OrdenServicioDTO> listOrdenServicioDTO = new ArrayList<>(); 
		switch(cdTipoBusqueda) {
			case "PLACA":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByPlaca(valor);	
				break;
			
			case "ORDEN_SERVICIO":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByOrdenServicio(valor);
				break;
			
			case "VIN":
				listOrdenServicioDTO = ordenServicioDAO.consultaOrdenByVin(valor);
				break;
			}
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
		
		if(osVO.getLoteOrdenServicio() != null) {
			LoteOrdenServicioDTO losDTO = new LoteOrdenServicioDTO();
			losDTO.setIdLoteOds(osVO.getLoteOrdenServicio().getIdLoteOds());
			osDTO.setLoteOrdenServicio(losDTO);
		}
		if(osVO.getVehiculo() != null) {
			VehiculoDTO vDTO = new VehiculoDTO();
			vDTO.setIdVehiculo(osVO.getVehiculo().getIdVehiculo());
			osDTO.setVehiculo(vDTO);
		}
		
		if(osVO.getCentroInstalacion() != null) {
			CentroInstalacionDTO ciDTO = new CentroInstalacionDTO();
			ciDTO.setIdCentroInstalacion(ciDTO.getIdCentroInstalacion());
			osDTO.setCentroInstalacion(ciDTO);
		}
		
		if(osVO.getKitInstalacion() != null) {
			KitInstalacionDTO kiDTO = new KitInstalacionDTO();
			kiDTO.setIdKitInstalacion(osVO.getKitInstalacion().getIdKitInstalacion());
			osDTO.setKitInstalacion(kiDTO);
		}
		
		if(osVO.getPlan() != null) {
			PlanDTO pDTO = new PlanDTO();
			pDTO.setIdPlan(osVO.getPlan().getIdPlan());
			osDTO.setPlan(pDTO);
		}
		
		// Actualizacion de Campos
		osDTO.setHrCita(osVO.getHrCita());
		osDTO.setFhAtencionFin(osVO.getFhAtencionFin());
		osDTO.setFhAtencionIni(osVO.getFhAtencionIni());
		osDTO.setIdOrdenODS(1L);
	
		ordenServicioDAO.update(osDTO);		
		return true;
	}

}
