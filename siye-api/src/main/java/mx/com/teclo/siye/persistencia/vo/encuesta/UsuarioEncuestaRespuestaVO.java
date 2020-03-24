package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.util.Date;

public class UsuarioEncuestaRespuestaVO {

	private UsuarioEncuestaRespuestaVOPK usuaroEncuestaRespuestaPK;
	private Long idOpcion;
	private Integer stCorrecto;
	private Date fhLectura;
	private Date fhRespuesta;
	private Integer nuIntentos;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private OpcionesVO opciones;
	private PreguntaVO pregunta;

	private UsuarioEncuestaRespuestaVOPK id;

	public UsuarioEncuestaRespuestaVOPK getUsuaroEncuestaRespuestaPK() {
		return usuaroEncuestaRespuestaPK;
	}

	public void setUsuaroEncuestaRespuestaPK(UsuarioEncuestaRespuestaVOPK usuaroEncuestaRespuestaPK) {
		this.usuaroEncuestaRespuestaPK = usuaroEncuestaRespuestaPK;
	}

	public Long getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(Long idOpcion) {
		this.idOpcion = idOpcion;
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

	/**
	 * @return the opciones
	 */
	public OpcionesVO getOpciones() {
		return opciones;
	}

	/**
	 * @param opciones the opciones to set
	 */
	public void setOpciones(OpcionesVO opciones) {
		this.opciones = opciones;
	}

	/**
	 * @return the pregunta
	 */
	public PreguntaVO getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(PreguntaVO pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the id
	 */
	public UsuarioEncuestaRespuestaVOPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UsuarioEncuestaRespuestaVOPK id) {
		this.id = id;
	}

}
