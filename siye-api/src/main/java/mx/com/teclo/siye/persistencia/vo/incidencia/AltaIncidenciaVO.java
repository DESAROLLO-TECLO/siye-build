package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;
import java.util.List;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;

public class AltaIncidenciaVO implements Serializable {

	private static final long serialVersionUID = 7580375171139464745L;
	
	private String ordenServicio;
	private StSeguimientoVO tpIncidencia;
	private StSeguimientoVO prioridad;
	private String descripcion;
	private List<ImagenVO> listImagen;
	
	public String getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(String ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public StSeguimientoVO getTpIncidencia() {
		return tpIncidencia;
	}
	public void setTpIncidencia(StSeguimientoVO tpIncidencia) {
		this.tpIncidencia = tpIncidencia;
	}
	public StSeguimientoVO getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(StSeguimientoVO prioridad) {
		this.prioridad = prioridad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<ImagenVO> getListImagen() {
		return listImagen;
	}
	public void setListImagen(List<ImagenVO> listImagen) {
		this.listImagen = listImagen;
	}
		
}