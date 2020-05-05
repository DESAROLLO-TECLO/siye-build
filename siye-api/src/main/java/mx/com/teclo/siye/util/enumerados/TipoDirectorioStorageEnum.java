package mx.com.teclo.siye.util.enumerados;

public enum TipoDirectorioStorageEnum {
	INPUT("recibido"),
	OUTPUT("entregado"),
	STAGE("validado");

	private String cdTipo;
	

	private TipoDirectorioStorageEnum( String cdTipo) {
		this.cdTipo = cdTipo;		
	}


	public String getCdTipo() {
		return cdTipo;
	}	

}
