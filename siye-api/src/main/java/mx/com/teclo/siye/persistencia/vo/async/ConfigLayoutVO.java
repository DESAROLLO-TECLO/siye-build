package mx.com.teclo.siye.persistencia.vo.async;

public class ConfigLayoutVO {
	private Long idTipoLayout;
	private String cdTipoLayout;
	private String nbTipoLayout;
	private Integer nuMaxRegistros;
	private Long idTipoArchivo;
	private String txMascara;
	private Short cdTamanioMax;
	private String nbDirectorio;
	private Boolean stCargaParcial;
	private Boolean stVigente;

	public Long getIdTipoLayout() {
		return idTipoLayout;
	}

	public void setIdTipoLayout(Long idTipoLayout) {
		this.idTipoLayout = idTipoLayout;
	}

	public String getCdTipoLayout() {
		return cdTipoLayout;
	}

	public void setCdTipoLayout(String cdTipoLayout) {
		this.cdTipoLayout = cdTipoLayout;
	}

	public String getNbTipoLayout() {
		return nbTipoLayout;
	}

	public void setNbTipoLayout(String nbTipoLayout) {
		this.nbTipoLayout = nbTipoLayout;
	}

	public Integer getNuMaxRegistros() {
		return nuMaxRegistros;
	}

	public void setNuMaxRegistros(Integer nuMaxRegistros) {
		this.nuMaxRegistros = nuMaxRegistros;
	}

	public Long getIdTipoArchivo() {
		return idTipoArchivo;
	}

	public void setIdTipoArchivo(Long idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}

	public String getTxMascara() {
		return txMascara;
	}

	public void setTxMascara(String txMascara) {
		this.txMascara = txMascara;
	}

	public Short getCdTamanioMax() {
		return cdTamanioMax;
	}

	public void setCdTamanioMax(Short cdTamanioMax) {
		this.cdTamanioMax = cdTamanioMax;
	}

	public String getNbDirectorio() {
		return nbDirectorio;
	}

	public void setNbDirectorio(String nbDirectorio) {
		this.nbDirectorio = nbDirectorio;
	}

	public Boolean getStCargaParcial() {
		return stCargaParcial;
	}

	public void setStCargaParcial(Boolean stCargaParcial) {
		this.stCargaParcial = stCargaParcial;
	}

	public Boolean getStVigente() {
		return stVigente;
	}

	public void setStVigente(Boolean stVigente) {
		this.stVigente = stVigente;
	}

}
