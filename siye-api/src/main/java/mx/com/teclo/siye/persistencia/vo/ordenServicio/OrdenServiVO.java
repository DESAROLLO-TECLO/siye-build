package mx.com.teclo.siye.persistencia.vo.ordenServicio;

import java.io.Serializable;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.proceso.KitInstDispVO;



public class OrdenServiVO implements Serializable{
	
	private static final long serialVersionUID = -2154104909635252655L;

	private String cdOrden;
	private Long centroI;
	private Long plan;
	private Long tpKit;
	private String cdKitIntalacion;
	private List<KitInstDispVO> kitInstalacionVO;
	private VehiculoOSVO vehiculoVO;
	private Long idProcesoActual;

	public VehiculoOSVO getVehiculoVO() {
		return vehiculoVO;
	}
	public void setVehiculoVO(VehiculoOSVO vehiculoVO) {
		this.vehiculoVO = vehiculoVO;
	}
	public String getCdOrden() {
		return cdOrden;
	}
	public void setCdOrden(String cdOrden) {
		this.cdOrden = cdOrden;
	}
	public Long getCentroI() {
		return centroI;
	}
	public void setCentroI(Long centroI) {
		this.centroI = centroI;
	}
	public Long getPlan() {
		return plan;
	}
	public void setPlan(Long plan) {
		this.plan = plan;
	}
	public Long getTpKit() {
		return tpKit;
	}
	public void setTpKit(Long tpKit) {
		this.tpKit = tpKit;
	}
	public String getCdKitIntalacion() {
		return cdKitIntalacion;
	}
	public void setCdKitIntalacion(String cdKitIntalacion) {
		this.cdKitIntalacion = cdKitIntalacion;
	}
	public List<KitInstDispVO> getKitInstalacionVO() {
		return kitInstalacionVO;
	}
	public void setKitInstalacionVO(List<KitInstDispVO> kitInstalacionVO) {
		this.kitInstalacionVO = kitInstalacionVO;
	}
	public Long getIdProcesoActual() {
		return idProcesoActual;
	}
	public void setIdProcesoActual(Long idProcesoActual) {
		this.idProcesoActual = idProcesoActual;
	}

}
