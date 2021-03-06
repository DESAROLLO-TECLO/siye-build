package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
/*
 * 
 * Clase que representa un Instalador o Tecnico
 * 
 * @author josec.castillo48@gmail.com
 *
 */
public class PersonaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559002806050553531L;
	
	private Long idPersona;
	private String nbPersona;
	private String cdPersona;
	private String nbPatPersona;
	private String nbMatPersona;
	private Integer idTipoPersona;
	private Boolean existia;
	private Long stPersona;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNbPersona() {
		return nbPersona;
	}
	
	public String getCdPersona() {
		return cdPersona;
	}
	public void setCdPersona(String cdPersona) {
		this.cdPersona = cdPersona;
	}
	public void setNbPersona(String nbPersona) {
		this.nbPersona = nbPersona;
	}
	public String getNbPatPersona() {
		return nbPatPersona;
	}
	public void setNbPatPersona(String nbPatPersona) {
		this.nbPatPersona = nbPatPersona;
	}
	public String getNbMatPersona() {
		return nbMatPersona;
	}
	public void setNbMatPersona(String nbMatPersona) {
		this.nbMatPersona = nbMatPersona;
	}
	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public Boolean getExistia() {
		return existia;
	}
	public void setExistia(Boolean existia) {
		this.existia = existia;
	}
	public Long getStPersona() {
		return stPersona;
	}
	public void setStPersona(Long stPersona) {
		this.stPersona = stPersona;
	}
	
}
