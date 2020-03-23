package mx.com.teclo.siye.persistencia.vo.menu;

import mx.com.teclo.siye.persistencia.vo.usuario.AplicacionVO;

public class MenuVO  {

	private Long idMenu;
	private AplicacionVO aplicacion;
	private String nbMenu;
	private String txMenu;
	private String txMenuEn;
	private Long nuMenuSuperior;
	private String nbMenuUrl;
	private Long nuMenuOrden;
	private String nbMenuUri;
	private String nbMenuIcono;
	private Integer stActivo;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public AplicacionVO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionVO aplicacion) {
		this.aplicacion = aplicacion;
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

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

//	public List<PerfilMenuVO> getPerfiles() {
//		return perfiles;
//	}
//
//	public void setPerfiles(List<PerfilMenuVO> perfiles) {
//		this.perfiles = perfiles;
//	}

}
