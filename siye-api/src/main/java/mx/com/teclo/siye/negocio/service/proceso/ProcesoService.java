package mx.com.teclo.siye.negocio.service.proceso;

import java.util.List;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;



public interface ProcesoService {
	
	/**
     * Consulta la informacion general de la orden de servicio para iniciar el proceso. 
     * @param String folioSolictud
     * @return List<OrdenServicioDTO>
     * David Guerra
     */
	OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud);

}
