package mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIE068P_COMPRESOR_IMG")
public class CompresorImgConfigDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8213454766232039745L;

	@Id
	@Column(name = "ID_COMPRESOR_IMG")
	private Long idCompresroImg;
	
	@Column(name = "NU_PESO_IMG_INICIAL")
	private Long nuPesoImgInicial;
	
	@Column(name = "NU_PESO_IMG_FINAL")
	private Long nuPesoImgFinal;
	
	@Column(name = "NU_PORCENTAJE_COMPRESION")
	private Integer nuPorcentaje;
	
	@Column(name = "ST_ACTIVO")
	private Integer stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name = "FH_CREACION")
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;

	/**
	 * @return the idCompresroImg
	 */
	public Long getIdCompresroImg() {
		return idCompresroImg;
	}

	/**
	 * @param idCompresroImg the idCompresroImg to set
	 */
	public void setIdCompresroImg(Long idCompresroImg) {
		this.idCompresroImg = idCompresroImg;
	}

	/**
	 * @return the nuPesoImgInicial
	 */
	public Long getNuPesoImgInicial() {
		return nuPesoImgInicial;
	}

	/**
	 * @param nuPesoImgInicial the nuPesoImgInicial to set
	 */
	public void setNuPesoImgInicial(Long nuPesoImgInicial) {
		this.nuPesoImgInicial = nuPesoImgInicial;
	}

	/**
	 * @return the nuPesoImgFinal
	 */
	public Long getNuPesoImgFinal() {
		return nuPesoImgFinal;
	}

	/**
	 * @param nuPesoImgFinal the nuPesoImgFinal to set
	 */
	public void setNuPesoImgFinal(Long nuPesoImgFinal) {
		this.nuPesoImgFinal = nuPesoImgFinal;
	}

	/**
	 * @return the nuPorcentaje
	 */
	public Integer getNuPorcentaje() {
		return nuPorcentaje;
	}

	/**
	 * @param nuPorcentaje the nuPorcentaje to set
	 */
	public void setNuPorcentaje(Integer nuPorcentaje) {
		this.nuPorcentaje = nuPorcentaje;
	}

	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
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
