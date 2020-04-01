/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE004D_EE_SECCION", catalog = "")
public class SeccionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_SECCION", nullable = false)
	private Long idSeccion;
	
	@Basic(optional = false)
	@Column(name = "CD_SECCION", nullable = false, length = 15)
	private String cdSeccion;
	
	@Basic(optional = false)
	@Column(name = "NB_SECCION", nullable = false, length = 100)
	private String nbSeccion;

	@Basic(optional = false)
	@Column(name = "TX_SECCION", nullable = false, length = 250)
	private String txSeccion;

	@Basic(optional = false)
	@Column(name = "NU_ORDEN", nullable = false)
	private Integer nuOrden;
	
	@Basic(optional = false)
	@Column(name = "ST_ACTIVO", nullable = false)
	private Integer stActivo;
	
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
	
	@Column(name = "FH_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhModificacion;
	
	@JoinColumn(name = "ID_ENCUESTA", referencedColumnName = "ID_ENCUESTA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private EncuestasDTO idEncuesta;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion", fetch = FetchType.EAGER)
	private List<PreguntasDTO> preguntas;

	public Long getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Long idSeccion) {
		this.idSeccion = idSeccion;
	}	

	public String getCdSeccion() {
		return cdSeccion;
	}

	public void setCdSeccion(String cdSeccion) {
		this.cdSeccion = cdSeccion;
	}

	public String getNbSeccion() {
		return nbSeccion;
	}

	public void setNbSeccion(String nbSeccion) {
		this.nbSeccion = nbSeccion;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
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

	public EncuestasDTO getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(EncuestasDTO idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	@XmlTransient
	public List<PreguntasDTO> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntasDTO> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * @return the txSeccion
	 */
	public String getTxSeccion() {
		return txSeccion;
	}

	/**
	 * @param txSeccion the txSeccion to set
	 */
	public void setTxSeccion(String txSeccion) {
		this.txSeccion = txSeccion;
	}

}
