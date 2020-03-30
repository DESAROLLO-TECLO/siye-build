package mx.com.teclo.siye.persistencia.vo.proceso;




public class PlanProcesoVO {

	private Long idPlanProceso;
	private PlanVO plan;
	private ProcesoVO proceso;
	private Long nuorden;
	private Boolean stActivo;
	
	//Se agrega esta variable para conocer en que etapa se ecnuetra la orden de servicio
	private Boolean statusProceos;
	
	
	public Boolean getStatusProceos() {
		return statusProceos;
	}
	public void setStatusProceos(Boolean statusProceos) {
		this.statusProceos = statusProceos;
	}
	public Long getIdPlanProceso() {
		return idPlanProceso;
	}
	public void setIdPlanProceso(Long idPlanProceso) {
		this.idPlanProceso = idPlanProceso;
	}
	public PlanVO getPlan() {
		return plan;
	}
	public void setPlan(PlanVO plan) {
		this.plan = plan;
	}
	public ProcesoVO getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoVO proceso) {
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
