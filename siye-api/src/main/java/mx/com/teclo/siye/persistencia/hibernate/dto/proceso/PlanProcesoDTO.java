package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "TIE036D_IE_PLAN_PROCESO")
public class PlanProcesoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "ID_PLAN_PROCESO", nullable = false)
	private Long idPlanProceso;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLAN", referencedColumnName = "ID_PLAN", nullable = false)
	private PlanDTO plan;
	@JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ProcesoDTO proceso;
	@Column(name = "NU_ORDEN")
	private Long nuorden;
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	public Long getIdPlanProceso() {
		return idPlanProceso;
	}
	public void setIdPlanProceso(Long idPlanProceso) {
		this.idPlanProceso = idPlanProceso;
	}
	public PlanDTO getPlan() {
		return plan;
	}
	public void setPlan(PlanDTO plan) {
		this.plan = plan;
	}
	public ProcesoDTO getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
	}
	public Long getNuorden() {
		return nuorden;
	}
	public void setNuorden(Long nuorden) {
		this.nuorden = nuorden;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	
	
	
	


}
