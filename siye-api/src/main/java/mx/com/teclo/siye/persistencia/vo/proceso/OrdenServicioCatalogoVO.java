package mx.com.teclo.siye.persistencia.vo.proceso;

import java.io.Serializable;

public class OrdenServicioCatalogoVO implements Serializable {
	
	private static final long serialVersionUID = -2948192570191045731L;
	
	private Long idOrdenServicio;
	private String cdOrdenServicio;
	
	public Long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	public void setIdOrdenServicio(Long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	public String getCdOrdenServicio() {
		return cdOrdenServicio;
	}
	public void setCdOrdenServicio(String cdOrdenServicio) {
		this.cdOrdenServicio = cdOrdenServicio;
	}

	
}
