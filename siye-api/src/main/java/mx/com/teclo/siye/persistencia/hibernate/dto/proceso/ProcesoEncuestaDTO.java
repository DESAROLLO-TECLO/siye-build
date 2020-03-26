package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestaDetalleDTO;


public class ProcesoEncuestaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "ID_PROCESO_ENCUESTA", nullable = false)
	private Long idProcesoEncuesta;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO", nullable = false)
	private ProcesoDTO proceso;
	@JoinColumn(name = "ID_ENCUESTA", referencedColumnName = "ID_ENCUESTA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EncuestaDetalleDTO encuesta;
	@Column(name = "NU_ORDEN")
	private Long nuorden;
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	public Long getIdProcesoEncuesta() {
		return idProcesoEncuesta;
	}
	public void setIdProcesoEncuesta(Long idProcesoEncuesta) {
		this.idProcesoEncuesta = idProcesoEncuesta;
	}
	public ProcesoDTO getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
	}
	public EncuestaDetalleDTO getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(EncuestaDetalleDTO encuesta) {
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
