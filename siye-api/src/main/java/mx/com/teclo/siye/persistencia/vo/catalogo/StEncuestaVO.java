package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class StEncuestaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2117870156790947860L;

	private Long idStEncuesta;
	private String cdStEncuesta;
	private String nbStEncuesta;
	private Integer nuOrden;
	private String cdColor;
	private Integer stActivo;

	/**
	 * @return the idStEncuesta
	 */
	public Long getIdStEncuesta() {
		return idStEncuesta;
	}

	/**
	 * @param idStEncuesta the idStEncuesta to set
	 */
	public void setIdStEncuesta(Long idStEncuesta) {
		this.idStEncuesta = idStEncuesta;
	}

	/**
	 * @return the cdStEncuesta
	 */
	public String getCdStEncuesta() {
		return cdStEncuesta;
	}

	/**
	 * @param cdStEncuesta the cdStEncuesta to set
	 */
	public void setCdStEncuesta(String cdStEncuesta) {
		this.cdStEncuesta = cdStEncuesta;
	}

	/**
	 * @return the nbStEncuesta
	 */
	public String getNbStEncuesta() {
		return nbStEncuesta;
	}

	/**
	 * @param nbStEncuesta the nbStEncuesta to set
	 */
	public void setNbStEncuesta(String nbStEncuesta) {
		this.nbStEncuesta = nbStEncuesta;
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

	/**
	 * @return the cdColor
	 */
	public String getCdColor() {
		return cdColor;
	}

	/**
	 * @param cdColor the cdColor to set
	 */
	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
	}

}
