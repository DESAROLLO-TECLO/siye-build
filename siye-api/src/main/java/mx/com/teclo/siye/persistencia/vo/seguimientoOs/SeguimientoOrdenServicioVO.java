package mx.com.teclo.siye.persistencia.vo.seguimientoOs;

import java.util.List;

public class SeguimientoOrdenServicioVO {
	private String nbModulo;
	private Long nuEnCurso;
	private Long nuCompleta;
	private Long nuProgramado;
	private Long nuNoPRogramado;
	private Long nuIncidencias;
	private List<OrdenServcioDetalleVO> detalleOrdenServicio;
	/**
	 * @return the nbModulo
	 */
	public String getNbModulo() {
		return nbModulo;
	}
	/**
	 * @param nbModulo the nbModulo to set
	 */
	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}
	/**
	 * @return the nuEnCurso
	 */
	public Long getNuEnCurso() {
		return nuEnCurso;
	}
	/**
	 * @param nuEnCurso the nuEnCurso to set
	 */
	public void setNuEnCurso(Long nuEnCurso) {
		this.nuEnCurso = nuEnCurso;
	}
	/**
	 * @return the nuCompleta
	 */
	public Long getNuCompleta() {
		return nuCompleta;
	}
	/**
	 * @param nuCompleta the nuCompleta to set
	 */
	public void setNuCompleta(Long nuCompleta) {
		this.nuCompleta = nuCompleta;
	}
	/**
	 * @return the nuProgramado
	 */
	public Long getNuProgramado() {
		return nuProgramado;
	}
	/**
	 * @param nuProgramado the nuProgramado to set
	 */
	public void setNuProgramado(Long nuProgramado) {
		this.nuProgramado = nuProgramado;
	}
	/**
	 * @return the nuNoPRogramado
	 */
	public Long getNuNoPRogramado() {
		return nuNoPRogramado;
	}
	/**
	 * @param nuNoPRogramado the nuNoPRogramado to set
	 */
	public void setNuNoPRogramado(Long nuNoPRogramado) {
		this.nuNoPRogramado = nuNoPRogramado;
	}
	/**
	 * @return the nuIncidencias
	 */
	public Long getNuIncidencias() {
		return nuIncidencias;
	}
	/**
	 * @param nuIncidencias the nuIncidencias to set
	 */
	public void setNuIncidencias(Long nuIncidencias) {
		this.nuIncidencias = nuIncidencias;
	}
	/**
	 * @return the detalleOrdenServicio
	 */
	public List<OrdenServcioDetalleVO> getDetalleOrdenServicio() {
		return detalleOrdenServicio;
	}
	/**
	 * @param detalleOrdenServicio the detalleOrdenServicio to set
	 */
	public void setDetalleOrdenServicio(List<OrdenServcioDetalleVO> detalleOrdenServicio) {
		this.detalleOrdenServicio = detalleOrdenServicio;
	}
	
	

}
