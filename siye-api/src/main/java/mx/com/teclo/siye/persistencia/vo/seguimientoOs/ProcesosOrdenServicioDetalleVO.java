package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.Date;
import java.util.List;

public class ProcesosOrdenServicioDetalleVO {
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private String nbModuloAtencion;
	private String cdIdentific;
	private Double nuPorcentaje;
	private String estatus;
	private String nbKit;
	private String nbPlan;
	private Date finicio;
	private Date ffin;
	private String fhDiferencia;
	private List<ProcesoDetalleVO> procesos;
	
	
	/**
	 * @return the idOrdenServicio
	 */
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	/**
	 * @param idOrdenServicio the idOrdenServicio to set
	 */
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	/**
	 * @return the cdOrdenServicio
	 */
	public String getCdOrdenServicio() {
		return cdOrdenServicio;
	}
	/**
	 * @param cdOrdenServicio the cdOrdenServicio to set
	 */
	public void setCdOrdenServicio(String cdOrdenServicio) {
		this.cdOrdenServicio = cdOrdenServicio;
	}
	/**
	 * @return the nbModuloAtencion
	 */
	public String getNbModuloAtencion() {
		return nbModuloAtencion;
	}
	/**
	 * @param nbModuloAtencion the nbModuloAtencion to set
	 */
	public void setNbModuloAtencion(String nbModuloAtencion) {
		this.nbModuloAtencion = nbModuloAtencion;
	}
	/**
	 * @return the cdIdentific
	 */
	public String getCdIdentific() {
		return cdIdentific;
	}
	/**
	 * @param cdIdentific the cdIdentific to set
	 */
	public void setCdIdentific(String cdIdentific) {
		this.cdIdentific = cdIdentific;
	}
	/**
	 * @return the nuPorcentaje
	 */
	public Double getNuPorcentaje() {
		return nuPorcentaje;
	}
	/**
	 * @param nuPorcentaje the nuPorcentaje to set
	 */
	public void setNuPorcentaje(Double nuPorcentaje) {
		this.nuPorcentaje = nuPorcentaje;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @return the nbKit
	 */
	public String getNbKit() {
		return nbKit;
	}
	/**
	 * @param nbKit the nbKit to set
	 */
	public void setNbKit(String nbKit) {
		this.nbKit = nbKit;
	}
	/**
	 * @return the nbPlan
	 */
	public String getNbPlan() {
		return nbPlan;
	}
	/**
	 * @param nbPlan the nbPlan to set
	 */
	public void setNbPlan(String nbPlan) {
		this.nbPlan = nbPlan;
	}
	/**
	 * @return the finicio
	 */
	public Date getFinicio() {
		return finicio;
	}
	/**
	 * @param finicio the finicio to set
	 */
	public void setFinicio(Date finicio) {
		this.finicio = finicio;
	}
	/**
	 * @return the ffin
	 */
	public Date getFfin() {
		return ffin;
	}
	/**
	 * @param ffin the ffin to set
	 */
	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the procesos
	 */
	public List<ProcesoDetalleVO> getProcesos() {
		return procesos;
	}
	/**
	 * @param procesos the procesos to set
	 */
	public void setProcesos(List<ProcesoDetalleVO> procesos) {
		this.procesos = procesos;
	}
	/**
	 * @return the fhDiferencia
	 */
	public String getFhDiferencia() {
		return fhDiferencia;
	}
	/**
	 * @param fhDiferencia the fhDiferencia to set
	 */
	public void setFhDiferencia(String fhDiferencia) {
		this.fhDiferencia = fhDiferencia;
	}
	
	
}
