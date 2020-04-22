package mx.com.teclo.siye.persistencia.vo.async;

public class ColumnaVO {
	private String nbColumna;
	private String cdTipo;
	private Integer nuOrden;
	private String txValorDefecto;
	private Integer nuLongitudMax;
	private Boolean stCampoFiltro;
	private Boolean stInsertable;
	
	public String getNbColumna() {
		return nbColumna;
	}
	public void setNbColumna(String nbColumna) {
		this.nbColumna = nbColumna;
	}
	public String getCdTipo() {
		return cdTipo;
	}
	public void setCdTipo(String cdTipo) {
		this.cdTipo = cdTipo;
	}
	public Integer getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}
	public String getTxValorDefecto() {
		return txValorDefecto;
	}
	public void setTxValorDefecto(String txValorDefecto) {
		this.txValorDefecto = txValorDefecto;
	}
	public Integer getNuLongitudMax() {
		return nuLongitudMax;
	}
	public void setNuLongitudMax(Integer nuLongitudMax) {
		this.nuLongitudMax = nuLongitudMax;
	}
	public Boolean getStCampoFiltro() {
		return stCampoFiltro;
	}
	public void setStCampoFiltro(Boolean stCampoFiltro) {
		this.stCampoFiltro = stCampoFiltro;
	}
	public Boolean getStInsertable() {
		return stInsertable;
	}
	public void setStInsertable(Boolean stInsertable) {
		this.stInsertable = stInsertable;
	}
	
	
}
