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

import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;

@Entity
@Table(name = "TIE043D_IE_VEHICULO_CONDUCTOR")
public class VehiculoConductorDTO implements Serializable {
	
	private static final long serialVersionUID = 310402532070423565L;
	
	@Id
	@SequenceGenerator(name = "sqie043dIEVehiculo", sequenceName="SQIE043D_IE_VEHICULO_", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqie043dIEVehiculo")
	@Column(name = "ID_VEHICULO_CONDUCTOR", unique = true, nullable = false, precision = 6, scale = 0)
	private Long idVehiculoConductor;
	
	@JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private VehiculoDTO vehiculo;
	
	@JoinColumn(name = "ID_CONDUCTOR", referencedColumnName = "ID_CONDUCTOR", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ConductorDTO conductor;
	
	@Column(name = "NU_ORDEN", nullable = true, precision = 4, scale = 0)
	private Integer nuOrden;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;

	public Long getIdVehiculoConductor() {
		return idVehiculoConductor;
	}

	public void setIdVehiculoConductor(Long idVehiculoConductor) {
		this.idVehiculoConductor = idVehiculoConductor;
	}

	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}

	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	
	

}

