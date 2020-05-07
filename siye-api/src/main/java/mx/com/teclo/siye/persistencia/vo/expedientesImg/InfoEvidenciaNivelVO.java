package mx.com.teclo.siye.persistencia.vo.expedientesImg;

import java.util.List;

public class InfoEvidenciaNivelVO {	
	
	private Integer tieneImagen;
	private String fechaIni;
	private String fechaFin;
	private String nbSupervisor;
	private String nbInstalador;
	private String nbTrasportista;
	private Boolean activo;
	private List<ImagenVO> imagenes;
	
	public Integer getTieneImagen() {
		return tieneImagen;
	}
	public void setTieneImagen(Integer tieneImagen) {
		this.tieneImagen = tieneImagen;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNbSupervisor() {
		return nbSupervisor;
	}
	public void setNbSupervisor(String nbSupervisor) {
		this.nbSupervisor = nbSupervisor;
	}
	public String getNbInstalador() {
		return nbInstalador;
	}
	public void setNbInstalador(String nbInstalador) {
		this.nbInstalador = nbInstalador;
	}
	public String getNbTrasportista() {
		return nbTrasportista;
	}
	public void setNbTrasportista(String nbTrasportista) {
		this.nbTrasportista = nbTrasportista;
	}
	public List<ImagenVO> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<ImagenVO> imagenes) {
		this.imagenes = imagenes;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
