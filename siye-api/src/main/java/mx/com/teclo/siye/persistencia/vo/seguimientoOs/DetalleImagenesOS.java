package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidencVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;

public class DetalleImagenesOS {
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private String centroInstalacion;
	private List<ImagenVO> nivelOrdenServicio;
	private List<PreguntasDetalleVO> nivelPreguntas;
	private List<ImagenVO> nivelEncuesta;
	private List<IncidencVO> listIncidenciasNivel;
	
	/**
	 * @return the idOrdenServicio
	 */
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	/**
	 * @param idOrdenServicio the idOrdenServicio to set
	 */
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	/**
	 * @return the cdOrdenServicio
	 */
	public String getCdOrdenServicio() {
		return cdOrdenServicio;
	}
	/**
	 * @param cdOrdenServicio the cdOrdenServicio to set
	 */
	public void setCdOrdenServicio(String cdOrdenServicio) {
		this.cdOrdenServicio = cdOrdenServicio;
	}
	/**
	 * @return the centroInstalacion
	 */
	public String getCentroInstalacion() {
		return centroInstalacion;
	}
	/**
	 * @param centroInstalacion the centroInstalacion to set
	 */
	public void setCentroInstalacion(String centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	/**
	 * @return the nivelPreguntas
	 */
	public List<PreguntasDetalleVO> getNivelPreguntas() {
		return nivelPreguntas;
	}
	/**
	 * @param nivelPreguntas the nivelPreguntas to set
	 */
	public void setNivelPreguntas(List<PreguntasDetalleVO> nivelPreguntas) {
		this.nivelPreguntas = nivelPreguntas;
	}
	/**
	 * @return the nivelEncuesta
	 */
	public List<ImagenVO> getNivelEncuesta() {
		return nivelEncuesta;
	}
	/**
	 * @param nivelEncuesta the nivelEncuesta to set
	 */
	public void setNivelEncuesta(List<ImagenVO> nivelEncuesta) {
		this.nivelEncuesta = nivelEncuesta;
	}
	/**
	 * @return the nivelOrdenServicio
	 */
	public List<ImagenVO> getNivelOrdenServicio() {
		return nivelOrdenServicio;
	}
	/**
	 * @param nivelOrdenServicio the nivelOrdenServicio to set
	 */
	public void setNivelOrdenServicio(List<ImagenVO> nivelOrdenServicio) {
		this.nivelOrdenServicio = nivelOrdenServicio;
	}
	/**
	 * @return the listIncidenciasNivel
	 */
	public List<IncidencVO> getListIncidenciasNivel() {
		return listIncidenciasNivel;
	}
	/**
	 * @param listIncidenciasNivel the listIncidenciasNivel to set
	 */
	public void setListIncidenciasNivel(List<IncidencVO> listIncidenciasNivel) {
		this.listIncidenciasNivel = listIncidenciasNivel;
	}

}
