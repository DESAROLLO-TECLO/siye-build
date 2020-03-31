package mx.com.teclo.siye.persistencia.hibernate.dto.configuracion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE019P_CONFIGURACION")
public class ConfiguracionOSDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_P_CONFIG")
	private Long idPConfig;
	
	@Column(name = "CD_LLAVE_P_CONFIG")
	private String cdLlaveConfig;
	
	@Column(name = "CD_VALOR_P_CONFIG")
	private String cdValorConfig;
	
	@Column(name = "TX_P_CONFIG")
	private String txConfig;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	

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
	 * @return the cdLlaveConfig
	 */
	public String getCdLlaveConfig() {
		return cdLlaveConfig;
	}

	/**
	 * @param cdLlaveConfig the cdLlaveConfig to set
	 */
	public void setCdLlaveConfig(String cdLlaveConfig) {
		this.cdLlaveConfig = cdLlaveConfig;
	}

	/**
	 * @return the cdValorConfig
	 */
	public String getCdValorConfig() {
		return cdValorConfig;
	}

	/**
	 * @param cdValorConfig the cdValorConfig to set
	 */
	public void setCdValorConfig(String cdValorConfig) {
		this.cdValorConfig = cdValorConfig;
	}

	/**
	 * @return the txConfig
	 */
	public String getTxConfig() {
		return txConfig;
	}

	/**
	 * @param txConfig the txConfig to set
	 */
	public void setTxConfig(String txConfig) {
		this.txConfig = txConfig;
	}

	/**
	 * @return the stActivo
	 */
	public Boolean getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}	

}
