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
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServcioDetalleVO;
import mx.com.teclo.siye.util.comun.RutinasTiempoImpl;

@Service("rptDetalle")
public class RptDetalleSeguimientoService {

	private ByteArrayOutputStream byteArrayOutputStream = null;

	public ByteArrayOutputStream generarReporte(List<OrdenServcioDetalleVO> info, String centroInstalacion, String fechaInicio, String fechaFin) {
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		RutinasTiempoImpl ru = new RutinasTiempoImpl();

		// obj cuerpo de reporte
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		// Parametros de busqueda
		subtitulos.add("Fecha de Consulta: " + ru.getFecha_ddMMYYYY_hhmmss(new Date()));
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");
		subtitulos.add("Rango de Fechas: " + fechaInicio + " al " + fechaFin);
		subtitulos.add("Centro de Instalación: "+ centroInstalacion.toUpperCase());
		
		/*Columnas del reporte*/
		titulos.add("ORDEN SERIVICIO");
		titulos.add("PLACA");
		titulos.add("ETAPA");
		titulos.add("PROCESO");
		titulos.add("INCIDENCIA");
		titulos.add("ESTADO");
		
		propiedadesReporte.setNombreReporte("Seguimiento");
		propiedadesReporte.setExtencionArchvio(".xls");
		propiedadesReporte.setTituloExcel("Reporte Seguimiento Orden Servicio");
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		for(OrdenServcioDetalleVO d : info) {
			List<String> rprt = new ArrayList<String>();
			rprt.add(d.getNuOrdenServicio());
			rprt.add(d.getTxPlaca());
			rprt.add(d.getTxEtapa());
			rprt.add(d.getTxProceso());
			rprt.add(d.getNuIncidencia().toString());
			rprt.add(d.getEstado());
			contenido1.add(rprt);
		}
		
	    contenido.add(contenido1);
		
		peticioReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticioReporteVO.setEncabezado(encabezadoTitulo);
		peticioReporteVO.setContenido(contenido);	
		

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticioReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;

	}

}