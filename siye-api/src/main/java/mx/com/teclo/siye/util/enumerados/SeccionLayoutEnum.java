package mx.com.teclo.siye.util.enumerados;

public enum SeccionLayoutEnum {
	HEADER("H", "HEADER"), DETALLE("D", "DETALLE"), FOOTER("F", "FOOTER");

	private String cdIndicadorReg;
	private String nbSeccion;

	private SeccionLayoutEnum(String cdIndicadorReg, String nbSeccion) {

		this.cdIndicadorReg = cdIndicadorReg;
		this.nbSeccion = nbSeccion;
	}

	public String getCdIndicadorReg() {
		return cdIndicadorReg;
	}

	public String getNbSeccion() {
		return nbSeccion;
	}

}
