package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

public class ProcesoDetalleVO {
	
	private Long idOdsProceso;
	private Long idProceso;
	private String nbProceso;
	private Double nuPorcentaje;
	private String fhInicio;
	private String fhFin;
	private String estatus;
	private List<ImagenVO> imagenes;
	private List<EncuestaDetalleVO> encuestas;
	
	
	/**
	 * @return the idProceso
	 */
	public Long getIdProceso() {
		return idProceso;
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
	 * @param idProceso the idProceso to set
	 */
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}
	/**
	 * @return the nbProceso
	 */
	public String getNbProceso() {
		return nbProceso;
	}
	/**
	 * @param nbProceso the nbProceso to set
	 */
	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
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
	/**
	 * @return the encuestas
	 */
	public List<EncuestaDetalleVO> getEncuestas() {
		return encuestas;
	}
	/**
	 * @param encuestas the encuestas to set
	 */
	public void setEncuestas(List<EncuestaDetalleVO> encuestas) {
		this.encuestas = encuestas;
	}
	/**
	 * @return the idOdsProceso
	 */
	public Long getIdOdsProceso() {
		return idOdsProceso;
	}
	/**
	 * @param idOdsProceso the idOdsProceso to set
	 */
	public void setIdOdsProceso(Long idOdsProceso) {
		this.idOdsProceso = idOdsProceso;
	}

}
