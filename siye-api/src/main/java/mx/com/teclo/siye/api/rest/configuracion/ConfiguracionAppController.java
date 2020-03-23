package mx.com.teclo.siye.api.rest.configuracion;

 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.ControllerException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.ConfiguracionVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.FavoritosVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.MenuOrientacionVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.ResolucionVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.TemaVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.TipoNavegacionVO;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.AmbienteVO;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.service.configuracion.ConfiguracionAplicacionService;
import mx.com.teclo.arquitectura.ortogonales.service.favoritos.FavoritosService;
 
@RestController
@RequestMapping("/aplicacion")
public class ConfiguracionAppController {

    @Value("${app.config.codigo}")
    private String codeApplication;
   
    
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
    
	@Autowired
	private ConfiguracionAplicacionService configuracionAplicacionService;
	
	@Autowired
	private FavoritosService favoritosService;
    
	/**
	 * @author Cesar Gomez
	 * @return <ConfiguracionVO>
	*/
//	OBTENER CONFIGURACIÓN DE LA APLICACIÓN
	@RequestMapping(value = "/configuraciones", method = RequestMethod.GET)
	public ResponseEntity<ConfiguracionVO> buscarMenuUsuario() throws NotFoundException {
			
		ConfiguracionVO configuracionVO = null;
		
		configuracionVO = configuracionAplicacionService.buscarConfiguracionXAplicacion(codeApplication);
		if (configuracionVO.equals(null)) {
			throw new NotFoundException("No se encontraron las configuraciones de la aplicación.");
		}	
		return new ResponseEntity<ConfiguracionVO>(configuracionVO, HttpStatus.OK);
	}
	 
	
	/**
	 * @author Cesar Gomez
	 * @return <List<ResolucionVO>>
	*/
//	OBTENER RESOLUCIONES
	@RequestMapping(value = "/resoluciones", method = RequestMethod.GET)
	public ResponseEntity<List<ResolucionVO>> obtenerResoluciones() throws NotFoundException {
		
		List<ResolucionVO> resolucionVO;
		
		resolucionVO = configuracionAplicacionService.obtenerListaResoluciones();
		if (resolucionVO.equals(null)) {
			throw new NotFoundException("No se encontraron las resoluciones de la aplicación.");
		}
		
		return new ResponseEntity<List<ResolucionVO>>(resolucionVO, HttpStatus.OK);
	}
	
	/**
	 * @author Cesar Gomez
	 * @return <List<TemaVO>>
	*/
//	OBTENER TEMAS
	@RequestMapping(value = "/temas", method = RequestMethod.GET)
	public ResponseEntity<List<TemaVO>> obtenerTemas() throws NotFoundException {
		
		List<TemaVO> temaVO;
		
		temaVO = configuracionAplicacionService.obtenerTemasXAplicacion(codeApplication);
		if (temaVO.equals(null)) {
			throw new NotFoundException("No se encontraron los temas de la aplicación.");
		}
		
		return new ResponseEntity<List<TemaVO>>(temaVO, HttpStatus.OK);
	}
	
	/**
	 * @author Cesar Gomez
	 * @param configuracionVO
	 * @return <ConfiguracionVO>
	 * @throws BusinessException 
	*/
//	GUARDAR/ACTUALIZAR CONFIGURACION
	@RequestMapping(value="/guardarConfiguracion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_CONFIGURACION_APLICACION')")
	public ResponseEntity<ConfiguracionVO> guardarConfiguracion(
		@RequestBody ConfiguracionVO configuracionVO)
	throws ControllerException, IOException, BusinessException {
		
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
        if(configuracionVO.getStrSectionToModif() != null) {
            configuracionAplicacionService.saveConfiguracion(configuracionVO); // Actualiza la configuracón por secciones
        }else {
            configuracionAplicacionService.saveConfiguracion(configuracionVO, codeApplication, usuario);
        }
		return new ResponseEntity<ConfiguracionVO>(configuracionVO, HttpStatus.OK);
	}	
	/**
	 * @author Cesar Gomez
	 * @return <AmbienteVO>
	*/
//	OBTENER CONFIGURACIÓN DE LA APLICACIÓN
	@RequestMapping(value = "/ambiente", method = RequestMethod.GET)
	public ResponseEntity<AmbienteVO> findCdAmbiente() throws NotFoundException {
			
		AmbienteVO ambienteVO = null;
		
		ambienteVO = configuracionAplicacionService.getCdAmbienteXAplication(codeApplication);
		if (ambienteVO.equals(null)) {
			throw new NotFoundException("No se encontró el código del ambiente");
		}	
		return new ResponseEntity<AmbienteVO>(ambienteVO, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/menu/favorite", method = RequestMethod.POST)
	 public ResponseEntity<List<Long>> favorite(@RequestBody FavoritosVO vo) throws NotFoundException, BusinessException {
		 Boolean ban = favoritosService.favoriteSetting(vo.getFavoriteCollection());
		 List<Long> listReturn = new ArrayList<>();
		 if(ban)
			 listReturn = favoritosService.getFavorites();
		 return new ResponseEntity<List<Long>>(listReturn, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/menu/orientation", method = RequestMethod.GET)
     public ResponseEntity<List<MenuOrientacionVO>> getMenuOrientation() throws NotFoundException {
             List<MenuOrientacionVO> listReturn = configuracionAplicacionService.getMenuOrientation();
             return new ResponseEntity<List<MenuOrientacionVO>>(listReturn, HttpStatus.OK);
     }

     @RequestMapping(value = "/menu/nevigation", method = RequestMethod.GET)
     public ResponseEntity<List<TipoNavegacionVO>> getNavigationType() throws NotFoundException {
             List<TipoNavegacionVO> listReturn = configuracionAplicacionService.getNavigationType();
             return new ResponseEntity<List<TipoNavegacionVO>>(listReturn, HttpStatus.OK);
     }

}
