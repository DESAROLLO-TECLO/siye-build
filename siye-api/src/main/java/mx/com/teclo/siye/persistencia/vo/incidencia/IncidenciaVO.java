package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;
import java.util.Date;
import mx.com.teclo.siye.persistencia.vo.proceso.TipoSeguimientoVO;

public class IncidenciaVO implements Serializable {
	
	private static final long serialVersionUID = 1374927111939946350L;
	
	private Long idIncidencia;
	private String cdIncidencia;
	private String nbIncidencia;
	private String txIncidencia;
	private TipoSeguimientoVO stSeguimiento;
	private Long stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	private TipoSeguimientoVO tpIncidencia;
	private TipoSeguimientoVO stIncidencia;
	private TipoSeguimientoVO stAutorizacion;
	private TipoSeguimientoVO prioridad;
	
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
	public Long getStActivo() {
		return stActivo;
	}
	public void setStActivo(Long stActivo) {
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

}
