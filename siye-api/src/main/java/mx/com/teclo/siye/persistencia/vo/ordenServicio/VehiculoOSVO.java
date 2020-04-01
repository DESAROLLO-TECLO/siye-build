package mx.com.teclo.siye.persistencia.vo.ordenServicio;

import mx.com.teclo.siye.persistencia.vo.proceso.ConsecionarioVO;

public class VehiculoOSVO {
	
	private String placa;
	private String cdVIN;
	private String tjtCirculacion;
	private String marca;
	private String subMarca;
	private TpVehiculoVO tpVehiculo;
	private Long cdModelo;
	private ConsecionarioVO concesionaria;
	
	public ConsecionarioVO getConcesionaria() {
		return concesionaria;
	}
	public void setConcesionaria(ConsecionarioVO concesionaria) {
		this.concesionaria = concesionaria;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCdVIN() {
		return cdVIN;
	}
	public void setCdVIN(String cdVIN) {
		this.cdVIN = cdVIN;
	}
	public String getTjtCirculacion() {
		return tjtCirculacion;
	}
	public void setTjtCirculacion(String tjtCirculacion) {
		this.tjtCirculacion = tjtCirculacion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getSubMarca() {
		return subMarca;
	}
	public void setSubMarca(String subMarca) {
		this.subMarca = subMarca;
	}
	public TpVehiculoVO getTpVehiculo() {
		return tpVehiculo;
	}
	public void setTpVehiculo(TpVehiculoVO tpVehiculo) {
		this.tpVehiculo = tpVehiculo;
	}
	public Long getCdModelo() {
		return cdModelo;
	}
	public void setCdModelo(Long cdModelo) {
		this.cdModelo = cdModelo;
	}
	
	
	
	

}
