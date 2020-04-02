package mx.com.teclo.siye.api.rest.incidencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.teclo.siye.negocio.service.incidencia.ReporteService;
import mx.com.teclo.siye.persistencia.vo.incidencia.ReporteVO;

@RestController
@RequestMapping("/reporte")
public class ReporteRestController {

	@Autowired
	private ReporteService reporteService;

	@RequestMapping(value="/reporteIncidencia", method = RequestMethod.POST)
	public ResponseEntity<byte[]> reporteIncidencia (@RequestBody ReporteVO reporteVO){
		byte [] byteToReturn = reporteService.reporteEXCEL(reporteVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		
		String fileName = reporteVO.getTitulo() != null ? reporteVO.getTitulo(): "Reporte de Incidencias"; 
		
		headers.add("Content-Disposition", "attachment; filename="+fileName+".xlsx");
		headers.add("filename", fileName+".xlsx");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(byteToReturn.length);
		return new ResponseEntity<byte[]>(byteToReturn, headers, HttpStatus.OK);	
	}
	
}
