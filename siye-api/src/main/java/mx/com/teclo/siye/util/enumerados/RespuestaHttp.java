package mx.com.teclo.siye.util.enumerados;

public enum RespuestaHttp {
	
	NOT_FOUND("No se encontraron registros","404"),
	CONFLICT("Hubo conflictos al tratar de generar un nuevo intento","409");

	private String message;
	private String code;
	
	
	private RespuestaHttp(String message, String code) {
		this.message = message;
		this.code = code;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	static RespuestaHttp getRespuestaHttp(String x) {
		for (RespuestaHttp currentType : RespuestaHttp.values()) {
			if (x.equals(currentType.getMessage())) {
				return currentType;
			}
		}
		return null;
	}

}
