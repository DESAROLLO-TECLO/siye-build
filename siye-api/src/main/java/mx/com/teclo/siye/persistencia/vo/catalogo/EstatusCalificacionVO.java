package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class EstatusCalificacionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6155109571777854047L;

	private Long idStCalificacion;
	private String cdStCalificacion;
	private String nbStCalificacion;
	private Integer nuOrden;
	private Integer stActivo;
	/**
	 * @return the idStCalificacion
	 */
	public Long getIdStCalificacion() {
		return idStCalificacion;
	}
	/**
	 * @param idStCalificacion the idStCalificacion to set
	 */
	public void setIdStCalificacion(Long idStCalificacion) {
		this.idStCalificacion = idStCalificacion;
	}
	/**
	 * @return the cdStCalificacion
	 */
	public String getCdStCalificacion() {
		return cdStCalificacion;
	}
	/**
	 * @param cdStCalificacion the cdStCalificacion to set
	 */
	public void setCdStCalificacion(String cdStCalificacion) {
		this.cdStCalificacion = cdStCalificacion;
	}
	/**
	 * @return the nbStCalificacion
	 */
	public String getNbStCalificacion() {
		return nbStCalificacion;
	}
	/**
	 * @param nbStCalificacion the nbStCalificacion to set
	 */
	public void setNbStCalificacion(String nbStCalificacion) {
		this.nbStCalificacion = nbStCalificacion;
	}
	/**
	 * @return the nuOrden
	 */
	public Integer getNuOrden() {
		return nuOrden;
	}
	/**
	 * @param nuOrden the nuOrden to set
	 */
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}
	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}
	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
	
	
}
