package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;

import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;

public class UsuarioEncuestaDetalleVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 267760952991527421L;
	
	private Long idUsuarioEncuesta;
	private UsuarioVO usuario;
	private Integer nuIntentos;
	private Boolean stAplicaEncuesta;
	private EncuestaDetalleVO encuesta;
	private IntentoDetalleVO intentoDetalleVO;
	/**
	 * @return the idUsuarioEncuesta
	 */
	public Long getIdUsuarioEncuesta() {
		return idUsuarioEncuesta;
	}
	/**
	 * @param idUsuarioEncuesta the idUsuarioEncuesta to set
	 */
	public void setIdUsuarioEncuesta(Long idUsuarioEncuesta) {
		this.idUsuarioEncuesta = idUsuarioEncuesta;
	}
	/**
	 * @return the usuario
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the nuIntentos
	 */
	public Integer getNuIntentos() {
		return nuIntentos;
	}
	/**
	 * @param nuIntentos the nuIntentos to set
	 */
	public void setNuIntentos(Integer nuIntentos) {
		this.nuIntentos = nuIntentos;
	}
	/**
	 * @return the stAplicaEncuesta
	 */
	public Boolean getStAplicaEncuesta() {
		return stAplicaEncuesta;
	}
	/**
	 * @param stAplicaEncuesta the stAplicaEncuesta to set
	 */
	public void setStAplicaEncuesta(Boolean stAplicaEncuesta) {
		this.stAplicaEncuesta = stAplicaEncuesta;
	}
	/**
	 * @return the encuesta
	 */
	public EncuestaDetalleVO getEncuesta() {
		return encuesta;
	}
	/**
	 * @param encuesta the encuesta to set
	 */
	public void setEncuesta(EncuestaDetalleVO encuesta) {
		this.encuesta = encuesta;
	}
	/**
	 * @return the intentoDetalleVO
//	 */
	public IntentoDetalleVO getIntentoDetalleVO() {
		return intentoDetalleVO;
	}
	/**
	 * @param intentoDetalleVO the intentoDetalleVO to set
	 */
	public void setIntentoDetalleVO(IntentoDetalleVO intentoDetalleVO) {
		this.intentoDetalleVO = intentoDetalleVO;
	}
	
}
