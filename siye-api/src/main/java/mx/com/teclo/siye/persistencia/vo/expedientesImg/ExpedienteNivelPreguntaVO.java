package mx.com.teclo.siye.persistencia.vo.expedientesImg;

public class ExpedienteNivelPreguntaVO {
	
	private Long idEncuesta;
	private Long idPregunta;
	private Long idSecccion;
	private String cdPregunta;
	private Long nuMaxImg;
	private String cdNivel;
	private String nbNivel;
	private String name;
	private InfoEvidenciaNivelVO infoEvidencia;
	
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
	 * @return the cdNivel
	 */
	public String getCdNivel() {
		return cdNivel;
	}
	/**
	 * @param cdNivel the cdNivel to set
	 */
	public void setCdNivel(String cdNivel) {
		this.cdNivel = cdNivel;
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
	public InfoEvidenciaNivelVO getInfoEvidencia() {
		return infoEvidencia;
	}
	public void setInfoEvidencia(InfoEvidenciaNivelVO infoEvidencia) {
		this.infoEvidencia = infoEvidencia;
	}
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
	public Long getIdSecccion() {
		return idSecccion;
	}
	public void setIdSecccion(Long idSecccion) {
		this.idSecccion = idSecccion;
	}
}
