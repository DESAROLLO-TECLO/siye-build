/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE003D_EE_USU_ENC_RESP", catalog = "")
public class UsuaroEncuestaRespuestaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UsuaroEncuestaRespuestaDTOPK id;

	@Basic(optional = false)
	@Column(name = "ST_CORRECTO", nullable = false)
	private Integer stCorrecto;

	@Column(name = "FH_LECTURA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhLectura;

	@Column(name = "FH_RESPUESTA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhRespuesta;

	@Column(name = "NU_INTENTOS", updatable = false)
	private Integer nuIntentos;
	@Basic(optional = false)

	@Column(name = "ST_ACTIVO", nullable = false, updatable = false)
	private Integer stActivo;

	@Basic(optional = false)
	@Column(name = "ID_USR_CREACION", nullable = false, updatable = false)
	private Long idUsrCreacion;

	@Basic(optional = false)
	@Column(name = "FH_CREACION", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OPCION", nullable = false)
	private OpcionesDTO opcionesDTO;

	@Transient
	private PreguntasDTO pregunta;
	
	@Column(name = "TX_OBSERVACION")
	private String descripcionCausa;

	public UsuaroEncuestaRespuestaDTOPK getUsuaroEncuestaRespuestaPK() {
		return id;
	}

	public void setUsuaroEncuestaRespuestaPK(UsuaroEncuestaRespuestaDTOPK usuaroEncuestaRespuestaDTOPK) {
		this.id = usuaroEncuestaRespuestaDTOPK;
	}

	public Integer getStCorrecto() {
		return stCorrecto;
	}

	public void setStCorrecto(Integer stCorrecto) {
		this.stCorrecto = stCorrecto;
	}

	public Date getFhLectura() {
		return fhLectura;
	}

	public void setFhLectura(Date fhLectura) {
		this.fhLectura = fhLectura;
	}

	public Date getFhRespuesta() {
		return fhRespuesta;
	}

	public void setFhRespuesta(Date fhRespuesta) {
		this.fhRespuesta = fhRespuesta;
	}

	public Integer getNuIntentos() {
		return nuIntentos;
	}

	public void setNuIntentos(Integer nuIntentos) {
		this.nuIntentos = nuIntentos;
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

	public OpcionesDTO getOpcionesDTO() {
		return opcionesDTO;
	}

	public void setOpcionesDTO(OpcionesDTO opcionesDTO) {
		this.opcionesDTO = opcionesDTO;
	}

	/**
	 * @return the id
	 */
	public UsuaroEncuestaRespuestaDTOPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UsuaroEncuestaRespuestaDTOPK id) {
		this.id = id;
	}

	/**
	 * @return the idUsrModifica
	 */
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}

	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	/**
	 * @return the pregunta
	 */
	public PreguntasDTO getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(PreguntasDTO pregunta) {
		this.pregunta = pregunta;
	}

	public String getDescripcionCausa() {
		return descripcionCausa;
	}

	public void setDescripcionCausa(String descripcionCausa) {
		this.descripcionCausa = descripcionCausa;
	}
	
	

}
