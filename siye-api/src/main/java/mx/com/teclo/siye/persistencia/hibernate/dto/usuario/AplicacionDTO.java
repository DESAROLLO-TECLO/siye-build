package mx.com.teclo.siye.persistencia.hibernate.dto.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ028C_SE_APLICACIONES")
public class AplicacionDTO implements Serializable {

	private static final long serialVersionUID = 4814258386727191940L;

	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_APLICACION", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idAplicacion;

	@Column(name = "CD_APLICACION", nullable = false, length = 15)
	private String cdAplicacion;

	@Column(name = "NB_APLICACION", nullable = false, length = 100)
	private String nbAplicacion;

	@Column(name = "TX_APLICACION", nullable = false, length = 250)
	private String txAplicacion;

	@Column(name = "NU_EXPIRACION", nullable = true, precision = 11, scale = 0)
	private Long nuExpiracion;

	@Column(name = "NU_INACTIVIDAD", nullable = true, precision = 11, scale = 0)
	private Long nuInactividad;

	@Column(name = "NB_ZONA", nullable = false, length = 100)
	private String nbZona;

	@Column(name = "NB_CLIENTE", nullable = false, length = 100)
	private String nbCliente;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	@Column(name = "CD_AMBIENTE", nullable = false)
	private String ambiente;

	public Long getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getCdAplicacion() {
		return cdAplicacion;
	}

	public void setCdAplicacion(String cdAplicacion) {
		this.cdAplicacion = cdAplicacion;
	}

	public String getNbAplicacion() {
		return nbAplicacion;
	}

	public void setNbAplicacion(String nbAplicacion) {
		this.nbAplicacion = nbAplicacion;
	}

	public String getTxAplicacion() {
		return txAplicacion;
	}

	public void setTxAplicacion(String txAplicacion) {
		this.txAplicacion = txAplicacion;
	}

	public Long getNuExpiracion() {
		return nuExpiracion;
	}

	public void setNuExpiracion(Long nuExpiracion) {
		this.nuExpiracion = nuExpiracion;
	}

	public Long getNuInactividad() {
		return nuInactividad;
	}

	public void setNuInactividad(Long nuInactividad) {
		this.nuInactividad = nuInactividad;
	}

	public String getNbZona() {
		return nbZona;
	}

	public void setNbZona(String nbZona) {
		this.nbZona = nbZona;
	}

	public String getNbCliente() {
		return nbCliente;
	}

	public void setNbCliente(String nbCliente) {
		this.nbCliente = nbCliente;
	}

	public Integer getStActivo() {
		return this.stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return this.idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return this.fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return this.idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return this.fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	@Override
	public String toString() {
		return "AplicacionDTO [idAplicacion=" + idAplicacion + ", cdAplicacion=" + cdAplicacion + ", nbAplicacion="
				+ nbAplicacion + ", txAplicacion=" + txAplicacion + ", nuExpiracion=" + nuExpiracion
				+ ", nuInactividad=" + nuInactividad + ", nbZona=" + nbZona + ", nbCliente=" + nbCliente + ", stActivo="
				+ stActivo + ", idUsrCreacion=" + idUsrCreacion + ", fhCreacion=" + fhCreacion + ", idUsrModifica="
				+ idUsrModifica + ", fhModificacion=" + fhModificacion + ", ambiente=" + ambiente + "]";
	}

}
