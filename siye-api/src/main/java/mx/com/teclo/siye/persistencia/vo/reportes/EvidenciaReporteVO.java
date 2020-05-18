package mx.com.teclo.siye.persistencia.vo.reportes;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

public class EvidenciaReporteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -311675475933587674L;
	private String nombre;
	private String fhInicio;
	private String fhFin;
	private String duracion;
	private String supervisores;
	private String instaladores;
	private String trasportistas;
	private List<ImagenesEvidenciaReporteVO> listImagenes;
//	private List<ByteArrayInputStream> listImagenes;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFhInicio() {
		return fhInicio;
	}
	public void setFhInicio(String fhInicio) {
		this.fhInicio = fhInicio;
	}
	public String getFhFin() {
		return fhFin;
	}
	public void setFhFin(String fhFin) {
		this.fhFin = fhFin;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getSupervisores() {
		return supervisores;
	}
	public void setSupervisores(String supervisores) {
		this.supervisores = supervisores;
	}
	public String getInstaladores() {
		return instaladores;
	}
	public void setInstaladores(String instaladores) {
		this.instaladores = instaladores;
	}
	public String getTrasportistas() {
		return trasportistas;
	}
	public void setTrasportistas(String trasportistas) {
		this.trasportistas = trasportistas;
	}
//	public List<ByteArrayInputStream> getListImagenes() {
//		return listImagenes;
//	}
//	public void setListImagenes(List<ByteArrayInputStream> listImagenes) {
//		this.listImagenes = listImagenes;
//	}
	public List<ImagenesEvidenciaReporteVO> getListImagenes() {
		return listImagenes;
	}
	public void setListImagenes(List<ImagenesEvidenciaReporteVO> listImagenes) {
		this.listImagenes = listImagenes;
	}
}
