package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;

public class UsuarioEncuestaRespuestaVOPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 394028055893777025L;
	
	private Long idUsuEncuIntento;
	private Long idEncuesta;
	private Long idSeccion;
	private Long idPregunta;
	
	public UsuarioEncuestaRespuestaVOPK() {}
	
	public UsuarioEncuestaRespuestaVOPK(Long idUsuEncuIntento, Long idEncuesta, Long idSeccion, Long idPregunta ) {
		this.idUsuEncuIntento = idUsuEncuIntento;
		this.idEncuesta = idEncuesta;
		this.idSeccion = idSeccion;
		this.idPregunta = idPregunta;
	}
	
	public Long getIdUsuEncuIntento() {
		return idUsuEncuIntento;
	}
	public void setIdUsuEncuIntento(Long idUsuEncuIntento) {
		this.idUsuEncuIntento = idUsuEncuIntento;
	}
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public Long getIdSeccion() {
		return idSeccion;
	}
	public void setIdSeccion(Long idSeccion) {
		this.idSeccion = idSeccion;
	}
	public Long getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}	
}
