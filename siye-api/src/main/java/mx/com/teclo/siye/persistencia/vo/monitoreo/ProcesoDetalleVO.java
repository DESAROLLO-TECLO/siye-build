package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.util.Date;
import java.util.List;



public class ProcesoDetalleVO {
	

	private Long idProceso;
	private String cdProceso;
	private String nbProceso;
	private String txProceso;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	private Long nuMaxImagenes;
	private List<IncidenciaDetalleVO> incidencia;
	private List<EncuestaDetaVO> encuesta;
	
	
	public List<IncidenciaDetalleVO> getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(List<IncidenciaDetalleVO> incidencia) {
		this.incidencia = incidencia;
	}
	public Long getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}
	public String getCdProceso() {
		return cdProceso;
	}
	public void setCdProceso(String cdProceso) {
		this.cdProceso = cdProceso;
	}
	public String getNbProceso() {
		return nbProceso;
	}
	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
	}
	public String getTxProceso() {
		return txProceso;
	}
	public void setTxProceso(String txProceso) {
		this.txProceso = txProceso;
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
	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}
	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}
	public List<EncuestaDetaVO> getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(List<EncuestaDetaVO> encuesta) {
		this.encuesta = encuesta;
	}
	

}
