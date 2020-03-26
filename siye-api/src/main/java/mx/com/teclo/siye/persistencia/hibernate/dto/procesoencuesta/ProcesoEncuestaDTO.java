package mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta;

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

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;

@Entity
@Table(name = "TIE037C_IE_PROCESO_ENCUESTA")
public class ProcesoEncuestaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seqProcEncu", sequenceName = "SQIE037C_IE_PROCESO_ENCUESTA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProcEncu")
	@Column(name = "ID_PROCESO_ENCUESTA")
	private Long idProcesoEncuesta;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_PROCESO", referencedColumnName="ID_PROCESO", insertable=false, updatable=false)
	private IEprocesosDTO idProceso;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_ENCUESTA", referencedColumnName="ID_ENCUESTA", insertable=false, updatable=false)
	private EncuestaDetalleDTO idEncuesta;
	
	@Column(name = "NU_ORDEN")
	private Long nuOrden;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;

	/**
	 * @return the idProcesoEncuesta
	 */
	public Long getIdProcesoEncuesta() {
		return idProcesoEncuesta;
	}

	/**
	 * @param idProcesoEncuesta the idProcesoEncuesta to set
	 */
	public void setIdProcesoEncuesta(Long idProcesoEncuesta) {
		this.idProcesoEncuesta = idProcesoEncuesta;
	}

	/**
	 * @return the idProceso
	 */
	public IEprocesosDTO getIdProceso() {
		return idProceso;
	}

	/**
	 * @param idProceso the idProceso to set
	 */
	public void setIdProceso(IEprocesosDTO idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * @return the idEncuesta
	 */
	public EncuestaDetalleDTO getIdEncuesta() {
		return idEncuesta;
	}

	/**
	 * @param idEncuesta the idEncuesta to set
	 */
	public void setIdEncuesta(EncuestaDetalleDTO idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	/**
	 * @return the nuOrden
	 */
	public Long getNuOrden() {
		return nuOrden;
	}

	/**
	 * @param nuOrden the nuOrden to set
	 */
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	/**
	 * @return the stActivo
	 */
	public Boolean getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
}
