package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

public class OrdenServicioDetalleVO {
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private String nbModuloAtencion;
	private String cdIdentific;
	private Double nuPorcentaje;
	private String estatus;
	private List<ImagenVO> evidencias;
	private List<ImagenVO> incidencias;
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
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the evidencias
	 */
	public List<ImagenVO> getEvidencias() {
		return evidencias;
	}
	/**
	 * @param evidencias the evidencias to set
	 */
	public void setEvidencias(List<ImagenVO> evidencias) {
		this.evidencias = evidencias;
	}
	/**
	 * @return the incidencias
	 */
	public List<ImagenVO> getIncidencias() {
		return incidencias;
	}
	/**
	 * @param incidencias the incidencias to set
	 */
	public void setIncidencias(List<ImagenVO> incidencias) {
		this.incidencias = incidencias;
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
	
	
}
