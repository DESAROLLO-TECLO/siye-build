package mx.com.teclo.siye.persistencia.vo.proceso;



import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.TipoKitDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.DispositivosDTO;

public class DispositivosVO  {
	

	private DispositivosDTO dispositivo;
	private TipoKitDTO tipoKit;
	
	public DispositivosDTO getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivosDTO dispositivo) {
		this.dispositivo = dispositivo;
	}
	public TipoKitDTO getTipoKit() {
		return tipoKit;
	}
	public void setTipoKit(TipoKitDTO tipoKit) {
		this.tipoKit = tipoKit;
	}
	
	
	
	

}
