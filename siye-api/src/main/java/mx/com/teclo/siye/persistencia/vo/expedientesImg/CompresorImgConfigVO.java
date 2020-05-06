package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.io.Serializable;

public class CompresorImgConfigVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3948708531551510574L;
	private Long idCompresroImg;
	private Long nuPesoImgInicial;
	private Long nuPesoImgFinal;
	private Integer nuPorcentaje;
	
	/**
	 * @return the idCompresroImg
	 */
	public Long getIdCompresroImg() {
		return idCompresroImg;
	}
	/**
	 * @param idCompresroImg the idCompresroImg to set
	 */
	public void setIdCompresroImg(Long idCompresroImg) {
		this.idCompresroImg = idCompresroImg;
	}
	/**
	 * @return the nuPesoImgInicial
	 */
	public Long getNuPesoImgInicial() {
		return nuPesoImgInicial;
	}
	/**
	 * @param nuPesoImgInicial the nuPesoImgInicial to set
	 */
	public void setNuPesoImgInicial(Long nuPesoImgInicial) {
		this.nuPesoImgInicial = nuPesoImgInicial;
	}
	/**
	 * @return the nuPesoImgFinal
	 */
	public Long getNuPesoImgFinal() {
		return nuPesoImgFinal;
	}
	/**
	 * @param nuPesoImgFinal the nuPesoImgFinal to set
	 */
	public void setNuPesoImgFinal(Long nuPesoImgFinal) {
		this.nuPesoImgFinal = nuPesoImgFinal;
	}
	/**
	 * @return the nuPorcentaje
	 */
	public Integer getNuPorcentaje() {
		return nuPorcentaje;
	}
	/**
	 * @param nuPorcentaje the nuPorcentaje to set
	 */
	public void setNuPorcentaje(Integer nuPorcentaje) {
		this.nuPorcentaje = nuPorcentaje;
	}
}
