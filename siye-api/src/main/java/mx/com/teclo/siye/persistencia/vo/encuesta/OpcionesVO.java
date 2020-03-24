package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;

public class OpcionesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8769910282690998588L;

	private Long idOpcion;
	private String txOpcion;
	private Integer stMarcado;
	private String cdOpcion;
	private Integer nuOrden;
	private Integer stActivo;

	/**
	 * @return the idOpcion
	 */
	public Long getIdOpcion() {
		return idOpcion;
	}

	/**
	 * @param idOpcion the idOpcion to set
	 */
	public void setIdOpcion(Long idOpcion) {
		this.idOpcion = idOpcion;
	}

	/**
	 * @return the txOpcion
	 */
	public String getTxOpcion() {
		return txOpcion;
	}

	/**
	 * @param txOpcion the txOpcion to set
	 */
	public void setTxOpcion(String txOpcion) {
		this.txOpcion = txOpcion;
	}

	/**
	 * @return the cdOpcion
	 */
	public String getCdOpcion() {
		return cdOpcion;
	}

	/**
	 * @param cdOpcion the cdOpcion to set
	 */
	public void setCdOpcion(String cdOpcion) {
		this.cdOpcion = cdOpcion;
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
	 * @return the stMarcado
	 */
	public Integer getStMarcado() {
		return stMarcado;
	}

	/**
	 * @param stMarcado the stMarcado to set
	 */
	public void setStMarcado(Integer stMarcado) {
		this.stMarcado = stMarcado;
	}

}
