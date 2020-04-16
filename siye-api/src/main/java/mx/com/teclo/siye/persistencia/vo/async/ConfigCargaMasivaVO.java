package mx.com.teclo.siye.persistencia.vo.async;

import java.util.List;
import java.util.Map;

import mx.com.teclo.siye.persistencia.vo.proceso.LoteOrdenServicioVO;

public class ConfigCargaMasivaVO {
	private LoteOrdenServicioVO configLote;
	private TipoLayoutVO configLayout;
	private Map<String, List<ColumnaVO>> configSecciones;
	private List<TablaDestinoVO> configInsercion;
	private Map<String, InsercionTablaVO> configMoldesSQL;
	private int totalColsEsperadas;
	private List<ColumnaArchivoVO> columnasEnArchivo;
	
	public LoteOrdenServicioVO getConfigLote() {
		return configLote;
	}
	public void setConfigLote(LoteOrdenServicioVO configLote) {
		this.configLote = configLote;
	}
	public TipoLayoutVO getConfigLayout() {
		return configLayout;
	}	
	public List<TablaDestinoVO> getConfigInsercion() {
		return configInsercion;
	}
	public void setConfigInsercion(List<TablaDestinoVO> configInsercion) {
		this.configInsercion = configInsercion;
	}
	public void setConfigLayout(TipoLayoutVO configLayout) {
		this.configLayout = configLayout;
	}
	public Map<String, List<ColumnaVO>> getConfigSecciones() {
		return configSecciones;
	}
	public void setConfigSecciones(Map<String, List<ColumnaVO>> configSecciones) {
		this.configSecciones = configSecciones;
	}
	public Map<String, InsercionTablaVO> getConfigMoldesSQL() {
		return configMoldesSQL;
	}
	public void setConfigMoldesSQL(Map<String, InsercionTablaVO> configMoldesSQL) {
		this.configMoldesSQL = configMoldesSQL;
	}
	public int getTotalColsEsperadas() {
		return totalColsEsperadas;
	}
	public void setTotalColsEsperadas(int totalColsEsperadas) {
		this.totalColsEsperadas = totalColsEsperadas;
	}
	public List<ColumnaArchivoVO> getColumnasEnArchivo() {
		return columnasEnArchivo;
	}
	public void setColumnasEnArchivo(List<ColumnaArchivoVO> columnasEnArchivo) {
		this.columnasEnArchivo = columnasEnArchivo;
	}
	
	
	
}
