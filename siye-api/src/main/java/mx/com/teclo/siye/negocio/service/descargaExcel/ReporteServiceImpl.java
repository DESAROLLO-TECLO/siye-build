package mx.com.teclo.siye.negocio.service.descargaExcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.teclo.generaexcel.v2.PeticioReporteVO;
import mx.com.teclo.generaexcel.v2.PropiedadesReporte;
import mx.com.teclo.generaexcel.v2.ReporteExcel;
import mx.com.teclo.siye.persistencia.vo.incidencia.ReporteVO;
import mx.com.teclo.siye.util.comun.RutinasTiempoImpl;

@Service
public class ReporteServiceImpl implements ReporteService{

	@Autowired
	private ReporteExcel reporteExcel;
	
	@Autowired
	private RutinasTiempoImpl rutinasTiempo;
	
	@Override
	public byte[] reporteEXCEL(ReporteVO vo) {
		ByteArrayOutputStream bo = null;
		byte [] byteReturn = null;
		List<Object> headers = new ArrayList<>(vo.getHeader());
		List<String> subtitulos = new ArrayList<String>();
		subtitulos.add("Fecha de Consulta: "+rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy HH:ss", new Date()));
		subtitulos.add(" ");
		PropiedadesReporte pReporteVO = new PropiedadesReporte();
		pReporteVO.setTxTituloExcel(vo.getTitulo()==null?"Reporte":vo.getTitulo());
		pReporteVO.setNbReporte(vo.getTitulo()==null?"Reporte":vo.getTitulo());
		pReporteVO.setTxExtension(".xlsx");
		pReporteVO.setSubtitulos(subtitulos);
		List<Object> valuesArray = new ArrayList<>(vo.getValues());
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO(pReporteVO, headers, valuesArray);
		try {
			bo = reporteExcel.generaReporte(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(bo != null) {
			byteReturn = bo.toByteArray();
		}
		return byteReturn;
	}

}
