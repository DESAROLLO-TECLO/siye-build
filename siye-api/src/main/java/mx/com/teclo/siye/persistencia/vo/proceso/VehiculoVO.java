package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;

import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;

public class VehiculoVO implements Serializable{
	
	private static final long serialVersionUID = 5026799603021950531L;
	
	private Long idVehiculo;
	private String cdPlacaVehiculo;
	private String cdVin;
	private String cdTarjetaDeCirculacion;
	private TipoVehiculoVO tipoVehiculo;
	private String nbMarca;
	private String nbSubMarca;
	private Long cdModelo;
	private Boolean stActivo;
	private Long idConcesion;
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

	public TipoVehiculoVO getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(TipoVehiculoVO tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getNbMarca() {
		return nbMarca;
	}
	public void setNbMarca(String nbMarca) {
		this.nbMarca = nbMarca;
	}
	public String getNbSubMarca() {
		return nbSubMarca;
	}
	public void setNbSubMarca(String nbSubMarca) {
		this.nbSubMarca = nbSubMarca;
	}

	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public Long getIdConcesion() {
		return idConcesion;
	}
	public void setIdConcesion(Long idConcesion) {
		this.idConcesion = idConcesion;
	}
	public String getCdTarjetaDeCirculacion() {
		return cdTarjetaDeCirculacion;
	}
	public void setCdTarjetaDeCirculacion(String cdTarjetaDeCirculacion) {
		this.cdTarjetaDeCirculacion = cdTarjetaDeCirculacion;
	}
	public Long getCdModelo() {
		return cdModelo;
	}
	public void setCdModelo(Long cdModelo) {
		this.cdModelo = cdModelo;
	}

	
	
	
}
