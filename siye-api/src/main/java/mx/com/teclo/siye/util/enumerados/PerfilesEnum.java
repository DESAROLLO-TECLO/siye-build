package mx.com.teclo.siye.util.enumerados;

public enum PerfilesEnum {
	SIN_PERFIL("SINPERFIL", "SIN PERFIL", "SIN PERFIL");
	
	private String cdPerfil;
	private String nbPerfil;
	private String txPerfil;
	
	private PerfilesEnum(String cdPerfil, String nbPerfil, String txPerfil){
		this.cdPerfil = cdPerfil;
		this.nbPerfil = nbPerfil;
		this.txPerfil = txPerfil;
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
}
