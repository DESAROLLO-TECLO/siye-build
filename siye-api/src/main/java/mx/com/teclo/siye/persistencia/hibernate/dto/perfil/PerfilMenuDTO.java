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

import mx.com.teclo.siye.persistencia.hibernate.dto.menu.MenuDTO;

@Entity
@Table(name = "TAQ035D_SE_PERFIL_MENU")
public class PerfilMenuDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1433335212995860177L;

	@Id
	@Column(name = "ID_PERFIL_MENU", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idPerfilMenu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable = false)
	private PerfilDTO perfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MENU", nullable = false)
	private MenuDTO menu;

	@Column(name = "ST_ACTIVO", nullable = false, length = 1)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, length = 11)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, length = 11)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdPerfilMenu() {
		return idPerfilMenu;
	}

	public void setIdPerfilMenu(Long idPerfilMenu) {
		this.idPerfilMenu = idPerfilMenu;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}

	public MenuDTO getMenu() {
		return menu;
	}

	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
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

}
