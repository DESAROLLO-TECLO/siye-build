package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

public class OrdenServcioDetalleVO {

	private Long nuOrdenServicio;
	private String fechaAtencion;
	private String txPlaca;
	private String txProceso;
	private String txEtapa;
	private Long nuIncidencia;
	private String estado;
	
	
	/**
	 * @return the nuOrdenServicio
	 */
	public Long getNuOrdenServicio() {
		return nuOrdenServicio;
	}
	/**
	 * @param nuOrdenServicio the nuOrdenServicio to set
	 */
	public void setNuOrdenServicio(Long nuOrdenServicio) {
		this.nuOrdenServicio = nuOrdenServicio;
	}
	/**
	 * @return the fechaAtencion
	 */
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	/**
	 * @param fechaAtencion the fechaAtencion to set
	 */
	public void setFechaAtencion(String fechaAtencion) {
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
	
}
