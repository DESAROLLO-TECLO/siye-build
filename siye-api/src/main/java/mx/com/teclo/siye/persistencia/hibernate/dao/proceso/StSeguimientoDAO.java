package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;

public interface StSeguimientoDAO extends BaseDao<StSeguimientoDTO> {

	/**
	 * Obtiene el siguiento por su identificador
	 * @param idSeguimiento
	 * @return
	 */

	public StSeguimientoVO obtenerSeguimiento(Long idSeguimiento);

	
	public StSeguimientoDTO obtenerSeguimientoDos(Long idSeg);


	StSeguimientoDTO obtenerStSeguimientoByCodigo(String cdStSeguimiento, String cdTpSeguimiento, String nbTpSeguimiento);

	List<StSeguimientoDTO> obtenerStSeguimientoByCdTpSeguimiento(String cdTpSeguimiento);


}
