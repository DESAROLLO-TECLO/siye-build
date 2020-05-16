package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelEncuestaVO {
	
	private Long idProceso;
	private Long idEncuesta;
	private String cdEncuesta;
	private Long nuMaxImg;
	private String cdNivel;
	private String nbNivel;
	private String name;
	private List<ExpedienteNivelPreguntaVO> listPreguntas;
	private InfoEvidenciaNivelVO infoEvidencia;
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
	/**
	 * @return the listPreguntas
	 */
	public List<ExpedienteNivelPreguntaVO> getListPreguntas() {
		return listPreguntas;
	}
	/**
	 * @param listPreguntas the listPreguntas to set
	 */
	public void setListPreguntas(List<ExpedienteNivelPreguntaVO> listPreguntas) {
		this.listPreguntas = listPreguntas;
	}
	public InfoEvidenciaNivelVO getInfoEvidencia() {
		return infoEvidencia;
	}
	public void setInfoEvidencia(InfoEvidenciaNivelVO infoEvidencia) {
		this.infoEvidencia = infoEvidencia;
	}
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
}
