package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class PersonaGenericaVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2088734124464508286L;
	
	private Long idPersona;
	private String nombre;
	private String cdPersona;
	private String aPaterno;
	private String aMaterno;
	private Long idTipoPersona;
	private Boolean existia;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCdPersona() {
		return cdPersona;
	}
	public void setCdPersona(String cdPersona) {
		this.cdPersona = cdPersona;
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
	public Long getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(Long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public Boolean getExistia() {
		return existia;
	}
	public void setExistia(Boolean existia) {
		this.existia = existia;
	}
}
