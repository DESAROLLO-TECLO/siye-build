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
public class EncuestasDTO implements Serializable {

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
	@JoinColumn(name = "ID_TIPO_ENCUESTA", referencedColumnName = "ID_TIPO_ENCUESTA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private TipoEncuestaDTO tipoEncuesta;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "encuesta")
	private List<UsuarioEncuestaDTO> usuarioEncuesta;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idEncuesta")
	private List<SeccionDTO> seccion;

	@Column(name = "FH_VIG_INI", nullable = false)
	private Date fhVigIni;
	@Column(name = "FH_VIG_FIN", nullable = false)
	private Date fhVigFin;

	@Column(name = "NU_ORDEN", nullable = false)
	private Integer nuOrden;

	@Column(name = "NB_ENCUESTA_ORIGEN", nullable = false)
	private String nbEncuestaOrigen;

    /*@Column(name = "NU_TIEMPO", nullable = false, precision = 1, scale = 0)
	private Integer nuTiempo;*/
	
	@Column(name = "NU_MAX_IMAGENES")
	private Long nuMaxImagenes;
	

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

	public TipoEncuestaDTO getTipoEncuesta() {
		return tipoEncuesta;
	}

	public void setTipoEncuesta(TipoEncuestaDTO tipoEncuesta) {
		this.tipoEncuesta = tipoEncuesta;
	}

	/**
	 * @return the usuarioEncuesta
	 */
	public List<UsuarioEncuestaDTO> getUsuarioEncuesta() {
		return usuarioEncuesta;
	}

	/**
	 * @param usuarioEncuesta the usuarioEncuesta to set
	 */
	public void setUsuarioEncuesta(List<UsuarioEncuestaDTO> usuarioEncuesta) {
		this.usuarioEncuesta = usuarioEncuesta;
	}

	public Date getFhVigIni() {
		return fhVigIni;
	}

	public void setFhVigIni(Date fhVigIni) {
		this.fhVigIni = fhVigIni;
	}

	public Date getFhVigFin() {
		return fhVigFin;
	}

	public void setFhVigFin(Date fhVigFin) {
		this.fhVigFin = fhVigFin;
	}

	/**
	 * @return the seccion
	 */
	public List<SeccionDTO> getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(List<SeccionDTO> seccion) {
		this.seccion = seccion;
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

	public Integer getNuSecciones() {
		return nuSecciones;
	}

	public void setNuSecciones(Integer nuSecciones) {
		this.nuSecciones = nuSecciones;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	public String getNbEncuestaOrigen() {
		return nbEncuestaOrigen;
	}

	public void setNbEncuestaOrigen(String nbEncuestaOrigen) {
		this.nbEncuestaOrigen = nbEncuestaOrigen;
	}

	/**
	 * @return the nuTiempo
	 */
	/*public Integer getNuTiempo() {
		return nuTiempo;
	}*/

	/**
	 * @param nuTiempo the nuTiempo to set
	 */
	/*public void setNuTiempo(Integer nuTiempo) {
		this.nuTiempo = nuTiempo;
	}*/

	/**
	 * @return the nuMaxImagenes
	 */
	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}

	/**
	 * @param nuMaxImagenes the nuMaxImagenes to set
	 */
	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}

}
