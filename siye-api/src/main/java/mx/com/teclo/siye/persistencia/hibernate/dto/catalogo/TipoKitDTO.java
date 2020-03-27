package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIE031C_IE_TIPO_KIT")
public class TipoKitDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4308954777810003866L;

	@Id
	@Column(name="ID_TIPO_KIT", unique = true, nullable = false)
	private Long idTipoKit;
	
	@Column(name="CD_TIPO_KIT")
	private String cdTipoKit;
	
	@Column(name="NB_TIPO_KIT")
	private String nbTipoKit;
	
	@Column(name="NU_DISPOSITIVOS_KIT")
	private Long nuDispositivoKit;
	
	@Column(name="NU_ORDEN")
	private Long nuOrden;
	
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name = "FH_CREACION", nullable = false)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 7, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false)
	private Date fhModificacion;

	public Long getIdTipoKit() {
		return idTipoKit;
	}

	public void setIdTipoKid(Long idTipoKit) {
		this.idTipoKit = idTipoKit;
	}

	public String getCdTipoKit() {
		return cdTipoKit;
	}

	public void setCdTipoKit(String cdTipoKit) {
		this.cdTipoKit = cdTipoKit;
	}

	public String getNbTipoKit() {
		return nbTipoKit;
	}

	public void setNbTipoKit(String nbTipoKit) {
		this.nbTipoKit = nbTipoKit;
	}

	public Long getNuDispositivoKit() {
		return nuDispositivoKit;
	}

	public void setNuDispositivoKit(Long nuDispositivoKit) {
		this.nuDispositivoKit = nuDispositivoKit;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
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
