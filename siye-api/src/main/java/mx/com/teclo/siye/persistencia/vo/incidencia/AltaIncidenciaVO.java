package mx.com.teclo.siye.persistencia.vo.incidencia;

import java.io.Serializable;
import java.util.Date;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.proceso.TipoSeguimientoVO;

public class AltaIncidenciaVO implements Serializable {

	private static final long serialVersionUID = 7580375171139464745L;
	
	private String OrdenServicio;
	private TipoSeguimientoVO tpIncidencia;
	private TipoSeguimientoVO prioridad;
	private String descripcion;
	private ExpedienteImgVO expedientesImgVO;
	
	
	public String getOrdenServicio() {
		return OrdenServicio;
	}
	public void setOrdenServicio(String ordenServicio) {
		OrdenServicio = ordenServicio;
	}
	public TipoSeguimientoVO getTpIncidencia() {
		return tpIncidencia;
	}
	public void setTpIncidencia(TipoSeguimientoVO tpIncidencia) {
		this.tpIncidencia = tpIncidencia;
	}
	public TipoSeguimientoVO getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(TipoSeguimientoVO prioridad) {
		this.prioridad = prioridad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ExpedienteImgVO getExpedientesImgVO() {
		return expedientesImgVO;
	}
	public void setExpedientesImgVO(ExpedienteImgVO expedientesImgVO) {
		this.expedientesImgVO = expedientesImgVO;
	}
}