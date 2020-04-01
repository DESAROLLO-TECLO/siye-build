package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;


public class TipoSeguimientoVO implements Serializable {

	private static final long serialVersionUID = 4857407727892817950L;

	private Long idTipoSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private Long nuOrden;
	private Boolean stActivo;
	
	public Long getIdTipoSeguimiento() {
		return idTipoSeguimiento;
	}
	public void setIdTipoSeguimiento(Long idTipoSeguimiento) {
		this.idTipoSeguimiento = idTipoSeguimiento;
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
	
}
