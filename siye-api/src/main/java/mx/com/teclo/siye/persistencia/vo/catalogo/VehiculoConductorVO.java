package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;


import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;

public class VehiculoConductorVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5432701010436407705L;
	
	private Long idVehiculoConductor;
	private VehiculoVO vehiculo;
	private ConductorVO conductor;
	private Integer nuOrden;
    private Boolean stActivo;
    
	public Long getIdVehiculoConductor() {
		return idVehiculoConductor;
	}
	public void setIdVehiculoConductor(Long idVehiculoConductor) {
		this.idVehiculoConductor = idVehiculoConductor;
	}
	public VehiculoVO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoVO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public ConductorVO getConductor() {
		return conductor;
	}
	public void setConductor(ConductorVO conductor) {
		this.conductor = conductor;
	}
	public Integer getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
    
    

}
