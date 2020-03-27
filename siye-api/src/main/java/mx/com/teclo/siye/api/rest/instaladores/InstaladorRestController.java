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
import mx.com.teclo.siye.negocio.service.instalador.InstaladorService;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;

@RestController
@RequestMapping("/instalador")
public class InstaladorRestController {
	
	@Autowired
	private InstaladorService instaladorService;
	
	@RequestMapping(value="/nuevoInstalador", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('SERVICE7_REP_USUARIO')")
	public ResponseEntity<PersonaGenericaVO> nuevoInstalador (
		@RequestBody PersonaGenericaVO personaGenericaVO
	)throws Exception, BusinessException, NotFoundException{
		String mensajeErr = "";
		PersonaGenericaVO result = instaladorService.nuevoInstalador(personaGenericaVO, mensajeErr);
		if(personaGenericaVO.getExistia() == false) {
			instaladorService.ordenarInstaladores(mensajeErr);
		}
		return new ResponseEntity<PersonaGenericaVO>(result, HttpStatus.OK);
	}
}
