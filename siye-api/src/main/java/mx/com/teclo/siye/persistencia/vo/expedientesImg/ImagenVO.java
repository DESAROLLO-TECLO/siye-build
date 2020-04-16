package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

public class ImagenVO {
	
	private Long idExpedienteODS;
	private Long idOrdenServicio;
	private Long idOdsEncuesta;
	private Long idProceso;
	private Long idPregunta;
	private Long idIncidencia;
	private Long idTipoExpediente;
	private Long nuOrden;
	private String nbExpedienteODS; 
	private String cdTipoArchivo;
	private byte[] lbExpedienteODS;
	private String nbNivel;
	private TipoExpedienteVO tipoExpediente;
	
	/**
	 * @return the idExpedienteODS
	 */
	public Long getIdExpedienteODS() {
		return idExpedienteODS;
	}
	/**
	 * @param idExpedienteODS the idExpedienteODS to set
	 */
	public void setIdExpedienteODS(Long idExpedienteODS) {
		this.idExpedienteODS = idExpedienteODS;
	}
	/**
	 * @return the idOrdenServicio
	 */
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	/**
	 * @param idOrdenServicio the idOrdenServicio to set
	 */
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	/**
	 * @return the idOdsEncuesta
	 */
	public Long getIdOdsEncuesta() {
		return idOdsEncuesta;
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
	 * @return the idIncidencia
	 */
	public Long getIdIncidencia() {
		return idIncidencia;
	}
	/**
	 * @param idIncidencia the idIncidencia to set
	 */
	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	/**
	 * @return the idTipoExpediente
	 */
	public Long getIdTipoExpediente() {
		return idTipoExpediente;
	}
	/**
	 * @param idTipoExpediente the idTipoExpediente to set
	 */
	public void setIdTipoExpediente(Long idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}
	/**
	 * @return the idProceso
	 */
	public Long getIdProceso() {
		return idProceso;
	}
	/**
	 * @param idProceso the idProceso to set
	 */
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
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
	 * @return the nbNivel
	 */
	public String getNbNivel() {
		return nbNivel;
	}
	/**
	 * @param nbNivel the nbNivel to set
	 */
	public void setNbNivel(String nbNivel) {
		this.nbNivel = nbNivel;
	}
	public TipoExpedienteVO getTipoExpediente() {
		return tipoExpediente;
	}
	public void setTipoExpediente(TipoExpedienteVO tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}
	
}
