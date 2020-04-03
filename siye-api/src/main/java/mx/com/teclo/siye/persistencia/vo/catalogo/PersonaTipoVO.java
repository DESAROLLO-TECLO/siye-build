package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class PersonaTipoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5432701010436407705L;
	
	private Integer idPersonaTipo;
	private PersonaCompVO persona;
	private TipoPersonaVO tipoPersona;
	public Integer getIdPersonaTipo() {
		return idPersonaTipo;
	}
	public void setIdPersonaTipo(Integer idPersonaTipo) {
		this.idPersonaTipo = idPersonaTipo;
	}
	public PersonaCompVO getPersona() {
		return persona;
	}
	public void setPersona(PersonaCompVO persona) {
		this.persona = persona;
	}
	public TipoPersonaVO getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersonaVO tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
}
