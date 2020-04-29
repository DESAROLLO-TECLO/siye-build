package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

public class ConsecionarioVO {
	
	
	private Long idConsecion;
	private String cdConsecion;
	private String nbConsecion;
	private Long nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;	
	private Date fhModificacion;
	private Long stConcesion;
	
	public Long getIdConsecion() {
		return idConsecion;
	}
	public void setIdConsecion(Long idConsecion) {
		this.idConsecion = idConsecion;
	}
	public String getCdConsecion() {
		return cdConsecion;
	}
	public void setCdConsecion(String cdConsecion) {
		this.cdConsecion = cdConsecion;
	}
	public String getNbConsecion() {
		return nbConsecion;
	}
	public void setNbConsecion(String nbConsecion) {
		this.nbConsecion = nbConsecion;
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
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	public Long getStConcesion() {
		return stConcesion;
	}
	public void setStConcesion(Long stConcesion) {
		this.stConcesion = stConcesion;
	}
	
	
	
	

}
