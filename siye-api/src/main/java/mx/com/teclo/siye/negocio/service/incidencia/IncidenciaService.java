package mx.com.teclo.siye.negocio.service.incidencia;


import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.incidencia.AltaIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidencVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;


public interface IncidenciaService {

	/**
     * Descripción: Obtener el registro de incidencia
     * @author Eric Rivera
     * @param  cdIncidencia
     * @return IncidenciaDTO
     * */
	
	public IncidenciaVO getIncidenciabycdIncidencia(String cdIncidencia) throws NotFoundException;
	
	/**
     * Descripción: Dar de alta incidencia
     * @author Estephanie Chavez
	 * @param incidenciaVO
	 * @return
	 * @throws BusinessException
	 */
	public Boolean altaIncidencia(AltaIncidenciaVO altaIncidenciaVO) throws BusinessException;
	
	
	public IncidencVO incidenciaByCdIncidencia(String cdIncidenc) throws NotFoundException;

}
