package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelPreguntaVO {
	
	private Long idPregunta;
	private String cdPregunta;
	private Long nuMaxImg;
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
	
	
	
}
