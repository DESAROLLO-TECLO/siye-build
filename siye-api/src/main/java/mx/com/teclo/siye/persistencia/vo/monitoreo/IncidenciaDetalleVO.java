package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.io.Serializable;

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
	private StSeguimientoVO stSeguimiento;
	private Boolean stActivo;
	private StSeguimientoVO tpIncidencia;
	private StSeguimientoVO stIncidencia;
	private StSeguimientoVO stAutorizacion;
	private StSeguimientoVO prioridad;
	
	
	
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
	public StSeguimientoVO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(StSeguimientoVO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public StSeguimientoVO getTpIncidencia() {
		return tpIncidencia;
	}
	public void setTpIncidencia(StSeguimientoVO tpIncidencia) {
		this.tpIncidencia = tpIncidencia;
	}
	public StSeguimientoVO getStIncidencia() {
		return stIncidencia;
	}
	public void setStIncidencia(StSeguimientoVO stIncidencia) {
		this.stIncidencia = stIncidencia;
	}
	public StSeguimientoVO getStAutorizacion() {
		return stAutorizacion;
	}
	public void setStAutorizacion(StSeguimientoVO stAutorizacion) {
		this.stAutorizacion = stAutorizacion;
	}
	public StSeguimientoVO getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(StSeguimientoVO prioridad) {
		this.prioridad = prioridad;
	}
	
}
