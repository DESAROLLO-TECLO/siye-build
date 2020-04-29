package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class TipoVehiculoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9072220977911507727L;
	
	
	private Long idTipoVehiculo;
	private String cdTipoVehiculo;
	private String nbTipoVehiculo;
	private Long nuOrden;
	private Boolean stActivo;
	private Long stTipoVehiculo;
	
	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}
	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}
	public String getCdTipoVehiculo() {
		return cdTipoVehiculo;
	}
	public void setCdTipoVehiculo(String cdTipoVehiculo) {
		this.cdTipoVehiculo = cdTipoVehiculo;
	}
	public String getNbTipoVehiculo() {
		return nbTipoVehiculo;
	}
	public void setNbTipoVehiculo(String nbTipoVehiculo) {
		this.nbTipoVehiculo = nbTipoVehiculo;
	}
	public Long getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stAtivo) {
		this.stActivo = stAtivo;
	}
	public Long getStTipoVehiculo() {
		return stTipoVehiculo;
	}
	public void setStTipoVehiculo(Long stTipoVehiculo) {
		this.stTipoVehiculo = stTipoVehiculo;
	}
	
	
	

}
