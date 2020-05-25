package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;
/*
 * 
 * Clase que representa un Conductor o Transportista
 * 
 * @author josec.castillo48@gmail.com
 *
 */
public class ConductorVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -621002232129680385L;
	
	private Long idConductor;
	private String nbConductor;
	private String nbApepatConductor;
	private String nbApematConductor;
	private Boolean existia;
	private Long stCondutor;
    private Long idVehiculoConductor;
	
	public Long getIdConductor() {
		return idConductor;
	}
	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}
	public String getNbConductor() {
		return nbConductor;
	}
	public void setNbConductor(String nbConductor) {
		this.nbConductor = nbConductor;
	}
	public String getNbApepatConductor() {
		return nbApepatConductor;
	}
	public void setNbApepatConductor(String nbApepatConductor) {
		this.nbApepatConductor = nbApepatConductor;
	}
	public String getNbApematConductor() {
		return nbApematConductor;
	}
	public void setNbApematConductor(String nbApematConductor) {
		this.nbApematConductor = nbApematConductor;
	}
	public Boolean getExistia() {
		return existia;
	}
	public void setExistia(Boolean existia) {
		this.existia = existia;
	}
	public Long getStCondutor() {
		return stCondutor;
	}
	public void setStCondutor(Long stCondutor) {
		this.stCondutor = stCondutor;
	}
	public Long getIdVehiculoConductor() {
		return idVehiculoConductor;
	}
	public void setIdVehiculoConductor(Long idVehiculoConductor) {
		this.idVehiculoConductor = idVehiculoConductor;
	}
	
	
	
}
