package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;

public class RequestUpdateProcessEncVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7712037188588007108L;
	
	private Long idEncuesta;
	private Long idOrdenServicio;
	private Boolean encInProcess;
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
	 * @return the encInProcess
	 */
	public Boolean getEncInProcess() {
		return encInProcess;
	}
	/**
	 * @param encInProcess the encInProcess to set
	 */
	public void setEncInProcess(Boolean encInProcess) {
		this.encInProcess = encInProcess;
	}
}
