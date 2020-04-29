package mx.com.teclo.siye.negocio.service.seguimientoOs;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.DetalleIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.EncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.PreguntasDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesosOrdenServicioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ReporteExcelVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

public interface SeguimientoOsService {
	
	/*
	 * @author Maverick
	 * @param List<String> columnas
	 * @param String fechaInicio
	 * @param String fechaFin
	 * @param Long idUsuario 
	 * @return List<>
	 * metodo para obener el seguimiento de las os por centro de isntalación*/
	public List<SeguimientoOrdenServicioVO> getSeguimientoOrdenServicio(Long idSupervisor, List<String> columnas, List<String> colOmitidas, String fInicio, String fFin);
	
	
	/*
	 * @author Maverick
	 * @param idOrdenServicio
	 * @return List<SeguimientoEtapasVO>
	 * metodo para obener las etapas de un OS, y sus encuestas y el porcentaje que lleva cada etapa */
	public ProcesosOrdenServicioDetalleVO getDetalleByEtapas(Long idOrdenServicio);
	
	/*
	 * @author Maverick
	 * @param idOrdenServicio
	 * @param idProceso
	 * @return List<EncuestaDetalleVO>
	 *  Metodo para consultar, el acance de una Os en especifico  */
	public List<EncuestaDetalleVO> getDetalleProcesos(Long idOrdenServicio, List<Long> idProceso);
	
	 /**
	  * Descripción: metodo que consulta la preguntas y su respuesta de una encuesta especifica,
	  * @author Maverick
	  * @param idOrdenServicio
	  * @param idEncuesta
	  * @return List<PreguntasDetalleVO>  
	  * */
	public List<PreguntasDetalleVO> getDetallePregunta(Long idOrdenServicio, Long idEncuesta);
	
	 /**
	  * Descripción: metodo para cunsultar las imagenes por nivel y tipo evidencia o incidencia ,
	  * @author Maverick
	  * @param idOrdenServicio
	  * @param valor
	  * @param nivel
	  * @param clase
	  * @return List<PreguntasDetalleVO>  
	  * */
	public List<ImagenVO> getDetalleImagenByNivel(Long idOrdenServicio, Long valor, String nivel, String clase);
	
	 /**
	  * Descripción: consulta el detalle, nivel, estatus de la incidencia 
	  * @author Maverick
	  * @param idOrdenServicio
	  * @return List<DetalleIncidenciaVO>  
	  * */
	public List<DetalleIncidenciaVO> getDetalleSeguimientoIncidencia(Long idOrdenServicio);
	
	
	/**
	 * Metodo para descargar el reprote excel a cualquier nivel que se encuentre dentro de las tablas
	 * @author Maverick
	 * @param List<ReporteExcelVO>
	 * @retun ByteArrayOutputStream 
	 * */
	public ByteArrayOutputStream getReporteSeguimientoOs(ReporteExcelVO listaObj);
	
	/**
	 * Metodo para realizar el corte diario de OS que no se atendieron en el dia correinte 
	 * @author Maverick
	 * @param String
	 * @retun String 
	 * */
	public String hacerCorteDiario(String fecha);
	


}
