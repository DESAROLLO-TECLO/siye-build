package mx.com.teclo.siye.persistencia.hibernate.dto.perfil;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;


@Entity
@Table(name = "TAQ026C_SE_PERFILES")
public class PerfilDTO implements Serializable {

	private static final long serialVersionUID = 4162120001095351651L;

	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_PERFIL", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idPerfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APLICACION", nullable = false)
	private AplicacionDTO aplicacion;

	@Column(name = "CD_PERFIL", nullable = false, length = 15)
	private String cdPerfil;

	@Column(name = "NB_PERFIL", nullable = false, length = 100)
	private String nbPerfil;

	@Column(name = "TX_PERFIL", nullable = false, length = 250)
	private String txPerfil;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", length = 7)
	private Date fhModificacion;

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public String getNbPerfil() {
		return nbPerfil;
	}

	public void setNbPerfil(String nbPerfil) {
		this.nbPerfil = nbPerfil;
	}

	public String getTxPerfil() {
		return txPerfil;
	}

	public void setTxPerfil(String txPerfil) {
		this.txPerfil = txPerfil;
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

	@Override
	public String toString() {
		return "PerfilDTO [idPerfil=" + idPerfil + ", aplicacion=" + aplicacion + ", cdPerfil=" + cdPerfil
				+ ", nbPerfil=" + nbPerfil + ", txPerfil=" + txPerfil + ", stActivo=" + stActivo + ", idUsrCreacion="
				+ idUsrCreacion + ", fhCreacion=" + fhCreacion + ", idUsrModifica=" + idUsrModifica
				+ ", fhModificacion=" + fhModificacion + "]";
	}

}
