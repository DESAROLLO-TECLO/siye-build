package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;
import java.util.Date;

/**
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class PlanVO implements Serializable {

	private static final long serialVersionUID = -3272429691323282653L;
	
	private Long idPlan;
	private String cdPlan;
	private String nbPlan;
	private String txPlan;
	private Boolean stActivo;
	
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

}
