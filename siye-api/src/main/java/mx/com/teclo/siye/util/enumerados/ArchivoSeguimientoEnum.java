package mx.com.teclo.siye.util.enumerados;

public enum ArchivoSeguimientoEnum {
	RECIBIDO("RECIBIDO", "ID_ST_SEGUIMIENTO", "TIE025D_IE_LOTE_ODS"),
	CARGANDO("CARGANDO", "ID_ST_SEGUIMIENTO", "TIE025D_IE_LOTE_ODS"),
	CARGADO("CARGADO", "ID_ST_SEGUIMIENTO", "TIE025D_IE_LOTE_ODS"),
	RECHAZADO("RECHAZADO", "ID_ST_SEGUIMIENTO", "TIE025D_IE_LOTE_ODS");

	private String cdArchivoSeg;
	private String cdTipoSeg;
	private String nbTipoSeg;

	private ArchivoSeguimientoEnum(String cdArchivoSeg, String cdTipoSeg, String nbTipoSeg) {

		this.cdArchivoSeg = cdArchivoSeg;
		this.cdTipoSeg = cdTipoSeg;
		this.nbTipoSeg = nbTipoSeg;
	}

	public String getCdArchivoSeg() {
		return cdArchivoSeg;
	}

	public String getCdTipoSeg() {
		return cdTipoSeg;
	}

	public String getNbTipoSeg() {
		return nbTipoSeg;
	}

}
