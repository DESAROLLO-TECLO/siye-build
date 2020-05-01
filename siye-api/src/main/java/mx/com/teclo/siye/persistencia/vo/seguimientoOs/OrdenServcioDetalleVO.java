package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.Date;

public class OrdenServcioDetalleVO {

	private Long idPlan;
	private Long idOrdenServicio;
	private String nuOrdenServicio;
	private Date fechaAtencion;
	private String txTransportista;
	private String txPlaca;
	private String txtRepresentante;
	private String txSupervisor;
	private String txTecnicoInstalacion;
	private String txProceso;
	private String txEtapa;
	private Long nuIncidencia;
	private String estado;
	private String color;
	
	/**
	 * @return the nuOrdenServicio
	 */
	public String getNuOrdenServicio() {
		return nuOrdenServicio;
	}
	/**
	 * @param nuOrdenServicio the nuOrdenServicio to set
	 */
	public void setNuOrdenServicio(String nuOrdenServicio) {
		this.nuOrdenServicio = nuOrdenServicio;
	}
	/**
	 * @return the fechaAtencion
	 */
	public Date getFechaAtencion() {
		return fechaAtencion;
	}
	/**
	 * @param fechaAtencion the fechaAtencion to set
	 */
	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	/**
	 * @return the txPlaca
	 */
	public String getTxPlaca() {
		return txPlaca;
	}
	/**
	 * @param txPlaca the txPlaca to set
	 */
	public void setTxPlaca(String txPlaca) {
		this.txPlaca = txPlaca;
	}
	/**
	 * @return the txProceso
	 */
	public String getTxProceso() {
		return txProceso;
	}
	/**
	 * @param txProceso the txProceso to set
	 */
	public void setTxProceso(String txProceso) {
		this.txProceso = txProceso;
	}
	/**
	 * @return the txEtapa
	 */
	public String getTxEtapa() {
		return txEtapa;
	}
	/**
	 * @param txEtapa the txEtapa to set
	 */
	public void setTxEtapa(String txEtapa) {
		this.txEtapa = txEtapa;
	}
	/**
	 * @return the nuIncidencia
	 */
	public Long getNuIncidencia() {
		return nuIncidencia;
	}
	/**
	 * @param nuIncidencia the nuIncidencia to set
	 */
	public void setNuIncidencia(Long nuIncidencia) {
		this.nuIncidencia = nuIncidencia;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the txTransportista
	 */
	public String getTxTransportista() {
		return txTransportista;
	}
	/**
	 * @param txTransportista the txTransportista to set
	 */
	public void setTxTransportista(String txTransportista) {
		this.txTransportista = txTransportista;
	}
	/**
	 * @return the txtRepresentante
	 */
	public String getTxtRepresentante() {
		return txtRepresentante;
	}
	/**
	 * @param txtRepresentante the txtRepresentante to set
	 */
	public void setTxtRepresentante(String txtRepresentante) {
		this.txtRepresentante = txtRepresentante;
	}
	/**
	 * @return the txSupervisor
	 */
	public String getTxSupervisor() {
		return txSupervisor;
	}
	/**
	 * @param txSupervisor the txSupervisor to set
	 */
	public void setTxSupervisor(String txSupervisor) {
		this.txSupervisor = txSupervisor;
	}
	/**
	 * @return the txTecnicoInstalacion
	 */
	public String getTxTecnicoInstalacion() {
		return txTecnicoInstalacion;
	}
	/**
	 * @param txTecnicoInstalacion the txTecnicoInstalacion to set
	 */
	public void setTxTecnicoInstalacion(String txTecnicoInstalacion) {
		this.txTecnicoInstalacion = txTecnicoInstalacion;
	}
	/**
	 * @return the idOrdenServicio
	 */
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	/**
	 * @param idOrdenServicio the idOrdenServicio to set
	 */
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	/**
	 * @return the idPlan
	 */
	public Long getIdPlan() {
		return idPlan;
	}
	/**
	 * @param idPlan the idPlan to set
	 */
	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
