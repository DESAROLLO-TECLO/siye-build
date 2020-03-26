package mx.com.teclo.siye.persistencia.vo.proceso;

import mx.com.teclo.siye.persistencia.vo.encuesta.EncuestaDetalleVO;

public class ProcesoEncuestaVO {
	
	

	private Long idProcesoEncuesta;
	private ProcesoVO proceso;
	private EncuestaDetalleVO encuesta;
	private Long nuorden;
	private Boolean stActivo;
	
	public Long getIdProcesoEncuesta() {
		return idProcesoEncuesta;
	}
	public void setIdProcesoEncuesta(Long idProcesoEncuesta) {
		this.idProcesoEncuesta = idProcesoEncuesta;
	}
	public ProcesoVO getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoVO proceso) {
		this.proceso = proceso;
	}
	public EncuestaDetalleVO getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(EncuestaDetalleVO encuesta) {
		this.encuesta = encuesta;
	}
	public Long getNuorden() {
		return nuorden;
	}
	public void setNuorden(Long nuorden) {
		this.nuorden = nuorden;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	
	

}
