package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EstatusCalificacionDTO;

public interface EstatusCalificacionDAO extends BaseDao<EstatusCalificacionDTO>{

	/**
	 * @Descripción: Método para consultar el catálogo de califaciones
	 * @author Jorge Luis
	 * @return List<GradoDTO>
	 */
	public List<EstatusCalificacionDTO> calificacion();

	/**
	 * @Descripción: Método para consultar un estatus 
	 * de calificación mediante su identificador unico
	 * @author Jorge Luis
	 * @return EstatusCalificacionDTO
	 */
	public EstatusCalificacionDTO calificacion(String cdStCalificacion);
}
