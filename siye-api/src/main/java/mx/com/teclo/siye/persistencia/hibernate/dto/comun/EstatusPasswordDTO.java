package mx.com.teclo.siye.persistencia.hibernate.dto.comun;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIE024C_ST_PASSWORD", catalog = "")
public class EstatusPasswordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8519855087866314270L;
	
	@Id
	@SequenceGenerator(name = "sqie024cStPassqord", sequenceName="SQIE024C_ST_PASSWORD", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqie024cStPassqord")
	@Column(name = "ID_PASSWORD", unique = true, nullable = false, precision = 4, scale = 0)
	private Integer idPassword;
	
	@Column(name = "CD_ST_PASSWORD", nullable = false, length = 20)
	private String CD_ST_PASSWORD;
	
	@Column(name = "NB_ST_PASSWORD", nullable = false, length = 100)
	private String NB_ST_PASSWORD;
	
	@Column(name = "TX_ST_PASSWORD", nullable = true, length = 250)
	private String nbApematConductor;
	
	@Column(name = "CD_COLOR", nullable = true, length = 15)
	private String cdColor;
	
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

	public Integer getIdPassword() {
		return idPassword;
	}

	public void setIdPassword(Integer idPassword) {
		this.idPassword = idPassword;
	}

	public String getCD_ST_PASSWORD() {
		return CD_ST_PASSWORD;
	}

	public void setCD_ST_PASSWORD(String cD_ST_PASSWORD) {
		CD_ST_PASSWORD = cD_ST_PASSWORD;
	}

	public String getNB_ST_PASSWORD() {
		return NB_ST_PASSWORD;
	}

	public void setNB_ST_PASSWORD(String nB_ST_PASSWORD) {
		NB_ST_PASSWORD = nB_ST_PASSWORD;
	}

	public String getNbApematConductor() {
		return nbApematConductor;
	}

	public void setNbApematConductor(String nbApematConductor) {
		this.nbApematConductor = nbApematConductor;
	}

	public String getCdColor() {
		return cdColor;
	}

	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
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
