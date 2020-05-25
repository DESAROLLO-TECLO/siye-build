package mx.com.teclo.siye.persistencia.vo.ordenServicio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstDispVO;



public class OrdenServiVO implements Serializable{
	
	private static final long serialVersionUID = -2154104909635252655L;

	private String cdOrden;
	private Long centroI;
	private Long plan;
	private Long tpKit;
	private String cdKitIntalacion;
	private List<KitInstDispVO> kitInstalacionVO;
	private VehiculoOSVO vehiculoVO;
	private Long idProcesoActual;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm",timezone = "America/Mexico_City")
	private Date fhCita;
	private Long idIncidencia;
	private List<ConductorVO> conductores;
	
	public VehiculoOSVO getVehiculoVO() {
		return vehiculoVO;
	}
	public void setVehiculoVO(VehiculoOSVO vehiculoVO) {
		this.vehiculoVO = vehiculoVO;
	}
	public String getCdOrden() {
		return cdOrden;
	}
	public void setCdOrden(String cdOrden) {
		this.cdOrden = cdOrden;
	}
	public Long getCentroI() {
		return centroI;
	}
	public void setCentroI(Long centroI) {
		this.centroI = centroI;
	}
	public Long getPlan() {
		return plan;
	}
	public void setPlan(Long plan) {
		this.plan = plan;
	}
	public Long getTpKit() {
		return tpKit;
	}
	public void setTpKit(Long tpKit) {
		this.tpKit = tpKit;
	}
	public String getCdKitIntalacion() {
		return cdKitIntalacion;
	}
	public void setCdKitIntalacion(String cdKitIntalacion) {
		this.cdKitIntalacion = cdKitIntalacion;
	}
	public List<KitInstDispVO> getKitInstalacionVO() {
		return kitInstalacionVO;
	}
	public void setKitInstalacionVO(List<KitInstDispVO> kitInstalacionVO) {
		this.kitInstalacionVO = kitInstalacionVO;
	}
	public Long getIdProcesoActual() {
		return idProcesoActual;
	}
	public void setIdProcesoActual(Long idProcesoActual) {
		this.idProcesoActual = idProcesoActual;
	}
	public Date getFhCita() {
		return fhCita;
	}
	public void setFhCita(Date fhCita) {
		this.fhCita = fhCita;
	}
	public Long getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	/**
	 * @return the conductores
	 */
	public List<ConductorVO> getConductores() {
		return conductores;
	}
	/**
	 * @param conductores the conductores to set
	 */
	public void setConductores(List<ConductorVO> conductores) {
		this.conductores = conductores;
	}
}
