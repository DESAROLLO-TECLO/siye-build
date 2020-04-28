package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;


public class KitInstalacionDispositivoVO implements Serializable {
	
	private static final long serialVersionUID = 4344922216390087305L;
	
	private Long idKitInstDispos;
	//private KitInstalacionVO kitInstalacion;
	private KitDispositivoCompletoVO kitDispositivo;
	//private ProveedorDTO proveedor;
	private String cdKitDispositivo;
	private Boolean stActivo;
	
	public Long getIdKitInstDispos() {
		return idKitInstDispos;
	}
	public void setIdKitInstDispos(Long idKitInstDispos) {
		this.idKitInstDispos = idKitInstDispos;
	}
	/*public KitInstalacionVO getKitInstalacion() {
		return kitInstalacion;
	}
	public void setKitInstalacion(KitInstalacionVO kitInstalacion) {
		this.kitInstalacion = kitInstalacion;
	}*/
	public KitDispositivoCompletoVO getKitDispositivo() {
		return kitDispositivo;
	}
	public void setKitDispositivo(KitDispositivoCompletoVO kitDispositivo) {
		this.kitDispositivo = kitDispositivo;
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
