package mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;

@Entity
@Table(name = "TIE054C_IE_TIPO_EXPEDIENTE")
public class TipoExpedienteDTO implements Serializable{

	private static final long serialVersionUID = -7940742095330864269L;

	@Id
	@Column(name = "ID_TIPO_EXPEDIENTE", unique = true, nullable = false, insertable = false)
	private Long idTipoExpediente;

	@Column(name = "NB_TIPO_EXPEDIENTE")
	private String nbTipoExpediente;

	@Column(name = "CD_TIPO_EXPEDIENTE")
	private String cdTipoExpediente;

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
