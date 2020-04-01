package mx.com.teclo.siye.util.enumerados;

public enum CalificacionEnum {
	APROBADO ("A", "APROBADO"),
	REPROBADO ("F", "REPROBADO");
	
	private String cdStCalificacion;
	private String nbStCalificacion;
 	
	private CalificacionEnum(String cdStCalificacion, String nbStCalificacion){
		this.cdStCalificacion = cdStCalificacion;
		this.nbStCalificacion = nbStCalificacion;
 	}

	public String getCdStCalificacion() {
		return cdStCalificacion;
	}

	public void setCdStCalificacion(String cdStCalificacion) {
		this.cdStCalificacion = cdStCalificacion;
	}

	public String getNbStCalificacion() {
		return nbStCalificacion;
	}

	public void setNbStCalificacion(String nbStCalificacion) {
		this.nbStCalificacion = nbStCalificacion;
	}

}
