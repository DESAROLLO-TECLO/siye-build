package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.List;

public class CatalogosOrdenProcesoVO {
	
	
	private List<CentroInstalacionVO> centrosInstalacion;
	
	private List<KitInstalacionVO> kitInstalacion;
	
	
	private List<PlanVO> plan;
	
	

	public List<CentroInstalacionVO> getCentrosInstalacion() {
		return centrosInstalacion;
	}

	public void setCentrosInstalacion(List<CentroInstalacionVO> centrosInstalacion) {
		this.centrosInstalacion = centrosInstalacion;
	}

	public List<KitInstalacionVO> getKitInstalacion() {
		return kitInstalacion;
	}

	public void setKitInstalacion(List<KitInstalacionVO> kitInstalacion) {
		this.kitInstalacion = kitInstalacion;
	}

	public List<PlanVO> getPlan() {
		return plan;
	}

	public void setPlan(List<PlanVO> plan) {
		this.plan = plan;
	}
	
	
	
	
	
}
