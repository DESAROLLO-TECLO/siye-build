package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * 
 * Clase que representa un Conductor o Transportista
 * 
 * @author josec.castillo48@gmail.com
 *
 */
@Entity
@Table(name = "TIE044C_IE_CONDUCTOR")
public class ConductorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5101564973860399717L;
	
	@Id
	@SequenceGenerator(name = "sqie044cIEConductor", sequenceName="SQIE044C_IE_CONDUCTOR", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqie044cIEConductor")
	@Column(name = "ID_CONDUCTOR", unique = true, nullable = false, precision = 4, scale = 0)
	private Long idConductor;
	
	@Column(name = "NB_CONDUCTOR", nullable = false, length = 50)
	private String nbConductor;
	
	@Column(name = "NB_APEPAT_CONDUCTOR", nullable = true, length = 50)
	private String nbApepatConductor;
	
	@Column(name = "NB_APEMAT_CONDUCTOR", nullable = true, length = 50)
	private String nbApematConductor;
	
	@Column(name = "NU_ORDEN", nullable = true, precision = 4, scale = 0)
	private Integer nuOrden;

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
	
	@Column(name = "ST_CONDUCTOR", nullable = false)
	private Long stCondutor;

	
	public Long getIdConductor() {
		return idConductor;
	}

	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}

	public String getNbConductor() {
		return nbConductor;
	}

	public void setNbConductor(String nbConductor) {
		this.nbConductor = nbConductor;
	}

	public String getNbApepatConductor() {
		return nbApepatConductor;
	}

	public void setNbApepatConductor(String nbApepatConductor) {
		this.nbApepatConductor = nbApepatConductor;
	}

	public String getNbApematConductor() {
		return nbApematConductor;
	}

	public void setNbApematConductor(String nbApematConductor) {
		this.nbApematConductor = nbApematConductor;
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

	public Long getStCondutor() {
		return stCondutor;
	}

	public void setStCondutor(Long stCondutor) {
		this.stCondutor = stCondutor;
	}
	
}
