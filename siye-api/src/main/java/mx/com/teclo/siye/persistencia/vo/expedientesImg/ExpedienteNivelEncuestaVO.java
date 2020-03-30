package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelEncuestaVO {
	
	private Long idEncuesta;
	private String cdEncuesta;
	private Long nuMaxImg;
	private List<ImagenVO> imagenes;
	private List<ExpedienteNivelPreguntaVO> preguntas;
	
	
	
	/**
	 * @return the idEncuesta
	 */
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	/**
	 * @param idEncuesta the idEncuesta to set
	 */
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	/**
	 * @return the cdEncuesta
	 */
	public String getCdEncuesta() {
		return cdEncuesta;
	}
	/**
	 * @param cdEncuesta the cdEncuesta to set
	 */
	public void setCdEncuesta(String cdEncuesta) {
		this.cdEncuesta = cdEncuesta;
	}
	/**
	 * @return the nuMaxImg
	 */
	public Long getNuMaxImg() {
		return nuMaxImg;
	}
	/**
	 * @param nuMaxImg the nuMaxImg to set
	 */
	public void setNuMaxImg(Long nuMaxImg) {
		this.nuMaxImg = nuMaxImg;
	}
	/**
	 * @return the imagenes
	 */
	public List<ImagenVO> getImagenes() {
		return imagenes;
	}
	/**
	 * @param imagenes the imagenes to set
	 */
	public void setImagenes(List<ImagenVO> imagenes) {
		this.imagenes = imagenes;
	}
	/**
	 * @return the preguntas
	 */
	public List<ExpedienteNivelPreguntaVO> getPreguntas() {
		return preguntas;
	}
	/**
	 * @param preguntas the preguntas to set
	 */
	public void setPreguntas(List<ExpedienteNivelPreguntaVO> preguntas) {
		this.preguntas = preguntas;
	}
}
