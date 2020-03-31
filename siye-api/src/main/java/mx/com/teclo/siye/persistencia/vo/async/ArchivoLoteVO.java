/**
 * 
 */
package mx.com.teclo.siye.persistencia.vo.async;

import java.util.Date;

/**
 * 
 * Clase que representa un archivo lote de &oacute;rdenes de servicio.
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public class ArchivoLoteVO {
	private Long idLoteOds;
	private String cdLoteOds;
	private String nbLoteOds;
	private Short idStSeguimiento;
	private Integer nuOdsReportados;
	private Integer nuOdsCargados;
	private Integer nuOdsAtendidos;
	private Integer nuOdsPendientes;
	private Integer nuOdsIncidencia;
	private String txLoteOds;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;

	public Long getIdLoteOds() {
		return idLoteOds;
	}

	public void setIdLoteOds(Long idLoteOds) {
		this.idLoteOds = idLoteOds;
	}

	public String getCdLoteOds() {
		return cdLoteOds;
	}

	public void setCdLoteOds(String cdLoteOds) {
		this.cdLoteOds = cdLoteOds;
	}

	public String getNbLoteOds() {
		return nbLoteOds;
	}

	public void setNbLoteOds(String nbLoteOds) {
		this.nbLoteOds = nbLoteOds;
	}

	public Short getIdStSeguimiento() {
		return idStSeguimiento;
	}

	public void setIdStSeguimiento(Short idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
	}

	public Integer getNuOdsReportados() {
		return nuOdsReportados;
	}

	public void setNuOdsReportados(Integer nuOdsReportados) {
		this.nuOdsReportados = nuOdsReportados;
	}

	public Integer getNuOdsCargados() {
		return nuOdsCargados;
	}

	public void setNuOdsCargados(Integer nuOdsCargados) {
		this.nuOdsCargados = nuOdsCargados;
	}

	public Integer getNuOdsAtendidos() {
		return nuOdsAtendidos;
	}

	public void setNuOdsAtendidos(Integer nuOdsAtendidos) {
		this.nuOdsAtendidos = nuOdsAtendidos;
	}

	public Integer getNuOdsPendientes() {
		return nuOdsPendientes;
	}

	public void setNuOdsPendientes(Integer nuOdsPendientes) {
		this.nuOdsPendientes = nuOdsPendientes;
	}

	public Integer getNuOdsIncidencia() {
		return nuOdsIncidencia;
	}

	public void setNuOdsIncidencia(Integer nuOdsIncidencia) {
		this.nuOdsIncidencia = nuOdsIncidencia;
	}

	public String getTxLoteOds() {
		return txLoteOds;
	}

	public void setTxLoteOds(String txLoteOds) {
		this.txLoteOds = txLoteOds;
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

}
