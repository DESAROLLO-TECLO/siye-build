package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE015C_EE_TP_PREGUNTA")
public class TipoPreguntaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4865973465657986034L;

	@Id
	@Column(name = "ID_TP_PREGUNTA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTpPregunta;

	@Column(name = "CD_TP_PREGUNTA", nullable = false, length = 15)
	private String cdTpPregunta;

	@Column(name = "NB_TP_PREGUNTA", nullable = false, length = 100)
	private String nbTpPregunta;
//
//	@Column(name = "NU_ORDEN", nullable = false, precision = 11, scale = 0)
//	private Integer nuOrden;

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
	 * @return the idTpPregunta
	 */
	public Long getIdTpPregunta() {
		return idTpPregunta;
	}

	/**
	 * @param idTpPregunta the idTpPregunta to set
	 */
	public void setIdTpPregunta(Long idTpPregunta) {
		this.idTpPregunta = idTpPregunta;
	}

	/**
	 * @return the cdTpPregunta
	 */
	public String getCdTpPregunta() {
		return cdTpPregunta;
	}

	/**
	 * @param cdTpPregunta the cdTpPregunta to set
	 */
	public void setCdTpPregunta(String cdTpPregunta) {
		this.cdTpPregunta = cdTpPregunta;
	}

	/**
	 * @return the nbTpPregunta
	 */
	public String getNbTpPregunta() {
		return nbTpPregunta;
	}

	/**
	 * @param nbTpPregunta the nbTpPregunta to set
	 */
	public void setNbTpPregunta(String nbTpPregunta) {
		this.nbTpPregunta = nbTpPregunta;
	}

	/**
	 * @return the nuOrden
	 */
//	public Integer getNuOrden() {
//		return nuOrden;
//	}
//
//	/**
//	 * @param nuOrden the nuOrden to set
//	 */
//	public void setNuOrden(Integer nuOrden) {
//		this.nuOrden = nuOrden;
//	}

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
