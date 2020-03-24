package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class TipoPreguntaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7862897051524979938L;

	private Long idTpPregunta;
	private String cdTpPregunta;
	private String nbTpPregunta;
	private Integer nuOrden;
	private Integer stActivo;

	/**
	 * @return the idTpPregunta
	 */
	public Long getIdTpPregunta() {
		return idTpPregunta;
	}

	/**
	 * @param idTpPregunta the idTpPregunta to set
	 */
	public void setIdTpPregunta(Long idTpPregunta) {
		this.idTpPregunta = idTpPregunta;
	}

	/**
	 * @return the cdTpPregunta
	 */
	public String getCdTpPregunta() {
		return cdTpPregunta;
	}

	/**
	 * @param cdTpPregunta the cdTpPregunta to set
	 */
	public void setCdTpPregunta(String cdTpPregunta) {
		this.cdTpPregunta = cdTpPregunta;
	}

	/**
	 * @return the nbTpPregunta
	 */
	public String getNbTpPregunta() {
		return nbTpPregunta;
	}

	/**
	 * @param nbTpPregunta the nbTpPregunta to set
	 */
	public void setNbTpPregunta(String nbTpPregunta) {
		this.nbTpPregunta = nbTpPregunta;
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
