package mx.com.teclo.siye.persistencia.hibernate.dto.procesos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE035C_IE_PROCESOS")
public class IEprocesosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_PROCESO")
	private Long idProceso;
	
	@Column(name = "CD_PROCESO")
	private String cdProceso;
	
	@Column(name = "NB_PROCESO")
	private String nbProceso;
	
	@Column(name = "TX_PROCESO")
	private String txProceso;
	
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION")
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")
	private Date fhModifica;
	
	@Column(name = "NU_MAX_IMAGENES")
	private Long nuMaxImagenes;
	

	/**
	 * @return the nuMaxImagenes
	 */
	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}

	/**
	 * @param nuMaxImagenes the nuMaxImagenes to set
	 */
	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}

	/**
	 * @return the idProceso
	 */
	public Long getIdProceso() {
		return idProceso;
	}

	/**
	 * @param idProceso the idProceso to set
	 */
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * @return the cdProceso
	 */
	public String getCdProceso() {
		return cdProceso;
	}

	/**
	 * @param cdProceso the cdProceso to set
	 */
	public void setCdProceso(String cdProceso) {
		this.cdProceso = cdProceso;
	}

	/**
	 * @return the nbProceso
	 */
	public String getNbProceso() {
		return nbProceso;
	}

	/**
	 * @param nbProceso the nbProceso to set
	 */
	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
	}

	/**
	 * @return the txProceso
	 */
	public String getTxProceso() {
		return txProceso;
	}

	/**
	 * @param txProceso the txProceso to set
	 */
	public void setTxProceso(String txProceso) {
		this.txProceso = txProceso;
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
	 * @return the fhModifica
	 */
	public Date getFhModifica() {
		return fhModifica;
	}

	/**
	 * @param fhModifica the fhModifica to set
	 */
	public void setFhModifica(Date fhModifica) {
		this.fhModifica = fhModifica;
	}
	
	

}
