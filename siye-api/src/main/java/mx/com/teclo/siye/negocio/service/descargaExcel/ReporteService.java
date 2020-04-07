package mx.com.teclo.siye.negocio.service.descargaExcel;

import mx.com.teclo.siye.persistencia.vo.incidencia.ReporteVO;

public interface ReporteService {

	/**
	 * @Descripción: Reporte en excel del resumen de la 
	 * encuesta de la incidencia
	 * @author Estephanie Chavez
	 * @param ReporteVO
	 * @return byte[]>
	 */
	public byte[] reporteEXCEL(ReporteVO reporteVO);
	
}
