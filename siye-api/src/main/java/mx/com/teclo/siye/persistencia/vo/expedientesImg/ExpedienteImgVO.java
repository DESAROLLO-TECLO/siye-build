package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteImgVO {
	
	private Long idOrdenServicio;
	private Long idOdsEncuesta;
	private Long idProcesoEncuesta;
	private Long idPregunta;
	private List<ImagenVO> archivos;
	
	
	/**
	 * @return the idOrdenServicio
	 */
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	/**
	 * @param idOrdenServicio the idOrdenServicio to set
	 */
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	/**
	 * @return the archivos
	 */
	public List<ImagenVO> getArchivos() {
		return archivos;
	}
	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(List<ImagenVO> archivos) {
		this.archivos = archivos;
	}
	/**
	 * @return the idOdsEncuesta
	 */
	public Long getIdOdsEncuesta() {
		return idOdsEncuesta;
	}
	/**
	 * @param idOdsEncuesta the idOdsEncuesta to set
	 */
	public void setIdOdsEncuesta(Long idOdsEncuesta) {
		this.idOdsEncuesta = idOdsEncuesta;
	}
	/**
	 * @return the idProcesoEncuesta
	 */
	public Long getIdProcesoEncuesta() {
		return idProcesoEncuesta;
	}
	/**
	 * @param idProcesoEncuesta the idProcesoEncuesta to set
	 */
	public void setIdProcesoEncuesta(Long idProcesoEncuesta) {
		this.idProcesoEncuesta = idProcesoEncuesta;
	}
	/**
	 * @return the idPregunta
	 */
	public Long getIdPregunta() {
		return idPregunta;
	}
	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

}
