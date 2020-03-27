package mx.com.teclo.siye.api.rest.async;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.negocio.service.async.AsyncArchivoLoteService;

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

	@Autowired
	private AsyncArchivoLoteService asyncLoteService;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity recibirArchivoLote(@RequestParam("file") MultipartFile archivoLote)
			throws IOException, BusinessException {
		Integer lineas = asyncLoteService.registrarArchivoLote(archivoLote);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
