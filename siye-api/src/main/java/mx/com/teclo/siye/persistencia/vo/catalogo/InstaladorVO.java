package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
/*
 * 
 * Clase que representa un Instalador o Tecnico
 * 
 * @author josec.castillo48@gmail.com
 *
 */
public class InstaladorVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559002806050553531L;
	
	private Integer idRhInstalador;
	private String nbRhInstalador;
	private String nbPatRhInstalador;
	private String nbMatRhInstalador;
	
	public Integer getIdRhInstalador() {
		return idRhInstalador;
	}
	public void setIdRhInstalador(Integer idRhInstalador) {
		this.idRhInstalador = idRhInstalador;
	}
	public String getNbRhInstalador() {
		return nbRhInstalador;
	}
	public void setNbRhInstalador(String nbRhInstalador) {
		this.nbRhInstalador = nbRhInstalador;
	}
	public String getNbPatRhInstalador() {
		return nbPatRhInstalador;
	}
	public void setNbPatRhInstalador(String nbPatRhInstalador) {
		this.nbPatRhInstalador = nbPatRhInstalador;
	}
	public String getNbMatRhInstalador() {
		return nbMatRhInstalador;
	}
	public void setNbMatRhInstalador(String nbMatRhInstalador) {
		this.nbMatRhInstalador = nbMatRhInstalador;
	}
}
