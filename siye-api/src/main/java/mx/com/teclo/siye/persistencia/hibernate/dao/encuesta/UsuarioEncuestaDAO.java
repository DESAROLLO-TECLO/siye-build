package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;



import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;




public interface UsuarioEncuestaDAO extends BaseDao<OrdenEncuestaDTO>{
	
	
	/**
	 * @author David Guerra
	 * @param idEncuesta Long
	 * @param idFolioOrden
	 * @return UsuarioEncuestaIntentosDTO
	 */
	
    List<OrdenEncuestaDTO> getEncuestasPorOrden(Long idOrden);
	
    public List<OrdenEncuestaDTO> consultaOrdenByOrdenServicio(String valor);
    public List<OrdenEncuestaDTO> consultaOrdenByPlaca(String valor);
    public List<OrdenEncuestaDTO> consultaOrdenByVin(String valor);
    
    /**
     * @author DanielUnitis
     * @param idOrden
     * @param idEnceusta
     * @return OrdenEncuestaDTO
     */
    public OrdenEncuestaDTO consultaByIdOrdernIdEncuesta(Long idOrden,Long idEnceusta);
}
