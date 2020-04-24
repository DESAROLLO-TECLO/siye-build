package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

public class MonitoreoIncidenciasVO {
	private Long idCentroInstalacion;
	private String nbModulo;
	private Long totalOrdenes;
	private Long nuOrdenesProgramadas;
	private Long nuOrdenesNoProgramadas;
	private Long nuOrdenesEnCurso;
	private Long nuOrdenesPendientes;
	private Long nuOrdenesAtendidas;
	private Long nuIncidencias;
	
	//-------------------------------------
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	private String nbConcesion;
	private String cdPlacaVehiculo;
	private String cdVIN;
	private String nbProceso;
	private String nbEncuesta;
	private Long nuIncidenciasOS;
	
	public Long getIdCentroInstalacion() {
		return idCentroInstalacion;
	}
	public void setIdCentroInstalacion(Long idCentroInstalacion) {
		this.idCentroInstalacion = idCentroInstalacion;
	}
	public String getNbModulo() {
		return nbModulo;
	}
	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}
	public Long getTotalOrdenes() {
		return totalOrdenes;
	}
	public void setTotalOrdenes(Long totalOrdenes) {
		this.totalOrdenes = totalOrdenes;
	}
	public Long getNuOrdenesProgramadas() {
		return nuOrdenesProgramadas;
	}
	public void setNuOrdenesProgramadas(Long nuOrdenesProgramadas) {
		this.nuOrdenesProgramadas = nuOrdenesProgramadas;
	}
	public Long getNuOrdenesNoProgramadas() {
		return nuOrdenesNoProgramadas;
	}
	public void setNuOrdenesNoProgramadas(Long nuOrdenesNoProgramadas) {
		this.nuOrdenesNoProgramadas = nuOrdenesNoProgramadas;
	}
	public Long getNuOrdenesEnCurso() {
		return nuOrdenesEnCurso;
	}
	public void setNuOrdenesEnCurso(Long nuOrdenesEnCurso) {
		this.nuOrdenesEnCurso = nuOrdenesEnCurso;
	}
	public Long getNuOrdenesPendientes() {
		return nuOrdenesPendientes;
	}
	public void setNuOrdenesPendientes(Long nuOrdenesPendientes) {
		this.nuOrdenesPendientes = nuOrdenesPendientes;
	}
	public Long getNuOrdenesAtendidas() {
		return nuOrdenesAtendidas;
	}
	public void setNuOrdenesAtendidas(Long nuOrdenesAtendidas) {
		this.nuOrdenesAtendidas = nuOrdenesAtendidas;
	}
	public Long getNuIncidencias() {
		return nuIncidencias;
	}
	public void setNuIncidencias(Long nuIncidencias) {
		this.nuIncidencias = nuIncidencias;
	}
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	public String getCdOrdenServicio() {
		return cdOrdenServicio;
	}
	public void setCdOrdenServicio(String cdOrdenServicio) {
		this.cdOrdenServicio = cdOrdenServicio;
	}
	public String getNbConcesion() {
		return nbConcesion;
	}
	public void setNbConcesion(String nbConcesion) {
		this.nbConcesion = nbConcesion;
	}
	public String getCdPlacaVehiculo() {
		return cdPlacaVehiculo;
	}
	public void setCdPlacaVehiculo(String cdPlacaVehiculo) {
		this.cdPlacaVehiculo = cdPlacaVehiculo;
	}
	public String getCdVIN() {
		return cdVIN;
	}
	public void setCdVIN(String cdVIN) {
		this.cdVIN = cdVIN;
	}
	public String getNbProceso() {
		return nbProceso;
	}
	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
	}
	public String getNbEncuesta() {
		return nbEncuesta;
	}
	public void setNbEncuesta(String nbEncuesta) {
		this.nbEncuesta = nbEncuesta;
	}
	public Long getNuIncidenciasOS() {
		return nuIncidenciasOS;
	}
	public void setNuIncidenciasOS(Long nuIncidenciasOS) {
		this.nuIncidenciasOS = nuIncidenciasOS;
	}
}
