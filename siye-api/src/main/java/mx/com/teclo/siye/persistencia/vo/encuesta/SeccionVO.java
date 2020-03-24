package mx.com.teclo.siye.persistencia.vo.encuesta;

import java.io.Serializable;
import java.util.List;

public class SeccionVO implements Serializable {

	private static final long serialVersionUID = 1756696697865917112L;

	private Long idSeccion;
	private String cdSeccion;
	private String nbSeccion;
	private String txSeccion;
	private Integer nuOrden;
	private List<PreguntaVO> preguntas;
	private Integer stActivo;

	private Integer nuPreguntasContestadas;

	public Long getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Long idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getCdSeccion() {
		return cdSeccion;
	}

	public void setCdSeccion(String cdSeccion) {
		this.cdSeccion = cdSeccion;
	}

	public String getNbSeccion() {
		return nbSeccion;
	}

	public void setNbSeccion(String nbSeccion) {
		this.nbSeccion = nbSeccion;
	}

	public Integer getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	public List<PreguntaVO> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntaVO> preguntas) {
		this.preguntas = preguntas;
	}

	public Integer getNuPreguntasContestadas() {
		return nuPreguntasContestadas;
	}

	public void setNuPreguntasContestadas(Integer nuPreguntasContestadas) {
		this.nuPreguntasContestadas = 0;
	}

	/**
	 * @return the txSeccion
	 */
	public String getTxSeccion() {
		return txSeccion;
	}

	/**
	 * @param txSeccion the txSeccion to set
	 */
	public void setTxSeccion(String txSeccion) {
		this.txSeccion = txSeccion;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
}
