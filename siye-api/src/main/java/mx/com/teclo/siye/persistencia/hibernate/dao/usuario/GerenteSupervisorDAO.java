package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;


import java.util.List;

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
	
	
	/**
     * Descripción: Obtener ID de los centros de instalación administrados por el usuario firmado
     * 
     * @author Maverick
     * @param idSupervisor
     * @return List<Long>
     * */
	public List<Long> getIdCentroInstalacion(Long idSupervisor);

	
	


}
