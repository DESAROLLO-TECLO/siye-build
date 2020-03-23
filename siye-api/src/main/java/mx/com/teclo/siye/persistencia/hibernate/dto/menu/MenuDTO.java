package mx.com.teclo.siye.persistencia.hibernate.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilMenuDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;

@Entity
@Table(name = "TAQ030C_SE_MENUS")
public class MenuDTO implements Serializable {

	private static final long serialVersionUID = 4851264393900433236L;

	@Id
	@Column(name = "ID_MENU", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idMenu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APLICACION", nullable = false)
	private AplicacionDTO aplicacion;

	@Column(name = "NB_MENU", nullable = false, length = 100)
	private String nbMenu;

	@Column(name = "TX_MENU", nullable = false, length = 250)
	private String txMenu;

	@Column(name = "TX_MENU_EN", nullable = false, length = 250)
	private String txMenuEn;

	@Column(name = "NU_MENU_SUPERIOR", nullable = false, length = 11)
	private Long nuMenuSuperior;

	@Column(name = "NB_MENU_URL", length = 100)
	private String nbMenuUrl;

	@Column(name = "NU_MENU_ORDEN", nullable = false, length = 11)
	private Long nuMenuOrden;

	@Column(name = "NB_MENU_URI", nullable = false, length = 100)
	private String nbMenuUri;

	@Column(name = "NB_MENU_ICONO", length = 100)
	private String nbMenuIcono;

	@Column(name = "ST_ACTIVO", nullable = false, length = 1)
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, length = 11)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, length = 11)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	private List<PerfilMenuDTO> perfiles = new ArrayList<PerfilMenuDTO>(0);

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public String getNbMenu() {
		return nbMenu;
	}

	public void setNbMenu(String nbMenu) {
		this.nbMenu = nbMenu;
	}

	public String getTxMenu() {
		return txMenu;
	}

	public void setTxMenu(String txMenu) {
		this.txMenu = txMenu;
	}

	public String getTxMenuEn() {
		return txMenuEn;
	}

	public void setTxMenuEn(String txMenuEn) {
		this.txMenuEn = txMenuEn;
	}

	public Long getNuMenuSuperior() {
		return nuMenuSuperior;
	}

	public void setNuMenuSuperior(Long nuMenuSuperior) {
		this.nuMenuSuperior = nuMenuSuperior;
	}

	public String getNbMenuUrl() {
		return nbMenuUrl;
	}

	public void setNbMenuUrl(String nbMenuUrl) {
		this.nbMenuUrl = nbMenuUrl;
	}

	public Long getNuMenuOrden() {
		return nuMenuOrden;
	}

	public void setNuMenuOrden(Long nuMenuOrden) {
		this.nuMenuOrden = nuMenuOrden;
	}

	public String getNbMenuUri() {
		return nbMenuUri;
	}

	public void setNbMenuUri(String nbMenuUri) {
		this.nbMenuUri = nbMenuUri;
	}

	public String getNbMenuIcono() {
		return nbMenuIcono;
	}

	public void setNbMenuIcono(String nbMenuIcono) {
		this.nbMenuIcono = nbMenuIcono;
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

	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = aplicacion;
	}

	public List<PerfilMenuDTO> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<PerfilMenuDTO> perfiles) {
		this.perfiles = perfiles;
	}

	@Override
	public String toString() {
		return "MenuDTO [idMenu=" + idMenu + ", aplicacion=" + aplicacion + ", nbMenu=" + nbMenu + ", txMenu=" + txMenu
				+ ", txMenuEn=" + txMenuEn + ", nuMenuSuperior=" + nuMenuSuperior + ", nbMenuUrl=" + nbMenuUrl
				+ ", nuMenuOrden=" + nuMenuOrden + ", nbMenuUri=" + nbMenuUri + ", nbMenuIcono=" + nbMenuIcono
				+ ", stActivo=" + stActivo + ", idUsrCreacion=" + idUsrCreacion + ", fhCreacion=" + fhCreacion
				+ ", idUsrModifica=" + idUsrModifica + ", fhModificacion=" + fhModificacion + ", perfiles=" + perfiles
				+ "]";
	}

}
