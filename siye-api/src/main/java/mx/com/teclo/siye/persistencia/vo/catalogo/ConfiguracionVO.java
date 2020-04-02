package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class ConfiguracionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4997421404163675402L;

	private Long idPConfig;
	private String cdLlavePConfig;
	private String cdValorPConfig;
	private String txPConfig;
	private Integer stActivo;

	/**
	 * @return the idPConfig
	 */
	public Long getIdPConfig() {
		return idPConfig;
	}

	/**
	 * @param idPConfig the idPConfig to set
	 */
	public void setIdPConfig(Long idPConfig) {
		this.idPConfig = idPConfig;
	}

	/**
	 * @return the cdLlavePConfig
	 */
	public String getCdLlavePConfig() {
		return cdLlavePConfig;
	}

	/**
	 * @param cdLlavePConfig the cdLlavePConfig to set
	 */
	public void setCdLlavePConfig(String cdLlavePConfig) {
		this.cdLlavePConfig = cdLlavePConfig;
	}

	/**
	 * @return the cdValorPConfig
	 */
	public String getCdValorPConfig() {
		return cdValorPConfig;
	}

	/**
	 * @param cdValorPConfig the cdValorPConfig to set
	 */
	public void setCdValorPConfig(String cdValorPConfig) {
		this.cdValorPConfig = cdValorPConfig;
	}

	/**
	 * @return the txPConfig
	 */
	public String getTxPConfig() {
		return txPConfig;
	}

	/**
	 * @param txPConfig the txPConfig to set
	 */
	public void setTxPConfig(String txPConfig) {
		this.txPConfig = txPConfig;
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
}
