package mx.com.teclo.siye.util.comun;

public class FilterValuesVO {

	private Integer codigo;
	private String codigoString;
	private String descripcion;
	
	public FilterValuesVO(Integer codigo, String codigoString, String descripcion) {
		this.codigo = codigo;
		this.codigoString = codigoString;
		this.descripcion = descripcion;
	}
	
	public FilterValuesVO(Integer codigo, String codigoString) {
		this(codigo, codigoString, "");
	}
	
	public FilterValuesVO(Integer codigo) {
		this(codigo, "");
	}
	
	public FilterValuesVO() {
		this(0);
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoString() {
		return codigoString;
	}
	public void setCodigoString(String codigoString) {
		this.codigoString = codigoString;
	}
	
}
