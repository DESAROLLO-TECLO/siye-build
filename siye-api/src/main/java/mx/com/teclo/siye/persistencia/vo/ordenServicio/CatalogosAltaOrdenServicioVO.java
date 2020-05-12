package mx.com.teclo.siye.persistencia.vo.ordenServicio;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.catalogo.ConcesionariaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoKitVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;

public class CatalogosAltaOrdenServicioVO {
	
	
	private List<CentroInstalacionVO> centrosInstalacion;
	
	private List<KitInstalacionVO> kitInstalacion;
	
	
	private List<PlanVO> plan;
	
	private List<TipoVehiculoVO> tipoVehiculo;
	
	private List<TipoKitVO> tipoKit;
	
	private List<ProveedorVO> proveedorVO;
	
	private List<ConcesionariaVO> concesionariaVO;
	

	public List<ConcesionariaVO> getConcesionariaVO() {
		return concesionariaVO;
	}

	public void setConcesionariaVO(List<ConcesionariaVO> concesionariaVO) {
		this.concesionariaVO = concesionariaVO;
	}

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

	public List<TipoKitVO> getTipoKit() {
		return tipoKit;
	}

	public void setTipoKit(List<TipoKitVO> tipoKit) {
		this.tipoKit = tipoKit;
	}

	public List<ProveedorVO> getProveedorVO() {
		return proveedorVO;
	}

	public void setProveedorVO(List<ProveedorVO> proveedorVO) {
		this.proveedorVO = proveedorVO;
	}
	
	
	
	
	
	
	
	
}
