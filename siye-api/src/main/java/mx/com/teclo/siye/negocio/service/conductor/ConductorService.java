package mx.com.teclo.siye.negocio.service.conductor;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

public interface ConductorService {
	
	public PersonaGenericaVO nuevoConductor(PersonaGenericaVO personaGenericaVO, String mensajeErr)	throws Exception, BusinessException, NotFoundException;
	
	public void ordenarConductores(String mensajeErr) throws Exception, BusinessException, NotFoundException;
}
