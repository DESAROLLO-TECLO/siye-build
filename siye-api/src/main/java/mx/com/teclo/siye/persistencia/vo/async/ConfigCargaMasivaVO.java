package mx.com.teclo.siye.persistencia.vo.async;

import java.util.List;
import java.util.Map;

import mx.com.teclo.siye.persistencia.vo.proceso.LoteOrdenServicioVO;

public class ConfigCargaMasivaVO {
	private LoteOrdenServicioVO configLote;
	private TipoLayoutVO configLayout;
	private Map<String, List<ColumnaVO>> configSecciones;
	private List<String> configInsercion;
	private Map<String, InsercionTablaVO> configMoldesSQL;
	public LoteOrdenServicioVO getConfigLote() {
		return configLote;
	}
	public void setConfigLote(LoteOrdenServicioVO configLote) {
		this.configLote = configLote;
	}
	public TipoLayoutVO getConfigLayout() {
		return configLayout;
	}	
	public List<String> getConfigInsercion() {
		return configInsercion;
	}
	public void setConfigInsercion(List<String> configInsercion) {
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
	
	
	
}
