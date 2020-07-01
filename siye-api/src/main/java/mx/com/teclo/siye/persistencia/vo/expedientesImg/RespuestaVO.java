package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.io.Serializable;

public class RespuestaVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2173486880553955767L;
	private String respuesta;
	private String causa;
	private String justificacion;
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	
}
