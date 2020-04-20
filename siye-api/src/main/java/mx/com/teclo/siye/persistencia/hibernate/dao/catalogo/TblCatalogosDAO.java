package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TblCatalogosDTO;

public interface TblCatalogosDAO extends BaseDao<TblCatalogosDTO> {
	
	/** 
	 * Descripción: Obtener la lista de catalogos
     * @author Estephanie Chavez
	 * @return List<TblCatalogosDTO>
	 */
	public List<TblCatalogosDTO> getTblCatalogos();

}
