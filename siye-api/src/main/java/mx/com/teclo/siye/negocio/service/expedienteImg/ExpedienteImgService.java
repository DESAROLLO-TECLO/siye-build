package mx.com.teclo.siye.negocio.service.expedienteImg;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteImgVO;

public interface ExpedienteImgService {
	
	/*@Author Mavericks
	 *@param  List<ExpedienteImgVO>
	 *@param  idUsuario 
	 *@return List<ExpedienteImgVO>
	 *  Metodo para realizar la insercion de la imagen de evidencia, a nivel proceso, gral o incidencia o pregunta */
	public List<ExpedienteImgVO> saveExpediente(List<ExpedienteImgVO> expedientes, Long idUsuario);

}
