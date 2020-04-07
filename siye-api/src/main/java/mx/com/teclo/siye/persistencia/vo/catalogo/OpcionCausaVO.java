package mx.com.teclo.siye.persistencia.vo.catalogo;

import java.io.Serializable;

import mx.com.teclo.siye.persistencia.vo.encuesta.OpcionVO;


public class OpcionCausaVO implements Serializable {
	
	private static final long serialVersionUID = 3771263775950160399L;
	
	
	private Long idOpcionCausa;
    private OpcionVO opcion;
	private CausasVO causas;
    private Integer nuOrden;
    private Integer stActivo;


	public CausasVO getCausas() {
		return causas;
	}


	public void setCausas(CausasVO causas) {
		this.causas = causas;
	}


	public Long getIdOpcionCausa() {
		return idOpcionCausa;
	}


	public void setIdOpcionCausa(Long idOpcionCausa) {
		this.idOpcionCausa = idOpcionCausa;
	}


	public OpcionVO getOpcion() {
		return opcion;
	}


	public void setOpcion(OpcionVO opcion) {
		this.opcion = opcion;
	}


	public Integer getNuOrden() {
		return nuOrden;
	}


	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}


	public Integer getStActivo() {
		return stActivo;
	}


	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
    
	

    
    
   
   

}
