package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;


import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.GerenteSupervisorDTO;

public interface GerenteSupervisorDAO extends BaseDao<GerenteSupervisorDTO>{
	
	/**
     * Descripción: Obtener los datos de GerenteSupervisor mediante 
     * 
     * @author Estephanie Chavez
     * @param idOrdenServicio
     * @return OrdenServicioDTO
     * */
	public GerenteSupervisorDTO consultaGerenteSupervisorBySupervisor(Long supervisor);

	
	


}
