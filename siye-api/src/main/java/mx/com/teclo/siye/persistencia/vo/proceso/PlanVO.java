package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

/**
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class PlanVO {
	private Long idPlan;
	private String cdPlan;
	private String nbPlan;
	private String txPlan;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;

	public Long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}

	public String getCdPlan() {
		return cdPlan;
	}

	public void setCdPlan(String cdPlan) {
		this.cdPlan = cdPlan;
	}

	public String getNbPlan() {
		return nbPlan;
	}

	public void setNbPlan(String nbPlan) {
		this.nbPlan = nbPlan;
	}

	public String getTxPlan() {
		return txPlan;
	}

	public void setTxPlan(String txPlan) {
		this.txPlan = txPlan;
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
