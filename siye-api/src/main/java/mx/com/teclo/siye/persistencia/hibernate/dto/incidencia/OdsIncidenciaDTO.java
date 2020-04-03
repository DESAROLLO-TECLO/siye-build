package mx.com.teclo.siye.persistencia.hibernate.dto.incidencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;

@Entity
@Table(name = "TIE058D_IE_ODS_INCIDENCIA")
public class OdsIncidenciaDTO implements Serializable {
	
	private static final long serialVersionUID = -5365441506721842142L;
	
	
	@Id
	@SequenceGenerator(name = "sqOdsIncidencia", sequenceName = "SQIE058D_IE_ODS_INCID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqOdsIncidencia")
	@Column(name = "ID_ODS_INCIDENCIA", nullable = false)
	private Long idOdsIncidencia;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ORDEN_SERVICIO", referencedColumnName="ID_ORDEN_SERVICIO")
	private OrdenServicioDTO idOrdenServicio;
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_INCIDENCIA", referencedColumnName="ID_INCIDENCIA")
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
