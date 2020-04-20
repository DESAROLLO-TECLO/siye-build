/**
 * 
 */
package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;

/**
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class CentroInstalacionVO implements Serializable {
	
	private static final long serialVersionUID = 3679354838746125808L;
	
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
	private String hrAtencionIni;
	private String hrAtencionFin;
	private Long nuOrden;
	private Boolean stActivo;
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

	public Long getStCentroInstalcion() {
		return stCentroInstalcion;
	}

	public void setStCentroInstalcion(Long stCentroInstalcion) {
		this.stCentroInstalcion = stCentroInstalcion;
	}
	

}
