package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TIE029C_IE_CENTROS_INSTALACION")
public class CentroInstalacionDTO implements Serializable {
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@SequenceGenerator(name = "sqIE029cIECentrosI", sequenceName="SQIE029C_IE_CENTROS_I", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqIE029cIECentrosI")
	@Column(name = "ID_CENTRO_INSTALACION", unique = true, nullable = false, precision = 4, scale = 0)
	private Long idCentroInstalacion;
	@Column(name = "CD_CENTRO_INSTALACION", nullable = false, length = 15)
	private String cdCentroInstalacion;
	@Column(name = "NB_CENTRO_INSTALACION", nullable = false, length = 100)
	private String nbCentroInstalacion;
	@Column(name = "NB_CALLE", nullable = true, length = 50)
	private String nbCalle;
	@Column(name = "NU_EXTERIOR", nullable = true, length = 10)
	private String nuExterior;
	@Column(name = "NB_ENTRE_CALLE", nullable = true, length = 50)
	private String nbEntreCalle;
	@Column(name = "NB_Y_CALLE", nullable = true, length = 50)
	private String nbYCalle;
	@Column(name = "NB_COLONIA", nullable = true, length = 50)
	private String nbColonia;
	@Column(name = "NB_ALCALDIA", nullable = true, length = 50)
	private String nbAlcaldia;
	@Column(name = "NB_DIAS_ATENCION", nullable = true, length = 90)
	private String nbDiasAtencion;
	@Column(name = "HR_ATENCION_INI", nullable = true, length = 8)
	private String hrAtencionIni;
	@Column(name = "HR_ATENCION_FIN", nullable = true, length = 8)
	private String hrAtencionFin;
	@Column(name = "NU_ORDEN", nullable = true, precision = 4, scale = 0)	
	private Long nuOrden;
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
	@Column(name = "ST_CENTRO_INSTALACION")	
	private Long stCentroInstalcion;
	
	public Long getIdCentroInstalacion() {
		return idCentroInstalacion;
	}
	public void setIdCentroInstalacion(Long idCentroInstalacion) {
		this.idCentroInstalacion = idCentroInstalacion;
	}
	public String getCdCentroInstalacion() {
		return cdCentroInstalacion;
	}
	public void setCdCentroInstalacion(String cdCentroInstalacion) {
		this.cdCentroInstalacion = cdCentroInstalacion;
	}
	public String getNbCentroInstalacion() {
		return nbCentroInstalacion;
	}
	public void setNbCentroInstalacion(String nbCentroInstalacion) {
		this.nbCentroInstalacion = nbCentroInstalacion;
	}
	public String getNbCalle() {
		return nbCalle;
	}
	public void setNbCalle(String nbCalle) {
		this.nbCalle = nbCalle;
	}
	public String getNuExterior() {
		return nuExterior;
	}
	public void setNuExterior(String nuExterior) {
		this.nuExterior = nuExterior;
	}
	public String getNbEntreCalle() {
		return nbEntreCalle;
	}
	public void setNbEntreCalle(String nbEntreCalle) {
		this.nbEntreCalle = nbEntreCalle;
	}
	public String getNbYCalle() {
		return nbYCalle;
	}
	public void setNbYCalle(String nbYCalle) {
		this.nbYCalle = nbYCalle;
	}
	public String getNbColonia() {
		return nbColonia;
	}
	public void setNbColonia(String nbColonia) {
		this.nbColonia = nbColonia;
	}
	public String getNbAlcaldia() {
		return nbAlcaldia;
	}
	public void setNbAlcaldia(String nbAlcaldia) {
		this.nbAlcaldia = nbAlcaldia;
	}
	public String getNbDiasAtencion() {
		return nbDiasAtencion;
	}
	public void setNbDiasAtencion(String nbDiasAtencion) {
		this.nbDiasAtencion = nbDiasAtencion;
	}
	public String getHrAtencionIni() {
		return hrAtencionIni;
	}
	public void setHrAtencionIni(String hrAtencionIni) {
		this.hrAtencionIni = hrAtencionIni;
	}
	public String getHrAtencionFin() {
		return hrAtencionFin;
	}
	public void setHrAtencionFin(String hrAtencionFin) {
		this.hrAtencionFin = hrAtencionFin;
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
	public Long getStCentroInstalcion() {
		return stCentroInstalcion;
	}
	public void setStCentroInstalcion(Long stCentroInstalcion) {
		this.stCentroInstalcion = stCentroInstalcion;
	}


}
