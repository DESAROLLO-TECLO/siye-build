package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.Date;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;

public interface LoteOrdenServicioDAO extends BaseDao<LoteOrdenServicioDTO> {

	/***
	 * Obtiene el lote por su identificador
	 * 
	 * @param idOrdenServicio
	 * @return ArchivoLoteVO
	 */
	public ArchivoLoteVO obtenerLote(Long idLote);

	/**
	 * @Descripcion: Regresa una lista de lotes cargados de una fecha y por el id del usuario en sesion
	 * @author DanielUnitis
	 * @param idUserSession
	 * @return List<ArchivoLoteVO>
	 */
	public List<ArchivoLoteVO> obtenerLotesPorFecha(Long idUserSession,Date date);
	
}
