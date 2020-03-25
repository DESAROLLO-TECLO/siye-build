/**
 * 
 */
package mx.com.teclo.siye.persistencia.vo.proceso;

import java.util.Date;

/**
 * @author UNITIS-ODM2
 *
 */
public class KitInstalacionVO {

	private Long idKitInstalacion;
	private String cdKitInstalacion;
	private Boolean stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
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
