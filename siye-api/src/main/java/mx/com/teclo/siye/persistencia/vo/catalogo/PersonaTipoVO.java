package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

public class PersonaTipoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5432701010436407705L;
	
	private Long idPersonaTipo;
	private PersonaCompVO persona;
	private TipoPersonaVO tipoPersona;
	public Long getIdPersonaTipo() {
		return idPersonaTipo;
	}
	public void setIdPersonaTipo(Long idPersonaTipo) {
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
