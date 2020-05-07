package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.com.teclo.siye.persistencia.vo.catalogo.EstatusCalificacionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;



/**
 * @author Fer
 *
 */
public class UsuarioEncuestaIntentosVO {

	private Long idUsuEncuIntento;

	@JsonIgnore
	private UsuarioEncuestaVO usuarioEncuesta;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	private Date fhInicio;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	private Date fhFin;

	private StEncuestaVO stEncuesta;
	private Integer nuCalificacion;
	private EstatusCalificacionVO stCalificacion;
	private Boolean stMostrar;
	private Integer nuPreguntas;
	private Integer nuPreguntasCorrectas;
	private Integer nuPreguntasIncorr;
	private Integer nuPreguntasVacias;
	private Boolean esProcesoExitoso;
	private Integer nuSeccionesRsp;
	private List<UsuarioEncuestaRespuestaVO> detalleRespuesta;
	private Integer nuMinConsumidos;
	private List<SeccionVO> seccionesListVO;
	private Long transportista;
	private Integer instalador;

	public UsuarioEncuestaIntentosVO() {
	}

	public UsuarioEncuestaIntentosVO(Long idUsuEncuIntento) {
		this.idUsuEncuIntento = idUsuEncuIntento;
	}

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

	public UsuarioEncuestaVO getUsuarioEncuesta() {
		return usuarioEncuesta;
	}

	public void setUsuarioEncuesta(UsuarioEncuestaVO usuarioEncuesta) {
		this.usuarioEncuesta = usuarioEncuesta;
	}

	/**
	 * @return the fhInicio
	 */
	public Date getFhInicio() {
		return fhInicio;
	}

	/**
	 * @param fhInicio the fhInicio to set
	 */
	public void setFhInicio(Date fhInicio) {
		this.fhInicio = fhInicio;
	}

	/**
	 * @return the fhFin
	 */
	public Date getFhFin() {
		return fhFin;
	}

	/**
	 * @param fhFin the fhFin to set
	 */
	public void setFhFin(Date fhFin) {
		this.fhFin = fhFin;
	}

	/**
	 * @return the stEncuesta
	 */
	public StEncuestaVO getStEncuesta() {
		return stEncuesta;
	}

	/**
	 * @param stEncuesta the stEncuesta to set
	 */
	public void setStEncuesta(StEncuestaVO stEncuesta) {
		this.stEncuesta = stEncuesta;
	}

	/**
	 * @return the nuCalificacion
	 */
	public Integer getNuCalificacion() {
		return nuCalificacion;
	}

	/**
	 * @param nuCalificacion the nuCalificacion to set
	 */
	public void setNuCalificacion(Integer nuCalificacion) {
		this.nuCalificacion = nuCalificacion;
	}

	/**
	 * @return the stCalificacion
	 */
	public EstatusCalificacionVO getStCalificacion() {
		return stCalificacion;
	}

	/**
	 * @param stCalificacion the stCalificacion to set
	 */
	public void setStCalificacion(EstatusCalificacionVO stCalificacion) {
		this.stCalificacion = stCalificacion;
	}

	/**
	 * @return the stMostrar
	 */
	public Boolean getStMostrar() {
		return stMostrar;
	}

	/**
	 * @param stMostrar the stMostrar to set
	 */
	public void setStMostrar(Boolean stMostrar) {
		this.stMostrar = stMostrar;
	}

	/**
	 * @return the nuPreguntas
	 */
	public Integer getNuPreguntas() {
		return nuPreguntas;
	}

	/**
	 * @param nuPreguntas the nuPreguntas to set
	 */
	public void setNuPreguntas(Integer nuPreguntas) {
		this.nuPreguntas = nuPreguntas;
	}

	/**
	 * @return the nuPreguntasCorrectas
	 */
	public Integer getNuPreguntasCorrectas() {
		return nuPreguntasCorrectas;
	}

	/**
	 * @param nuPreguntasCorrectas the nuPreguntasCorrectas to set
	 */
	public void setNuPreguntasCorrectas(Integer nuPreguntasCorrectas) {
		this.nuPreguntasCorrectas = nuPreguntasCorrectas;
	}

	/**
	 * @return the nuPreguntasIncorr
	 */
	public Integer getNuPreguntasIncorr() {
		return nuPreguntasIncorr;
	}

	/**
	 * @param nuPreguntasIncorr the nuPreguntasIncorr to set
	 */
	public void setNuPreguntasIncorr(Integer nuPreguntasIncorr) {
		this.nuPreguntasIncorr = nuPreguntasIncorr;
	}

	public Boolean getEsProcesoExitoso() {
		return esProcesoExitoso;
	}

	public void setEsProcesoExitoso(Boolean esProcesoExitoso) {
		this.esProcesoExitoso = esProcesoExitoso;
	}

	public Integer getNuSeccionesRsp() {
		return nuSeccionesRsp;
	}

	public void setNuSeccionesRsp(Integer nuSeccionesRsp) {
		this.nuSeccionesRsp = nuSeccionesRsp;
	}

	@Override
	public String toString() {
		return "UsuarioEncuestaIntentosVO [idUsuEncuIntento=" + idUsuEncuIntento + ", usuarioEncuesta="
				+ usuarioEncuesta + ", fhInicio=" + fhInicio + ", fhFin=" + fhFin + ", stEncuesta=" + stEncuesta
				+ ", nuCalificacion=" + nuCalificacion + ", stCalificacion=" + stCalificacion + ", stMostrar="
				+ stMostrar + ", nuPreguntas=" + nuPreguntas + ", nuPreguntasCorrectas=" + nuPreguntasCorrectas
				+ ", nuPreguntasIncorr=" + nuPreguntasIncorr + ", nuPreguntasNoRespondidas=" + nuPreguntasVacias
				+ "]";
	}

	/**
	 * @return the detalleRespuesta
	 */
	public List<UsuarioEncuestaRespuestaVO> getDetalleRespuesta() {
		return detalleRespuesta;
	}

	/**
	 * @param detalleRespuesta the detalleRespuesta to set
	 */
	public void setDetalleRespuesta(List<UsuarioEncuestaRespuestaVO> detalleRespuesta) {
		this.detalleRespuesta = detalleRespuesta;
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

	/**
	 * @return the seccionesListVO
	 */
	public List<SeccionVO> getSeccionesListVO() {
		return seccionesListVO;
	}

	/**
	 * @param seccionesListVO the seccionesListVO to set
	 */
	public void setSeccionesListVO(List<SeccionVO> seccionesListVO) {
		this.seccionesListVO = seccionesListVO;
	}

	public Integer getNuPreguntasVacias() {
		return nuPreguntasVacias;
	}

	public void setNuPreguntasVacias(Integer nuPreguntasVacias) {
		this.nuPreguntasVacias = nuPreguntasVacias;
	}

	public Long getTransportista() {
		return transportista;
	}

	public void setTransportista(Long transportista) {
		this.transportista = transportista;
	}

	public Integer getInstalador() {
		return instalador;
	}

	public void setInstalador(Integer instalador) {
		this.instalador = instalador;
	}
	
	
}
