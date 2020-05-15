package mx.com.teclo.siye.persistencia.vo.async;

import java.util.List;
import java.util.Map;

public class ConfigCargaMasivaVO {
	private ArchivoLoteVO configLote;
	private ConfigLayoutVO configLayout;
	private Map<String, List<ColumnaVO>> configSecciones;
	private List<TablaDestinoVO> configInsercion;
	private Map<String, InsercionTablaVO> configMoldesSQL;
	private List<ColumnaArchivoVO> columnasEnArchivo;
	
	
	public ArchivoLoteVO getConfigLote() {
		return configLote;
	}
	public void setConfigLote(ArchivoLoteVO configLote) {
		this.configLote = configLote;
	}
	public ConfigLayoutVO getConfigLayout() {
		return configLayout;
	}	
	public List<TablaDestinoVO> getConfigInsercion() {
		return configInsercion;
	}
	public void setConfigInsercion(List<TablaDestinoVO> configInsercion) {
		this.configInsercion = configInsercion;
	}
	public void setConfigLayout(ConfigLayoutVO configLayout) {
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
	
	public List<ColumnaArchivoVO> getColumnasEnArchivo() {
		return columnasEnArchivo;
	}
	public void setColumnasEnArchivo(List<ColumnaArchivoVO> columnasEnArchivo) {
		this.columnasEnArchivo = columnasEnArchivo;
	}
	
	
	
}
