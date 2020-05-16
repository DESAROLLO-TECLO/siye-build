package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class ExpedienteNivelProcesoVO {
	
	private Long idPlan;
	private Long idProceso;
	private String cdProceso;
	private Long nuMaxImg;
	private String cdNivel;
	private String nbNivel;
	private String name;
	private List<ExpedienteNivelEncuestaVO> listEncuestas;
	private InfoEvidenciaNivelVO infoEvidencia;
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
	 * @return the listEncuestas
	 */
	public List<ExpedienteNivelEncuestaVO> getListEncuestas() {
		return listEncuestas;
	}
	/**
	 * @param listEncuestas the listEncuestas to set
	 */
	public void setListEncuestas(List<ExpedienteNivelEncuestaVO> listEncuestas) {
		this.listEncuestas = listEncuestas;
	}
	public InfoEvidenciaNivelVO getInfoEvidencia() {
		return infoEvidencia;
	}
	public void setInfoEvidencia(InfoEvidenciaNivelVO infoEvidencia) {
		this.infoEvidencia = infoEvidencia;
	}
	/**
	 * @return the idPlan
	 */
	public Long getIdPlan() {
		return idPlan;
	}
	/**
	 * @param idPlan the idPlan to set
	 */
	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}
}
