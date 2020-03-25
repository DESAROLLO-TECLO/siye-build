package mx.com.teclo.siye.persistencia.vo.proceso;



public class OrdenServicioProcesoVO {
	
	private Long idOrdenServicio;
    private String cdOrdenServicio;
    private String transportista;
    private VehiculoVO vehiculo;
    private CentroInstalacionVO centroInstalacion;
    private KitInstalacionVO kitInstalacion;
    
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
	public String getTransportista() {
		return transportista;
	}
	public void setTransportista(String transportista) {
		this.transportista = transportista;
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
    
    
    
    

}
