package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.Date;

public class EncuestaDetalleVO {
	
	private Long idEncuesta;
	private String nbEncuesta;
	private Date fhInicio;
	private Date fhFin;
	private String nuPorcentaje;
	private String preguntas;
	private String estatus;
	private String nbColor;
	
	
	
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
	public Date getFhInicio() {
		return fhInicio;
	}
	/**
	 * @param fhInicio the fhInicio to set
	 */
	public void setFhInicio(Date fhInicio) {
		this.fhInicio = fhInicio;
	}
	/**
	 * @return the fhFin
	 */
	public Date getFhFin() {
		return fhFin;
	}
	/**
	 * @param fhFin the fhFin to set
	 */
	public void setFhFin(Date fhFin) {
		this.fhFin = fhFin;
	}
	/**
	 * @return the nuPorcentaje
	 */
	public String getNuPorcentaje() {
		return nuPorcentaje;
	}
	/**
	 * @param nuPorcentaje the nuPorcentaje to set
	 */
	public void setNuPorcentaje(String nuPorcentaje) {
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
	 * @return the nbColor
	 */
	public String getNbColor() {
		return nbColor;
	}
	/**
	 * @param nbColor the nbColor to set
	 */
	public void setNbColor(String nbColor) {
		this.nbColor = nbColor;
	}
	
}
