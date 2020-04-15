package mx.com.teclo.siye.persistencia.vo.async;

public class InsercionTablaVO {

	private String columnas;
	private String valores;
	private String tipos;
	private String maxLengths;
	private String titulos;
	private String piesPagina;
	private String comodines;
	private String campoFiltro;
	private String valorFiltro;
	private String campoID;
	private String insertSQL;
	private String selectSQL;
	private Long id;

	public InsercionTablaVO() {
	}

	public InsercionTablaVO(String insertSQL, String selectSQL) {
		this.insertSQL = insertSQL;
		this.selectSQL = selectSQL;
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

	public String getTipos() {
		return tipos;
	}

	public void setTipos(String tipos) {
		this.tipos = tipos;
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

	public String getComodines() {
		return comodines;
	}

	public void setComodines(String comodines) {
		this.comodines = comodines;
	}

	public String getCampoFiltro() {
		return campoFiltro;
	}

	public void setCampoFiltro(String campoFiltro) {
		this.campoFiltro = campoFiltro;
	}

	public String getValorFiltro() {
		return valorFiltro;
	}

	public void setValorFiltro(String valorFiltro) {
		this.valorFiltro = valorFiltro;
	}

	public String getInsertSQL() {
		return insertSQL;
	}

	public void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}

	public String getSelectSQL() {
		return selectSQL;
	}

	public void setSelectSQL(String selectSQL) {
		this.selectSQL = selectSQL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCampoID() {
		return campoID;
	}

	public void setCampoID(String campoID) {
		this.campoID = campoID;
	}

	@Override
	public String toString() {
		return "InsercionTablaVO [columnas=" + columnas + ", valores=" + valores + "]";
	}

	

}
