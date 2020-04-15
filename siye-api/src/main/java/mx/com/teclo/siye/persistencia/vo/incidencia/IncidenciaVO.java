package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.TipoSeguimientoVO;

public class IncidenciaVO implements Serializable {
	
	private static final long serialVersionUID = 1374927111939946350L;
	
	private Long idIncidencia;
	private String cdIncidencia;
	private String nbIncidencia;
	private String txIncidencia;
	private TipoSeguimientoVO stSeguimiento;
	private Boolean stActivo;
	private TipoSeguimientoVO tpIncidencia;
	private TipoSeguimientoVO stIncidencia;
	private TipoSeguimientoVO stAutorizacion;
	private TipoSeguimientoVO prioridad;
	private IEprocesosDTO iEproceso;
	private EncuestasDTO encuesta;
	
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
	public TipoSeguimientoVO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(TipoSeguimientoVO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public TipoSeguimientoVO getTpIncidencia() {
		return tpIncidencia;
	}
	public void setTpIncidencia(TipoSeguimientoVO tpIncidencia) {
		this.tpIncidencia = tpIncidencia;
	}
	public TipoSeguimientoVO getStIncidencia() {
		return stIncidencia;
	}
	public void setStIncidencia(TipoSeguimientoVO stIncidencia) {
		this.stIncidencia = stIncidencia;
	}
	public TipoSeguimientoVO getStAutorizacion() {
		return stAutorizacion;
	}
	public void setStAutorizacion(TipoSeguimientoVO stAutorizacion) {
		this.stAutorizacion = stAutorizacion;
	}
	public TipoSeguimientoVO getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(TipoSeguimientoVO prioridad) {
		this.prioridad = prioridad;
	}
	public IEprocesosDTO getiEproceso() {
		return iEproceso;
	}
	public void setiEproceso(IEprocesosDTO iEproceso) {
		this.iEproceso = iEproceso;
	}
	public EncuestasDTO getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(EncuestasDTO encuesta) {
		this.encuesta = encuesta;
	}
	

}
