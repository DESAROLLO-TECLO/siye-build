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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE006D_EE_USU_ENCU_INTEN", catalog = "")
public class UsuarioEncuestaIntentosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
 	@SequenceGenerator(name = "sqee006d", sequenceName="SQEE006D_EE_USU_ENC", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqee006d")
	@Column(name = "ID_USU_ENCU_INTENTO", nullable = false)
	private Long idUsuEncuIntento;
	
	@Basic(optional = false)
	@Column(name = "FH_INICIO", nullable = true)
	private Date fhInicio;
	
	@Basic(optional = false)
	@Column(name = "FH_FIN", nullable = true)
	private Date fhFin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ST_ENCUESTA", nullable = false)
	private StEncuestaDTO stEncuesta;

	@Basic(optional = false)
	@Column(name = "NU_CALIFICACION", nullable = false)
	private Integer nuCalificacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ST_CALIFICACION", nullable = false)
	private EstatusCalificacionDTO stCalificacion;

	@Basic(optional = false)
	@Column(name = "ST_MOSTRAR", nullable = false)
	private Boolean stMostrar;
	@Column(name = "NU_PREGUNTAS")
	private Integer nuPreguntas;
	@Column(name = "NU_PREGUNTAS_CORRECTAS")
	private Integer nuPreguntasCorrectas;
	@Column(name = "NU_PREGUNTAS_INCORR")
	private Integer nuPreguntasIncorr;

	@Column(name = "NU_SECCIONES_TOT", nullable = false, precision = 11, scale = 0)
	private Integer nuSeccionesTot;

	@Column(name = "NU_SECCIONES_RSP", nullable = false, precision = 11, scale = 0)
	private Integer nuSeccionesRsp;

	@Column(name = "NU_PREGUNTAS_VACIAS", nullable = false, precision = 11, scale = 0)
	private Integer nuPreguntasVacias;
 
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
	@JoinColumn(name = "ID_USUARIO_ENCUESTA", referencedColumnName = "ID_USUARIO_ENCUESTA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UsuarioEncuestaDTO usuarioEncuesta;

	@Column(name = "NU_MIN_CONSUMIDOS", nullable = false, precision = 4, scale = 0)
	private Integer nuMinConsumidos;

	public UsuarioEncuestaIntentosDTO() {
		super();
	}

	public UsuarioEncuestaIntentosDTO(Long idUsuEncuIntento) {
		super();
		this.idUsuEncuIntento = idUsuEncuIntento;
	}

	public Long getIdUsuEncuIntento() {
		return idUsuEncuIntento;
	}

	public void setIdUsuEncuIntento(Long idUsuEncuIntento) {
		this.idUsuEncuIntento = idUsuEncuIntento;
	}

	public Date getFhInicio() {
		return fhInicio;
	}

	public void setFhInicio(Date fhInicio) {
		this.fhInicio = fhInicio;
	}

	public Date getFhFin() {
		return fhFin;
	}

	public void setFhFin(Date fhFin) {
		this.fhFin = fhFin;
	}

	public Integer getNuCalificacion() {
		return nuCalificacion;
	}

	public void setNuCalificacion(Integer nuCalificacion) {
		this.nuCalificacion = nuCalificacion;
	}

	public Boolean getStMostrar() {
		return stMostrar;
	}

	public void setStMostrar(Boolean stMostrar) {
		this.stMostrar = stMostrar;
	}

	public Integer getNuPreguntas() {
		return nuPreguntas;
	}

	public void setNuPreguntas(Integer nuPreguntas) {
		this.nuPreguntas = nuPreguntas;
	}

	public Integer getNuPreguntasCorrectas() {
		return nuPreguntasCorrectas;
	}

	public void setNuPreguntasCorrectas(Integer nuPreguntasCorrectas) {
		this.nuPreguntasCorrectas = nuPreguntasCorrectas;
	}

	public Integer getNuPreguntasIncorr() {
		return nuPreguntasIncorr;
	}

	public void setNuPreguntasIncorr(Integer nuPreguntasIncorr) {
		this.nuPreguntasIncorr = nuPreguntasIncorr;
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

	public UsuarioEncuestaDTO getUsuarioEncuesta() {
		return usuarioEncuesta;
	}

	public void setUsuarioEncuesta(UsuarioEncuestaDTO usuarioEncuesta) {
		this.usuarioEncuesta = usuarioEncuesta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the stEncuesta
	 */
	public StEncuestaDTO getStEncuesta() {
		return stEncuesta;
	}

	/**
	 * @param stEncuesta the stEncuesta to set
	 */
	public void setStEncuesta(StEncuestaDTO stEncuesta) {
		this.stEncuesta = stEncuesta;
	}

	/**
	 * @return the stCalificacion
	 */
	public EstatusCalificacionDTO getStCalificacion() {
		return stCalificacion;
	}

	/**
	 * @param stCalificacion the stCalificacion to set
	 */
	public void setStCalificacion(EstatusCalificacionDTO stCalificacion) {
		this.stCalificacion = stCalificacion;
	}

	/**
	 * @return the nuSeccionesTot
	 */
	public Integer getNuSeccionesTot() {
		return nuSeccionesTot;
	}

	/**
	 * @param nuSeccionesTot the nuSeccionesTot to set
	 */
	public void setNuSeccionesTot(Integer nuSeccionesTot) {
		this.nuSeccionesTot = nuSeccionesTot;
	}

	/**
	 * @return the nuSeccionesRsp
	 */
	public Integer getNuSeccionesRsp() {
		return nuSeccionesRsp;
	}

	/**
	 * @param nuSeccionesRsp the nuSeccionesRsp to set
	 */
	public void setNuSeccionesRsp(Integer nuSeccionesRsp) {
		this.nuSeccionesRsp = nuSeccionesRsp;
	}

	/**
	 * @return the nuPreguntasVacias
	 */
	public Integer getNuPreguntasVacias() {
		return nuPreguntasVacias;
	}

	/**
	 * @param nuPreguntasVacias the nuPreguntasVacias to set
	 */
	public void setNuPreguntasVacias(Integer nuPreguntasVacias) {
		this.nuPreguntasVacias = nuPreguntasVacias;
	}

	/**
	 * @return the nuMinConsumidos
	 */
	public Integer getNuMinConsumidos() {
		return nuMinConsumidos;
	}

	/**
	 * @param nuMinConsumidos the nuMinConsumidos to set
	 */
	public void setNuMinConsumidos(Integer nuMinConsumidos) {
		this.nuMinConsumidos = nuMinConsumidos;
	}

}
