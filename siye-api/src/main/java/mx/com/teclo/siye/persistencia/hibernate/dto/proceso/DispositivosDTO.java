package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIE032C_IE_DISPOSITIVO")
public class DispositivosDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5128824398280470457L;
	/**
	 * 
	 */
	
	@Id
	@Column(name = "ID_DISPOSITIVO", unique = true, nullable = false)
	private Long idDispositivo;
	@Column(name = "CD_DISPOSITIVO")
	private String cdDispositivo;
	@Column(name = "NB_DISPOSITIVO")
	private String nbDispositivo;
	@Column(name = "NB_MARCA_DISPOSITIVO")
	private String nbMarcaDispositivo;
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
	public Long getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	public String getCdDispositivo() {
		return cdDispositivo;
	}
	public void setCdDispositivo(String cdDispositivo) {
		this.cdDispositivo = cdDispositivo;
	}
	public String getNbDispositivo() {
		return nbDispositivo;
	}
	public void setNbDispositivo(String nbDispositivo) {
		this.nbDispositivo = nbDispositivo;
	}
	public String getNbMarcaDispositivo() {
		return nbMarcaDispositivo;
	}
	public void setNbMarcaDispositivo(String nbMarcaDispositivo) {
		this.nbMarcaDispositivo = nbMarcaDispositivo;
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
	
	

}
