package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class InstaladorCompVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6450793529068488324L;
	
	private Integer idRhInstalador;
	private String nbRhInstalador;
	private String nbPatRhInstalador;
	private String nbMatRhInstalador;
	private Integer nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	
	public Integer getIdRhInstalador() {
		return idRhInstalador;
	}
	public void setIdRhInstalador(Integer idRhInstalador) {
		this.idRhInstalador = idRhInstalador;
	}
	public String getNbRhInstalador() {
		return nbRhInstalador;
	}
	public void setNbRhInstalador(String nbRhInstalador) {
		this.nbRhInstalador = nbRhInstalador;
	}
	public String getNbPatRhInstalador() {
		return nbPatRhInstalador;
	}
	public void setNbPatRhInstalador(String nbPatRhInstalador) {
		this.nbPatRhInstalador = nbPatRhInstalador;
	}
	public String getNbMatRhInstalador() {
		return nbMatRhInstalador;
	}
	public void setNbMatRhInstalador(String nbMatRhInstalador) {
		this.nbMatRhInstalador = nbMatRhInstalador;
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
