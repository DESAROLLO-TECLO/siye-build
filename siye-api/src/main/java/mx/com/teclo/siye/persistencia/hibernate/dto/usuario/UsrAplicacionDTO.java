package mx.com.teclo.siye.persistencia.hibernate.dto.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ029D_SE_USR_APLICACION")
public class UsrAplicacionDTO implements Serializable {
 
	private static final long serialVersionUID = -7611532874828006635L;

	@Id
	@SequenceGenerator(name = "aq029dSeq", sequenceName="SQAQ029D_SE_USR_APLICACION", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aq029dSeq")
	@Column(name = "ID_USR_APLI", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idUsrApli;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private UsuarioDTO usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APLICACION", nullable = false)
	private AplicacionDTO aplicacion;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", length = 7)
	private Date fhModificacion;

	public Long getIdUsrApli() {
		return idUsrApli;
	}

	public void setIdUsrApli(Long idUsrApli) {
		this.idUsrApli = idUsrApli;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Boolean getStActivo() {
		return this.stActivo;
	}

	public void setStActivo(Boolean stActivo) {
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

}
