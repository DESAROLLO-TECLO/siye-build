package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TIE028C_IE_TIPO_VEHICULO")
public class TipoVehiculoDTO implements Serializable {
	
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@Column(name = "ID_TIPO_VEHICULO", unique = true, nullable = false)
	private Long idTipoVehiculo;
	@Column(name = "CD_TIPO_VEHICULO")
	private String cdTipoVehiculo;
	@Column(name = "NB_TIPO_VEHICULO")
	private String nbTipoVehiculo;
	@Column(name = "NU_ORDEN")
	private Long nuOrden;
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	@Column(name = "FH_CREACION")	
	private Date fhCreacion;
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	@Column(name = "FH_MODIFICACION")	
	private Date fhModificacion;
	@Column(name = "ST_TIPO_VEHICULO")
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
	public Long getStTipoVehiculo() {
		return stTipoVehiculo;
	}
	public void setStTipoVehiculo(Long stTipoVehiculo) {
		this.stTipoVehiculo = stTipoVehiculo;
	}
	
	
	
	
	


}
