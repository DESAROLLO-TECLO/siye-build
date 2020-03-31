package mx.com.teclo.siye.negocio.service.incidencia;


import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;

public interface IncidenciaService {

	/**
     * Descripción: Obtener el registro de incidencia
     * @author Eric Rivera
     * @param  cdIncidencia
     * @return IncidenciaDTO
     * */
	
	public IncidenciaVO getIncidenciabycdIncidencia(String cdIncidencia);

}
