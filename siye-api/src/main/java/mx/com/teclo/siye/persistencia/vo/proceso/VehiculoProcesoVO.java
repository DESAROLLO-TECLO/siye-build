package mx.com.teclo.siye.persistencia.vo.proceso;


public class VehiculoProcesoVO {
	
	
	private Long idVehiculo;
	private String cdPlacaVehiculo;
	private String cdVin;
	private String cdTarjetaDeCirculacion;
	private TipoVehiculoProcesoVO tipoVehiculo;
	private ConsecionarioProcesoVO consecionario;
	
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getCdPlacaVehiculo() {
		return cdPlacaVehiculo;
	}
	public void setCdPlacaVehiculo(String cdPlacaVehiculo) {
		this.cdPlacaVehiculo = cdPlacaVehiculo;
	}
	public String getCdVin() {
		return cdVin;
	}
	public void setCdVin(String cdVin) {
		this.cdVin = cdVin;
	}
	public String getCdTarjetaDeCirculacion() {
		return cdTarjetaDeCirculacion;
	}
	public void setCdTarjetaDeCirculacion(String cdTarjetaDeCirculacion) {
		this.cdTarjetaDeCirculacion = cdTarjetaDeCirculacion;
	}
	public TipoVehiculoProcesoVO getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(TipoVehiculoProcesoVO tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public ConsecionarioProcesoVO getConsecionario() {
		return consecionario;
	}
	public void setConsecionario(ConsecionarioProcesoVO consecionario) {
		this.consecionario = consecionario;
	}
	
	

}
