package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.util.Date;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

public class OrdenServicioDetVO {
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private String  nbStSeguimiento;
	private String nbProcesoActual;
	private String cdProcesoActual;
	private String nbEncuestaActual;
	private String cdEncuestaActual;
	private Date fhCita;
	private Date fhAtencionIni;
	private Date fhAtencionFin;
	
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
	public String getNbStSeguimiento() {
		return nbStSeguimiento;
	}
	public void setNbStSeguimiento(String nbStSeguimiento) {
		this.nbStSeguimiento = nbStSeguimiento;
	}
	public String getNbProcesoActual() {
		return nbProcesoActual;
	}
	public void setNbProcesoActual(String nbProcesoActual) {
		this.nbProcesoActual = nbProcesoActual;
	}
	public String getCdProcesoActual() {
		return cdProcesoActual;
	}
	public void setCdProcesoActual(String cdProcesoActual) {
		this.cdProcesoActual = cdProcesoActual;
	}
	public String getNbEncuestaActual() {
		return nbEncuestaActual;
	}
	public void setNbEncuestaActual(String nbEncuestaActual) {
		this.nbEncuestaActual = nbEncuestaActual;
	}
	public String getCdEncuestaActual() {
		return cdEncuestaActual;
	}
	public void setCdEncuestaActual(String cdEncuestaActual) {
		this.cdEncuestaActual = cdEncuestaActual;
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
	
	
}
