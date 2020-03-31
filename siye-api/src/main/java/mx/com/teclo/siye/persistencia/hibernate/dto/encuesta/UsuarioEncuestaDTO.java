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
public class UsuarioEncuestaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_ODS_ENCUESTA", nullable = false)
	private Long idUsuarioEncuesta;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ORDEN_SERVICIO", referencedColumnName = "ID_ORDEN_SERVICIO", nullable = false)
	private OrdenServicioDTO usuario;

	@Basic(optional = false)
	@Column(name = "NU_INTENTOS", nullable = false)
	private Integer nuIntegerentos;
	@Basic(optional = false)
	@Column(name = "ST_APLICA_ENCUESTA", nullable = false)
	private Boolean stAplicaEncuesta;
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
	@JoinColumn(name = "ID_ENCUESTA", referencedColumnName = "ID_ENCUESTA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EncuestasDTO encuesta;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioEncuesta")
	private List<UsuarioEncuestaIntentosDTO> usuarioEncuestaIntentos;*/

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

	public OrdenServicioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(OrdenServicioDTO usuario) {
		this.usuario = usuario;
	}

	public EncuestasDTO getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(EncuestasDTO encuesta) {
		this.encuesta = encuesta;
	}
/*
	
	public List<UsuarioEncuestaIntentosDTO> getUsuarioEncuestaIntentos() {
		return usuarioEncuestaIntentos;
	}

	public void setUsuarioEncuestaIntentos(List<UsuarioEncuestaIntentosDTO> usuarioEncuestaIntentos) {
		this.usuarioEncuestaIntentos = usuarioEncuestaIntentos;
	}*/

}
