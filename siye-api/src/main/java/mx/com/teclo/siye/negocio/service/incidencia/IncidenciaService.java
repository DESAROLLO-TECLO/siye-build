package mx.com.teclo.siye.negocio.service.incidencia;


import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.incidencia.AltaIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;

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

}
