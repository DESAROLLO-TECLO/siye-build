package mx.com.teclo.siye.persistencia.hibernate.dto.async;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TIE055C_IE_LAYOUT")
public class LayoutDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709391736225279403L;

	@Id
	@Column(name = "ID_CAMPO")
	private Long idCampo;

	@Column(name = "CD_INDICADOR_REG")
	private String cdIndicadorReg;

	@Column(name = "NB_CAMPO")
	private String nbCampo;

	@Column(name = "CD_TIPO_DATO")
	private String cdTipoDato;

	@Column(name = "NU_LONGITUD_MIN")
	private Short nuLongitudMin;

	@Column(name = "NU_LONGITUD_MAX")
	private Integer nuLongitudMax;

	@Column(name = "ST_REQUERIDO")
	private Boolean stRequerido;

	@Column(name = "TX_MASCARA")
	private String txMascara;

	@Column(name = "TX_VALOR_DEFECTO")
	private String txValorDefecto;

	@Column(name = "NU_ORDEN_REGISTRO")
	private Integer nuOrdenRegistro;

	@Column(name = "TX_DESCRIPCION")
	private String txDescripcion;

	@Column(name = "ST_APLICA_VALIDACION")
	private Boolean stAplicaValidacion;

	@JoinColumn(name = "ID_TABLA_DESTINO", referencedColumnName = "ID_TABLA_DESTINO")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private TablaDestinoDTO idTablaDestino;

	
	@JoinColumn(name = "ID_TIPO_LAYOUT", referencedColumnName = "ID_TIPO_LAYOUT")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private TipoLayoutDTO idTipoLayout;

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

	public Long getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(Long idCampo) {
		this.idCampo = idCampo;
	}

	public String getCdIndicadorReg() {
		return cdIndicadorReg;
	}

	public void setCdIndicadorReg(String cdIndicadorReg) {
		this.cdIndicadorReg = cdIndicadorReg;
	}

	public String getNbCampo() {
		return nbCampo;
	}

	public void setNbCampo(String nbCampo) {
		this.nbCampo = nbCampo;
	}

	public String getCdTipoDato() {
		return cdTipoDato;
	}

	public void setCdTipoDato(String cdTipoDato) {
		this.cdTipoDato = cdTipoDato;
	}

	public Short getNuLongitudMin() {
		return nuLongitudMin;
	}

	public void setNuLongitudMin(Short nuLongitudMin) {
		this.nuLongitudMin = nuLongitudMin;
	}
	
	public Integer getNuLongitudMax() {
		return nuLongitudMax;
	}

	public void setNuLongitudMax(Integer nuLongitudMax) {
		this.nuLongitudMax = nuLongitudMax;
	}

	public Boolean getStRequerido() {
		return stRequerido;
	}

	public void setStRequerido(Boolean stRequerido) {
		this.stRequerido = stRequerido;
	}

	public String getTxMascara() {
		return txMascara;
	}

	public void setTxMascara(String txMascara) {
		this.txMascara = txMascara;
	}

	public String getTxValorDefecto() {
		return txValorDefecto;
	}

	public void setTxValorDefecto(String txValorDefecto) {
		this.txValorDefecto = txValorDefecto;
	}

	public Integer getNuOrdenRegistro() {
		return nuOrdenRegistro;
	}

	public void setNuOrdenRegistro(Integer nuOrdenRegistro) {
		this.nuOrdenRegistro = nuOrdenRegistro;
	}

	public String getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}

	public Boolean getStAplicaValidacion() {
		return stAplicaValidacion;
	}

	public void setStAplicaValidacion(Boolean stAplicaValidacion) {
		this.stAplicaValidacion = stAplicaValidacion;
	}

	public TablaDestinoDTO getIdTablaDestino() {
		return idTablaDestino;
	}

	public void setIdTablaDestino(TablaDestinoDTO idTablaDestino) {
		this.idTablaDestino = idTablaDestino;
	}

	public TipoLayoutDTO getIdTipoLayout() {
		return idTipoLayout;
	}

	public void setIdTipoLayout(TipoLayoutDTO idTipoLayout) {
		this.idTipoLayout = idTipoLayout;
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