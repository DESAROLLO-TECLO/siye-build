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
	
	/**
     * @author Maverick
     * @param numOrdenServicio
     * @param Long idProceso
     * @return List<ImagenVO>
     * Metodo para traer las imagenes que pertenescan a ordenes de servicio y al plan corespondiente 
     * */
	public List<ImagenVO> getImgByProceso(Long OrdenServicio, Long idProceso);
	
	/**
     * @author Maverick
     * @param numOrdenServicio
     * @param Long idEncuesta
     * @return List<ImagenVO>
     * Metodo para traer las imagenes que pertenescan a ordenes de servicio y a la encuensta 
     * */
	public List<ImagenVO> getImgByEncuesta(Long OrdenServicio, Long idEncuesta);
	
	
	/**
     * @author Maverick
     * @param numOrdenServicio
     * @param Long idPlan
     * @return List<ImagenVO>
     * Metodo para traer las imagenes que pertenescan a ordenes de servicio y a la pregunta especifica 
     * */
	public List<ImagenVO> getImgByPregunta(Long OrdenServicio, Long idPregunta);
	
	/**
     * @author Maverick
     * @param numOrdenServicio
     * @return List<ImagenVO>
     * Metodo para traer las imagenes que pertenescan a una OS y que estan en este nivel
     * */
	public List<ImagenVO> getImgByOrdenServicio(Long OrdenServicio);
	
	/**
     * @author Maverick
     * @param numOrdenServicio
     * @return List<ImagenVO>
     * Metodo para traer las iamgenes que pertenes a nivel OS
     * */
	public List<ImagenVO> getImagenOS(Long OrdenServicio, String restriccion);
	/**
     * @author Mannuel
     * @param numOrdenServicio
     * @return List<ImagenVO>
     * Metodo para traer lista de imagenes por incidencia
     * */
	public List<ImagenVO> getImagenesByIdIncidencia(Long idIncidencia);
	
	
	/**
     * @author Maverick
     * @param consulta
     * @return List<ImagenVO>
     * Metodo para traer las imagenes que pertenescan a un nivel de proceso incidencia o evidencia 
     * */
	public List<ImagenVO> getImagenPorNivel(StringBuilder consulta);
}
