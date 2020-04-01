package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.com.teclo.siye.persistencia.vo.catalogo.TipoEncuestaVO;



public class EncuestaVO {

	private Long idEncuesta;
	private String cdEncuesta;
	private String nbEncuesta;
	private String txEncuesta;
	private String txInstrucciones;
	private Integer nuPreguntas;
	private Integer nuSecciones;
	private Integer nuTiempo;
	@JsonIgnore
	private Boolean stActivo;
	@JsonIgnore
	private Long idUsrCreacion;
	@JsonIgnore
	private Date fhCreacion;
	@JsonIgnore
	private Long idUsrModifica;
	@JsonIgnore
	private Date fhModificacion;

	private TipoEncuestaVO tipoEncuesta;
	private List<SeccionVO> seccion;
	private Date fhVigIni;
	private Date fhVigFin;
	private Integer nuCalificacionApro;
	
	

	public List<SeccionVO> getSeccion() {
		return seccion;
	}

	public void setSeccion(List<SeccionVO> seccion) {
		this.seccion = seccion;
	}

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

	public TipoEncuestaVO getTipoEncuesta() {
		return tipoEncuesta;
	}

	public void setTipoEncuesta(TipoEncuestaVO tipoEncuesta) {
		this.tipoEncuesta = tipoEncuesta;
	}

	public Date getFhVigIni() {
		return fhVigIni;
	}

	public void setFhVigIni(Date fhVigIni) {
		this.fhVigIni = fhVigIni;
	}

	public Date getFhVigFin() {
		return fhVigFin;
	}

	public void setFhVigFin(Date fhVigFin) {
		this.fhVigFin = fhVigFin;
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
	

}
