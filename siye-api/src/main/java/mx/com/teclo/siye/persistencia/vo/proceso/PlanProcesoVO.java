package mx.com.teclo.siye.persistencia.vo.proceso;




public class PlanProcesoVO {

	private Long idPlanProceso;
	private PlanVO plan;
	private ProcesoVO proceso;
	private Long nuorden;
	private Boolean stActivo;
	
	//Se agrega esta variable para conocer si la etapa esta activa
	private Boolean statusProceos;
	//variable para conocer el estatus del proceso
	//private Boolean procesoCompleto;
	private StSeguimientoVO stSeguimiento;
	
	
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
	/*public Boolean getProcesoCompleto() {
		return procesoCompleto;
	}
	public void setProcesoCompleto(Boolean procesoCompleto) {
		this.procesoCompleto = procesoCompleto;
	}*/
	public StSeguimientoVO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(StSeguimientoVO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
	}
	

	
	
	
	
	
	

}
