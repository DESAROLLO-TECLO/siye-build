package mx.com.teclo.siye.api.rest.incidencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.negocio.service.incidencia.IncidenciaService;
import mx.com.teclo.siye.persistencia.vo.incidencia.AltaIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;

@RestController
@RequestMapping("/incidencia")
public class IncidenciaRestController {
	
	@Autowired
	private IncidenciaService incidenciaService;
	
	@RequestMapping(value = "/getIncidenciaBycdIncidencia", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ACTUALIZACION_ORDEN_SERVICIO')")
	public ResponseEntity<IncidenciaVO> getIncidenciaBycdInicidencia(@RequestParam(value="cdIncidencia", required=true) String cdIncidencia) throws NotFoundException{
		IncidenciaVO iVO = incidenciaService.getIncidenciabycdIncidencia(cdIncidencia);
		return new ResponseEntity<IncidenciaVO>(iVO, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/altaIncidencia", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ALTA_INCIDENCIA')")
	public Boolean altaIncidencia(@RequestBody AltaIncidenciaVO altaIncidenciaVO) throws BusinessException{
		return incidenciaService.altaIncidencia(altaIncidenciaVO);
		 
		
	}
	
}
