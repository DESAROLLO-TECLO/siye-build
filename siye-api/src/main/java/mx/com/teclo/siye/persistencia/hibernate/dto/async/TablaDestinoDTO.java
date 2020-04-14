package mx.com.teclo.siye.persistencia.hibernate.dto.async;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE056C_IE_TABLA_DESTINO")
public class TablaDestinoDTO {

	@Id
	@Column(name = "ID_TABLA_DESTINO")
	private Long idTablaDestino;

	@Column(name = "NB_TABLA")
	private String nbTabla;

	@Column(name = "NB_CAMPO")
	private String nbCampo;

	@Column(name = "NU_LONGITUD")
	private Short nuLongitud;

	@Column(name = "ST_PERMITE_NULO")
	private Boolean stPermiteNulo;

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
	
	@Column(name = "TX_VALOR_DEFECTO")
	private String txValorDefecto;

	public Long getIdTablaDestino() {
		return idTablaDestino;
	}

	public void setIdTablaDestino(Long idTablaDestino) {
		this.idTablaDestino = idTablaDestino;
	}

	public String getNbTabla() {
		return nbTabla;
	}

	public void setNbTabla(String nbTabla) {
		this.nbTabla = nbTabla;
	}

	public String getNbCampo() {
		return nbCampo;
	}

	public void setNbCampo(String nbCampo) {
		this.nbCampo = nbCampo;
	}

	public Short getNuLongitud() {
		return nuLongitud;
	}

	public void setNuLongitud(Short nuLongitud) {
		this.nuLongitud = nuLongitud;
	}

	public Boolean getStPermiteNulo() {
		return stPermiteNulo;
	}

	public void setStPermiteNulo(Boolean stPermiteNulo) {
		this.stPermiteNulo = stPermiteNulo;
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

	public String getTxValorDefecto() {
		return txValorDefecto;
	}

	public void setTxValorDefecto(String txValorDefecto) {
		this.txValorDefecto = txValorDefecto;
	}
	
	
}
