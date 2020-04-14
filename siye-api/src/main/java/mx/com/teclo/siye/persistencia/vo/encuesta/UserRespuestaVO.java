package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;

public class UserRespuestaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6120807422978066513L;

	private Long idEncuesta;
	private Long idSeccion;
	private Long idPregunta;
	private Long idOpcion;
	private Long idIntento;
	//nueva variable causas
	private String causas;
	
	private String descripcionCausa;

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

	/**
	 * @return the idOpcion
	 */
	public Long getIdOpcion() {
		return idOpcion;
	}

	/**
	 * @param idOpcion the idOpcion to set
	 */
	public void setIdOpcion(Long idOpcion) {
		this.idOpcion = idOpcion;
	}

	/**
	 * @return the idIntento
	 */
	public Long getIdIntento() {
		return idIntento;
	}

	/**
	 * @param idIntento the idIntento to set
	 */
	public void setIdIntento(Long idIntento) {
		this.idIntento = idIntento;
	}

	public String getCausas() {
		return causas;
	}

	public void setCausas(String causas) {
		this.causas = causas;
	}

	public String getDescripcionCausa() {
		return descripcionCausa;
	}

	public void setDescripcionCausa(String descripcionCausa) {
		this.descripcionCausa = descripcionCausa;
	}
	
	
	

}
