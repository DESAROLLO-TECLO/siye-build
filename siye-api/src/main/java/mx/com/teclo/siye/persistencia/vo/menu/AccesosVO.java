package mx.com.teclo.siye.persistencia.vo.menu;

import java.util.List;

public class AccesosVO {
	private Long idPerfil;
	private List<MenuVO> autorizados;
	private List<MenuVO> noAutorizados;
	
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public List<MenuVO> getAutorizados() {
		return autorizados;
	}
	public void setAutorizados(List<MenuVO> autorizados) {
		this.autorizados = autorizados;
	}
	public List<MenuVO> getNoAutorizados() {
		return noAutorizados;
	}
	public void setNoAutorizados(List<MenuVO> noAutorizados) {
		this.noAutorizados = noAutorizados;
	}
}
