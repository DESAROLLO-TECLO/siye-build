package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

import mx.com.teclo.siye.persistencia.vo.encuesta.EncuestaDetalleVO;

public class ProcesoEncuestaVO {
	
	

	private Long idProcesoEncuesta;
	private ProcesoVO idProceso;
	private EncuestaDetalleVO idEncuesta;
	private Long nuorden;
	private Boolean stActivo;
	
	//variable para conocer si la encuesta ya esta terminanda o no
	private Boolean stRespondida=false;
	//variable para conocer si la encuesta esta activa
	private Boolean stActivaMostrar=false;
	//variable para conocer si la de satisfaccion ya esta activa o no
	private Boolean stSatisfaccion;
	
	//se agrega variables para fecha de inicio primer proceso y fecha fin ultimo procesos
	
      private Date fechaInicioProceso;
	  private Date fechaFinProceso;
	
	
	public Long getIdProcesoEncuesta() {
		return idProcesoEncuesta;
	}
	public void setIdProcesoEncuesta(Long idProcesoEncuesta) {
		this.idProcesoEncuesta = idProcesoEncuesta;
	}


	public ProcesoVO getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(ProcesoVO idProceso) {
		this.idProceso = idProceso;
	}
	public EncuestaDetalleVO getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(EncuestaDetalleVO idEncuesta) {
		this.idEncuesta = idEncuesta;
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
	public Boolean getStRespondida() {
		return stRespondida;
	}
	public void setStRespondida(Boolean stRespondida) {
		this.stRespondida = stRespondida;
	}
	public Boolean getStActivaMostrar() {
		return stActivaMostrar;
	}
	public void setStActivaMostrar(Boolean stActivaMostrar) {
		this.stActivaMostrar = stActivaMostrar;
	}
	public Boolean getStSatisfaccion() {
		return stSatisfaccion;
	}
	public void setStSatisfaccion(Boolean stSatisfaccion) {
		this.stSatisfaccion = stSatisfaccion;
	}
	public Date getFechaInicioProceso() {
		return fechaInicioProceso;
	}
	public void setFechaInicioProceso(Date fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}
	public Date getFechaFinProceso() {
		return fechaFinProceso;
	}
	public void setFechaFinProceso(Date fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}
	
	
	
	
	

}
