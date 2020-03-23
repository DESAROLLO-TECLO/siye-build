package mx.com.teclo.siye.api.rest.perfil;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.siye.negocio.service.menu.MenuService;
import mx.com.teclo.siye.negocio.service.perfil.PerfilService;
import mx.com.teclo.siye.persistencia.vo.menu.AccesosVO;
import mx.com.teclo.siye.persistencia.vo.menu.MenuVO;
import mx.com.teclo.siye.persistencia.vo.perfil.ConsultaPerfilVO;
import mx.com.teclo.siye.persistencia.vo.perfil.PerfilVO;



@RestController
public class PerfilRestController {
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private MenuService menuService;
	
	@Value("${app.config.codigo}")
	private String cdApplication;
    
	@RequestMapping(value = "/perfiles", method = RequestMethod.GET)
	public ResponseEntity<List<PerfilVO>> findProfilesByApp() {
		List<PerfilVO> listaPerfiles = perfilService.findProfilesByApp();

		return new ResponseEntity<List<PerfilVO>>(listaPerfiles, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/menusbyperfil", method = RequestMethod.GET)
	public ResponseEntity<List<MenuVO>> findMenusByProfile(@RequestParam("profile") Long profile) {
		
		List<MenuVO> menuArray = menuService.findMenusByProfile(profile, cdApplication);
		
		return new ResponseEntity<List<MenuVO>>(menuArray, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaPerfiles", method = RequestMethod.GET)
	public ResponseEntity<List<ConsultaPerfilVO>> consultaProfiles() {
		 List<ConsultaPerfilVO> listaPerfiles = perfilService.consultaPerfiles();

		return new ResponseEntity<List<ConsultaPerfilVO>>(listaPerfiles, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/consultaMenusPorPerfil", method = RequestMethod.GET)
	public ResponseEntity<Map> consultaMenusPorPerfil(@RequestParam("profile") Long profile) {
		Map menuArray = menuService.consultarMenusPorPerfil(profile, cdApplication);
		
		return new ResponseEntity<Map>(menuArray, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/asignaAccesoPerfil", method = RequestMethod.GET)
	public ResponseEntity<Boolean> consultaMenusPorPerfil(@RequestParam("profile") Long profile, @RequestParam("menu") Long menu, @RequestParam("codigo") Long codigo) {
		//Realizamos la addición o eliminación del permiso depende del codigo
		if(codigo == 0L){
			//Revocamos los privilegios al modulo
			menuService.revocaModulo(profile, menu);
		}
		if(codigo == 1L){
			//Autorizamos los privilegios al modulo
			menuService.autorizaModulo(profile, menu);
		}
		if(codigo == 2L){
			//Autorizamos los privilegios al modulo
			menuService.autorizaTodoModulo(profile);
		}
		if(codigo == 3L){
			//Autorizamos los privilegios al modulo
			menuService.revocaTodoModulo(profile);
		}
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/restablecerAccesoPerfil", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> restablecerMenusPorPerfil(@RequestBody AccesosVO accesosVO) {
		//Restablecemos todos los menus a su estado anterior
		Boolean res = menuService.restablecerModulos(accesosVO);
		
		return new ResponseEntity<Boolean>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/habilitaDeshabilitaPerfil", method = RequestMethod.GET)
	public ResponseEntity<Boolean> habDesPerfil(@RequestParam("profile") Long profile, @RequestParam("activo") Boolean activo) {
		Boolean res = perfilService.habilitaDeshabilitaPerfil(profile, activo);
		
		return new ResponseEntity<Boolean>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/crearPerfil", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> crearPerfil(@RequestBody PerfilVO profile) {
		Boolean res = perfilService.crearPerfil(profile);		
		return new ResponseEntity<Boolean>(res, HttpStatus.OK);
	}
}
