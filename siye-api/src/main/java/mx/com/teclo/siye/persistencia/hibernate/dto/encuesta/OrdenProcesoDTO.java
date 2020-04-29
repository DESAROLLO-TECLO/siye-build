package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;

@Entity
@Table(name = "TIE065D_ODS_PROCESOS")
public class OrdenProcesoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "ID_ODS_PROCESO", nullable = false)
	private Long idOdsProceso;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORDEN_SERVICIO", referencedColumnName = "ID_ORDEN_SERVICIO", nullable = false)
	private OrdenServicioDTO ordenServicio;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO", nullable = false)
	private ProcesoDTO proceso;
	
	@Basic(optional = false)
	@Column(name = "FH_INI_PROCESO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhInicioProceso;
	
	@Basic(optional = false)
	@Column(name = "FH_FIN_PROCESO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhFinProceso;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ST_SEGUIMIENTO", referencedColumnName = "ID_ST_SEGUIMIENTO", nullable = false)
	private StSeguimientoDTO stSeguimiento;

	public Long getIdOdsProceso() {
		return idOdsProceso;
	}

	public void setIdOdsProceso(Long idOdsProceso) {
		this.idOdsProceso = idOdsProceso;
	}

	public OrdenServicioDTO getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(OrdenServicioDTO ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	public ProcesoDTO getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
	}

	public Date getFhInicioProceso() {
		return fhInicioProceso;
	}

	public void setFhInicioProceso(Date fhInicioProceso) {
		this.fhInicioProceso = fhInicioProceso;
	}

	public Date getFhFinProceso() {
		return fhFinProceso;
	}

	public void setFhFinProceso(Date fhFinProceso) {
		this.fhFinProceso = fhFinProceso;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public StSeguimientoDTO getStSeguimiento() {
		return stSeguimiento;
	}

	public void setStSeguimiento(StSeguimientoDTO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
	}
	
	
	
	


	
	

}
