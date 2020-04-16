package mx.com.teclo.siye.persistencia.vo.async;

public class ColumnaArchivoVO {

	private Long idCampo;
	private String nbCampo;
	private Integer nuOrden;
	private String tipoDato;
	private Integer longMax;
	private Boolean isRequerido;
	private String txValorDefecto;
	
	public Long getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(Long idCampo) {
		this.idCampo = idCampo;
	}
	public String getNbCampo() {
		return nbCampo;
	}
	public void setNbCampo(String nbCampo) {
		this.nbCampo = nbCampo;
	}
	public Integer getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public Integer getLongMax() {
		return longMax;
	}
	public void setLongMax(Integer longMax) {
		this.longMax = longMax;
	}
	public Boolean getIsRequerido() {
		return isRequerido;
	}
	public void setIsRequerido(Boolean isRequerido) {
		this.isRequerido = isRequerido;
	}
	public String getTxValorDefecto() {
		return txValorDefecto;
	}
	public void setTxValorDefecto(String txValorDefecto) {
		this.txValorDefecto = txValorDefecto;
	}
	
	
}
