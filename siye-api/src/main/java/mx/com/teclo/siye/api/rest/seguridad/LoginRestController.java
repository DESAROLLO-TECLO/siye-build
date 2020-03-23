package mx.com.teclo.siye.api.rest.seguridad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.assembler.menu.MenuAssembler;
import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.persistencia.hibernate.dto.seguridad.MenusDTO;
import mx.com.teclo.arquitectura.ortogonales.seguridad.util.TokenUtils;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.AuthenticationRequestVO;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.AuthenticationResponseVO;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.MenuDinamicoVO;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.service.favoritos.FavoritosService;
import mx.com.teclo.arquitectura.ortogonales.service.menus.MenusService;
import mx.com.teclo.arquitectura.ortogonales.service.passwordprimeruso.ContraseniaPrimerUsoService;

@RestController
@RequestMapping("/login")
public class LoginRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	@Qualifier("customUserDetailService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MenusService menusService;
	
	@Autowired
	private FavoritosService favoritosService;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
    
	@Autowired
	private ContraseniaPrimerUsoService contraseniaPrimerUsoService;
	
	public final String tokenHeader = "X-Auth-Token";

    @Value("${app.config.codigo}")
    private String codeApplication;
    
    /*@Autowired
	private BitacoraCambiosService bitac;*/
    
    
    
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponseVO> authenticationRequest(@RequestBody AuthenticationRequestVO authenticationRequest) throws AuthenticationException, BusinessException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		UsuarioFirmadoVO usuarioFirmado = (UsuarioFirmadoVO) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenUtils.generateToken(usuarioFirmado);
		Boolean stContrasenia = contraseniaPrimerUsoService.checkPwdFirstChange(codeApplication, usuarioFirmado);
		/*bitac.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(), 
				1L, 
				1L, 
				usuarioFirmado.getUsername(), 
				usuarioFirmado.getUsername(), 
				usuarioFirmado.getId(), 
				"0",
				"--", 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());*/
		return new ResponseEntity<AuthenticationResponseVO>(new AuthenticationResponseVO(token, favoritosService.getFavorites(), stContrasenia), HttpStatus.OK);
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<AuthenticationResponseVO> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = this.tokenUtils.getUserNameFromToken(token);
		UsuarioFirmadoVO user = (UsuarioFirmadoVO) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token,user);
			return new ResponseEntity<AuthenticationResponseVO>(new AuthenticationResponseVO(refreshedToken), HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public ResponseEntity<List<MenuDinamicoVO>> buscarMenuUsuario() throws NotFoundException {
		UsuarioFirmadoVO usuarioFirmado = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<MenusDTO> menus = menusService.buscarMenuUsuario(usuarioFirmado.getPerfilId(),usuarioFirmado.getId(),codeApplication);
		//List<MenuDinamicoVO> menusVO = ResponseConverter.converterLista(new ArrayList<>(), menus, MenuDinamicoVO.class);
		List<MenuDinamicoVO> menusVO = MenuAssembler.listMenuDinamicoVO(menus);
		if (menus.isEmpty()) {
			throw new NotFoundException("No se encontraron menus con los datos informados.");
		}	
		return new ResponseEntity<List<MenuDinamicoVO>>(menusVO, HttpStatus.OK);
	}
	
}
