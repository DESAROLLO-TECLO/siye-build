package mx.com.teclo.siye.persistencia.vo.expedientesImg;

public class ImagenVO {
	
	private Long idExpedienteODS;
	private String nbExpedienteODS;
	private String cdTipoArchivo;
	private Byte[] lbExpedienteODS;
	
	
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

}
