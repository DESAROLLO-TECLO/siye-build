package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelEncuestaVO {
	
	private Long idEncuesta;
	private String cdEncuesta;
	private Long nuMaxImg;
	private String cdClasif;
	private String nbNivel;
	private String name;
	private List<ImagenVO> imagenes;
	private List<ExpedienteNivelPreguntaVO> listImageClasif;
	
	/**
	 * @return the idEncuesta
	 */
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	/**
	 * @param idEncuesta the idEncuesta to set
	 */
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	/**
	 * @return the cdEncuesta
	 */
	public String getCdEncuesta() {
		return cdEncuesta;
	}
	/**
	 * @param cdEncuesta the cdEncuesta to set
	 */
	public void setCdEncuesta(String cdEncuesta) {
		this.cdEncuesta = cdEncuesta;
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
	 * @return the listImageClasif
	 */
	public List<ExpedienteNivelPreguntaVO> getListImageClasif() {
		return listImageClasif;
	}
	/**
	 * @param listImageClasif the listImageClasif to set
	 */
	public void setListImageClasif(List<ExpedienteNivelPreguntaVO> listImageClasif) {
		this.listImageClasif = listImageClasif;
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
