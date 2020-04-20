package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

public class EncuestaDetalleVO {
	
	private Long idEncuesta;
	private String nbEncuesta;
	private String fhInicio;
	private String fhFin;
	private Double nuPorcentaje;
	private String preguntas;
	private String estatus;
	private List<ImagenVO> imagenes;
	
	
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
	 * @return the nbEncuesta
	 */
	public String getNbEncuesta() {
		return nbEncuesta;
	}
	/**
	 * @param nbEncuesta the nbEncuesta to set
	 */
	public void setNbEncuesta(String nbEncuesta) {
		this.nbEncuesta = nbEncuesta;
	}
	/**
	 * @return the fhInicio
	 */
	public String getFhInicio() {
		return fhInicio;
	}
	/**
	 * @param fhInicio the fhInicio to set
	 */
	public void setFhInicio(String fhInicio) {
		this.fhInicio = fhInicio;
	}
	/**
	 * @return the fhFin
	 */
	public String getFhFin() {
		return fhFin;
	}
	/**
	 * @param fhFin the fhFin to set
	 */
	public void setFhFin(String fhFin) {
		this.fhFin = fhFin;
	}
	/**
	 * @return the nuPorcentaje
	 */
	public Double getNuPorcentaje() {
		return nuPorcentaje;
	}
	/**
	 * @param nuPorcentaje the nuPorcentaje to set
	 */
	public void setNuPorcentaje(Double nuPorcentaje) {
		this.nuPorcentaje = nuPorcentaje;
	}
	/**
	 * @return the preguntas
	 */
	public String getPreguntas() {
		return preguntas;
	}
	/**
	 * @param preguntas the preguntas to set
	 */
	public void setPreguntas(String preguntas) {
		this.preguntas = preguntas;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
	
}
