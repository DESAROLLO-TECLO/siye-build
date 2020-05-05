package mx.com.teclo.siye.persistencia.vo.monitoreo;

import java.io.Serializable;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.ordenServicio.OrdenServiVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ProcesoVO;

public class OrdenIncidenciaDetalleVO implements Serializable {
	
	private static final long serialVersionUID = 1374927111939946350L;
	
	private OrdenServicioDetVO ordenServicio;
	private List<ProcesoDetalleVO> proceso;
	private List<IncidenciaDetalleVO> incidencia;
	public OrdenServicioDetVO getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicioDetVO ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public List<ProcesoDetalleVO> getProceso() {
		return proceso;
	}
	public void setProceso(List<ProcesoDetalleVO> proceso) {
		this.proceso = proceso;
	}
	public List<IncidenciaDetalleVO> getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(List<IncidenciaDetalleVO> incidencia) {
		this.incidencia = incidencia;
	}
	
	
}
