package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

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



@Entity
@Table(name = "TIE026D_IE_ORDEN_SERVICIOS")
public class OrdenServicioDTO implements Serializable {
	
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@SequenceGenerator(name = "sqOrdenServicio", sequenceName = "SQIE026D_IE_ORDEN_SER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqOrdenServicio")
	@Column(name = "ID_ORDEN_SERVICIO", unique = true, nullable = false)
	private Long idOrdenServicio;
	
	@Column(name = "CD_ORDEN_SERVICIO")
	private String cdOrdenServicio;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_LOTE_ODS", referencedColumnName="ID_LOTE_ODS", insertable=false, updatable=false)
	private LoteOrdenServicioDTO loteOrdenServicio;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_VEHICULO", referencedColumnName="ID_VEHICULO", insertable=false, updatable=false)
	private VehiculoDTO vehiculo;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CENTRO_INSTALACION", referencedColumnName="ID_CENTRO_INSTALACION", insertable=false, updatable=false)
	private CentroInstalacionDTO centroInstalacion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_KIT_INSTALACION", referencedColumnName="ID_KIT_INSTALACION", insertable=false, updatable=false)
	private KitInstalacionDTO kitInstalacion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PLAN", referencedColumnName="ID_PLAN", insertable=false, updatable=false)
	private PlanDTO plan;
	
	@Column(name = "ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO idStSeguimiento;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION")	
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICACION")
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")	
	private Date fhModificacion;
	
	@Column(name = "HR_CITA")
	private String hrCita;
	
	@Column(name = "FH_ATENCION_INI")
	private Date fhAtencionIni;
	
	@Column(name = "FH_ATENCION_FIN")
	private Date fhAtencionFin;
	
	@Column(name = "ID_ORIGEN_ODS")
	private Long idOrdenODS;
	
	
	
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	public String getCdOrdenServicio() {
		return cdOrdenServicio;
	}
	public void setCdOrdenServicio(String cdOrdenServicio) {
		this.cdOrdenServicio = cdOrdenServicio;
	}
	public LoteOrdenServicioDTO getLoteOrdenServicio() {
		return loteOrdenServicio;
	}
	public void setLoteOrdenServicio(LoteOrdenServicioDTO loteOrdenServicio) {
		this.loteOrdenServicio = loteOrdenServicio;
	}
	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public CentroInstalacionDTO getCentroInstalacion() {
		return centroInstalacion;
	}
	public void setCentroInstalacion(CentroInstalacionDTO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	public KitInstalacionDTO getKitInstalacion() {
		return kitInstalacion;
	}
	public void setKitInstalacion(KitInstalacionDTO kitInstalacion) {
		this.kitInstalacion = kitInstalacion;
	}
	public PlanDTO getPlan() {
		return plan;
	}
	public void setPlan(PlanDTO plan) {
		this.plan = plan;
	}
	public StSeguimientoDTO getIdStSeguimiento() {
		return idStSeguimiento;
	}
	public void setIdStSeguimiento(StSeguimientoDTO idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
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
	public String getHrCita() {
		return hrCita;
	}
	public void setHrCita(String hrCita) {
		this.hrCita = hrCita;
	}
	public Date getFhAtencionIni() {
		return fhAtencionIni;
	}
	public void setFhAtencionIni(Date fhAtencionIni) {
		this.fhAtencionIni = fhAtencionIni;
	}
	public Date getFhAtencionFin() {
		return fhAtencionFin;
	}
	public void setFhAtencionFin(Date fhAtencionFin) {
		this.fhAtencionFin = fhAtencionFin;
	}
	public Long getIdOrdenODS() {
		return idOrdenODS;
	}
	public void setIdOrdenODS(Long idOrdenODS) {
		this.idOrdenODS = idOrdenODS;
	}
	
	
	
	
	
	
	
	
	

}
