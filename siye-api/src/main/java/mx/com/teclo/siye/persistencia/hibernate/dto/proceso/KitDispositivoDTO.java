package mx.com.teclo.siye.persistencia.hibernate.dto.proceso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;

@Entity
@Table(name="TIE033D_IE_KID_DISPOSITIVO")
public class KitDispositivoDTO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4058963203398168730L;
	/**
	 * 
	 */

	@Id
	@Column(name = "ID_KIT_DISPOSITIVO", nullable = false)
	private Long idKitDispositivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_KIT", referencedColumnName="ID_TIPO_KIT", nullable = false)
	private TipoKitDTO tipoKit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DISPOSITIVO",  referencedColumnName = "ID_DISPOSITIVO", nullable = false)
	private DispositivosDTO dispositivo;
	
	@Column(name = "NU_ORDEN")
	private Long nuOrden;
	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;
	
	

	public TipoKitDTO getTipoKit() {
		return tipoKit;
	}
	public void setTipoKit(TipoKitDTO tipoKit) {
		this.tipoKit = tipoKit;
	}
	public Long getIdKitDispositivo() {
		return idKitDispositivo;
	}
	public void setIdKitDispositivo(Long idKitDispositivo) {
		this.idKitDispositivo = idKitDispositivo;
	}
	public DispositivosDTO getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivosDTO dispositivo) {
		this.dispositivo = dispositivo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
