package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

public class ReporteExcelVO {

	private String columnas;
	private String centroInstalacion;
	private String fechaInicio;
	private String fechaFin;
	private String nivel;
	private List<SeguimientoOrdenServicioVO> nivelGeneral;
	private List<OrdenServcioDetalleVO> nivelDetalle;
	private List<DetalleIncidenciaVO> nivelIncidencia;
	
	
	/**
	 * @return the columnas
	 */
	public String getColumnas() {
		return columnas;
	}
	/**
	 * @param columnas the columnas to set
	 */
	public void setColumnas(String columnas) {
		this.columnas = columnas;
	}
	/**
	 * @return the centroInstalacion
	 */
	public String getCentroInstalacion() {
		return centroInstalacion;
	}
	/**
	 * @param centroInstalacion the centroInstalacion to set
	 */
	public void setCentroInstalacion(String centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the nivelGeneral
	 */
	public List<SeguimientoOrdenServicioVO> getNivelGeneral() {
		return nivelGeneral;
	}
	/**
	 * @param nivelGeneral the nivelGeneral to set
	 */
	public void setNivelGeneral(List<SeguimientoOrdenServicioVO> nivelGeneral) {
		this.nivelGeneral = nivelGeneral;
	}
	/**
	 * @return the nivelDetalle
	 */
	public List<OrdenServcioDetalleVO> getNivelDetalle() {
		return nivelDetalle;
	}
	/**
	 * @param nivelDetalle the nivelDetalle to set
	 */
	public void setNivelDetalle(List<OrdenServcioDetalleVO> nivelDetalle) {
		this.nivelDetalle = nivelDetalle;
	}
	/**
	 * @return the nivelIncidencia
	 */
	public List<DetalleIncidenciaVO> getNivelIncidencia() {
		return nivelIncidencia;
	}
	/**
	 * @param nivelIncidencia the nivelIncidencia to set
	 */
	public void setNivelIncidencia(List<DetalleIncidenciaVO> nivelIncidencia) {
		this.nivelIncidencia = nivelIncidencia;
	}

}
