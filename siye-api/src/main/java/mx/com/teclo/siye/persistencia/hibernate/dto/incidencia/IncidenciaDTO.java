package mx.com.teclo.siye.persistencia.hibernate.dto.incidencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestasDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;

@Entity
@Table(name = "TIE051D_IE_INCIDENCIA")
public class IncidenciaDTO implements Serializable {
	
	private static final long serialVersionUID = -5365441506721842142L;

	@Id
	@SequenceGenerator(name = "sqIncidencia", sequenceName = "SQIE041B_IE_INCIDENCI", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqIncidencia")
	@Column(name = "ID_INCIDENCIA", unique = true, nullable = false, insertable = false)
	private Long idIncidencia;

	@Column(name = "CD_INCIDENCIA")
	private String cdIncidencia;

	@Column(name = "NB_INCIDENCIA")
	private String nbIncidencia;

	@Column(name = "TX_INCIDENCIA")
	private String txIncidencia;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ST_SEGUIMIENTO", referencedColumnName="ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO stSeguimiento;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TP_INCIDENCIA", referencedColumnName="ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO tpIncidencia;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ST_INCIDENCIA", referencedColumnName="ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO stIncidencia;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ST_AUTORIZACION", referencedColumnName="ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO stAutorizacion;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PRIORIDAD", referencedColumnName="ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO prioridad;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PROCESO", referencedColumnName="ID_PROCESO")
	private IEprocesosDTO iEproceso;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ENCUESTA", referencedColumnName="ID_ENCUESTA")
	private EncuestasDTO encuesta;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CENTRO_INSTALACION", referencedColumnName="ID_CENTRO_INSTALACION")
	private CentroInstalacionDTO centroInstalacion;

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

	public CentroInstalacionDTO getCentroInstalacion() {
		return centroInstalacion;
	}

	public void setCentroInstalacion(CentroInstalacionDTO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	

}
