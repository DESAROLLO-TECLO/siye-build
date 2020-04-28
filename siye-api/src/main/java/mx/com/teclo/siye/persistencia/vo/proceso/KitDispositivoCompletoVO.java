package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;


public class KitDispositivoCompletoVO implements Serializable {
	
	private static final long serialVersionUID = 3398117821017518569L;
    
	private Long idKitDispositivo;
	private TipoKitVO tipoKit;
	private DispositivoVO dispositivo;
	private Long nuOrden;
	private Boolean stActivo;
	
	public Long getIdKitDispositivo() {
		return idKitDispositivo;
	}
	public void setIdKitDispositivo(Long idKitDispositivo) {
		this.idKitDispositivo = idKitDispositivo;
	}
	public TipoKitVO getTipoKit() {
		return tipoKit;
	}
	public void setTipoKit(TipoKitVO tipoKit) {
		this.tipoKit = tipoKit;
	}
	public DispositivoVO getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivoVO dispositivo) {
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
	
	

}
