package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OpcionesDTO;

public interface OpcionesDAO extends BaseDao<OpcionesDTO>{

	/**
	 * @Descripción: Método para consultar una opción por
	 * su identificador unico
	 * @author Mannnuel
	 * @return OpcionesDTO
	 */
	public OpcionesDTO opcion(Long idOpcion);
	
}
