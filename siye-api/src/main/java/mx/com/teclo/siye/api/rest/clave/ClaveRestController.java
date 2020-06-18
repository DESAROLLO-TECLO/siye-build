package mx.com.teclo.siye.api.rest.clave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.clave.ClaveService;
import mx.com.teclo.siye.persistencia.vo.clave.PasswordVO;

@RestController
public class ClaveRestController {

	@Autowired
	ClaveService claveService;
	
	@RequestMapping(value="/clave", method = RequestMethod.GET)
	public ResponseEntity<PasswordVO> getClave() throws NotFoundException {
		PasswordVO passwordVO = claveService.clave();
		if(passwordVO!=null)
			return new ResponseEntity<PasswordVO>(passwordVO, HttpStatus.OK);
		else
			throw new NotFoundException("No hay contraseña activa");
	}
}