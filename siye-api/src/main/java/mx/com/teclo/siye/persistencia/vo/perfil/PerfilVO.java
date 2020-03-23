package mx.com.teclo.siye.persistencia.vo.perfil;

import mx.com.teclo.siye.persistencia.vo.usuario.AplicacionVO;

public class PerfilVO {

	private Long idPerfil;
//	@JsonIgnore
	private AplicacionVO aplicacion;
	private String cdPerfil;
	private String nbPerfil;
	private String txPerfil;
	private Integer stActivo;
	 

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public AplicacionVO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionVO aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public String getNbPerfil() {
		return nbPerfil;
	}

	public void setNbPerfil(String nbPerfil) {
		this.nbPerfil = nbPerfil;
	}

	public String getTxPerfil() {
		return txPerfil;
	}

	public void setTxPerfil(String txPerfil) {
		this.txPerfil = txPerfil;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	@Override
	public String toString() {
		return "PerfilVO [idPerfil=" + idPerfil + ", aplicacion=" + aplicacion + ", cdPerfil=" + cdPerfil
				+ ", nbPerfil=" + nbPerfil + ", txPerfil=" + txPerfil + ", stActivo=" + stActivo + "]";
	}

	 
}
