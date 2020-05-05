package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

public class PreguntasDetalleVO {

	private Long idOrdenServicio;
	private Long idEncuesta;
	private String nbEncuesta;
	private Long idPegunta;
	private String txPregunta;
	private String txRespuesta;
	private List<ImagenVO> listaImagen;
	
	
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
	/**
	 * @return the listaImagen
	 */
	public List<ImagenVO> getListaImagen() {
		return listaImagen;
	}
	/**
	 * @param listaImagen the listaImagen to set
	 */
	public void setListaImagen(List<ImagenVO> listaImagen) {
		this.listaImagen = listaImagen;
	}
	/**
	 * @return the nbEncuesta
	 */
	public String getNbEncuesta() {
		return nbEncuesta;
	}
	/**
	 * @param nbEncuesta the nbEncuesta to set
	 */
	public void setNbEncuesta(String nbEncuesta) {
		this.nbEncuesta = nbEncuesta;
	}

}
