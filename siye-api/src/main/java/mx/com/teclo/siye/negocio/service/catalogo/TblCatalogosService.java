package mx.com.teclo.siye.negocio.service.catalogo;



import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

public interface TblCatalogosService {
	
	public String altaCentrodeInstalacion(CentroInstalacionVO centroInstalacionVO) throws BusinessException;
	
	public Boolean actualizaCentrodeInstalacion(CentroInstalacionVO centroInstalacionVO) throws NotFoundException, BusinessException;
	
	public CentroInstalacionVO findCentroInstalacion(Long idCentroInstalacion);

	public String altaPersona(PersonaVO personaVO) throws BusinessException;

	public Boolean actualizaPersona(PersonaVO personaVO) throws NotFoundException, BusinessException;

	public PersonaVO findPersona(Integer idPersona);
}
