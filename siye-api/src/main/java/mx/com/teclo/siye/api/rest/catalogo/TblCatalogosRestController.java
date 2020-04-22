package mx.com.teclo.siye.api.rest.catalogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.negocio.service.catalogo.TblCatalogosService;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;


@RestController
@RequestMapping("/catalogo")
public class TblCatalogosRestController {
	
	@Autowired
	private TblCatalogosService tblCatalogosService;

	@RequestMapping(value = "/altaCentrodeInstalacion", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<String> altaCentrodeInstalacion(@RequestBody CentroInstalacionVO centroInstalacionVO) throws BusinessException{
		String resp = tblCatalogosService.altaCentrodeInstalacion(centroInstalacionVO);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
		
	}
	
}
