package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import mx.com.teclo.siye.persistencia.vo.encuesta.EncuestaVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss",timezone = "America/Mexico_City")
	private Date fhCita;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss",timezone = "America/Mexico_City")
	private Date fhAtencionIni;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss",timezone = "America/Mexico_City")
	private Date fhAtencionFin;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss",timezone = "America/Mexico_City")
	private Date fhAtencionParcial;
	private Long idOrigenOds;
	private Boolean stActivo;
	private IncidenciaVO incidencia;
	private ProcesoVO proceso;
	private EncuestaVO encuesta;
	
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
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public IncidenciaVO getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(IncidenciaVO incidencia) {
		this.incidencia = incidencia;
	}
	public ProcesoVO getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoVO proceso) {
		this.proceso = proceso;
	}
	public Date getFhAtencionParcial() {
		return fhAtencionParcial;
	}
	public void setFhAtencionParcial(Date fhAtencionParcial) {
		this.fhAtencionParcial = fhAtencionParcial;
	}
	public EncuestaVO getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(EncuestaVO encuesta) {
		this.encuesta = encuesta;
	}
	
	
}
