package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;

public interface MonitoreoIncidenciaService {
	
	public void getMonIncidencias(String mensajeErr) throws Exception, BusinessException, NotFoundException;
}
