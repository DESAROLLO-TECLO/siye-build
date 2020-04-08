package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;
import java.util.Date;


import mx.com.teclo.siye.persistencia.vo.catalogo.CausasVO;

public class IERespCausaVO implements Serializable {
	
	private static final long serialVersionUID = 3771263775950160399L;
	
	private Long idRespCausa;
	private UsuarioEncuestaIntentosVO usuarioEncuestaIntento;
	private EncuestaVO encuesta;
	private SeccionVO seccion;
	private PreguntaVO preguntas;
	private CausasVO causas;
	private Boolean stActivo;
	private Date fhCreacion;
	public Long getIdRespCausa() {
		return idRespCausa;
	}
	public void setIdRespCausa(Long idRespCausa) {
		this.idRespCausa = idRespCausa;
	}
	public UsuarioEncuestaIntentosVO getUsuarioEncuestaIntento() {
		return usuarioEncuestaIntento;
	}
	public void setUsuarioEncuestaIntento(
			UsuarioEncuestaIntentosVO usuarioEncuestaIntento) {
		this.usuarioEncuestaIntento = usuarioEncuestaIntento;
	}
	public EncuestaVO getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(EncuestaVO encuesta) {
		this.encuesta = encuesta;
	}
	public SeccionVO getSeccion() {
		return seccion;
	}
	public void setSeccion(SeccionVO seccion) {
		this.seccion = seccion;
	}
	public PreguntaVO getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(PreguntaVO preguntas) {
		this.preguntas = preguntas;
	}
	public CausasVO getCausas() {
		return causas;
	}
	public void setCausas(CausasVO causas) {
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
