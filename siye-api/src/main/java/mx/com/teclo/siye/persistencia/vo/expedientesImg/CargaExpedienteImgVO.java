package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class CargaExpedienteImgVO {	
	
	private String placa;
	private String numvim;
	private String nameConsesionario;
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private Long nuMaxImg;
	private String cdClasif;
	private String nbNivel;
	private List<ImagenVO> imagenes;
	private List<ExpedienteNivelProcesoVO> procesos;
	
	
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the numvim
	 */
	public String getNumvim() {
		return numvim;
	}
	/**
	 * @param numvim the numvim to set
	 */
	public void setNumvim(String numvim) {
		this.numvim = numvim;
	}
	/**
	 * @return the nameConsesionario
	 */
	public String getNameConsesionario() {
		return nameConsesionario;
	}
	/**
	 * @param nameConsesionario the nameConsesionario to set
	 */
	public void setNameConsesionario(String nameConsesionario) {
		this.nameConsesionario = nameConsesionario;
	}
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
	 * @return the procesos
	 */
	public List<ExpedienteNivelProcesoVO> getProcesos() {
		return procesos;
	}
	/**
	 * @param procesos the procesos to set
	 */
	public void setProcesos(List<ExpedienteNivelProcesoVO> procesos) {
		this.procesos = procesos;
	}
	/**
	 * @return the cdClasif
	 */
	public String getCdClasif() {
		return cdClasif;
	}
	/**
	 * @param cdClasif the cdClasif to set
	 */
	public void setCdClasif(String cdClasif) {
		this.cdClasif = cdClasif;
	}
	/**
	 * @return the nbNivel
	 */
	public String getNbNivel() {
		return nbNivel;
	}
	/**
	 * @param nbNivel the nbNivel to set
	 */
	public void setNbNivel(String nbNivel) {
		this.nbNivel = nbNivel;
	}
}
