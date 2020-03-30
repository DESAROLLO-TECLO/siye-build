package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelOrdenServicioVO {
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private Long nuMaxImg;
	private List<ImagenVO> imagenes;
	private List<ExpedienteNivelProcesoVO> proocesos;
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
	 * @return the nuMaxImg
	 */
	public Long getNuMaxImg() {
		return nuMaxImg;
	}
	/**
	 * @param nuMaxImg the nuMaxImg to set
	 */
	public void setNuMaxImg(Long nuMaxImg) {
		this.nuMaxImg = nuMaxImg;
	}
	/**
	 * @return the imagenes
	 */
	public List<ImagenVO> getImagenes() {
		return imagenes;
	}
	/**
	 * @param imagenes the imagenes to set
	 */
	public void setImagenes(List<ImagenVO> imagenes) {
		this.imagenes = imagenes;
	}
	/**
	 * @return the proocesos
	 */
	public List<ExpedienteNivelProcesoVO> getProocesos() {
		return proocesos;
	}
	/**
	 * @param proocesos the proocesos to set
	 */
	public void setProocesos(List<ExpedienteNivelProcesoVO> proocesos) {
		this.proocesos = proocesos;
	}
	
	

}
