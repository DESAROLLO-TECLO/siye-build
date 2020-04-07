package mx.com.teclo.siye.negocio.service.instalador;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;

public interface PersonasService {
	
	public PersonaGenericaVO nuevoInstalador(PersonaGenericaVO personaGenericaVO, String mensajeErr) throws Exception, BusinessException, NotFoundException;
	
	public void ordenarInstaladores(String mensajeErr) throws Exception, BusinessException, NotFoundException; 
}
