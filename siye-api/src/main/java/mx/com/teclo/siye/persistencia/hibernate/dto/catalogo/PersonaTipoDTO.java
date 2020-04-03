package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.comun.EstatusPasswordDTO;

@Entity
@Table(name = "TIE059D_IE_PERSONA_TIPO")
public class PersonaTipoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 310402532070423565L;
	
//	ID_PERSONA_TIPO	NUMBER(6,0)	No
//	ID_PERSONA		NUMBER(6,0)	Yes
//	ID_TIPO_PERSONA	NUMBER(6,0)	Yes
	
	@Id
	@SequenceGenerator(name = "sqie059dIEPersonaT", sequenceName="SQIE059D_IE_PERSONA_T", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqie059dIEPersonaT")
	@Column(name = "ID_PERSONA_TIPO", unique = true, nullable = false, precision = 6, scale = 0)
	private Integer idPersonaTipo;

	@JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PersonaDTO persona;

	@JoinColumn(name = "ID_TIPO_PERSONA", referencedColumnName = "ID_TIPO_PERSONA", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private TipoPersonaDTO tipoPersona;

	public Integer getIdPersonaTipo() {
		return idPersonaTipo;
	}

	public void setIdPersonaTipo(Integer idPersonaTipo) {
		this.idPersonaTipo = idPersonaTipo;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public TipoPersonaDTO getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersonaDTO tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
}
