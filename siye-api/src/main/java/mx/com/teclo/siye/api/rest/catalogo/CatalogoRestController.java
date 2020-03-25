package mx.com.teclo.siye.api.rest.catalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.catalogo.CatalogoService;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;

@RestController
@RequestMapping("/catalogo")
public class CatalogoRestController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	@RequestMapping(value="/encuesta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_ENCUESTA')")
	public ResponseEntity<List<StEncuestaVO>>encuesta() throws NotFoundException{
		List<StEncuestaVO> listCatalogoReturn = catalogoService.estatusEncuesta();
		return new ResponseEntity<List<StEncuestaVO>>(listCatalogoReturn, HttpStatus.OK);
		}
	
	@RequestMapping(value="/tipoVehiculo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICE_CAT_VEHICULO')")
	public ResponseEntity<List<TipoVehiculoVO>> tipoVehiculo() throws NotFoundException{
		List<TipoVehiculoVO> listTpVehiculoReturn = catalogoService.tipoVehiculo();
		return new ResponseEntity<List<TipoVehiculoVO>>(listTpVehiculoReturn, HttpStatus.OK);
	}

}
