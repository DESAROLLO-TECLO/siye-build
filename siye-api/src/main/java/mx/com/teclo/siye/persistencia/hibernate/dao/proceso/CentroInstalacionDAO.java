package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;


import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

/**
 * Administra la alta, actualizaci&oacute;n, consulta y baja l&oacute:gica de un
 * centro de instalaci&oacute;n
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface CentroInstalacionDAO extends BaseDao<CentroInstalacionDTO> {

	/**
	 * Obtiene un centro de instalaci&oacute;n por su identificador
	 * 
	 * @param idCentroInstalacion
	 * @return CentroInstalacionVO
	 */
	public CentroInstalacionVO obtenerCentroInstalacion(Long idCentroInstalacion);

	
	/**
	 * Obtiene todos los centros de instalacion
	 * 
	 * @return List<CentroInstalacionDTO>
	 */
	public List<CentroInstalacionDTO> obtenerCentroInstalacion();
	
	public CentroInstalacionDTO centroIns(Long centroIn);
	
	/**
	 * Obtiene todos los centros de instalacion visibles
	 * 
	 * @return List<CentroInstalacionDTO>
	 */
	public List<CentroInstalacionDTO> obtenerCentroInstalacionVisible(Long value);
	
	/** 
	 * Descripción: Obtener la lista de incidencias
     * @author Estephanie Chavez
	 * @return Long
	 */
	public Long getUltimoId();
	
	List<CentroInstalacionVO> getCatCentroInstalacion();
}
