package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;

import javax.persistence.Column;

public class StSeguimientoVO implements Serializable {


	private static final long serialVersionUID = -8576263801899806172L;

	private Long idStSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private String cdColor;
	private Long nuOrden;
	private Long stActivo;

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

	public Long getStActivo() {
		return stActivo;
	}

	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}
	
	
}
