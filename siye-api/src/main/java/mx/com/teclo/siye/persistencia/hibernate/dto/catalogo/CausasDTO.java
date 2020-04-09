package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TIE061C_IE_CAUSAS")
public class CausasDTO implements Serializable {
	
	private static final long serialVersionUID = 3771263775950160399L;
	
	@Id
	@Column(name = "ID_CAUSA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idCausa;

	@Column(name = "CD_CAUSA", nullable = false, length = 15)
	private String cdCausa;

	@Column(name = "NB_CAUSA", nullable = false, length = 100)
	private String nbCausa;
	
	@Column(name = "TX_CAUSA", nullable = false, length = 7)
	private String txCausa;

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
	

	public Long getIdCausa() {
		return idCausa;
	}

	public void setIdCausa(Long idCausa) {
		this.idCausa = idCausa;
	}

	public String getCdCausa() {
		return cdCausa;
	}

	public void setCdCausa(String cdCausa) {
		this.cdCausa = cdCausa;
	}

	public String getNbCausa() {
		return nbCausa;
	}

	public void setNbCausa(String nbCausa) {
		this.nbCausa = nbCausa;
	}

	public String getTxCausa() {
		return txCausa;
	}

	public void setTxCausa(String txCausa) {
		this.txCausa = txCausa;
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
	
	




	

}
