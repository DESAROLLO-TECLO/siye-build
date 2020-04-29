package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE052C_IE_CONCESIONES")
public class ConsecionarioDTO implements Serializable {
	
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@Column(name = "ID_CONCESION", unique = true, nullable = false)
	private Long idConsecion;
	@Column(name = "CD_CONCESION")
	private String cdConsecion;
	@Column(name = "NB_CONCESION")
	private String nbConsecion;
	@Column(name = "NU_ORDEN")
	private Long nuOrden;
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	@Column(name = "FH_CREACION")	
	private Date fhCreacion;
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	@Column(name = "FH_MODIFICACION")	
	private Date fhModificacion;
	@Column(name = "ST_CONCESION")
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
