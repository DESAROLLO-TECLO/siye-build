package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class PersonaGenericaVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2088734124464508286L;
	
	private Integer idPersona;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private Boolean existia;
	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public Boolean getExistia() {
		return existia;
	}
	public void setExistia(Boolean existia) {
		this.existia = existia;
	}
}
