package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;

public class StSeguimientoVO  implements Serializable {

	private static final long serialVersionUID = -6432339200022501620L;
	
	private Long idStSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private String cdColor;
	private Long nuOrden;
	private Boolean stActivo;
	private TipoSeguimientoVO tipoSeguimiento;
	
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
	public TipoSeguimientoVO getTipoSeguimiento() {
		return tipoSeguimiento;
	}
	public void setTipoSeguimiento(TipoSeguimientoVO tipoSeguimiento) {
		this.tipoSeguimiento = tipoSeguimiento;
	}
	
}
