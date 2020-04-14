package mx.com.teclo.siye.persistencia.mybatis.dao.proceso;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoDTO;

public interface ProcesoDAO extends BaseDao<ProcesoDTO>{
	
	
	/**
     * Descripción: Obtener el proceso mediante el id
     * @author David Guerra
     * @param idProceso
     * @return IEprocesosDTO
     * */
	public ProcesoDTO obtenerProceso(Long idProceso);

}
