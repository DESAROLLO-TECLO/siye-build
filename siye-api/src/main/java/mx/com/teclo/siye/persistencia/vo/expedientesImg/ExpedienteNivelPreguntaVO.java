package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelPreguntaVO {
	
	private Long idPregunta;
	private String cdPregunta;
	private Long nuMaxImg;
	private String cdClasif;
	private String nbNivel;
	private String name;
	private List<ImagenVO> imagenes;
	
	
	/**
	 * @return the idPregunta
	 */
	public Long getIdPregunta() {
		return idPregunta;
	}
	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}
	/**
	 * @return the cdPregunta
	 */
	public String getCdPregunta() {
		return cdPregunta;
	}
	/**
	 * @param cdPregunta the cdPregunta to set
	 */
	public void setCdPregunta(String cdPregunta) {
		this.cdPregunta = cdPregunta;
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
