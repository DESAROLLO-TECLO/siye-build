package mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg;

import java.io.Serializable;
import java.util.Date;

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

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.tipoexpediente.TipoExpedienteDTO;

@Entity
@Table(name = "TIE050D_IE_EXPEDIENTES_IMG")
public class ExpedientesImgDTO implements Serializable{
	
	private static final long serialVersionUID = -5086259212784236181L;

	@Id
	@SequenceGenerator(name = "seqExpeImg", sequenceName = "SQIE050D_IE_EXPEDIENT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqExpeImg")
	@Column(name = "ID_EXPEDIENTE_ODS")
	private Long idExpedienteODS;

	@Column(name = "NB_EXPEDIENTE_ODS")
	private String nbExpedienteODS;

	@Column(name = "CD_TIPO_ARCHIVO")
	private String cdTipoArchivo;

	@Column(name = "LB_EXPEDIENTE_ODS")
	private byte[] lbExpedienteODS;

	@Column(name = "TX_RUTA_EXPEDIENTE_ODS")
	private String txRutaExpedienteODS;

	@JoinColumn(name = "ID_ORDEN_SERVICIO")
	@ManyToOne(fetch = FetchType.LAZY)
	private OrdenServicioDTO idOrdenServicio;

	@Column(name = "ID_ODS_ENCUESTA")
	//@ManyToOne(fetch = FetchType.LAZY)
	private Long idOdsEncuesta;

	@JoinColumn(name = "ID_PROCESO_ENCUESTA")
	@ManyToOne(fetch = FetchType.LAZY)
	private ProcesoEncuestaDTO idProcesoEncuesta;

	@Column(name = "ID_PREGUNTA")
	private Long idPregunta;

	@Column(name = "NU_ORDEN")
	private Long nuOrden;

	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION")
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION")
	private Date fhModifica;

	@JoinColumn(name = "ID_INCIDENCIA")
	@ManyToOne(fetch = FetchType.LAZY)
	private IncidenciaDTO incidencia;

	@JoinColumn(name = "ID_TIPO_EXPEDIENTE")
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoExpedienteDTO tipoExpediente;
	

	/**
	 * @return the idExpedienteODS
	 */
	public Long getIdExpedienteODS() {
		return idExpedienteODS;
	}

	/**
	 * @return the incidencia
	 */
	public IncidenciaDTO getIncidencia() {
		return incidencia;
	}

	/**
	 * @param incidencia the incidencia to set
	 */
	public void setIncidencia(IncidenciaDTO incidencia) {
		this.incidencia = incidencia;
	}

	/**
	 * @return the tipoExpediente
	 */
	public TipoExpedienteDTO getTipoExpediente() {
		return tipoExpediente;
	}

	/**
	 * @param tipoExpediente the tipoExpediente to set
	 */
	public void setTipoExpediente(TipoExpedienteDTO tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	/**
	 * @param idExpedienteODS the idExpedienteODS to set
	 */
	public void setIdExpedienteODS(Long idExpedienteODS) {
		this.idExpedienteODS = idExpedienteODS;
	}

	/**
	 * @return the nbExpedienteODS
	 */
	public String getNbExpedienteODS() {
		return nbExpedienteODS;
	}

	/**
	 * @param nbExpedienteODS the nbExpedienteODS to set
	 */
	public void setNbExpedienteODS(String nbExpedienteODS) {
		this.nbExpedienteODS = nbExpedienteODS;
	}

	/**
	 * @return the cdTipoArchivo
	 */
	public String getCdTipoArchivo() {
		return cdTipoArchivo;
	}

	/**
	 * @param cdTipoArchivo the cdTipoArchivo to set
	 */
	public void setCdTipoArchivo(String cdTipoArchivo) {
		this.cdTipoArchivo = cdTipoArchivo;
	}

	/**
	 * @return the lbExpedienteODS
	 */
	public byte[] getLbExpedienteODS() {
		return lbExpedienteODS;
	}

	/**
	 * @param lbExpedienteODS the lbExpedienteODS to set
	 */
	public void setLbExpedienteODS(byte[] lbExpedienteODS) {
		this.lbExpedienteODS = lbExpedienteODS;
	}

	/**
	 * @return the txRutaExpedienteODS
	 */
	public String getTxRutaExpedienteODS() {
		return txRutaExpedienteODS;
	}

	/**
	 * @param txRutaExpedienteODS the txRutaExpedienteODS to set
	 */
	public void setTxRutaExpedienteODS(String txRutaExpedienteODS) {
		this.txRutaExpedienteODS = txRutaExpedienteODS;
	}

	/**
	 * @return the idOrdenServicio
	 */
	public OrdenServicioDTO getIdOrdenServicio() {
		return idOrdenServicio;
	}

	/**
	 * @param idOrdenServicio the idOrdenServicio to set
	 */
	public void setIdOrdenServicio(OrdenServicioDTO idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}

	/**
	 * @return the idOdsEncuesta
	 */
	public Long getIdOdsEncuesta() {
		return idOdsEncuesta;
	}

	/**
	 * @param idOdsEncuesta the idOdsEncuesta to set
	 */
	public void setIdOdsEncuesta(Long idOdsEncuesta) {
		this.idOdsEncuesta = idOdsEncuesta;
	}

	/**
	 * @return the idProcesoEncuesta
	 */
	public ProcesoEncuestaDTO getIdProcesoEncuesta() {
		return idProcesoEncuesta;
	}

	/**
	 * @param idProcesoEncuesta the idProcesoEncuesta to set
	 */
	public void setIdProcesoEncuesta(ProcesoEncuestaDTO idProcesoEncuesta) {
		this.idProcesoEncuesta = idProcesoEncuesta;
	}

	/**
	 * @return the idPregunta
	 */
	public Long getIdPregunta() {
		return idPregunta;
	}

	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
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

	/**
	 * @return the idUsrCreacion
	 */
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}

	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	/**
	 * @return the idUsrModifica
	 */
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	/**
	 * @return the fhModifica
	 */
	public Date getFhModifica() {
		return fhModifica;
	}

	/**
	 * @param fhModifica the fhModifica to set
	 */
	public void setFhModifica(Date fhModifica) {
		this.fhModifica = fhModifica;
	}

	
}
