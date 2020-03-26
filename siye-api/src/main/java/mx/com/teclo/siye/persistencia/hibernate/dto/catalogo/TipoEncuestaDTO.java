package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE014C_EE_TIPO_ENCUESTAS")
public class TipoEncuestaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8058271907510016963L;

	@Id
	@Column(name = "ID_TIPO_ENCUESTA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoEncuesta;

	@Column(name = "CD_TIPO_ENCUESTA", nullable = false, length = 15)
	private String cdTipoEncuesta;

	@Column(name = "NB_TIPO_ENCUESTA", nullable = false, length = 100)
	private String nbTipoEncuesta;

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
	 * @return the idTipoEncuesta
	 */
	public Long getIdTipoEncuesta() {
		return idTipoEncuesta;
	}

	/**
	 * @param idTipoEncuesta the idTipoEncuesta to set
	 */
	public void setIdTipoEncuesta(Long idTipoEncuesta) {
		this.idTipoEncuesta = idTipoEncuesta;
	}

	/**
	 * @return the cdTipoEncuesta
	 */
	public String getCdTipoEncuesta() {
		return cdTipoEncuesta;
	}

	/**
	 * @param cdTipoEncuesta the cdTipoEncuesta to set
	 */
	public void setCdTipoEncuesta(String cdTipoEncuesta) {
		this.cdTipoEncuesta = cdTipoEncuesta;
	}

	/**
	 * @return the nbTipoEncuesta
	 */
	public String getNbTipoEncuesta() {
		return nbTipoEncuesta;
	}

	/**
	 * @param nbTipoEncuesta the nbTipoEncuesta to set
	 */
	public void setNbTipoEncuesta(String nbTipoEncuesta) {
		this.nbTipoEncuesta = nbTipoEncuesta;
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
