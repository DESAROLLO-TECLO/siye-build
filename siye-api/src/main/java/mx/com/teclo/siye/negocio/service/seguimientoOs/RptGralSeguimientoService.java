package mx.com.teclo.siye.negocio.service.seguimientoOs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclo.generaexcel.vo.PeticioReporteVO;
import mx.com.teclo.generaexcel.vo.PropiedadesReporte;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;
import mx.com.teclo.siye.util.comun.RutinasTiempoImpl;

@Service("rptGeneral")
public class RptGralSeguimientoService {
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	
	public ByteArrayOutputStream generarReporte(List<SeguimientoOrdenServicioVO> informacion, String columnas, String fechaInicio, String fechaFin) {
		
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		RutinasTiempoImpl ru= new RutinasTiempoImpl();
		
		
		//obj cuerpo de reporte 
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();
		
		//Parametros de busqueda 
		subtitulos.add("Fecha de Consulta: "+ ru.getFecha_ddMMYYYY_hhmmss(new Date()));
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");
		subtitulos.add("Rango de Fechas: "+ fechaInicio +" al "+ fechaFin);
		subtitulos.add("ESTATUS: "+columnas);
	
		
		/*Columnas del reporte*/
		titulos.add("MODULO DE ATENCION");
		titulos.add("EN CURSO");
		titulos.add("COMPLETA");
		titulos.add("PROGRAMADA");
		titulos.add("NO PROGRAMADA");
		titulos.add("INCIDENCIA");
		
		propiedadesReporte.setNombreReporte("ReporteTotalSeguimientoOS");
		propiedadesReporte.setExtencionArchvio(".xls");
		propiedadesReporte.setTituloExcel("Reporte Seguimiento Orden Servicio");
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		for(SeguimientoOrdenServicioVO info: informacion) {
			List<String> rprt = new ArrayList<String>();		
			rprt.add(info.getNbModulo());
			rprt.add(info.getNuEnCurso()!=null ? info.getNuEnCurso().toString() : "0");
			rprt.add(info.getNuCompleta()!=null ? info.getNuCompleta().toString() : "0");
			rprt.add(info.getNuProgramado() !=null ? info.getNuProgramado().toString() : "0");
			rprt.add(info.getNuNoProgramado()!=null ? info.getNuNoProgramado().toString() : "0");
			rprt.add(info.getNuIncidencias()!=null ? info.getNuIncidencias().toString() : "0");
			contenido1.add(rprt);
			
		}

		contenido.add(contenido1);
		
		peticioReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticioReporteVO.setEncabezado(encabezadoTitulo);
		peticioReporteVO.setContenido(contenido);
		
		try {
			byteArrayOutputStream =	peticionReporteBOImpl.generaReporteExcel(peticioReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return byteArrayOutputStream;
	
	}

}
