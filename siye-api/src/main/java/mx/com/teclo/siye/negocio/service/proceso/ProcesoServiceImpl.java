package mx.com.teclo.siye.negocio.service.proceso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;



@Service
public class ProcesoServiceImpl implements ProcesoService {
	
	@Autowired
	private OrdenServicioDAO ordenServicioDAO;	

	@Override
	public OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud) {
		// TODO Auto-generated method stub
		return ordenServicioDAO.obtenerOrdenServicio(idSolicitud);
	}

}
