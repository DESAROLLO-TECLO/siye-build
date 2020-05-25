package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
import java.util.Date;

public class ConductorCompVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4793024118322833723L;
	
	private Long idConductor;
	private String nbConductor;
	private String nbApepatConductor;
	private String nbApematConductor;
	private Integer nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	
	public Long getIdConductor() {
		return idConductor;
	}
	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}
	public String getNbConductor() {
		return nbConductor;
	}
	public void setNbConductor(String nbConductor) {
		this.nbConductor = nbConductor;
	}
	public String getNbApepatConductor() {
		return nbApepatConductor;
	}
	public void setNbApepatConductor(String nbApepatConductor) {
		this.nbApepatConductor = nbApepatConductor;
	}
	public String getNbApematConductor() {
		return nbApematConductor;
	}
	public void setNbApematConductor(String nbApematConductor) {
		this.nbApematConductor = nbApematConductor;
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
