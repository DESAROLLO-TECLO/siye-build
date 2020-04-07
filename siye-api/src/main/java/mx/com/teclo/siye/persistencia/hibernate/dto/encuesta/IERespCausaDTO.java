package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

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

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.CausasDTO;



@Entity
@Table(name = "TIE063D_IE_RESP_CAUSA")
public class IERespCausaDTO implements Serializable {
	
	private static final long serialVersionUID = 3771263775950160399L;
	
	@Id
	@SequenceGenerator(name = "sqTERespCausa", sequenceName = "SQIE063D_IE_RESP_CAUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqTERespCausa")
	@Column(name = "ID_RESP_CAUSA", unique = true, nullable = false)
	private Long idRespCausa;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_USU_ENCU_INTENTO", referencedColumnName="ID_USU_ENCU_INTENTO")
	private UsuarioEncuestaIntentosDTO usuarioEncuestaIntento;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ENCUESTA", referencedColumnName="ID_ENCUESTA")
	private EncuestasDTO encuesta;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SECCION", referencedColumnName="ID_SECCION")
	private SeccionDTO seccion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PREGUNTA", referencedColumnName="ID_PREGUNTA")
	private PreguntasDTO preguntas;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CAUSA", referencedColumnName="ID_CAUSA")
	private CausasDTO causas;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "FH_CREACION")
	private Date fhCreacion;

	public Long getIdRespCausa() {
		return idRespCausa;
	}

	public void setIdRespCausa(Long idRespCausa) {
		this.idRespCausa = idRespCausa;
	}

	public UsuarioEncuestaIntentosDTO getUsuarioEncuestaIntento() {
		return usuarioEncuestaIntento;
	}

	public void setUsuarioEncuestaIntento(
			UsuarioEncuestaIntentosDTO usuarioEncuestaIntento) {
		this.usuarioEncuestaIntento = usuarioEncuestaIntento;
	}

	public EncuestasDTO getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(EncuestasDTO encuesta) {
		this.encuesta = encuesta;
	}

	public SeccionDTO getSeccion() {
		return seccion;
	}

	public void setSeccion(SeccionDTO seccion) {
		this.seccion = seccion;
	}

	public PreguntasDTO getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(PreguntasDTO preguntas) {
		this.preguntas = preguntas;
	}

	public CausasDTO getCausas() {
		return causas;
	}

	public void setCausas(CausasDTO causas) {
		this.causas = causas;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	
	
	
	
	

	



}
