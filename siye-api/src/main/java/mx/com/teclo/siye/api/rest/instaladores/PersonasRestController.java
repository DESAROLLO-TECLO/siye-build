package mx.com.teclo.siye.api.rest.instaladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.instalador.PersonasService;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;

@RestController
@RequestMapping("/instalador")
public class PersonasRestController {
	
	@Autowired
	private PersonasService personasService;
	
	@RequestMapping(value="/nuevoInstalador", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('SERVICE7_REP_USUARIO')")
	public ResponseEntity<PersonaVO> nuevoInstalador (
		@RequestBody PersonaGenericaVO personaGenericaVO
	)throws Exception, BusinessException, NotFoundException{
		String mensajeErr = "";
		PersonaVO result = personasService.nuevoInstalador(personaGenericaVO, mensajeErr);
		if(result.getExistia() == false) {
			personasService.ordenarInstaladores(mensajeErr);
		}
		return new ResponseEntity<PersonaVO>(result, HttpStatus.OK);
	}
}
