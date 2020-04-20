package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.io.Serializable;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.ordenServicio.OrdenServiVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ProcesoVO;

public class OrdenIncidenciaDetalleVO implements Serializable {
	
	private static final long serialVersionUID = 1374927111939946350L;
	
	private OrdenServiVO ordenServicio;
	private List<ProcesoDetalleVO> proceso;
	
	public OrdenServiVO getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServiVO ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public List<ProcesoDetalleVO> getProceso() {
		return proceso;
	}
	public void setProceso(List<ProcesoDetalleVO> proceso) {
		this.proceso = proceso;
	}
		
}
