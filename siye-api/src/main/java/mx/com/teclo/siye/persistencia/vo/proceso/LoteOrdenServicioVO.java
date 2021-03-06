package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;
import java.util.Date;

public class LoteOrdenServicioVO implements Serializable {
	
	private static final long serialVersionUID = 166525209015486035L;
	
	private Long idLoteOds;
	private String cdLoteOds;
	private String nbLoteOds;
	private String nbArchivoFinal;
	private StSeguimientoVO stSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private Long nuOdsReportados;
	private Long nuOdsCargados;
	private Long nuOdsAtendidos;
	private Long nuOdsPendientes;
	private Long nuOdsIncidencia;
	private String txLoteOds;
	private TipoLayoutVO tipoLayout;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	
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
	
	public StSeguimientoVO getStSeguimiento() {
		return stSeguimiento;
	}
	public void setStSeguimiento(StSeguimientoVO stSeguimiento) {
		this.stSeguimiento = stSeguimiento;
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
	public String getTxLoteOds() {
		return txLoteOds;
	}
	public void setTxLoteOds(String txLoteOds) {
		this.txLoteOds = txLoteOds;
	}	
	public TipoLayoutVO getTipoLayout() {
		return tipoLayout;
	}
	public void setTipoLayout(TipoLayoutVO tipoLayout) {
		this.tipoLayout = tipoLayout;
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
	
}
