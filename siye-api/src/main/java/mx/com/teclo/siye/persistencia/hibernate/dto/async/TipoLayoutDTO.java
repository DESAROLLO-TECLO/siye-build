package mx.com.teclo.siye.persistencia.hibernate.dto.async;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE057C_IE_TIPO_LAYOUT")
public class TipoLayoutDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4710173459955538883L;

	@Id
	@Column(name = "ID_TIPO_LAYOUT", unique = true, nullable = false, insertable = false)
	private Long idTipoLayout;

	@Column(name = "CD_TIPO_LAYOUT", nullable = false, insertable = false)
	private String cdTipoLayout;

	@Column(name = "NB_TIPO_LAYOUT", nullable = false, insertable = false)
	private String nbTipoLayout;

	@Column(name = "NU_MAX_REGISTROS")
	private Short nuMaxRegistros;

	@Column(name = "ID_TIPO_ARCHIVO")
	private String idTipoArchivo;

	@Column(name = "TX_MASCARA")
	private String txMascara;

	@Column(name = "CD_TAMANIO_MAX")
	private Short cdTamanioMax;

	@Column(name = "NB_DIRECTORIO")
	private String nbDirectorio;

	@Column(name = "ST_CARGA_PARCIAL", precision = 1, scale = 0)
	private Boolean stCargaParcial;

	@Column(name = "ST_VIGENTE", precision = 1, scale = 0)
	private Boolean stVigente;

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

	public Long getIdTipoLayout() {
		return idTipoLayout;
	}

	public void setIdTipoLayout(Long idTipoLayout) {
		this.idTipoLayout = idTipoLayout;
	}

	public String getCdTipoLayout() {
		return cdTipoLayout;
	}

	public void setCdTipoLayout(String cdTipoLayout) {
		this.cdTipoLayout = cdTipoLayout;
	}

	public String getNbTipoLayout() {
		return nbTipoLayout;
	}

	public void setNbTipoLayout(String nbTipoLayout) {
		this.nbTipoLayout = nbTipoLayout;
	}

	public Short getNuMaxRegistros() {
		return nuMaxRegistros;
	}

	public void setNuMaxRegistros(Short nuMaxRegistros) {
		this.nuMaxRegistros = nuMaxRegistros;
	}

	public String getIdTipoArchivo() {
		return idTipoArchivo;
	}

	public void setIdTipoArchivo(String idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}

	public String getTxMascara() {
		return txMascara;
	}

	public void setTxMascara(String txMascara) {
		this.txMascara = txMascara;
	}

	public Short getCdTamanioMax() {
		return cdTamanioMax;
	}

	public void setCdTamanioMax(Short cdTamanioMax) {
		this.cdTamanioMax = cdTamanioMax;
	}

	public String getNbDirectorio() {
		return nbDirectorio;
	}

	public void setNbDirectorio(String nbDirectorio) {
		this.nbDirectorio = nbDirectorio;
	}

	public Boolean getStCargaParcial() {
		return stCargaParcial;
	}

	public void setStCargaParcial(Boolean stCargaParcial) {
		this.stCargaParcial = stCargaParcial;
	}

	public Boolean getStVigente() {
		return stVigente;
	}

	public void setStVigente(Boolean stVigente) {
		this.stVigente = stVigente;
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
