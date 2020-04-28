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
	private String nbArchivoFinal;
	private Long idStSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private String cdColor;
	private Long nuOdsReportados;
	private Long nuOdsCargados;
	private Long nuOdsAtendidos;
	private Long nuOdsPendientes;
	private Long nuOdsIncidencia;
	private Long nuOdsIgnoradas;
	private String txLoteOds;
	private Long idTipoLayout;
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

	public String getNbArchivoFinal() {
		return nbArchivoFinal;
	}

	public void setNbArchivoFinal(String nbArchivoFinal) {
		this.nbArchivoFinal = nbArchivoFinal;
	}

	public Long getIdStSeguimiento() {
		return idStSeguimiento;
	}

	public void setIdStSeguimiento(Long idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
	}

	public String getCdStSeguimiento() {
		return cdStSeguimiento;
	}

	public void setCdStSeguimiento(String cdStSeguimiento) {
		this.cdStSeguimiento = cdStSeguimiento;
	}

	public String getNbStSeguimiento() {
		return nbStSeguimiento;
	}

	public void setNbStSeguimiento(String nbStSeguimiento) {
		this.nbStSeguimiento = nbStSeguimiento;
	}

	public String getCdColor() {
		return cdColor;
	}

	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
	}

	public Long getNuOdsReportados() {
		return nuOdsReportados;
	}

	public void setNuOdsReportados(Long nuOdsReportados) {
		this.nuOdsReportados = nuOdsReportados;
	}

	public Long getNuOdsCargados() {
		return nuOdsCargados;
	}

	public void setNuOdsCargados(Long nuOdsCargados) {
		this.nuOdsCargados = nuOdsCargados;
	}

	public Long getNuOdsAtendidos() {
		return nuOdsAtendidos;
	}

	public void setNuOdsAtendidos(Long nuOdsAtendidos) {
		this.nuOdsAtendidos = nuOdsAtendidos;
	}

	public Long getNuOdsPendientes() {
		return nuOdsPendientes;
	}

	public void setNuOdsPendientes(Long nuOdsPendientes) {
		this.nuOdsPendientes = nuOdsPendientes;
	}

	public Long getNuOdsIncidencia() {
		return nuOdsIncidencia;
	}

	public void setNuOdsIncidencia(Long nuOdsIncidencia) {
		this.nuOdsIncidencia = nuOdsIncidencia;
	}

	public Long getNuOdsIgnoradas() {
		return nuOdsIgnoradas;
	}

	public void setNuOdsIgnoradas(Long nuOdsIgnoradas) {
		this.nuOdsIgnoradas = nuOdsIgnoradas;
	}

	public String getTxLoteOds() {
		return txLoteOds;
	}

	public void setTxLoteOds(String txLoteOds) {
		this.txLoteOds = txLoteOds;
	}

	public Long getIdTipoLayout() {
		return idTipoLayout;
	}

	public void setIdTipoLayout(Long idTipoLayout) {
		this.idTipoLayout = idTipoLayout;
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
