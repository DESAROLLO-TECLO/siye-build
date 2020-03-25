/**
 * 
 */
package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

/**
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class CentroInstalacionVO {
	private Long idCentroInstalacion;
	private String cdCentroInstalacion;
	private String nbCentroInstalacion;
	private String nbCalle;
	private String nuExterior;
	private String nbEntreCalle;
	private String nbYCalle;
	private String nbColonia;
	private String nbAlcaldia;
	private String nbDiasAtencion;
	private Date hrAtencionIni;
	private Date hrAtencionFin;
	private Long nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;

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

	public Date getHrAtencionIni() {
		return hrAtencionIni;
	}

	public void setHrAtencionIni(Date hrAtencionIni) {
		this.hrAtencionIni = hrAtencionIni;
	}

	public Date getHrAtencionFin() {
		return hrAtencionFin;
	}

	public void setHrAtencionFin(Date hrAtencionFin) {
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

}
