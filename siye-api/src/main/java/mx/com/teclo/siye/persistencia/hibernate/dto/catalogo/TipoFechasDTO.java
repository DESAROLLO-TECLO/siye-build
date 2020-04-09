package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE066C_TIPO_FECHAS")
public class TipoFechasDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8058271907510016963L;
	
	@Id
	@Column(name = "ID_TIPO_FECHA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoFecha;
	
	@Column(name = "CD_TIPO_FECHA")
	private String cdTipoFecha;
	
	@Column(name = "NB_TIPO_FECHA")
	private String nbTipoFecha;
	
	@Column(name = "TX_TIPO_FECHA")
	private String txTipoFecha;
	
	@Column(name = "NU_ORDEN")
	private Long nuOrden;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION")
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;
	
	

	/**
	 * @return the idTipoFecha
	 */
	public Long getIdTipoFecha() {
		return idTipoFecha;
	}

	/**
	 * @param idTipoFecha the idTipoFecha to set
	 */
	public void setIdTipoFecha(Long idTipoFecha) {
		this.idTipoFecha = idTipoFecha;
	}

	/**
	 * @return the cdTipoFecha
	 */
	public String getCdTipoFecha() {
		return cdTipoFecha;
	}

	/**
	 * @param cdTipoFecha the cdTipoFecha to set
	 */
	public void setCdTipoFecha(String cdTipoFecha) {
		this.cdTipoFecha = cdTipoFecha;
	}

	/**
	 * @return the nbTipoFecha
	 */
	public String getNbTipoFecha() {
		return nbTipoFecha;
	}

	/**
	 * @param nbTipoFecha the nbTipoFecha to set
	 */
	public void setNbTipoFecha(String nbTipoFecha) {
		this.nbTipoFecha = nbTipoFecha;
	}

	/**
	 * @return the txTipoFecha
	 */
	public String getTxTipoFecha() {
		return txTipoFecha;
	}

	/**
	 * @param txTipoFecha the txTipoFecha to set
	 */
	public void setTxTipoFecha(String txTipoFecha) {
		this.txTipoFecha = txTipoFecha;
	}

	/**
	 * @return the nuOrden
	 */
	public Long getNuOrden() {
		return nuOrden;
	}

	/**
	 * @param nuOrden the nuOrden to set
	 */
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	/**
	 * @return the stActivo
	 */
	public Boolean getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Boolean stActivo) {
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
