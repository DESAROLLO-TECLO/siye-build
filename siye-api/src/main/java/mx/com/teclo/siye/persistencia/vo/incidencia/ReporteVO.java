package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;
import java.util.List;

public class ReporteVO implements Serializable {

	private static final long serialVersionUID = 7510678635935997756L;
	
	private List<Object> header;
	private List<Object> values;
	private List<String> subtitulos;
	private String titulo;

	/**
	 * @return the header
	 */
	public List<Object> getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(List<Object> header) {
		this.header = header;
	}

	/**
	 * @return the values
	 */
	public List<Object> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<Object> values) {
		this.values = values;
	}

	/**
	 * @return the subtitulos
	 */
	public List<String> getSubtitulos() {
		return subtitulos;
	}

	/**
	 * @param subtitulos the subtitulos to set
	 */
	public void setSubtitulos(List<String> subtitulos) {
		this.subtitulos = subtitulos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
