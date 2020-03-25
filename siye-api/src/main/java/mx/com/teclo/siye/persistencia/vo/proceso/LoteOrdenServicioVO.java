package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;

public class LoteOrdenServicioVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 166525209015486035L;
	private Long idLoteOds;
	private String cdLoteOds;
	private String nbLoteOds;
	private Long idStSeguimiento;
	private Long nuOdsReportados;
	private Long nuOdsCargados;
	private Long nuOdsAtendidos;
	private Long nuOdsPendientes;
	private Long nuOdsIncidencia;
	private String txtLoteOds;
	
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
	public Long getIdStSeguimiento() {
		return idStSeguimiento;
	}
	public void setIdStSeguimiento(Long idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
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
	public String getTxtLoteOds() {
		return txtLoteOds;
	}
	public void setTxtLoteOds(String txtLoteOds) {
		this.txtLoteOds = txtLoteOds;
	}
}
