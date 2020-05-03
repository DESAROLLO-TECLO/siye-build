package mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.CompresorImgConfigDTO;

public interface CompresorImgConfigDAO extends BaseDao<CompresorImgConfigDTO>{

	/**
	 * @Descriocion: Retorna la lista de todas las configuraciones activas 
	 * para comprimir imagenes
	 * @author DanielUnitis
	 * @return List<CompresorImgConfigDTO>
	 */
	public List<CompresorImgConfigDTO> getAllConfoCompress();
	
}
