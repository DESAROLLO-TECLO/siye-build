package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE018C_EE_ST_ENCUESTAS")
public class StEncuestaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771263775950160399L;

	@Id
	@Column(name = "ID_ST_ENCUESTA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idStEncuesta;

	@Column(name = "CD_ST_ENCUESTAS", nullable = false, length = 15)
	private String cdStEncuesta;

	@Column(name = "NB_ST_ENCUESTA", nullable = false, length = 100)
	private String nbStEncuesta;
	
	@Column(name = "CD_COLOR", nullable = false, length = 7)
	private String cdColor;

	@Column(name = "NU_ORDEN", nullable = false, precision = 11, scale = 0)
	private Integer nuOrden;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	/**
	 * @return the idStEncuesta
	 */
	public Long getIdStEncuesta() {
		return idStEncuesta;
	}

	/**
	 * @param idStEncuesta the idStEncuesta to set
	 */
	public void setIdStEncuesta(Long idStEncuesta) {
		this.idStEncuesta = idStEncuesta;
	}

	/**
	 * @return the cdStEncuesta
	 */
	public String getCdStEncuesta() {
		return cdStEncuesta;
	}

	/**
	 * @param cdStEncuesta the cdStEncuesta to set
	 */
	public void setCdStEncuesta(String cdStEncuesta) {
		this.cdStEncuesta = cdStEncuesta;
	}

	/**
	 * @return the nbStEncuesta
	 */
	public String getNbStEncuesta() {
		return nbStEncuesta;
	}

	/**
	 * @param nbStEncuesta the nbStEncuesta to set
	 */
	public void setNbStEncuesta(String nbStEncuesta) {
		this.nbStEncuesta = nbStEncuesta;
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

	/**
	 * @return the cdColor
	 */
	public String getCdColor() {
		return cdColor;
	}

	/**
	 * @param cdColor the cdColor to set
	 */
	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
	}
}
