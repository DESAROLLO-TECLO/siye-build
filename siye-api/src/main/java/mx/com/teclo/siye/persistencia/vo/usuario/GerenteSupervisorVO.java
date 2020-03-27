package mx.com.teclo.siye.persistencia.vo.usuario;

import java.io.Serializable;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;


public class GerenteSupervisorVO implements Serializable {

	private static final long serialVersionUID = -3397102013147676157L;
	
	private Long idGerenteSupervisor;
	private Long idGerente;
	private Long idSupervisor;
	private CentroInstalacionDTO centroInstalacion;
	private Long nuOrden;
	private Long stActivo;
	
	public Long getIdGerenteSupervisor() {
		return idGerenteSupervisor;
	}
	public void setIdGerenteSupervisor(Long idGerenteSupervisor) {
		this.idGerenteSupervisor = idGerenteSupervisor;
	}
	public Long getIdGerente() {
		return idGerente;
	}
	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}
	public Long getIdSupervisor() {
		return idSupervisor;
	}
	public void setIdSupervisor(Long idSupervisor) {
		this.idSupervisor = idSupervisor;
	}
	public CentroInstalacionDTO getCentroInstalacion() {
		return centroInstalacion;
	}
	public void setCentroInstalacion(CentroInstalacionDTO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}
	public Long getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}
	public Long getStActivo() {
		return stActivo;
	}
	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}

}
