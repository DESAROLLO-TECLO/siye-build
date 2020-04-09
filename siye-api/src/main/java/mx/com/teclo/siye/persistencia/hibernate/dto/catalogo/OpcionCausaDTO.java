package mx.com.teclo.siye.persistencia.hibernate.dto.catalogo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OpcionesDTO;

@Entity
@Table(name = "TIE062D_IE_OPCION_CAUSA")
public class OpcionCausaDTO implements Serializable {
	
	private static final long serialVersionUID = 3771263775950160399L;
	
	@Id
	@Column(name = "ID_OPCION_CAUSA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idOpcionCausa;
    
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_OPCION", referencedColumnName="ID_OPCION")
	private OpcionesDTO opcion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CAUSA", referencedColumnName="ID_CAUSA")
	private CausasDTO causas;

	@Column(name = "NU_ORDEN", nullable = false, precision = 11, scale = 0)
	private Integer nuOrden;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	public Long getIdOpcionCausa() {
		return idOpcionCausa;
	}

	public void setIdOpcionCausa(Long idOpcionCausa) {
		this.idOpcionCausa = idOpcionCausa;
	}

	public OpcionesDTO getOpcion() {
		return opcion;
	}

	public void setOpcion(OpcionesDTO opcion) {
		this.opcion = opcion;
	}

	public CausasDTO getCausas() {
		return causas;
	}

	public void setCausas(CausasDTO causas) {
		this.causas = causas;
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
