package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

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
@Table(name = "TIE049C_TIPO_SEGUIMIENTO")
public class TipoSeguimientoDTO implements Serializable {
	
	private static final long serialVersionUID = 3573326788851789799L;


	@Id
	@SequenceGenerator(name = "sqTipoSeguimiento", sequenceName = "SQIE049C_TIPO_SEGUIMI", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqTipoSeguimiento")
	@Column(name = "ID_TIPO_SEGUIMIENTO", unique = true, nullable = false)
	private Long idTipoSeguimiento;
	
	@Column(name = "CD_TIPO_SEGUIMIENTO")
	private String cdTpSeguimiento;
	
	@Column(name = "NB_TIPO_SEGUIMIENTO")
	private String nbTpSeguimiento;
	
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

	public Long getIdTipoSeguimiento() {
		return idTipoSeguimiento;
	}

	public void setIdTipoSeguimiento(Long idTipoSeguimiento) {
		this.idTipoSeguimiento = idTipoSeguimiento;
	}

	public String getCdTpSeguimiento() {
		return cdTpSeguimiento;
	}

	public void setCdTpSeguimiento(String cdTpSeguimiento) {
		this.cdTpSeguimiento = cdTpSeguimiento;
	}

	public String getNbTpSeguimiento() {
		return nbTpSeguimiento;
	}

	public void setNbTpSeguimiento(String nbTpSeguimiento) {
		this.nbTpSeguimiento = nbTpSeguimiento;
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
