package mx.com.teclo.siye.negocio.service.proceso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;

@Service
public class ProcesoServiceImpl implements ProcesoService {

	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Autowired
	private PlanProcesoDAO planProcesoDAO;

	@Override
	public OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud) {
		return ordenServicioDAO.obtenerOrdenServicio(idSolicitud);
	}

	@Override
	public List<PlanProcesoDTO> getPlanOrdenServicio(Long idPlan) {
		return planProcesoDAO.obtenerPorcesosPlan(idPlan);
	}

	@Override
	public List<ProcesoEncuestaDTO> getEncuestasProceso(Long idProceso) {
		return null;
	}

}
