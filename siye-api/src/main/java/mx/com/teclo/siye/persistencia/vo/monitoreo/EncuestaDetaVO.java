package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoEncuestaVO;



public class EncuestaDetaVO {
	private Long idProceso;
	private Long idEncuesta;
	private String cdEncuesta;
	private String nbEncuesta;
	private String txEncuesta;
	private StEncuestaVO stEncuesta;
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
	private Date fhVigIni;
	private Date fhVigFin;
	private Integer nuCalificacionApro;
	private List<IncidenciaDetalleVO> incidencia;
	
	public StEncuestaVO getStEncuesta() {
		return stEncuesta;
	}
	public void setStEncuesta(StEncuestaVO stEncuesta) {
		this.stEncuesta = stEncuesta;
	}
	public Long getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
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
	public Integer getNuSecciones() {
		return nuSecciones;
	}
	public void setNuSecciones(Integer nuSecciones) {
		this.nuSecciones = nuSecciones;
	}
	public Integer getNuTiempo() {
		return nuTiempo;
	}
	public void setNuTiempo(Integer nuTiempo) {
		this.nuTiempo = nuTiempo;
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
	public List<IncidenciaDetalleVO> getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(List<IncidenciaDetalleVO> incidencia) {
		this.incidencia = incidencia;
	}
	
}
