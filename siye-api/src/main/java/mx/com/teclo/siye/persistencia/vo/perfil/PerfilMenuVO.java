package mx.com.teclo.siye.persistencia.vo.perfil;

import java.util.Date;

import mx.com.teclo.siye.persistencia.vo.menu.MenuVO;


 
public class PerfilMenuVO {

	private Long idPerfilMenu;
	private PerfilVO perfil;
	private MenuVO menu;
	private Integer stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;

	public Long getIdPerfilMenu() {
		return idPerfilMenu;
	}

	public void setIdPerfilMenu(Long idPerfilMenu) {
		this.idPerfilMenu = idPerfilMenu;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public MenuVO getMenu() {
		return menu;
	}

	public void setMenu(MenuVO menu) {
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
