package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.SeccionDTO;

@Entity
@Table(name = "TIE030D_IE_KIT_INSTALACION")
public class KitInstalacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8058271907510016963L;

	@Id
	@SequenceGenerator(name = "sqKitInsta", sequenceName = "SQIE030D_IE_KIT_INSTA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqKitInsta")
	@Column(name = "ID_KIT_INSTALACION", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idKitInstalacion;

	@Column(name = "CD_KIT_INSTALACION", insertable=false, length = 15)
	private String cdKitInstalacion;

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
	
	//probando
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kitInstalacion", fetch = FetchType.LAZY)
	private List<KitInstalacionDispDTO> kitInstalacionDispDTO;

	/**
	 * @return the idKitInstalacion
	 */
	public Long getIdKitInstalacion() {
		return idKitInstalacion;
	}

	/**
	 * @param idKitInstalacion the idKitInstalacion to set
	 */
	public void setIdKitInstalacion(Long idKitInstalacion) {
		this.idKitInstalacion = idKitInstalacion;
	}

	/**
	 * @return the cdKitInstalacion
	 */
	public String getCdKitInstalacion() {
		return cdKitInstalacion;
	}

	/**
	 * @param cdKitInstalacion the cdKitInstalacion to set
	 */
	public void setCdKitInstalacion(String cdKitInstalacion) {
		this.cdKitInstalacion = cdKitInstalacion;
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

	public List<KitInstalacionDispDTO> getKitInstalacionDispDTO() {
		return kitInstalacionDispDTO;
	}

	public void setKitInstalacionDispDTO(
			List<KitInstalacionDispDTO> kitInstalacionDispDTO) {
		this.kitInstalacionDispDTO = kitInstalacionDispDTO;
	}

	

	

}
