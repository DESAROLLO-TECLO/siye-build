package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.io.Serializable;
import java.util.Date;


public class TipoExpedienteVO implements Serializable{

	private static final long serialVersionUID = 4534315578997596032L;

	private Long idTipoExpediente;
	private String nbTipoExpediente;
	private String cdTipoExpediente;
	private Long nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModifica;
	
	public Long getIdTipoExpediente() {
		return idTipoExpediente;
	}
	public void setIdTipoExpediente(Long idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}
	public String getNbTipoExpediente() {
		return nbTipoExpediente;
	}
	public void setNbTipoExpediente(String nbTipoExpediente) {
		this.nbTipoExpediente = nbTipoExpediente;
	}
	public String getCdTipoExpediente() {
		return cdTipoExpediente;
	}
	public void setCdTipoExpediente(String cdTipoExpediente) {
		this.cdTipoExpediente = cdTipoExpediente;
	}
	public Long getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Long nuOrden) {
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
	public Date getFhModifica() {
		return fhModifica;
	}
	public void setFhModifica(Date fhModifica) {
		this.fhModifica = fhModifica;
	}
	
	
}
