package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TIE027C_IE_VEHICULO")
public class VehiculoDTO implements Serializable {
	
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@Column(name = "ID_VEHICULO", unique = true, nullable = false)
	private Long idVehiculo;
	
	@Column(name = "CD_PLACA_VEHICULO")
	private String cdPlacaVehiculo;
	
	@Column(name = "CD_VIN")
	private String cdVin;
	
	@Column(name = "CD_TARJETA_CIRCULACION")
	private String cdTarjetaDeCirculacion;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_VEHICULO", referencedColumnName="ID_TIPO_VEHICULO", insertable=false, updatable=false)
	private TipoVehiculoDTO tipoVehiculo;
	
	@Column(name = "NB_MARCA")
	private String nbMarca;
	
	@Column(name = "NB_SUB_MARCA")
	private String nbSubMarca;
	
	@Column(name = "CD_MODELO")
	private Long cdModelo;
	
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
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CONCESION", referencedColumnName="ID_CONCESION", insertable=false, updatable=false)
	private ConsecionarioDTO consecionario;

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

	public TipoVehiculoDTO getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoDTO tipoVehiculo) {
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

	public Long getCdModelo() {
		return cdModelo;
	}

	public void setCdModelo(Long cdModelo) {
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

	public ConsecionarioDTO getConsecionario() {
		return consecionario;
	}

	public void setConsecionario(ConsecionarioDTO consecionario) {
		this.consecionario = consecionario;
	}
	

	

	


}
