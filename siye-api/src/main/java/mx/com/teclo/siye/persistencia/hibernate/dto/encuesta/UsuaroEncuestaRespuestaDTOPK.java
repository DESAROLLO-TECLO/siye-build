/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author unitis0521
 */
@Embeddable
public class UsuaroEncuestaRespuestaDTOPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1964342137149817712L;

	@Basic(optional = false)
	@Column(name = "ID_USU_ENCU_INTENTO", nullable = false)
	private Long idUsuEncuIntento;

	@Basic(optional = false)
	@Column(name = "ID_ENCUESTA", nullable = false)
	private Long idEncuesta;

	@Basic(optional = false)
	@Column(name = "ID_SECCION", nullable = false)
	private Long idSeccion;

	@Basic(optional = false)
	@Column(name = "ID_PREGUNTA", nullable = false)
	private Long idPregunta;

	/**
	 * @return the idUsuEncuIntento
	 */
	public Long getIdUsuEncuIntento() {
		return idUsuEncuIntento;
	}

	/**
	 * @param idUsuEncuIntento the idUsuEncuIntento to set
	 */
	public void setIdUsuEncuIntento(Long idUsuEncuIntento) {
		this.idUsuEncuIntento = idUsuEncuIntento;
	}

	/**
	 * @return the idEncuesta
	 */
	public Long getIdEncuesta() {
		return idEncuesta;
	}

	/**
	 * @param idEncuesta the idEncuesta to set
	 */
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	/**
	 * @return the idSeccion
	 */
	public Long getIdSeccion() {
		return idSeccion;
	}

	/**
	 * @param idSeccion the idSeccion to set
	 */
	public void setIdSeccion(Long idSeccion) {
		this.idSeccion = idSeccion;
	}

	/**
	 * @return the idPregunta
	 */
	public Long getIdPregunta() {
		return idPregunta;
	}

	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

}
