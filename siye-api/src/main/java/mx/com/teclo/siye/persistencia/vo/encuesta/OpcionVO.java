package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;

public class OpcionVO implements Serializable {

	private static final long serialVersionUID = 5687053790949441604L;

	private Long idOpcion;
	private String txOpcion;
	private String cdOpcion;
	private Integer nuOrden;
	private Integer stMarcado;
	private Integer stCorrecto;
	private Integer stActivo;
	// nuevo
	private Boolean cdMostrarCausas;
	private String cadenaCausas;

	public Long getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(Long idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getTxOpcion() {
		return txOpcion;
	}

	public void setTxOpcion(String txOpcion) {
		this.txOpcion = txOpcion;
	}

	public String getCdOpcion() {
		return cdOpcion;
	}

	public void setCdOpcion(String cdOpcion) {
		this.cdOpcion = cdOpcion;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
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

	/**
	 * @return the stCorrecto
	 */
	public Integer getStCorrecto() {
		return stCorrecto;
	}

	/**
	 * @param stCorrecto the stCorrecto to set
	 */
	public void setStCorrecto(Integer stCorrecto) {
		this.stCorrecto = stCorrecto;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Boolean getCdMostrarCausas() {
		return cdMostrarCausas;
	}

	public void setCdMostrarCausas(Boolean cdMostrarCausas) {
		this.cdMostrarCausas = cdMostrarCausas;
	}

	public String getCadenaCausas() {
		return cadenaCausas;
	}

	public void setCadenaCausas(String cadenaCausas) {
		this.cadenaCausas = cadenaCausas;
	}
	
	
	
	
}
