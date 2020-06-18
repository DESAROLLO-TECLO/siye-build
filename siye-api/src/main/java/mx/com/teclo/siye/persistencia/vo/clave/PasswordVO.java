package mx.com.teclo.siye.persistencia.vo.clave;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PasswordVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4024993062190443345L;

	private Long idPassword;
	private String txValor;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm",timezone = "America/Mexico_City")
	private Date fhVigenciaIni;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm",timezone = "America/Mexico_City")
	private Date fhVigenciaFin;
	private Integer stActual;
	private Integer stActivo;
//	private StPasswordVO stPassword;

	/**
	 * @return the idPassword
	 */
	public Long getIdPassword() {
		return idPassword;
	}

	/**
	 * @param idPassword the idPassword to set
	 */
	public void setIdPassword(Long idPassword) {
		this.idPassword = idPassword;
	}

	/**
	 * @return the txValor
	 */
	public String getTxValor() {
		return txValor;
	}

	/**
	 * @param txValor the txValor to set
	 */
	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

	/**
	 * @return the fhVigenciaIni
	 */
	public Date getFhVigenciaIni() {
		return fhVigenciaIni;
	}

	/**
	 * @param fhVigenciaIni the fhVigenciaIni to set
	 */
	public void setFhVigenciaIni(Date fhVigenciaIni) {
		this.fhVigenciaIni = fhVigenciaIni;
	}

	/**
	 * @return the fhVigenciaFin
	 */
	public Date getFhVigenciaFin() {
		return fhVigenciaFin;
	}

	/**
	 * @param fhVigenciaFin the fhVigenciaFin to set
	 */
	public void setFhVigenciaFin(Date fhVigenciaFin) {
		this.fhVigenciaFin = fhVigenciaFin;
	}

	/**
	 * @return the stActual
	 */
	public Integer getStActual() {
		return stActual;
	}

	/**
	 * @param stActual the stActual to set
	 */
	public void setStActual(Integer stActual) {
		this.stActual = stActual;
	}

	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

//	public StPasswordVO getStPassword() {
//		return stPassword;
//	}
//
//	public void setStPassword(StPasswordVO stPassword) {
//		this.stPassword = stPassword;
//	}
}
