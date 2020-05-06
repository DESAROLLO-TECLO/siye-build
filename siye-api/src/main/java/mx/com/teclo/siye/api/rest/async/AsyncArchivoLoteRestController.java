package mx.com.teclo.siye.api.rest.async;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.async.AsyncArchivoLoteService;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;

/**
 * Recibe el archivo lote para registrar masivamente &oacute;rdenes de servicio
 * en el sistema
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
@RestController
@RequestMapping("/async/lote")
public class AsyncArchivoLoteRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncArchivoLoteRestController.class);
	@Autowired
	private AsyncArchivoLoteService asyncLoteService;


	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity<ArchivoLoteVO> recibirArchivoLote(@RequestParam("file") MultipartFile archivoLote)
			throws IOException, BusinessException {

		Long idFile = asyncLoteService.registrarArchivoLote(archivoLote);
		LOGGER.debug("Archivo lote registrado con ID " + idFile);
		asyncLoteService.cargarArchivoLote(idFile);

		return new ResponseEntity<ArchivoLoteVO>(asyncLoteService.obtenerArchivoLote(idFile), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getFilesUploadToDay")
	public ResponseEntity<List<ArchivoLoteVO>> getFilesUploadToDay() throws NotFoundException{
	
		List<ArchivoLoteVO> lisLotes=asyncLoteService.getFilesUploadToDay();
		
		if(lisLotes == null || lisLotes.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return new ResponseEntity<List<ArchivoLoteVO>>(lisLotes, HttpStatus.OK);
	}
	
}
