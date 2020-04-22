package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.io.Serializable;
import java.util.Date;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.encuesta.EncuestaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;

public class IncidenciaDetalleVO implements Serializable {
	
	private static final long serialVersionUID = 1374927111939946350L;
	private Long idEncuesta;
	private Long idIncidencia;
	private String cdIncidencia;
	private String nbIncidencia;
	private String txIncidencia;
	private Date fhCreacion;
	private StSeguimientoDTO stSeguimiento;
	private Boolean stActivo;
	private StSeguimientoDTO tpIncidencia;
	private StSeguimientoDTO stIncidencia;
	private StSeguimientoDTO stAutorizacion;
	private StSeguimientoDTO prioridad;
	
	private StSeguimientoVO stSeguimientoVO;
	private StSeguimientoVO tpIncidenciaVO;
	private StSeguimientoVO stIncidenciaVO;
	private StSeguimientoVO stAutorizacionVO;
	private StSeguimientoVO prioridadVO;
	
	

	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public StSeguimientoVO getStSeguimientoVO() {
		return stSeguimientoVO;
	}
	public void setStSeguimientoVO(StSeguimientoVO stSeguimientoVO) {
		this.stSeguimientoVO = stSeguimientoVO;
	}
	public StSeguimientoVO getTpIncidenciaVO() {
		return tpIncidenciaVO;
	}
	public void setTpIncidenciaVO(StSeguimientoVO tpIncidenciaVO) {
		this.tpIncidenciaVO = tpIncidenciaVO;
	}
	public StSeguimientoVO getStIncidenciaVO() {
		return stIncidenciaVO;
	}
	public void setStIncidenciaVO(StSeguimientoVO stIncidenciaVO) {
		this.stIncidenciaVO = stIncidenciaVO;
	}
	public StSeguimientoVO getStAutorizacionVO() {
		return stAutorizacionVO;
	}
	public void setStAutorizacionVO(StSeguimientoVO stAutorizacionVO) {
		this.stAutorizacionVO = stAutorizacionVO;
	}
	public StSeguimientoVO getPrioridadVO() {
		return prioridadVO;
	}
	public void setPrioridadVO(StSeguimientoVO prioridadVO) {
		this.prioridadVO = prioridadVO;
	}
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public Long getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	public String getCdIncidencia() {
		return cdIncidencia;
	}
	public void setCdIncidencia(String cdIncidencia) {
		this.cdIncidencia = cdIncidencia;
	}
	public String getNbIncidencia() {
		return nbIncidencia;
	}
	public void setNbIncidencia(String nbIncidencia) {
		this.nbIncidencia = nbIncidencia;
	}
	public String getTxIncidencia() {
		return txIncidencia;
	}
	public void setTxIncidencia(String txIncidencia) {
		this.txIncidencia = txIncidencia;
	}
	public StSeguimientoDTO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(StSeguimientoDTO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public StSeguimientoDTO getTpIncidencia() {
		return tpIncidencia;
	}
	public void setTpIncidencia(StSeguimientoDTO tpIncidencia) {
		this.tpIncidencia = tpIncidencia;
	}
	public StSeguimientoDTO getStIncidencia() {
		return stIncidencia;
	}
	public void setStIncidencia(StSeguimientoDTO stIncidencia) {
		this.stIncidencia = stIncidencia;
	}
	public StSeguimientoDTO getStAutorizacion() {
		return stAutorizacion;
	}
	public void setStAutorizacion(StSeguimientoDTO stAutorizacion) {
		this.stAutorizacion = stAutorizacion;
	}
	public StSeguimientoDTO getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(StSeguimientoDTO prioridad) {
		this.prioridad = prioridad;
	}
	
}
