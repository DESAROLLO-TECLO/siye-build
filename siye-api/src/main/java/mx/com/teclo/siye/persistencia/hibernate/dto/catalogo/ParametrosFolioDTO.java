package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIE064P_FOLIOS")
public class ParametrosFolioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774569716441360618L;
	@Id
	@Column(name = "ID_P_FOLIO", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idFolio;	
	@Column(name="NB_PARAMETRO_FOLIO")
	private String nbParametroFolio;
	@Column(name="CD_PARAMETRO_FOLIO")
	private String cdParametroFolio;
	@Column(name="NB_PREFIJO")
	private String nbPrefijo;
	@Column(name="NU_FOLIOS")
	private Long nuFolios;
	@Column(name="ID_USR_CREACION")
	private Long idUsrCreacion;
	@Column(name="FH_CREACION")
	private Date fhCreacion;
	@Column(name="ID_USR_MODIFICA")
	private Long idUsrModifica;
	@Column(name="FH_MODIFICACION")
	private Date fhModificacion;
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;
	
	

	public Long getIdFolio() {
		return idFolio;
	}
	public void setIdFolio(Long idFolio) {
		this.idFolio = idFolio;
	}
	public String getNbParametroFolio() {
		return nbParametroFolio;
	}
	public void setNbParametroFolio(String nbParametroFolio) {
		this.nbParametroFolio = nbParametroFolio;
	}
	public String getCdParametroFolio() {
		return cdParametroFolio;
	}
	public void setCdParametroFolio(String cdParametroFolio) {
		this.cdParametroFolio = cdParametroFolio;
	}
	public String getNbPrefijo() {
		return nbPrefijo;
	}
	public void setNbPrefijo(String nbPrefijo) {
		this.nbPrefijo = nbPrefijo;
	}
	public Long getNuFolios() {
		return nuFolios;
	}
	public void setNuFolios(Long nuFolios) {
		this.nuFolios = nuFolios;
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
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

}
