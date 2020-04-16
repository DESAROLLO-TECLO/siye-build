package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.catalogo.TipoEncuestaVO;

public class EncuestaDetalleVO implements Serializable {

	private static final long serialVersionUID = -7504518940624530773L;

	private Long idEncuesta;
	private String cdEncuesta;
	private String nbEncuesta;
	private String txEncuesta;
	private String txInstrucciones;
	private Integer nuPreguntas;
	private Integer nuSecciones;
	private Integer nuTiempo;
	private TipoEncuestaVO tipoEncuesta;
	private List<SeccionVO> secciones;
	private Integer nuCalificacionApro;
	private Integer stActivo;
	private Long nuMaxImagenes;
	
	//nuevo fechaInicioEncuesta y fechaFin
	private Date fechaInicioEncuesta;
	private Date fechaFinEncuesta;
	
	
	public Long getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public String getCdEncuesta() {
		return cdEncuesta;
	}

	public void setCdEncuesta(String cdEncuesta) {
		this.cdEncuesta = cdEncuesta;
	}

	public String getNbEncuesta() {
		return nbEncuesta;
	}

	public void setNbEncuesta(String nbEncuesta) {
		this.nbEncuesta = nbEncuesta;
	}

	public String getTxEncuesta() {
		return txEncuesta;
	}

	public void setTxEncuesta(String txEncuesta) {
		this.txEncuesta = txEncuesta;
	}

	public String getTxInstrucciones() {
		return txInstrucciones;
	}

	public void setTxInstrucciones(String txInstrucciones) {
		this.txInstrucciones = txInstrucciones;
	}

	public Integer getNuPreguntas() {
		return nuPreguntas;
	}

	public void setNuPreguntas(Integer nuPreguntas) {
		this.nuPreguntas = nuPreguntas;
	}

	public TipoEncuestaVO getTipoEncuesta() {
		return tipoEncuesta;
	}

	public void setTipoEncuesta(TipoEncuestaVO tipoEncuesta) {
		this.tipoEncuesta = tipoEncuesta;
	}

	public List<SeccionVO> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<SeccionVO> secciones) {
		this.secciones = secciones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getNuCalificacionApro() {
		return nuCalificacionApro;
	}

	public void setNuCalificacionApro(Integer nuCalificacionApro) {
		this.nuCalificacionApro = nuCalificacionApro;
	}

	public Integer getNuSecciones() {
		return nuSecciones;
	}

	public void setNuSecciones(Integer nuSecciones) {
		this.nuSecciones = nuSecciones;
	}

	/**
	 * @return the nuTiempo
	 */
	public Integer getNuTiempo() {
		return nuTiempo;
	}

	/**
	 * @param nuTiempo the nuTiempo to set
	 */
	public void setNuTiempo(Integer nuTiempo) {
		this.nuTiempo = nuTiempo;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Date getFechaInicioEncuesta() {
		return fechaInicioEncuesta;
	}

	public void setFechaInicioEncuesta(Date fechaInicioEncuesta) {
		this.fechaInicioEncuesta = fechaInicioEncuesta;
	}

	public Date getFechaFinEncuesta() {
		return fechaFinEncuesta;
	}

	public void setFechaFinEncuesta(Date fechaFinEncuesta) {
		this.fechaFinEncuesta = fechaFinEncuesta;
	}

	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}

	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}
	
	
}
