package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE067S_IE_TBL_CATALOGOS")
public class TblCatalogosDTO implements Serializable {
	
	private static final long serialVersionUID = 7502148761289685252L;

	@Id
	@Column(name = "ID_TBL_CATALOGO", unique = true, nullable = false, insertable = false)
	private Long idTblCatalogo;

	@Column(name = "ID_PADRE_TBL_CATALOGO")
	private Long idPadreTblCatalogo;

	@Column(name = "CD_TBL_CATALOGO")
	private String cdTblCatalogo;

	@Column(name = "NB_TBL_CATALOGO")
	private String nbTblCatalogo;

	@Column(name = "TX_TBL_CATALOGO")
	private String txTblCatalogo;

	@Column(name = "TX_URI")
	private String txUri;

	@Column(name = "NU_ORDEN")
	private Long nuOrden;

	@Column(name = "ST_MOSTRAR")
	private Boolean stMostrar;
	
	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdTblCatalogo() {
		return idTblCatalogo;
	}

	public void setIdTblCatalogo(Long idTblCatalogo) {
		this.idTblCatalogo = idTblCatalogo;
	}

	public Long getIdPadreTblCatalogo() {
		return idPadreTblCatalogo;
	}

	public void setIdPadreTblCatalogo(Long idPadreTblCatalogo) {
		this.idPadreTblCatalogo = idPadreTblCatalogo;
	}

	public String getCdTblCatalogo() {
		return cdTblCatalogo;
	}

	public void setCdTblCatalogo(String cdTblCatalogo) {
		this.cdTblCatalogo = cdTblCatalogo;
	}

	public String getNbTblCatalogo() {
		return nbTblCatalogo;
	}

	public void setNbTblCatalogo(String nbTblCatalogo) {
		this.nbTblCatalogo = nbTblCatalogo;
	}

	public String getTxTblCatalogo() {
		return txTblCatalogo;
	}

	public void setTxTblCatalogo(String txTblCatalogo) {
		this.txTblCatalogo = txTblCatalogo;
	}

	public String getTxUri() {
		return txUri;
	}

	public void setTxUri(String txUri) {
		this.txUri = txUri;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public Boolean getStMostrar() {
		return stMostrar;
	}

	public void setStMostrar(Boolean stMostrar) {
		this.stMostrar = stMostrar;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
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
