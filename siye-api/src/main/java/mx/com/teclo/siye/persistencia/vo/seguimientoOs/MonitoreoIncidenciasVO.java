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
}
