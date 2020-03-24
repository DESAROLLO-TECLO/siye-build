/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.siye.persistencia.hibernate.dto.encuesta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TIE008C_EE_ST_CALIFICACION", catalog = "")
public class EstatusCalificacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_ST_CALIFICACION", nullable = false)
	private Long idStCalificacion;
	@Basic(optional = false)
	@Column(name = "CD_ST_CALIFICACION", nullable = false, length = 15)
	private String cdStCalificacion;
	@Basic(optional = false)
	@Column(name = "NB_ST_CALIFICACION", nullable = false, length = 100)
	private String nbStCalificacion;
	@Column(name = "NU_ORDEN")
	private Integer nuOrden;
	@Basic(optional = false)
	@Column(name = "ST_ACTIVO", nullable = false)
	private Integer stActivo;
	@Basic(optional = false)
	@Column(name = "ID_USR_CREACION", nullable = false)
	private Long idUsrCreacion;
	@Basic(optional = false)
	@Column(name = "FH_CREACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhCreacion;
	@Basic(optional = false)
	@Column(name = "ID_USR_MODIFICA", nullable = false)
	private Long idUsrModifica;
	@Basic(optional = false)
	@Column(name = "FH_MODIFICACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhModificacion;

	/**
	 * @return the idStCalificacion
	 */
	public Long getIdStCalificacion() {
		return idStCalificacion;
	}

	/**
	 * @param idStCalificacion the idStCalificacion to set
	 */
	public void setIdStCalificacion(Long idStCalificacion) {
		this.idStCalificacion = idStCalificacion;
	}

	/**
	 * @return the cdStCalificacion
	 */
	public String getCdStCalificacion() {
		return cdStCalificacion;
	}

	/**
	 * @param cdStCalificacion the cdStCalificacion to set
	 */
	public void setCdStCalificacion(String cdStCalificacion) {
		this.cdStCalificacion = cdStCalificacion;
	}

	/**
	 * @return the nbStCalificacion
	 */
	public String getNbStCalificacion() {
		return nbStCalificacion;
	}

	/**
	 * @param nbStCalificacion the nbStCalificacion to set
	 */
	public void setNbStCalificacion(String nbStCalificacion) {
		this.nbStCalificacion = nbStCalificacion;
	}

	/**
	 * @return the nuOrden
	 */
	public Integer getNuOrden() {
		return nuOrden;
	}

	/**
	 * @param nuOrden the nuOrden to set
	 */
	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the idUsrCreacion
	 */
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}

	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	/**
	 * @return the idUsrModifica
	 */
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}

	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

}
