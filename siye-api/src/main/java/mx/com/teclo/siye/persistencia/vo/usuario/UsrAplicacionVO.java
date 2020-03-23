package mx.com.teclo.siye.persistencia.vo.usuario;

import java.util.Date;

public class UsrAplicacionVO {

	private Long idUsrApli;
	private UsuarioVO usuario;
	private AplicacionVO aplicacion;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;

	public Long getIdUsrApli() {
		return idUsrApli;
	}

	public void setIdUsrApli(Long idUsrApli) {
		this.idUsrApli = idUsrApli;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public AplicacionVO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionVO aplicacion) {
		this.aplicacion = aplicacion;
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

}
