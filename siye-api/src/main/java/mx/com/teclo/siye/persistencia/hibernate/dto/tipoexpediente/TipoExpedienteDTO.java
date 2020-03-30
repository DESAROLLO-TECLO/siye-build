package mx.com.teclo.siye.persistencia.hibernate.dto.tipoexpediente;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TIE054C_IE_TIPO_EXPEDIENTE")
public class TipoExpedienteDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_TIPO_EXPEDIENTE")
	private Long idDispositivo;
	
	@Column(name = "CD_TIPO_EXPEDIENTE")
	private String cdTipoExpediente;
	
	@Column(name="NB_TIPO_EXPEDIENTE")
	private String nbTipoExpediente;
	
	@Column(name="NU_ORDEN")
	private Long nuOrden;
	
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name="ID_USR_CREACION")
	private Long idUserCreacion;
	
	@Column(name="FH_CREACION")
	private Date fhCreacion;
	
	@Column(name="ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	@Column(name="FH_MODIFICACION")
	private Date fhModificacion;

	/**
	 * @return the idDispositivo
	 */
	public Long getIdDispositivo() {
		return idDispositivo;
	}

	/**
	 * @param idDispositivo the idDispositivo to set
	 */
	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	/**
	 * @return the cdTipoExpediente
	 */
	public String getCdTipoExpediente() {
		return cdTipoExpediente;
	}

	/**
	 * @param cdTipoExpediente the cdTipoExpediente to set
	 */
	public void setCdTipoExpediente(String cdTipoExpediente) {
		this.cdTipoExpediente = cdTipoExpediente;
	}

	/**
	 * @return the nbTipoExpediente
	 */
	public String getNbTipoExpediente() {
		return nbTipoExpediente;
	}

	/**
	 * @param nbTipoExpediente the nbTipoExpediente to set
	 */
	public void setNbTipoExpediente(String nbTipoExpediente) {
		this.nbTipoExpediente = nbTipoExpediente;
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
	 * @return the idUserCreacion
	 */
	public Long getIdUserCreacion() {
		return idUserCreacion;
	}

	/**
	 * @param idUserCreacion the idUserCreacion to set
	 */
	public void setIdUserCreacion(Long idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
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
