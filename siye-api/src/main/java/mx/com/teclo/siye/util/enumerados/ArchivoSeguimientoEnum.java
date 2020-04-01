package mx.com.teclo.siye.util.enumerados;

public enum ArchivoSeguimientoEnum {
	RECIBIDO(4L, "RECIBIDO", "RECIBIDO"), CARGANDO(5L, "CARGANDO", "CARGANDO"), CARGADO(6L, "CARGADO", "CARGADO A SIE"), RECHAZADO(7L, "RECHAZADO","RECHAZADO");

	private Long idArchivoSeg;
	private String cdArchivoSeg;
	private String nbArchivoSeg;

	private ArchivoSeguimientoEnum(Long idArchivoSeg, String cdArchivoSeg, String nbArchivoSeg) {
		this.idArchivoSeg = idArchivoSeg;
		this.cdArchivoSeg = cdArchivoSeg;
		this.nbArchivoSeg = nbArchivoSeg;
	}

	public Long getIdArchivoSeg() {
		return idArchivoSeg;
	}

	public void setIdArchivoSeg(Long idArchivoSeg) {
		this.idArchivoSeg = idArchivoSeg;
	}

	public String getCdArchivoSeg() {
		return cdArchivoSeg;
	}

	public void setCdArchivoSeg(String cdArchivoSeg) {
		this.cdArchivoSeg = cdArchivoSeg;
	}

	public String getNbArchivoSeg() {
		return nbArchivoSeg;
	}

	public void setNbArchivoSeg(String nbArchivoSeg) {
		this.nbArchivoSeg = nbArchivoSeg;
	}

}