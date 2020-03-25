package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

/**
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class VehiculoVO {
	private Long idVehiculo;
	private String cdPlacaVehiculo;
	private String cdVin;
	private String cdTarjetaCirculacion;
	private Long idTipoVehiculo;
	private String nbMarca;
	private String nbSubMarca;
	private String cdModelo;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
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
	public String getCdTarjetaCirculacion() {
		return cdTarjetaCirculacion;
	}
	public void setCdTarjetaCirculacion(String cdTarjetaCirculacion) {
		this.cdTarjetaCirculacion = cdTarjetaCirculacion;
	}
	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}
	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
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
	public String getCdModelo() {
		return cdModelo;
	}
	public void setCdModelo(String cdModelo) {
		this.cdModelo = cdModelo;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	public Long getIdConcesion() {
		return idConcesion;
	}
	public void setIdConcesion(Long idConcesion) {
		this.idConcesion = idConcesion;
	}
	
	
	
}
