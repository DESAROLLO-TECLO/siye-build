package mx.com.teclo.siye.persistencia.vo.clave;

import java.io.Serializable;

public class StPasswordVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4517723583841495925L;

	private Long idStPassword;
	private String cdStPassword;
	private String nbStPassword;
	private String txStPassword;
	private Integer stActivo;
	private String cdColor;

	/**
	 * @return the idStPassword
	 */
	public Long getIdStPassword() {
		return idStPassword;
	}

	/**
	 * @param idStPassword the idStPassword to set
	 */
	public void setIdStPassword(Long idStPassword) {
		this.idStPassword = idStPassword;
	}

	/**
	 * @return the cdStPassword
	 */
	public String getCdStPassword() {
		return cdStPassword;
	}

	/**
	 * @param cdStPassword the cdStPassword to set
	 */
	public void setCdStPassword(String cdStPassword) {
		this.cdStPassword = cdStPassword;
	}

	/**
	 * @return the nbStPassword
	 */
	public String getNbStPassword() {
		return nbStPassword;
	}

	/**
	 * @param nbStPassword the nbStPassword to set
	 */
	public void setNbStPassword(String nbStPassword) {
		this.nbStPassword = nbStPassword;
	}

	/**
	 * @return the txStPassword
	 */
	public String getTxStPassword() {
		return txStPassword;
	}

	/**
	 * @param txStPassword the txStPassword to set
	 */
	public void setTxStPassword(String txStPassword) {
		this.txStPassword = txStPassword;
	}

	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the cdColor
	 */
	public String getCdColor() {
		return cdColor;
	}

	/**
	 * @param cdColor the cdColor to set
	 */
	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
	}

}
