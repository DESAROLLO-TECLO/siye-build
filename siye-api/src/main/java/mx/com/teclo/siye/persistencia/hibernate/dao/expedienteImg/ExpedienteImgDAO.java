package mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

public interface ExpedienteImgDAO extends BaseDao<ExpedientesImgDTO> {
	
	/**
     * @author Maverick
     * @param idOrdenServicio
     * @return List<ExpedientesImgDTO>
     * Metodo para traer todos los expedientes que tenga una orden de servicio 
     * */
	public List<ExpedientesImgDTO> getAllExpedientesDTO(Long idOrdenServicio);
	
	/**
     * @author Maverick
     * @param idOrdenServicio
     * @return List<ImagenVO>
     * Metodo para traer las imagenes que pertenescan a ordenes de servicio 
     * */
	public List<ImagenVO> getAllExpedientesImgVO(List<Long> idOrdenServicio);

}
