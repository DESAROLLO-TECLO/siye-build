package mx.com.teclo.siye.persistencia.vo.perfil;

import java.util.Date;

import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;


public class PerfilUsuarioVO {

	private Long idPerfilUsr;
	private UsuarioVO usuario;
	private PerfilVO perfil;
	private Integer stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;

	public Long getIdPerfilUsr() {
		return idPerfilUsr;
	}

	public void setIdPerfilUsr(Long idPerfilUsr) {
		this.idPerfilUsr = idPerfilUsr;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
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
