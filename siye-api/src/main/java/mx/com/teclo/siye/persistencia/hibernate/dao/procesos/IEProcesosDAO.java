package mx.com.teclo.siye.persistencia.hibernate.dao.procesos;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;

public interface IEProcesosDAO extends BaseDao<IEprocesosDTO>{
	
	/**
	 * @Descripción: Método para consultar el proceso por su identificador
	 * único
	 * @author Estephanie Chavez
	 * @return IEprocesosDTO
	 */
	public IEprocesosDTO consultarProcesoByidProceso(Long idProceso);
}
