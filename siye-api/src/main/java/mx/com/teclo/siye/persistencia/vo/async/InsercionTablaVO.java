package mx.com.teclo.siye.persistencia.vo.async;

public class InsercionTablaVO {

	private String querySQL;
	private String valores;
	private String patterns;
	private String maxLengths;
	private String titulos;
	private String piesPagina;
	
	public InsercionTablaVO() {
	}

	public InsercionTablaVO(String querySQL, String valores) {
		this.querySQL = querySQL;
		this.valores = valores;
	}
	public String getQuerySQL() {
		return querySQL;
	}



	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}



	public void setColumnas(String columnas) {
		this.querySQL = columnas;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}	

	public String getPatterns() {
		return patterns;
	}

	public void setPatterns(String patterns) {
		this.patterns = patterns;
	}
	
	public String getMaxLengths() {
		return maxLengths;
	}

	public void setMaxLengths(String maxLengths) {
		this.maxLengths = maxLengths;
	}
	
	

	public String getTitulos() {
		return titulos;
	}

	public void setTitulos(String titulos) {
		this.titulos = titulos;
	}

	public String getPiesPagina() {
		return piesPagina;
	}

	public void setPiesPagina(String piesPagina) {
		this.piesPagina = piesPagina;
	}

	@Override
	public String toString() {
		return "InsercionTablaVO [columnas=" + querySQL + ", valores=" + valores + "]";
	}

}
