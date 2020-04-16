package mx.com.teclo.siye.negocio.service.expedienteImg;

import java.util.List;

import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.responsehttp.BadRequestHttpResponse;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

public interface ExpedienteImgService {
	
	/*@Author Maverick
	 *@param  List<ExpedienteImgVO>
	 *@param  idUsuario 
	 *@return List<ExpedienteImgVO>
	 *  Metodo para realizar la insercion de la imagen de evidencia, a nivel proceso, gral o incidencia o pregunta */
	public List<ImagenVO> saveExpediente(List<ImagenVO> expedientes, Long idUsuario) throws BusinessException,BadRequestHttpResponse;
	
	/*@Author Mavericks
	 *@param  List<CargaExpedienteImgVO>
	 *@param  tipoBusqueda
	 *@param  valorBusqueda 
	 *@return List<CargaExpedienteImgVO>
	 *  Metodo para obtener la informacion del Servicio, nivel donde se encuentra y en caso las iamgenes  */
	public List<CargaExpedienteImgVO> getInformacionExpediente(String tipoBusqueda, String valor);
	
	
	/*@Author Maverick
	 *@return List<TipoExpedienteVO>
	 *  Metodo para obtener tipos de expedientes para clasificar las imagenes
	 *   */
	public List<TipoExpedienteVO> getTipoExpediente();
	
	/*@Author Maverick
	 *@param  List<ImagenVO>
	 *@param  idUsuario 
	 *@return List<ImagenVO>
	 *  Metodo para realizar la eliminacion logica, de una evidencia en especifico */
	public List<ImagenVO> delListEvidencia(List<ImagenVO> expedientes, Long idUsuario);

	
	/*@Author Maverick
	 *@param nuOrdenServicio
	 *@param idValorBuscar
	 *@param cdNivel 
	 *@return List<ImagenVO>
	 *  Metodo para obtener las imagenes que pertenescan a la Orden de Servicio en el nivel Indicado*/
	public List<ImagenVO> getInfoExpedienteByNivel(Long nuOrderServicio, Long idValorBuscar, String cdNivel);
	
	/**
	 * @Author Estephanie Chavez
	 * @param imagenVO
	 * @return Boolean
	 * Metodo para guardar las imagenes provenientes de las incidencias
	 */
	public String saveImagenIncidencia(List<ImagenVO> listImagenVO, IncidenciaDTO incidenciaDTO) throws BusinessException ;
}
