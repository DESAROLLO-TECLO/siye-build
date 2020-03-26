package mx.com.teclo.siye.persistencia.vo.filtro;

public class FiltroVehiculoVO {
	private String cdPlacaVehiculo;
	private String cdVin;
	private Boolean stActivo;

	public String getCdPlacaVehiculo() {
		return cdPlacaVehiculo;
	}

	public void setCdPlacaVehiculo(String cdPlacaVehiculo) {
		this.cdPlacaVehiculo = cdPlacaVehiculo;
	}

	public String getCdVin() {
		return cdVin;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public void setCdVin(String cdVin) {
		this.cdVin = cdVin;
	}

}
