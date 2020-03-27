package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;

public class CatalogosOrdenProcesoVO {
	
	
	private List<CentroInstalacionVO> centrosInstalacion;
	
	private List<KitInstalacionVO> kitInstalacion;
	
	
	private List<PlanVO> plan;
	
	private List<TipoVehiculoVO> tipoVehiculo;
	

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

	public List<TipoVehiculoVO> getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(List<TipoVehiculoVO> tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
	
	
	
	
	
	
}