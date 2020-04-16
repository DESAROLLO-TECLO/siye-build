package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;
import java.util.List;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;

public class AltaIncidenciaVO implements Serializable {

	private static final long serialVersionUID = 7580375171139464745L;
	
	private String ordenServicio;
	private StSeguimientoVO tpIncidencia;
	private StSeguimientoVO prioridad;
	private String descripcion;
	private List<ImagenVO> listImagen;
	private Long idProceso;
	private Long idEncuesta;
	private Long idOrdenServicio;
	private Long idPregunta;
	private CentroInstalacionVO centroInstalacion;
	
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
	public Long getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	public Long getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}
	public CentroInstalacionVO getCentroInstalacion() {
		return centroInstalacion;
	}
	public void setCentroInstalacion(CentroInstalacionVO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	
	
		
}