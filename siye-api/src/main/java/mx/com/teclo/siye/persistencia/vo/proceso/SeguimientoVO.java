package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

public class SeguimientoVO {
	private Long idStSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private String cdColor;
	private String idTipoSeguimiento;
	private Long nuOrden;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private String idUsrModifica;
	private Date fhModificacion;
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
	public String getIdTipoSeguimiento() {
		return idTipoSeguimiento;
	}
	public void setIdTipoSeguimiento(String idTipoSeguimiento) {
		this.idTipoSeguimiento = idTipoSeguimiento;
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
	public String getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(String idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	
	
}
