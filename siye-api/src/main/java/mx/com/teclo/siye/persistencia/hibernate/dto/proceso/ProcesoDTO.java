package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class ProcesoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@Basic(optional = false)
	@Column(name = "ID_PROCESO", nullable = false)
	private Long idProceso;
	@Column(name = "CD_PROCESO")
	private String cdProceso;
	@Column(name = "NB_PROCESO")
	private String nbProceso;
	@Column(name = "TX_PROCESO")
	private String txProceso;
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	@Basic(optional = false)
	@Column(name = "ID_USR_CREACION", nullable = false)
	private Long idUsrCreacion;
	@Basic(optional = false)
	@Column(name = "FH_CREACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhCreacion;
	@Basic(optional = false)
	@Column(name = "ID_USR_MODIFICA", nullable = false)
	private Long idUsrModifica;
	@Basic(optional = false)
	@Column(name = "FH_MODIFICACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhModificacion;
	@Column(name = "NU_MAX_IMAGENES", nullable = false)
	private Long nuMaxImagenes;
	
	public Long getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}
	public String getCdProceso() {
		return cdProceso;
	}
	public void setCdProceso(String cdProceso) {
		this.cdProceso = cdProceso;
	}
	public String getNbProceso() {
		return nbProceso;
	}
	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
	}
	public String getTxProceso() {
		return txProceso;
	}
	public void setTxProceso(String txProceso) {
		this.txProceso = txProceso;
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
	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}
	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}
    
	


}
