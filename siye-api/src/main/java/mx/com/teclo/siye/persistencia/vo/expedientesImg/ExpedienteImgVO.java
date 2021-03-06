package mx.com.teclo.siye.persistencia.vo.expedientesImg;

public class ExpedienteImgVO {
	
	private Long idExpedienteODS;
	private Long idOrdenServicio;
	private Long idOdsEncuesta;
	private Long idProcesoEncuesta;
	private Long idPregunta;
	private String nbExpedienteODS; 
	private String cdTipoArchivo;
	private Byte[] lbExpedienteODS;
	
	
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
	 * @param idOdsEncuesta the idOdsEncuesta to set
	 */
	public void setIdOdsEncuesta(Long idOdsEncuesta) {
		this.idOdsEncuesta = idOdsEncuesta;
	}
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
	public Byte[] getLbExpedienteODS() {
		return lbExpedienteODS;
	}
	/**
	 * @param lbExpedienteODS the lbExpedienteODS to set
	 */
	public void setLbExpedienteODS(Byte[] lbExpedienteODS) {
		this.lbExpedienteODS = lbExpedienteODS;
	}
	
}
