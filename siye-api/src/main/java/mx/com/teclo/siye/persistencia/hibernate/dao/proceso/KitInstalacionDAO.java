package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstalacionVO;

/**
 * Administra la alta, actualizaci&oacute;n, consulta y baja l&oacute:gica de un
 * kit de instalaci&oacute;n
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface KitInstalacionDAO extends BaseDao<KitInstalacionDTO> {

	/**
	 * Obtiene un kit de instalaci&oacute;n por su identificador
	 * 
	 * @param idKitInstalacion
	 * @return KitInstalacionVO
	 */
	public KitInstalacionVO obtenerKitInstalacion(Long idKitInstalacion);
	
	
	/**
	 * Obtiene todos los kit de instalacion
	 * @return List<KitInstalacionV>
	 */
	
	public List<KitInstalacionDTO> obtenerkitInstalacionAll()throws NotFoundException ;

	public KitInstalacionDTO kitIns(String cdKitIns);
	
	public KitInstalacionDTO ultimoId();
	
	List<KitInstalacionVO> getCatKitInstalacion();
}
