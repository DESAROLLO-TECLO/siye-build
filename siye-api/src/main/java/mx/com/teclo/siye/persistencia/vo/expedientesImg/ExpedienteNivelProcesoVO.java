package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelProcesoVO {
	
	private Long idProceso;
	private String cdProceso;
	private Long nuMaxImg;
	private List<ImagenVO> imagenes;
	private List<ExpedienteNivelEncuestaVO> encuestas;
	
	/**
	 * @return the idProceso
	 */
	public Long getIdProceso() {
		return idProceso;
	}
	/**
	 * @param idProceso the idProceso to set
	 */
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}
	/**
	 * @return the cdProceso
	 */
	public String getCdProceso() {
		return cdProceso;
	}
	/**
	 * @param cdProceso the cdProceso to set
	 */
	public void setCdProceso(String cdProceso) {
		this.cdProceso = cdProceso;
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
	 * @return the encuestas
	 */
	public List<ExpedienteNivelEncuestaVO> getEncuestas() {
		return encuestas;
	}
	/**
	 * @param encuestas the encuestas to set
	 */
	public void setEncuestas(List<ExpedienteNivelEncuestaVO> encuestas) {
		this.encuestas = encuestas;
	}

}