package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;



public class IncidencVO implements Serializable {
	
	
	private static final long serialVersionUID = 7010564377174920304L;

	private Long idIncidencia;
	private String cdIncidencia;
	private String nbIncidencia;
	private String txIncidencia;
	private TpSeguimientoVO stSeguimiento;
	private Boolean stActivo;
	private TpSeguimientoVO tpIncidencia;
	private TpSeguimientoVO stIncidencia;
	private TpSeguimientoVO stAutorizacion;
	private TpSeguimientoVO prioridad;
	
	
	
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
	public TpSeguimientoVO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(TpSeguimientoVO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public TpSeguimientoVO getTpIncidencia() {
		return tpIncidencia;
	}
	public void setTpIncidencia(TpSeguimientoVO tpIncidencia) {
		this.tpIncidencia = tpIncidencia;
	}
	public TpSeguimientoVO getStIncidencia() {
		return stIncidencia;
	}
	public void setStIncidencia(TpSeguimientoVO stIncidencia) {
		this.stIncidencia = stIncidencia;
	}
	public TpSeguimientoVO getStAutorizacion() {
		return stAutorizacion;
	}
	public void setStAutorizacion(TpSeguimientoVO stAutorizacion) {
		this.stAutorizacion = stAutorizacion;
	}
	public TpSeguimientoVO getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(TpSeguimientoVO prioridad) {
		this.prioridad = prioridad;
	}
	
	

}
