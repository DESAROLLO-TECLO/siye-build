package mx.com.teclo.siye.persistencia.vo.proceso;



public class OrdenServicioProcesoVO {
	
	private Long idOrdenServicio;
    private String cdOrdenServicio;
    private String transportista;
    private VehiculoProcesoVO vehiculo;
    private CentroInstalacionProcesoVO centroInstalacion;
    private KitInstalacionProcesoVO kitInstalacion;
    
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
	public VehiculoProcesoVO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoProcesoVO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public CentroInstalacionProcesoVO getCentroInstalacion() {
		return centroInstalacion;
	}
	public void setCentroInstalacion(CentroInstalacionProcesoVO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	public KitInstalacionProcesoVO getKitInstalacion() {
		return kitInstalacion;
	}
	public void setKitInstalacion(KitInstalacionProcesoVO kitInstalacion) {
		this.kitInstalacion = kitInstalacion;
	}
    
    
    
    

}
