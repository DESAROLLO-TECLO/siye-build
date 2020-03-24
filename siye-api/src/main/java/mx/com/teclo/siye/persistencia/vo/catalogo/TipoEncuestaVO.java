package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TipoEncuestaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7094408856725616099L;

	private Long idTipoEncuesta;
	private String cdTipoEncuesta;
	private String nbTipoEncuesta;
	private Integer nuOrden;
	@JsonIgnore
	private Integer stActivo;

	/**
	 * @return the idTipoEncuesta
	 */
	public Long getIdTipoEncuesta() {
		return idTipoEncuesta;
	}

	/**
	 * @param idTipoEncuesta the idTipoEncuesta to set
	 */
	public void setIdTipoEncuesta(Long idTipoEncuesta) {
		this.idTipoEncuesta = idTipoEncuesta;
	}

	/**
	 * @return the cdTipoEncuesta
	 */
	public String getCdTipoEncuesta() {
		return cdTipoEncuesta;
	}

	/**
	 * @param cdTipoEncuesta the cdTipoEncuesta to set
	 */
	public void setCdTipoEncuesta(String cdTipoEncuesta) {
		this.cdTipoEncuesta = cdTipoEncuesta;
	}

	/**
	 * @return the nbTipoEncuesta
	 */
	public String getNbTipoEncuesta() {
		return nbTipoEncuesta;
	}

	/**
	 * @param nbTipoEncuesta the nbTipoEncuesta to set
	 */
	public void setNbTipoEncuesta(String nbTipoEncuesta) {
		this.nbTipoEncuesta = nbTipoEncuesta;
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
