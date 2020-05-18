package mx.com.teclo.siye.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE069P_CONFIG_REPORTE")
public class ConfiguracionReportesDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_CONFIG")
	private Integer idConfig;
	@Column(name = "CD_LLAVE_CONFIG")
	private String cdLlaveConfig;
	@Column(name = "CD_VALOR_IMG")
	private Blob cdValorImg;
	@Column(name = "NB_REPORTE")
	private String nbReporte;
	@Column(name = "TX_CONFIG")
	private String txConfig;
	@Column(name = "ST_ACTIVO")
	private Integer stActivo;
	
	public Integer getIdConfig() {
		return idConfig;
	}
	public void setIdConfig(Integer idConfig) {
		this.idConfig = idConfig;
	}
	public String getCdLlaveConfig() {
		return cdLlaveConfig;
	}
	public void setCdLlaveConfig(String cdLlaveConfig) {
		this.cdLlaveConfig = cdLlaveConfig;
	}
	public Blob getCdValorImg() {
		return cdValorImg;
	}
	public void setCdValorImg(Blob cdValorImg) {
		this.cdValorImg = cdValorImg;
	}
	public String getNbReporte() {
		return nbReporte;
	}
	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}
	public String getTxConfig() {
		return txConfig;
	}
	public void setTxConfig(String txConfig) {
		this.txConfig = txConfig;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
}
