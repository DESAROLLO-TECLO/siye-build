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

/*
 * 
 * Clase que representa un tipo de Instalador o tipo de Tecnico
 * 
 * @author josec.castillo48@gmail.com
 *
 */
@Entity
@Table(name = "TIE060C_IE_TIPO_PERSONA")
public class TipoPersonaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9036924422977461820L;
	
//	ID_TIPO_PERSONA	NUMBER(6,0)			No
//	CD_TIPO_PERSONA	VARCHAR2(30 BYTE)	No
//	NB_TIPO_PERSONA	VARCHAR2(50 BYTE)	Yes
//	TX_TIPO_PERSONA	VARCHAR2(100 BYTE)	Yes
//	NU_ORDEN		NUMBER(4,0)			Yes
//	ST_ACTIVO		NUMBER(1,0)			No
//	ID_USR_CREACION	NUMBER(7,0)			No
//	FH_CREACION		DATE				No
//	ID_USR_MODIFICA	NUMBER(7,0)			No
//	FH_MODIFICACION	DATE				No
	
	@Id
	@SequenceGenerator(name = "sqie060cIETipoPers", sequenceName="SQIE060C_IE_TIPO_PERS", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqie060cIETipoPers")
	@Column(name = "ID_TIPO_PERSONA", unique = true, nullable = false, precision = 6, scale = 0)
	private Integer idTipoPersona;
	
	@Column(name = "CD_TIPO_PERSONA", nullable = false, length = 50)
	private String cdTipoPersona;
	
	@Column(name = "NB_TIPO_PERSONA", nullable = true, length = 50)
	private String nbTipoPersona;
	
	@Column(name = "TX_TIPO_PERSONA", nullable = true, length = 50)
	private String txTipoPersona;
	
	@Column(name = "NU_ORDEN", nullable = true, precision = 4, scale = 0)
	private Integer nuOrden;
	
	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Boolean stActivo;
	
	@Column(name = "ID_USR_CREACION", nullable = false, precision = 7, scale = 0)
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION", nullable = false)
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 7, scale = 0)
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION", nullable = false)
	private Date fhModificacion;

	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}

	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	public String getCdTipoPersona() {
		return cdTipoPersona;
	}

	public void setCdTipoPersona(String cdTipoPersona) {
		this.cdTipoPersona = cdTipoPersona;
	}

	public String getNbTipoPersona() {
		return nbTipoPersona;
	}

	public void setNbTipoPersona(String nbTipoPersona) {
		this.nbTipoPersona = nbTipoPersona;
	}

	public String getTxTipoPersona() {
		return txTipoPersona;
	}

	public void setTxTipoPersona(String txTipoPersona) {
		this.txTipoPersona = txTipoPersona;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
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
