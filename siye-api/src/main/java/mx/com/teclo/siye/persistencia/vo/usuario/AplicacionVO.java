package mx.com.teclo.siye.persistencia.vo.usuario;

public class AplicacionVO {

	private Long idAplicacion;
	private String cdAplicacion;
	private String nbAplicacion;
	private String txAplicacion;
	private Long nuExpiracion;
	private Long nuInactividad;
	private String nbZona;
	private String nbCliente;
	private Integer stActivo;
	private String ambiente;

	public Long getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getCdAplicacion() {
		return cdAplicacion;
	}

	public void setCdAplicacion(String cdAplicacion) {
		this.cdAplicacion = cdAplicacion;
	}

	public String getNbAplicacion() {
		return nbAplicacion;
	}

	public void setNbAplicacion(String nbAplicacion) {
		this.nbAplicacion = nbAplicacion;
	}

	public String getTxAplicacion() {
		return txAplicacion;
	}

	public void setTxAplicacion(String txAplicacion) {
		this.txAplicacion = txAplicacion;
	}

	public Long getNuExpiracion() {
		return nuExpiracion;
	}

	public void setNuExpiracion(Long nuExpiracion) {
		this.nuExpiracion = nuExpiracion;
	}

	public Long getNuInactividad() {
		return nuInactividad;
	}

	public void setNuInactividad(Long nuInactividad) {
		this.nuInactividad = nuInactividad;
	}

	public String getNbZona() {
		return nbZona;
	}

	public void setNbZona(String nbZona) {
		this.nbZona = nbZona;
	}

	public String getNbCliente() {
		return nbCliente;
	}

	public void setNbCliente(String nbCliente) {
		this.nbCliente = nbCliente;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

}
