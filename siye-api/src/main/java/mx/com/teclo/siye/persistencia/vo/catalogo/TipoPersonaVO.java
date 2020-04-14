package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
import java.util.Date;

public class TipoPersonaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2468763049156427121L;
	
	private Integer idTipoPersona;
	private String cdTipoPersona;
	private String nbTipoPersona;
	private String txTipoPersona;
	private Integer nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	
	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public String getCdTipoPersona() {
		return cdTipoPersona;
	}
	public void setCdTipoPersona(String cdTipoPersona) {
		this.cdTipoPersona = cdTipoPersona;
	}
	public String getNbTipoPersona() {
		return nbTipoPersona;
	}
	public void setNbTipoPersona(String nbTipoPersona) {
		this.nbTipoPersona = nbTipoPersona;
	}
	public String getTxTipoPersona() {
		return txTipoPersona;
	}
	public void setTxTipoPersona(String txTipoPersona) {
		this.txTipoPersona = txTipoPersona;
	}
	public Integer getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
}
