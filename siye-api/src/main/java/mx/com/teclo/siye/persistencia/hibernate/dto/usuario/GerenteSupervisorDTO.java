package mx.com.teclo.siye.persistencia.hibernate.dto.usuario;

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
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;

@Entity
@Table(name = "TIE046D_IE_GERENTE_SUPERVISOR")
public class GerenteSupervisorDTO implements Serializable {

	private static final long serialVersionUID = 8418221651962601813L;

	@Id
	@SequenceGenerator(name = "sqGerenteSupervisor", sequenceName="SQIE046D_IE_GERENTE_S", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqGerenteSupervisor")
	@Column(name = "ID_GERENTE_SUPERVISOR", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idGerenteSupervisor;

	@Column(name = "ID_GERENTE", nullable = false, length = 15)
	private Long gerente;

	@Column(name = "ID_SUPERVISOR", nullable = false, length = 100)
	private Long supervisor;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CENTRO_INSTALACION", referencedColumnName="ID_CENTRO_INSTALACION", insertable=false, updatable=false)
	private CentroInstalacionDTO centroInstalacion;

	@Column(name = "NU_ORDEN", nullable = false, length = 100)
	private Long nuOrden;

	@Column(name = "ST_ACTIVO", nullable = true, length = 100)
	private Boolean stActivo;

	public Long getIdGerenteSupervisor() {
		return idGerenteSupervisor;
	}

	public void setIdGerenteSupervisor(Long idGerenteSupervisor) {
		this.idGerenteSupervisor = idGerenteSupervisor;
	}

	public Long getGerente() {
		return gerente;
	}

	public void setGerente(Long gerente) {
		this.gerente = gerente;
	}

	public Long getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Long supervisor) {
		this.supervisor = supervisor;
	}

	public CentroInstalacionDTO getCentroInstalacion() {
		return centroInstalacion;
	}

	public void setCentroInstalacion(CentroInstalacionDTO centroInstalacion) {
		this.centroInstalacion = centroInstalacion;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	
}