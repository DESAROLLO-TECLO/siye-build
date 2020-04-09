package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class PersonaCompVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6450793529068488324L;
	
	private Integer idPersona;
	private String nbPersona;
	private String nbPatPersona;
	private String cdPersona;
	private String nbMatPersona;
	private Integer nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNbPersona() {
		return nbPersona;
	}
	
	public String getCdPersona() {
		return cdPersona;
	}
	public void setCdPersona(String cdPersona) {
		this.cdPersona = cdPersona;
	}
	public void setNbPersona(String nbPersona) {
		this.nbPersona = nbPersona;
	}
	public String getNbPatPersona() {
		return nbPatPersona;
	}
	public void setNbPatPersona(String nbPatPersona) {
		this.nbPatPersona = nbPatPersona;
	}
	public String getNbMatPersona() {
		return nbMatPersona;
	}
	public void setNbMatPersona(String nbMatPersona) {
		this.nbMatPersona = nbMatPersona;
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
