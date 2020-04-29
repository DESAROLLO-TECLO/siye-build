package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIE038C_IE_PROVEEDOR")
public class ProveedorDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 337988032531386567L;
	/**
	 * 
	 */
	
	@Id
	@Column(name="ID_PROVEEDOR")
	private Long idProveedor;
	@Column(name="CD_PROVEEDOR")
	private String cdProveeror;
	@Column(name="NB_PROVEEDOR")
	private String nbProveedor;
	@Column(name="NU_ORDEN")
	private Long nuOrden;
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;
	@Column(name="ID_USR_CREACION")
	private Long idUsrCreacion;
	@Column(name="FH_CREACION")
	private Date fhCreacion;
	@Column(name="ID_USR_MODIFICA")
	private Long idUsrModifica;
	@Column(name="FH_MODIFICACION")
	private Date fhModificacion;
	@Column(name="ST_PROVEEDOR")
	private Long stProveedor;
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getCdProveeror() {
		return cdProveeror;
	}
	public void setCdProveeror(String cdProveeror) {
		this.cdProveeror = cdProveeror;
	}
	public String getNbProveedor() {
		return nbProveedor;
	}
	public void setNbProveedor(String nbProveedor) {
		this.nbProveedor = nbProveedor;
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
	public Long getStProveedor() {
		return stProveedor;
	}
	public void setStProveedor(Long stProveedor) {
		this.stProveedor = stProveedor;
	}
	
	
	

}
