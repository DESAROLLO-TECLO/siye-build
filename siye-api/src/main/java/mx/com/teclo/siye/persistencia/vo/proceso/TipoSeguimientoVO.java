package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;


public class TipoSeguimientoVO implements Serializable {

	private static final long serialVersionUID = 4857407727892817950L;

	private Long idTipoSeguimiento;
	private String cdTpSeguimiento;
	private String nbTpSeguimiento;
	private Long nuOrden;
	private Boolean stActivo;

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
	
	
	
}
