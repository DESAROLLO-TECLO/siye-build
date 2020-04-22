package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

public class PreguntasDetalleVO {

	private Long idOrdenServicio;
	private Long idEncuesta;
	private Long idPegunta;
	private String txPregunta;
	private String txRespuesta;
	
	
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
	 * @return the idEncuesta
	 */
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	/**
	 * @param idEncuesta the idEncuesta to set
	 */
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	/**
	 * @return the idPegunta
	 */
	public Long getIdPegunta() {
		return idPegunta;
	}
	/**
	 * @param idPegunta the idPegunta to set
	 */
	public void setIdPegunta(Long idPegunta) {
		this.idPegunta = idPegunta;
	}
	/**
	 * @return the txPregunta
	 */
	public String getTxPregunta() {
		return txPregunta;
	}
	/**
	 * @param txPregunta the txPregunta to set
	 */
	public void setTxPregunta(String txPregunta) {
		this.txPregunta = txPregunta;
	}
	/**
	 * @return the txRespuesta
	 */
	public String getTxRespuesta() {
		return txRespuesta;
	}
	/**
	 * @param txRespuesta the txRespuesta to set
	 */
	public void setTxRespuesta(String txRespuesta) {
		this.txRespuesta = txRespuesta;
	}

}
