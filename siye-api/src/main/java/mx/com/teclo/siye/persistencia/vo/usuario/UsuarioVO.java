package mx.com.teclo.siye.persistencia.vo.usuario;

import java.util.Arrays;
import java.util.Date;

import mx.com.teclo.siye.persistencia.vo.perfil.PerfilVO;


public class UsuarioVO {

	private Long idUsuario;
	private String cdUsuario;
	private String nbContrasenia;
	private String nbUsuario;
	private String nbApaterno;
	private String nbAmaterno;
	private String nbEmail;
	private Long nuTelefono;
	private byte[] lbFoto;
	private Integer stActivo;
	private PerfilVO perfil;
	private Date fhCreacion;
	private Date fhModificacion;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getNbContrasenia() {
		return nbContrasenia;
	}

	public void setNbContrasenia(String nbContrasenia) {
		this.nbContrasenia = nbContrasenia;
	}

	public String getNbUsuario() {
		return nbUsuario;
	}

	public void setNbUsuario(String nbUsuario) {
		this.nbUsuario = nbUsuario;
	}

	public String getNbApaterno() {
		return nbApaterno;
	}

	public void setNbApaterno(String nbApaterno) {
		this.nbApaterno = nbApaterno;
	}

	public String getNbAmaterno() {
		return nbAmaterno;
	}

	public void setNbAmaterno(String nbAmaterno) {
		this.nbAmaterno = nbAmaterno;
	}

	public String getNbEmail() {
		return nbEmail;
	}

	public void setNbEmail(String nbEmail) {
		this.nbEmail = nbEmail;
	}

	public Long getNuTelefono() {
		return nuTelefono;
	}

	public void setNuTelefono(Long nuTelefono) {
		this.nuTelefono = nuTelefono;
	}

	public byte[] getLbFoto() {
		return lbFoto;
	}

	public void setLbFoto(byte[] lbFoto) {
		this.lbFoto = lbFoto;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	@Override
	public String toString() {
		return "UsuarioVO [idUsuario=" + idUsuario + ", cdUsuario=" + cdUsuario + ", nbContrasenia=" + nbContrasenia
				+ ", nbUsuario=" + nbUsuario + ", nbApaterno=" + nbApaterno + ", nbAmaterno=" + nbAmaterno
				+ ", nbEmail=" + nbEmail + ", nuTelefono=" + nuTelefono + ", lbFoto=" + Arrays.toString(lbFoto)
				+ ", stActivo=" + stActivo + ", perfil=" + perfil + ", fhCreacion=" + fhCreacion + ", fhModificacion="
				+ fhModificacion + "]";
	}

}
