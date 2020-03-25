package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 
 * Clase que representa un Instalador o Tecnico
 * 
 * @author josec.castillo48@gmail.com
 *
 */
@Entity
@Table(name = "TIE045C_IE_RH_INSTALADOR")
public class InstaladorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2882405773306037465L;
	
	@Id
	@Column(name = "ID_RH_INSTALADOR", unique = true, nullable = false, precision = 6, scale = 0)
	private Integer idRhInstalador;
	
	@Column(name = "NB_RH_INSTALADOR", nullable = false, length = 50)
	private String nbRhInstalador;
	
	@Column(name = "NB_PAT_RH_INSTALADOR", nullable = true, length = 50)
	private String nbPatRhInstalador;

	@Column(name = "NB_MAT_RH_INSTALADOR", nullable = true, length = 50)
	private String nbMatRhInstalador;
	
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
	
	public Integer getIdRhInstalador() {
		return idRhInstalador;
	}

	public void setIdRhInstalador(Integer idRhInstalador) {
		this.idRhInstalador = idRhInstalador;
	}

	public String getNbRhInstalador() {
		return nbRhInstalador;
	}

	public void setNbRhInstalador(String nbRhInstalador) {
		this.nbRhInstalador = nbRhInstalador;
	}

	public String getNbPatRhInstalador() {
		return nbPatRhInstalador;
	}

	public void setNbPatRhInstalador(String nbPatRhInstalador) {
		this.nbPatRhInstalador = nbPatRhInstalador;
	}

	public String getNbMatRhInstalador() {
		return nbMatRhInstalador;
	}

	public void setNbMatRhInstalador(String nbMatRhInstalador) {
		this.nbMatRhInstalador = nbMatRhInstalador;
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
