package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;

@Entity
@Table(name = "TIE039D_IE_KIT_INST_DISPOS")
public class KitInstalacionDispDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439677401771184965L;

	@Id
	@SequenceGenerator(name = "sqKitInst", sequenceName = "SQIE039D_IE_KIT_INST_", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqKitInst")
	@Column(name="ID_KIT_INST_DISPOS")
	private Long idKitInstDispos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_KIT_INSTALACION", referencedColumnName="ID_KIT_INSTALACION", nullable = false)
	private KitInstalacionDTO kitInstalacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_KIT_DISPOSITIVO", referencedColumnName="ID_KIT_DISPOSITIVO", nullable = false)
	private KitDispositivoDTO kitDispositivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROVEEDOR", referencedColumnName="ID_PROVEEDOR", nullable = false)
	private ProveedorDTO proveedor;
	
	@Column(name="CD_KIT_DISPOSITIVO")
	private String cdKitDispositivo;
	
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;

	public Long getIdKitInstDispos() {
		return idKitInstDispos;
	}

	public void setIdKitInstDispos(Long idKitInstDispos) {
		this.idKitInstDispos = idKitInstDispos;
	}

	public KitInstalacionDTO getKitInstalacion() {
		return kitInstalacion;
	}

	public void setKitInstalacion(KitInstalacionDTO kitInstalacion) {
		this.kitInstalacion = kitInstalacion;
	}

	public KitDispositivoDTO getKitDispositivo() {
		return kitDispositivo;
	}

	public void setKitDispositivo(KitDispositivoDTO kitDispositivo) {
		this.kitDispositivo = kitDispositivo;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

	public String getCdKitDispositivo() {
		return cdKitDispositivo;
	}

	public void setCdKitDispositivo(String cdKitDispositivo) {
		this.cdKitDispositivo = cdKitDispositivo;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	
	
	

}
