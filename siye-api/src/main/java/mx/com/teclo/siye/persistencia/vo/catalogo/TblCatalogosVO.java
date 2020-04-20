package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class TblCatalogosVO implements Serializable {
	
	private static final long serialVersionUID = 7187812001796816023L;
	
	private Long idTblCatalogo;
	private Long idPadreTblCatalogo;
	private String cdTblCatalogo;
	private String nbTblCatalogo;
	private String txTblCatalogo;
	private String txUri;
	private Long nuOrden;
	private Boolean stMostrar;
	private Boolean stActivo;
	public Long getIdTblCatalogo() {
		return idTblCatalogo;
	}
	public void setIdTblCatalogo(Long idTblCatalogo) {
		this.idTblCatalogo = idTblCatalogo;
	}
	public Long getIdPadreTblCatalogo() {
		return idPadreTblCatalogo;
	}
	public void setIdPadreTblCatalogo(Long idPadreTblCatalogo) {
		this.idPadreTblCatalogo = idPadreTblCatalogo;
	}
	public String getCdTblCatalogo() {
		return cdTblCatalogo;
	}
	public void setCdTblCatalogo(String cdTblCatalogo) {
		this.cdTblCatalogo = cdTblCatalogo;
	}
	public String getNbTblCatalogo() {
		return nbTblCatalogo;
	}
	public void setNbTblCatalogo(String nbTblCatalogo) {
		this.nbTblCatalogo = nbTblCatalogo;
	}
	public String getTxTblCatalogo() {
		return txTblCatalogo;
	}
	public void setTxTblCatalogo(String txTblCatalogo) {
		this.txTblCatalogo = txTblCatalogo;
	}
	public String getTxUri() {
		return txUri;
	}
	public void setTxUri(String txUri) {
		this.txUri = txUri;
	}
	public Long getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Boolean getStMostrar() {
		return stMostrar;
	}
	public void setStMostrar(Boolean stMostrar) {
		this.stMostrar = stMostrar;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
}
