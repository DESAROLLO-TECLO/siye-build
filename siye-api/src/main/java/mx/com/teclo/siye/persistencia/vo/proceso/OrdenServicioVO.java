package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;
import java.util.Date;

public class OrdenServicioVO implements Serializable {
	private static final long serialVersionUID = 3398117821017518569L;
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private LoteOrdenServicioVO loteOrdenServicio;
	private VehiculoVO vehiculo;
	private CentroInstalacionVO centroInstalacion;
	private KitInstalacionVO kitInstalacion;
	private PlanVO plan;
	private StSeguimientoVO stSeguimiento;
	private String hrCita;
	private Date fhAtencionIni;
	private Date fhAtencionFin;
	private Long idOrigenOds;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModificacion;
	private Date fhModificacion;
	
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
	public LoteOrdenServicioVO getLoteOrdenServicio() {
		return loteOrdenServicio;
	}
	public void setLoteOrdenServicio(LoteOrdenServicioVO loteOrdenServicio) {
		this.loteOrdenServicio = loteOrdenServicio;
	}
	public VehiculoVO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoVO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public CentroInstalacionVO getCentroInstalacion() {
		return centroInstalacion;
	}
	public void setCentroInstalacion(CentroInstalacionVO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	public KitInstalacionVO getKitInstalacion() {
		return kitInstalacion;
	}
	public void setKitInstalacion(KitInstalacionVO kitInstalacion) {
		this.kitInstalacion = kitInstalacion;
	}
	public PlanVO getPlan() {
		return plan;
	}
	public void setPlan(PlanVO plan) {
		this.plan = plan;
	}
	public StSeguimientoVO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(StSeguimientoVO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
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
	public Long getIdOrigenOds() {
		return idOrigenOds;
	}
	public void setIdOrigenOds(Long idOrigenOds) {
		this.idOrigenOds = idOrigenOds;
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
	public Long getIdUsrModificacion() {
		return idUsrModificacion;
	}
	public void setIdUsrModificacion(Long idUsrModificacion) {
		this.idUsrModificacion = idUsrModificacion;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	
	
}
