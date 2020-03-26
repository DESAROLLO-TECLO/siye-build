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

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoEncuestaDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE001D_EE_ENCUESTAS", catalog = "")
public class EncuestaDetalleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_ENCUESTA", nullable = false)
	private Long idEncuesta;
	@Column(name = "CD_ENCUESTA", length = 15)
	private String cdEncuesta;
	@Basic(optional = false)
	@Column(name = "NB_ENCUESTA", nullable = false, length = 100)
	private String nbEncuesta;
	@Column(name = "TX_ENCUESTA", length = 250)
	private String txEncuesta;
	@Basic(optional = false)
	@Column(name = "TX_INSTRUCCIONES", nullable = false, length = 300)
	private String txInstrucciones;
	@Basic(optional = false)
	@Column(name = "NU_PREGUNTAS", nullable = false)
	private Integer nuPreguntas;
	@Column(name = "NU_SECCIONES", nullable = false)
	private Integer nuSecciones;

	@Column(name = "NU_CALIFICACION_APRO", nullable = false)
	private Integer nuCalificacionApro;

	@Basic(optional = false)
	@Column(name = "ST_ACTIVO", nullable = false)
	private Integer stActivo;
	@Basic(optional = false)
	@Column(name = "ID_USR_CREACION", nullable = false)
	private int idUsrCreacion;
	@Basic(optional = false)
	@Column(name = "FH_CREACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhCreacion;
	@Basic(optional = false)
	@Column(name = "ID_USR_MODIFICA", nullable = false)
	private int idUsrModifica;
	@Basic(optional = false)
	@Column(name = "FH_MODIFICACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhModificacion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuesta", fetch = FetchType.LAZY)
	private List<SeccionDTO> secciones;
	@JoinColumn(name = "ID_TIPO_ENCUESTA", referencedColumnName = "ID_TIPO_ENCUESTA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private TipoEncuestaDTO tipoEncuesta;

	@Column(name = "NU_TIEMPO", nullable = false, precision = 1, scale = 0)
	private Integer nuTiempo;

	public EncuestaDetalleDTO() {
	}

	public EncuestaDetalleDTO(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public EncuestaDetalleDTO(Long idEncuesta, String nbEncuesta, String txInstrucciones, Integer nuPreguntas,
			Integer stActivo, int idUsrCreacion, Date fhCreacion, int idUsrModifica, Date fhModificacion) {
		this.idEncuesta = idEncuesta;
		this.nbEncuesta = nbEncuesta;
		this.txInstrucciones = txInstrucciones;
		this.nuPreguntas = nuPreguntas;
		this.stActivo = stActivo;
		this.idUsrCreacion = idUsrCreacion;
		this.fhCreacion = fhCreacion;
		this.idUsrModifica = idUsrModifica;
		this.fhModificacion = fhModificacion;
	}

	public Long getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public String getCdEncuesta() {
		return cdEncuesta;
	}

	public void setCdEncuesta(String cdEncuesta) {
		this.cdEncuesta = cdEncuesta;
	}

	public String getNbEncuesta() {
		return nbEncuesta;
	}

	public void setNbEncuesta(String nbEncuesta) {
		this.nbEncuesta = nbEncuesta;
	}

	public String getTxEncuesta() {
		return txEncuesta;
	}

	public void setTxEncuesta(String txEncuesta) {
		this.txEncuesta = txEncuesta;
	}

	public String getTxInstrucciones() {
		return txInstrucciones;
	}

	public void setTxInstrucciones(String txInstrucciones) {
		this.txInstrucciones = txInstrucciones;
	}

	public Integer getNuPreguntas() {
		return nuPreguntas;
	}

	public void setNuPreguntas(Integer nuPreguntas) {
		this.nuPreguntas = nuPreguntas;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public int getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(int idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public int getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(int idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public List<SeccionDTO> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<SeccionDTO> secciones) {
		this.secciones = secciones;
	}

	public TipoEncuestaDTO getIdTipoEncuesta() {
		return tipoEncuesta;
	}

	public void setIdTipoEncuesta(TipoEncuestaDTO tipoEncuesta) {
		this.tipoEncuesta = tipoEncuesta;
	}

	/**
	 * @return the nuCalificacionApro
	 */
	public Integer getNuCalificacionApro() {
		return nuCalificacionApro;
	}

	/**
	 * @param nuCalificacionApro the nuCalificacionApro to set
	 */
	public void setNuCalificacionApro(Integer nuCalificacionApro) {
		this.nuCalificacionApro = nuCalificacionApro;
	}

	/**
	 * @return the tipoEncuesta
	 */
	public TipoEncuestaDTO getTipoEncuesta() {
		return tipoEncuesta;
	}

	/**
	 * @param tipoEncuesta the tipoEncuesta to set
	 */
	public void setTipoEncuesta(TipoEncuestaDTO tipoEncuesta) {
		this.tipoEncuesta = tipoEncuesta;
	}

	public Integer getNuSecciones() {
		return nuSecciones;
	}

	public void setNuSecciones(Integer nuSecciones) {
		this.nuSecciones = nuSecciones;
	}

	/**
	 * @return the nuTiempo
	 */
	public Integer getNuTiempo() {
		return nuTiempo;
	}

	/**
	 * @param nuTiempo the nuTiempo to set
	 */
	public void setNuTiempo(Integer nuTiempo) {
		this.nuTiempo = nuTiempo;
	}

}
