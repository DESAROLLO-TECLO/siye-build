package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.util.Date;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;



public class UsuarioEncuestaVO {

	public OrdenServicioVO getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(OrdenServicioVO ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	private Long idUsuarioEncuesta;
	private OrdenServicioVO ordenServicio;
	private Integer nuIntegerentos;
	private Boolean stAplicaEncuesta;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	private EncuestaVO encuesta;
	private List<UsuarioEncuestaIntentosVO> usuarioEncuestaIntentos;

	private Integer valor;
	private Long intento;

	public Long getIdUsuarioEncuesta() {
		return idUsuarioEncuesta;
	}

	public void setIdUsuarioEncuesta(Long idUsuarioEncuesta) {
		this.idUsuarioEncuesta = idUsuarioEncuesta;
	}



	public Integer getNuIntegerentos() {
		return nuIntegerentos;
	}

	public void setNuIntegerentos(Integer nuIntegerentos) {
		this.nuIntegerentos = nuIntegerentos;
	}

	public Boolean getStAplicaEncuesta() {
		return stAplicaEncuesta;
	}

	public void setStAplicaEncuesta(Boolean stAplicaEncuesta) {
		this.stAplicaEncuesta = stAplicaEncuesta;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public EncuestaVO getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(EncuestaVO encuesta) {
		this.encuesta = encuesta;
	}

	/**
	 * @return the usuarioEncuestaIntentos
	 */
	public List<UsuarioEncuestaIntentosVO> getUsuarioEncuestaIntentos() {
		return usuarioEncuestaIntentos;
	}

	/**
	 * @param usuarioEncuestaIntentos the usuarioEncuestaIntentos to set
	 */
	public void setUsuarioEncuestaIntentos(List<UsuarioEncuestaIntentosVO> usuarioEncuestaIntentos) {
		this.usuarioEncuestaIntentos = usuarioEncuestaIntentos;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	/**
	 * @return the intento
	 */
	public Long getIntento() {
		return intento;
	}

	/**
	 * @param intento the intento to set
	 */
	public void setIntento(Long intento) {
		this.intento = intento;
	}

}
