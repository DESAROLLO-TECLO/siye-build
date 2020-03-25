package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(name = "ID_CONDUCTOR", unique = true, nullable = false, precision = 4, scale = 0)
	private Integer idConductor;
	
	@Column(name = "NB_CONDUCTOR", nullable = false, length = 50)
	private String nbConductor;
	
	@Column(name = "NB_APEPAT_CONDUCTOR", nullable = true, length = 50)
	private String nbApepatConductor;
	
	@Column(name = "NB_APEMAT_CONDUCTOR", nullable = true, length = 50)
	private String nbApematConductor;
	
	@Column(name = "NU_ORDEN", nullable = false, precision = 4, scale = 0)
	private Integer nuOrden;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 7, scale = 0)
	private Integer idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 7, scale = 0)
	private Integer idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false)
	private Date fhModificacion;
	
	public Integer getIdConductor() {
		return idConductor;
	}

	public void setIdConductor(Integer idConductor) {
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

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Integer getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Integer idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Integer getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Integer idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
}
