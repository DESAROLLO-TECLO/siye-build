package mx.com.teclo.siye.api.rest.conductores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.siye.negocio.service.conductor.ConductorService;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

@RestController
@RequestMapping("/conductor")
public class ConductorRestController {
	
	@Autowired
	private ConductorService conductorService;
	
	@RequestMapping(value="/nuevoConductor", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('SERVICE7_REP_USUARIO')")
	public ResponseEntity<PersonaGenericaVO> nuevoConductor (
		@RequestBody PersonaGenericaVO personaGenericaVO
	){ 
		PersonaGenericaVO result = conductorService.nuevoConductor(personaGenericaVO);
		return new ResponseEntity<PersonaGenericaVO>(result, HttpStatus.OK);
	}
}
