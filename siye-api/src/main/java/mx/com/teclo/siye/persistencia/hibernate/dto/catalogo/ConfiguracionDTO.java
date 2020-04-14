package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE019P_CONFIGURACION")
public class ConfiguracionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000388255348045363L;

	@Id
	@Column(name = "ID_P_CONFIG", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idPConfig;

	@Column(name = "CD_LLAVE_P_CONFIG", nullable = false, length = 50)
	private String cdLlavePConfig;

	@Column(name = "CD_VALOR_P_CONFIG", nullable = false, length = 4000)
	private String cdValorPConfig;

	@Column(name = "TX_P_CONFIG", nullable = false, length = 200)
	private String txPConfig;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
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
