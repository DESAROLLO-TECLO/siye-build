package mx.com.teclo.siye.api.rest.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.persistencia.password.vo.CambioContraseniaVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.password.vo.ResponseVO;
import mx.com.teclo.arquitectura.ortogonales.service.password.ContraseniaSeguraService;
import mx.com.teclo.siye.negocio.service.usuario.UsuarioService;
import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;
import mx.com.teclo.siye.util.comun.FilterValuesVO;

@RestController 
@RequestMapping("/usuarios")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ContraseniaSeguraService contraseniaSeguraService;
	
//	OBTENER USUARIOS POR PARÁMETRO
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_USUARIOS_PARAM')")
	public ResponseEntity<List<UsuarioVO>> findUsersByParams(@RequestParam("parameter") String parameter,
			@RequestParam("value") String value	) {

		List<UsuarioVO> listaUsuarios = usuarioService.findUsersByParams(parameter, value);

		if(listaUsuarios == null) {
			return new ResponseEntity<List<UsuarioVO>>(listaUsuarios, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<UsuarioVO>>(listaUsuarios, HttpStatus.OK);
	}

//	OBTENER USUARIOS POR PERFIL
	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_USUARIOS_PERFIL')")
	public ResponseEntity<List<UsuarioVO>> findUsersByProfileParams(@RequestParam("profile") String profile) {

		List<UsuarioVO> listaUsuarios = usuarioService.findUsersByProfile( profile );

		if(listaUsuarios == null) {
			return new ResponseEntity<List<UsuarioVO>>(listaUsuarios, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<UsuarioVO>>(listaUsuarios, HttpStatus.OK);
	}

//	REESTABLECER LA CONTRASEÑA DEL USUARIO
	@RequestMapping(value = "/password/restablecer", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('REESTABLECER_CONTRASENIA_USUARIO')")
	public ResponseEntity<UsuarioVO> resetPassword(@RequestBody UsuarioVO usuarioVO) {

		usuarioService.resetPassword(usuarioVO.getCdUsuario());

		return new ResponseEntity<UsuarioVO>(usuarioVO, HttpStatus.OK);
	}

//	ACTUALIZAR LA CONTRASEÑA DEL USUARIO
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('CAMBIO_CONTRASENIA')")
	public ResponseEntity<ResponseVO> updatePassword(@RequestBody CambioContraseniaVO vo) {
		ResponseVO responseVO =  contraseniaSeguraService.updatePassword(vo.getNewPassword(), vo.getNewPasswordRepeat(), vo.getPassword());
		if(responseVO.getCode().equals(409))
			return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.CONFLICT);
		return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
	}

//	ACTUALIZAR EL ESTATUS DEL USUARIO
	@RequestMapping(value = "/status", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_ESTATUS_USUARIO')")
	public ResponseEntity<UsuarioVO> changeStatusUser(@RequestBody UsuarioVO usuarioVO) {

		usuarioService.changeStatusUser(usuarioVO);

		return new ResponseEntity<UsuarioVO>(usuarioVO, HttpStatus.OK);
	}

//	OBTENER LOS TIPOS DE BÚSQUEDA
	@RequestMapping(value = "/tipoBusqueda", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> getQueryParamsUser() {

		List<FilterValuesVO> listaBusqueda = usuarioService.getQueryParamsUser();

		return new ResponseEntity<List<FilterValuesVO>>(listaBusqueda, HttpStatus.OK);
	}
	
//	OBTENER LOS TIPOS DE BÚSQUEDA PARA LA OPCIÓN TODOS
	@RequestMapping(value = "/searchForAll", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> getQueryParamsAll() {

		List<FilterValuesVO> listaBusqueda = usuarioService.getQueryParamsAll();

		return new ResponseEntity<List<FilterValuesVO>>(listaBusqueda, HttpStatus.OK);
	}

//	GUARDAR USUARIO
	@RequestMapping(value="/saveOrUpdateUser", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('GUARDAR_USUARIO')")
	public ResponseEntity<UsuarioVO> saveUser(@RequestBody UsuarioVO usuarioVO) throws NotFoundException {
		String action = "new";
		Boolean operation = usuarioService.saveOrUpdateUser(usuarioVO, action);
		if(operation)
			usuarioVO = usuarioService.getUserByCd(usuarioVO.getCdUsuario());
		return new ResponseEntity<UsuarioVO>( usuarioVO, HttpStatus.OK);
	}

//	ACTUALIZAR USUARIO
	@RequestMapping(value="/saveOrUpdateUser", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_USUARIO')")
	public ResponseEntity<UsuarioVO> editUser(@RequestBody UsuarioVO usuarioVO) throws NotFoundException {
		String action = "edit";
		Boolean operation = usuarioService.saveOrUpdateUser(usuarioVO, action);
		if(operation)
			usuarioVO = usuarioService.getUserByCd(usuarioVO.getCdUsuario());
		return new ResponseEntity<UsuarioVO>(usuarioVO, HttpStatus.OK);
	}

//	ENCRIPTAR | DESENCRIPTAR CONTRASEÑA
	@RequestMapping(value="/toggleEncryption", method = RequestMethod.GET, produces = "text/plain")
	public ResponseEntity<String> toggleEncryption(@RequestParam("password") String password,
		@RequestParam("action") String action) {

		password = usuarioService.toggleEncryption(password, action);

		return new ResponseEntity<String>(password, HttpStatus.OK);
	}
}
