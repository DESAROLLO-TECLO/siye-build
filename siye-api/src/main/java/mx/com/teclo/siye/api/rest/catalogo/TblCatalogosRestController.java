package mx.com.teclo.siye.api.rest.catalogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.catalogo.TblCatalogosService;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;


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

	@RequestMapping(value ="/updateCentrodeInstalacion", method = RequestMethod.PUT)
	public ResponseEntity<CentroInstalacionVO> updateCentrodeInstalacion(@RequestBody CentroInstalacionVO centroInstalacionVO) throws NotFoundException, BusinessException{
		Boolean  status = tblCatalogosService.actualizaCentrodeInstalacion(centroInstalacionVO);
		CentroInstalacionVO CentroInstalacionVORespuesta = new CentroInstalacionVO();
		if (status) {
			CentroInstalacionVORespuesta = tblCatalogosService.findCentroInstalacion(centroInstalacionVO.getIdCentroInstalacion());
		}
		return new ResponseEntity<CentroInstalacionVO>(CentroInstalacionVORespuesta,  HttpStatus.OK);
	}
	
	@RequestMapping(value = "/altaPersona", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<String> altaPersona(@RequestBody PersonaVO personaVO) throws BusinessException{
		String resp = tblCatalogosService.altaPersona(personaVO);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
		
	}

	@RequestMapping(value ="/updatePersona", method = RequestMethod.PUT)
	public ResponseEntity<PersonaVO> updatePersona(@RequestBody PersonaVO personaVO) throws NotFoundException, BusinessException{
		Boolean  status = tblCatalogosService.actualizaPersona(personaVO);
		PersonaVO PersonaVORespuesta = new PersonaVO();
		if (status) {
			PersonaVORespuesta = tblCatalogosService.findPersona(personaVO.getIdPersona());
		}
		return new ResponseEntity<PersonaVO>(PersonaVORespuesta,  HttpStatus.OK);
	}
	
}
