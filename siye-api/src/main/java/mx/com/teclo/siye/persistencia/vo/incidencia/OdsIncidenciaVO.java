package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;



import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;

public class OdsIncidenciaVO implements Serializable  {
	
	private static final long serialVersionUID = 1374927111939946350L;
	
	
	private Long idOdsIncidencia;
	
	private OrdenServicioDTO idOrdenServicio;
	
	private IncidenciaDTO idIncidencia;

	public Long getIdOdsIncidencia() {
		return idOdsIncidencia;
	}

	public void setIdOdsIncidencia(Long idOdsIncidencia) {
		this.idOdsIncidencia = idOdsIncidencia;
	}

	public OrdenServicioDTO getIdOrdenServicio() {
		return idOrdenServicio;
	}

	public void setIdOrdenServicio(OrdenServicioDTO idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}

	public IncidenciaDTO getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(IncidenciaDTO idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	

	

}
