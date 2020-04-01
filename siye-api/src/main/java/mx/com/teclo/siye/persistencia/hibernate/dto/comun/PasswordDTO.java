package mx.com.teclo.siye.persistencia.hibernate.dto.comun;

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
@Table(name = "TIE020D_PASSWORD", catalog = "")
public class PasswordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352827025195474700L;
	@Id
//	@SequenceGenerator(name = "sqie044cIEConductor", sequenceName="SQIE044C_IE_CONDUCTOR", allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqie044cIEConductor")
	@Column(name = "ID_PASSWORD", unique = true, nullable = false, precision = 7, scale = 0)
	private Integer idPassword;
	
	@Column(name = "TX_VALOR", nullable = false, length = 300)
	private String txValor;
	
	@Column(name = "FH_VIGENCIA_INI", nullable = false)
	private Date fhVigenciaIni;

	@Column(name = "FH_VIGENCIA_FIN", nullable = false)
	private Date fhVigenciaFin;
	
	@Column(name = "ST_ACTUAL", nullable = false, precision = 1, scale = 0)
	private Boolean stActual;
	
	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 7, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 7, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false)
	private Date fhModificacion;
	
	@JoinColumn(name = "ID_ST_PASSWORD", referencedColumnName = "ID_PASSWORD", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EstatusPasswordDTO stPassword;

	public Integer getIdPassword() {
		return idPassword;
	}

	public void setIdPassword(Integer idPassword) {
		this.idPassword = idPassword;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

	public Date getFhVigenciaIni() {
		return fhVigenciaIni;
	}

	public void setFhVigenciaIni(Date fhVigenciaIni) {
		this.fhVigenciaIni = fhVigenciaIni;
	}

	public Date getFhVigenciaFin() {
		return fhVigenciaFin;
	}

	public void setFhVigenciaFin(Date fhVigenciaFin) {
		this.fhVigenciaFin = fhVigenciaFin;
	}

	public Boolean getStActual() {
		return stActual;
	}

	public void setStActual(Boolean stActual) {
		this.stActual = stActual;
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

	public EstatusPasswordDTO getStPassword() {
		return stPassword;
	}

	public void setStPassword(EstatusPasswordDTO stPassword) {
		this.stPassword = stPassword;
	}
}
