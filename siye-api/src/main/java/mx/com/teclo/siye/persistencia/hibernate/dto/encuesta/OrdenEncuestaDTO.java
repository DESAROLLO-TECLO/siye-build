/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;


/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE002D_EE_ODS_ENCUESTA", catalog = "")
public class OrdenEncuestaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_ODS_ENCUESTA", nullable = false)
	private Long idUsuarioEncuesta;
	@Basic(optional = false)
	@Column(name = "NU_INTENTOS", nullable = false)
	private Integer nuIntegerentos;
	@Basic(optional = false)
	@Column(name = "ST_APLICA_ENCUESTA", nullable = false)
	private Boolean stAplicaEncuesta;
	
	@Column(name = "ST_ENC_EN_PROCESO", nullable = false)
	private Boolean encEnProceso;
	
	@Column(name = "FH_EN_INI_ENC", nullable = false)
	private Date fhEnIniEnc;
	
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
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENCUESTA", referencedColumnName = "ID_ENCUESTA", nullable = false)
	private EncuestasDTO encuesta;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORDEN_SERVICIO", referencedColumnName = "ID_ORDEN_SERVICIO", nullable = false)
	private OrdenServicioDTO ordenServicio;
	

	public OrdenServicioDTO getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(OrdenServicioDTO ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	public Long getIdUsuarioEncuesta() {
		return idUsuarioEncuesta;
	}

	public void setIdUsuarioEncuesta(Long idUsuarioEncuesta) {
		this.idUsuarioEncuesta = idUsuarioEncuesta;
	}

	public Integer getNuIntegerentos() {
		return nuIntegerentos;
	}

	public void setNuIntegerentos(Integer nuIntegerentos) {
		this.nuIntegerentos = nuIntegerentos;
	}

	public Boolean getStAplicaEncuesta() {
		return stAplicaEncuesta;
	}

	public void setStAplicaEncuesta(Boolean stAplicaEncuesta) {
		this.stAplicaEncuesta = stAplicaEncuesta;
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


	public EncuestasDTO getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(EncuestasDTO encuesta) {
		this.encuesta = encuesta;
	}

	/**
	 * @return the encEnProceso
	 */
	public Boolean getEncEnProceso() {
		return encEnProceso;
	}

	/**
	 * @param encEnProceso the encEnProceso to set
	 */
	public void setEncEnProceso(Boolean encEnProceso) {
		this.encEnProceso = encEnProceso;
	}

	/**
	 * @return the fhEnIniEnc
	 */
	public Date getFhEnIniEnc() {
		return fhEnIniEnc;
	}

	/**
	 * @param fhEnIniEnc the fhEnIniEnc to set
	 */
	public void setFhEnIniEnc(Date fhEnIniEnc) {
		this.fhEnIniEnc = fhEnIniEnc;
	}


}
