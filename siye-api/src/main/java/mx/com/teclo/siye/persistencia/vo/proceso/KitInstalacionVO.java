/**
 * 
 */
package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;
import java.util.List;

/**
 * @author UNITIS-ODM2
 *
 */
public class KitInstalacionVO implements Serializable {

	private static final long serialVersionUID = 4344922216390087305L;
	
	private Long idKitInstalacion;
	private String cdKitInstalacion;
	private Boolean stActivo;
	private List<KitInstalacionDispositivoVO>  kitInstalacionDispDTO;
	
	public Long getIdKitInstalacion() {
		return idKitInstalacion;
	}
	public void setIdKitInstalacion(Long idKitInstalacion) {
		this.idKitInstalacion = idKitInstalacion;
	}
	public String getCdKitInstalacion() {
		return cdKitInstalacion;
	}
	public void setCdKitInstalacion(String cdKitInstalacion) {
		this.cdKitInstalacion = cdKitInstalacion;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public List<KitInstalacionDispositivoVO> getKitInstalacionDispDTO() {
		return kitInstalacionDispDTO;
	}
	public void setKitInstalacionDispDTO(
			List<KitInstalacionDispositivoVO> kitInstalacionDispDTO) {
		this.kitInstalacionDispDTO = kitInstalacionDispDTO;
	}
	
	
		
}
