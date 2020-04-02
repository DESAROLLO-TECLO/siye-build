package mx.com.teclo.siye.persistencia.vo.catalogo;

public class ConcesionariaVO {
	
	private Long idConsecion;
	private String cdConsecion;
	private String nbConsecion;
	private Long nuOrden;
	private Boolean stActivo;
	
	public Long getIdConsecion() {
		return idConsecion;
	}
	public void setIdConsecion(Long idConsecion) {
		this.idConsecion = idConsecion;
	}
	public String getCdConsecion() {
		return cdConsecion;
	}
	public void setCdConsecion(String cdConsecion) {
		this.cdConsecion = cdConsecion;
	}
	public String getNbConsecion() {
		return nbConsecion;
	}
	public void setNbConsecion(String nbConsecion) {
		this.nbConsecion = nbConsecion;
	}
	public Long getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	
	
}
