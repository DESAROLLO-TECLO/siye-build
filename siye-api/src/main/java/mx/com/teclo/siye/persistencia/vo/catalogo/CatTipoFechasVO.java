package mx.com.teclo.siye.persistencia.vo.catalogo;

public class CatTipoFechasVO {
	
	private Long idPeriodo;
	private String cdTipoFecha;
	private String nbTipoFecha;
	private String fechaInicio;
	private String fechaFin;
	
	
	/**
	 * @return the cdTipoFecha
	 */
	public String getCdTipoFecha() {
		return cdTipoFecha;
	}
	/**
	 * @param cdTipoFecha the cdTipoFecha to set
	 */
	public void setCdTipoFecha(String cdTipoFecha) {
		this.cdTipoFecha = cdTipoFecha;
	}
	/**
	 * @return the nbTipoFecha
	 */
	public String getNbTipoFecha() {
		return nbTipoFecha;
	}
	/**
	 * @param nbTipoFecha the nbTipoFecha to set
	 */
	public void setNbTipoFecha(String nbTipoFecha) {
		this.nbTipoFecha = nbTipoFecha;
	}
	/**
	 * @return the idPeriodo
	 */
	public Long getIdPeriodo() {
		return idPeriodo;
	}
	/**
	 * @param idPeriodo the idPeriodo to set
	 */
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
