package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;

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

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.StEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.VehiculoConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.GerenteSupervisorDTO;

@Entity
@Table(name = "TIE040B_IE_ST_USU_ENCU_INTEN")
public class IeStUsuEncuIntenDTO implements Serializable {
	
	private static final long serialVersionUID = 4814258386727191940L;
	
	@Id
	@SequenceGenerator(name = "sqIeStUsuEn", sequenceName = "SQIE040B_IE_ST_USU_EN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqIeStUsuEn")
	@Column(name = "ID_ST_USU_ENCU_INTEN", unique = true, nullable = false)
	private Long idStUsuEncuInten;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_USU_ENCU_INTENTO", referencedColumnName="ID_USU_ENCU_INTENTO")
	private UsuarioEncuestaIntentosDTO idUsuEncuIntento;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ST_ENCUESTA", referencedColumnName="ID_ST_ENCUESTA")
	private StEncuestaDTO idStEncuesta;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_GERENTE_SUPERVISOR", referencedColumnName="ID_GERENTE_SUPERVISOR")
	private GerenteSupervisorDTO idGerenteSuoervisor;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_VEHICULO_CONDUCTOR", referencedColumnName="ID_VEHICULO_CONDUCTOR")
	private VehiculoConductorDTO idVehiculoConductor;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_RH_INSTALADOR", referencedColumnName="ID_PERSONA")
	private PersonaDTO idRHInstalador;
	
	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 7, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 7, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false)
	private Date fhModificacion;

	public Long getIdStUsuEncuInten() {
		return idStUsuEncuInten;
	}

	public void setIdStUsuEncuInten(Long idStUsuEncuInten) {
		this.idStUsuEncuInten = idStUsuEncuInten;
	}

	public UsuarioEncuestaIntentosDTO getIdUsuEncuIntento() {
		return idUsuEncuIntento;
	}

	public void setIdUsuEncuIntento(UsuarioEncuestaIntentosDTO idUsuEncuIntento) {
		this.idUsuEncuIntento = idUsuEncuIntento;
	}

	public StEncuestaDTO getIdStEncuesta() {
		return idStEncuesta;
	}

	public void setIdStEncuesta(StEncuestaDTO idStEncuesta) {
		this.idStEncuesta = idStEncuesta;
	}

	public GerenteSupervisorDTO getIdGerenteSuoervisor() {
		return idGerenteSuoervisor;
	}

	public void setIdGerenteSuoervisor(GerenteSupervisorDTO idGerenteSuoervisor) {
		this.idGerenteSuoervisor = idGerenteSuoervisor;
	}

	public VehiculoConductorDTO getIdVehiculoConductor() {
		return idVehiculoConductor;
	}

	public void setIdVehiculoConductor(VehiculoConductorDTO idVehiculoConductor) {
		this.idVehiculoConductor = idVehiculoConductor;
	}

	public PersonaDTO getIdRHInstalador() {
		return idRHInstalador;
	}

	public void setIdRHInstalador(PersonaDTO idRHInstalador) {
		this.idRHInstalador = idRHInstalador;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	
	
	

}
