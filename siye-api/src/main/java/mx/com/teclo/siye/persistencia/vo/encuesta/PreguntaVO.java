package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.catalogo.TipoPreguntaVO;

public class PreguntaVO implements Serializable {

	private static final long serialVersionUID = 8333931040736690374L;

	private Long idPregunta;
	private String cdPregunta;
	private String txPregunta;
	private Integer nuOrden;
	private List<OpcionVO> opciones;
	private TipoPreguntaVO idTipoPregunta;
	private Integer stMarcado;
	private Integer stActivo;
	private Long nuMaxImagenes;

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getCdPregunta() {
		return cdPregunta;
	}

	public void setCdPregunta(String cdPregunta) {
		this.cdPregunta = cdPregunta;
	}

	public String getTxPregunta() {
		return txPregunta;
	}

	public void setTxPregunta(String txPregunta) {
		this.txPregunta = txPregunta;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	/**
	 * @return the opciones
	 */
	public List<OpcionVO> getOpciones() {
		return opciones;
	}

	/**
	 * @param opciones the opciones to set
	 */
	public void setOpciones(List<OpcionVO> opciones) {
		this.opciones = opciones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoPreguntaVO getIdTipoPregunta() {
		return idTipoPregunta;
	}

	public void setIdTipoPregunta(TipoPreguntaVO idTipoPregunta) {
		this.idTipoPregunta = idTipoPregunta;
	}

	/**
	 * @return the stMarcado
	 */
	public Integer getStMarcado() {
		return stMarcado;
	}

	/**
	 * @param stMarcado the stMarcado to set
	 */
	public void setStMarcado(Integer stMarcado) {
		this.stMarcado = stMarcado;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getNuMaxImagenes() {
		return nuMaxImagenes;
	}

	public void setNuMaxImagenes(Long nuMaxImagenes) {
		this.nuMaxImagenes = nuMaxImagenes;
	}
	
	
}
