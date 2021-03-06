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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "TIE053B_IE_ODS_DETALLE_CAMBIO")
public class OdsDetalleCambioDTO  implements Serializable{
	
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_DETALLE_CAMBIO", unique = true, nullable = false)
	private Long idDetalleCambio;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ORDEN_SERVICIO", referencedColumnName="ID_ORDEN_SERVICIO")
	private OrdenServicioDTO OrdenServicio2;
	
	
	@Column(name = "CD_ORDEN_SERVICIO")
	private String cdOrdenServicio;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_LOTE_ODS", referencedColumnName="ID_LOTE_ODS")
	private LoteOrdenServicioDTO loteOrdenServicio;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_VEHICULO", referencedColumnName="ID_VEHICULO")
	private VehiculoDTO vehiculo;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CENTRO_INSTALACION", referencedColumnName="ID_CENTRO_INSTALACION")
	private CentroInstalacionDTO centroInstalacion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_KIT_INSTALACION", referencedColumnName="ID_KIT_INSTALACION")
	private KitInstalacionDTO kitInstalacion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PLAN", referencedColumnName="ID_PLAN")
	private PlanDTO plan;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ST_SEGUIMIENTO", referencedColumnName="ID_ST_SEGUIMIENTO")
	private StSeguimientoDTO stSeguimiento;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION")	
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")	
	private Date fhModificacion;
	
	@Column(name = "FH_CITA")
	private Date fhCita;
	
	@Column(name = "FH_ATENCION_INI")
	private Date fhAtencionIni;
	
	@Column(name = "FH_ATENCION_FIN")
	private Date fhAtencionFin;
	
	@Column(name = "ID_ORIGEN_ODS")
	private Long idOrigenOds;
	
	@Column(name = "ID_PROCESO_ACTUAL")
	private Long idProcesoActual;

	public Long getIdDetalleCambio() {
		return idDetalleCambio;
	}

	public void setIdDetalleCambio(Long idDetalleCambio) {
		this.idDetalleCambio = idDetalleCambio;
	}



	public OrdenServicioDTO getOrdenServicio2() {
		return OrdenServicio2;
	}

	public void setOrdenServicio2(OrdenServicioDTO ordenServicio2) {
		OrdenServicio2 = ordenServicio2;
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

	public Date getFhCita() {
		return fhCita;
	}

	public void setFhCita(Date fhCita) {
		this.fhCita = fhCita;
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

	public Long getIdOrigenOds() {
		return idOrigenOds;
	}

	public void setIdOrigenOds(Long idOrigenOds) {
		this.idOrigenOds = idOrigenOds;
	}

	public Long getIdProcesoActual() {
		return idProcesoActual;
	}

	public void setIdProcesoActual(Long idProcesoActual) {
		this.idProcesoActual = idProcesoActual;
	}

	
	
	

}
