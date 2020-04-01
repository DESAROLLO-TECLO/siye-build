package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

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
@Table(name = "TIE048C_IE_ST_SEGUIMIENTO")
public class StSeguimientoDTO implements Serializable {
	
	private static final long serialVersionUID = 1666800297557578438L;

	@Id
	@SequenceGenerator(name = "sqStSeguimiento", sequenceName = "SQIE048C_IE_ST_SEGUIM", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqStSeguimiento")
	@Column(name = "ID_ST_SEGUIMIENTO", unique = true, nullable = false)
	private Long idStSeguimiento;
	
	@Column(name = "CD_ST_SEGUIMIENTO")
	private String cdStSeguimiento;
	
	@Column(name = "NB_ST_SEGUIMIENTO")
	private String nbStSeguimiento;
	
	@Column(name = "CD_COLOR")
	private String cdColor;
	
	@Column(name = "NU_ORDEN")
	private Long nuOrden;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION")	
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")	
	private Date fhModificacion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TIPO_SEGUIMIENTO", referencedColumnName="ID_TIPO_SEGUIMIENTO")
	private TipoSeguimientoDTO tipoSeguimiento;
	
	

	public Long getIdStSeguimiento() {
		return idStSeguimiento;
	}

	public void setIdStSeguimiento(Long idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
	}

	public String getCdStSeguimiento() {
		return cdStSeguimiento;
	}

	public void setCdStSeguimiento(String cdStSeguimiento) {
		this.cdStSeguimiento = cdStSeguimiento;
	}

	public String getNbStSeguimiento() {
		return nbStSeguimiento;
	}

	public void setNbStSeguimiento(String nbStSeguimiento) {
		this.nbStSeguimiento = nbStSeguimiento;
	}

	public String getCdColor() {
		return cdColor;
	}

	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
