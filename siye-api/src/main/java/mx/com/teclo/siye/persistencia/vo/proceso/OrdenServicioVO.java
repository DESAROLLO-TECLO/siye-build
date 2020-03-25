package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

/**
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class OrdenServicioVO {

	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private LoteOrdenServicioVO idLoteOds;
	private VehiculoVO idVehiculo;
	private CentroInstalacionVO idCentroInstalacion;
	private KitInstalacionVO idKitInstalacion;
	private PlanVO idPlan;
	private Long idStSeguimiento;
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

	public LoteOrdenServicioVO getIdLoteOds() {
		return idLoteOds;
	}

	public void setIdLoteOds(LoteOrdenServicioVO idLoteOds) {
		this.idLoteOds = idLoteOds;
	}

	public VehiculoVO getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(VehiculoVO idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public CentroInstalacionVO getIdCentroInstalacion() {
		return idCentroInstalacion;
	}

	public void setIdCentroInstalacion(CentroInstalacionVO idCentroInstalacion) {
		this.idCentroInstalacion = idCentroInstalacion;
	}

	public KitInstalacionVO getIdKitInstalacion() {
		return idKitInstalacion;
	}

	public void setIdKitInstalacion(KitInstalacionVO idKitInstalacion) {
		this.idKitInstalacion = idKitInstalacion;
	}

	public PlanVO getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(PlanVO idPlan) {
		this.idPlan = idPlan;
	}

	public Long getIdStSeguimiento() {
		return idStSeguimiento;
	}

	public void setIdStSeguimiento(Long idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
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
