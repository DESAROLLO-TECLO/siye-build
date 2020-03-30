package mx.com.teclo.siye.negocio.service.expedienteImg;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

public interface ExpedienteImgService {
	
	/*@Author Mavericks
	 *@param  List<ExpedienteImgVO>
	 *@param  idUsuario 
	 *@return List<ExpedienteImgVO>
	 *  Metodo para realizar la insercion de la imagen de evidencia, a nivel proceso, gral o incidencia o pregunta */
	public List<ImagenVO> saveExpediente(List<ImagenVO> expedientes, Long idUsuario);
	
	/*@Author Mavericks
	 *@param  List<CargaExpedienteImgVO>
	 *@param  tipoBusqueda
	 *@param  valorBusqueda 
	 *@return List<CargaExpedienteImgVO>
	 *  Metodo para obtener la informacion del Servicio, nivel donde se encuentra y en caso las iamgenes  */
	public List<CargaExpedienteImgVO> getInformacionExpediente(String tipoBusqueda, String valor);
	
	
	/*@Author Mavericks
	 *@return List<TipoExpedienteVO>
	 *  Metodo para obtener tipos de expedientes para clasificar las imagenes
	 *   */
	public List<TipoExpedienteVO> getTipoExpediente();

}
