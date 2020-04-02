package mx.com.teclo.siye.persistencia.vo.async;

public class InsercionTablaVO {

	private String columnas;
	private String valores;

	public InsercionTablaVO() {
	}

	public InsercionTablaVO(String columnas, String valores) {
		this.columnas = columnas;
		this.valores = valores;
	}

	public String getColumnas() {
		return columnas;
	}

	public void setColumnas(String columnas) {
		this.columnas = columnas;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	@Override
	public String toString() {
		return "InsercionTablaVO [columnas=" + columnas + ", valores=" + valores + "]";
	}

}
