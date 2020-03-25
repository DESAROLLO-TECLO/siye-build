package mx.com.teclo.siye.api.rest.ordenServicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.ordenServicio.OrdenServicioService;
import mx.com.teclo.siye.persistencia.vo.ordenServicio.OrdenServicioVO;

@RestController
@RequestMapping("/ordenServicio")
public class OrdenServicioRestController {

	@Autowired
	private OrdenServicioService ordenServicioService;

	@RequestMapping(value = "/consultaOrden", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('')")
	public ResponseEntity<List<OrdenServicioVO>> consultaOrden(@RequestParam(value="cdTipoBusqueda") String cdTipoBusqueda, @RequestParam(value="valor") String valor) throws NotFoundException {
		List<OrdenServicioVO> listOrdenServicioVO = ordenServicioService.consultaOrden(cdTipoBusqueda, valor);
		return new ResponseEntity<List<OrdenServicioVO>>(listOrdenServicioVO, HttpStatus.OK);
	}

}