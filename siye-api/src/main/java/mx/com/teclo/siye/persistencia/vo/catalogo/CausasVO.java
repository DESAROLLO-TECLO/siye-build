package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
;

public class CausasVO implements Serializable {
	
	private static final long serialVersionUID = 2117870156790947860L;
	
	private Long idCausa;
    private String cdCausa;
	private String nbCausa;
	private String txCausa;
    private Integer nuOrden;
    private Integer stActivo;
    
	public Long getIdCausa() {
		return idCausa;
	}
	public void setIdCausa(Long idCausa) {
		this.idCausa = idCausa;
	}
	public String getCdCausa() {
		return cdCausa;
	}
	public void setCdCausa(String cdCausa) {
		this.cdCausa = cdCausa;
	}
	public String getNbCausa() {
		return nbCausa;
	}
	public void setNbCausa(String nbCausa) {
		this.nbCausa = nbCausa;
	}
	public String getTxCausa() {
		return txCausa;
	}
	public void setTxCausa(String txCausa) {
		this.txCausa = txCausa;
	}
	public Integer getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
    
    
    

}
