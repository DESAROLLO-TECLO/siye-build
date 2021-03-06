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
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidencVO;
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
	
	
	@RequestMapping(value = "/altaIncidencia", method = RequestMethod.POST, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ALTA_INCIDENCIA')")
	public ResponseEntity<String> altaIncidencia(@RequestBody AltaIncidenciaVO altaIncidenciaVO) throws BusinessException{
		String resp = incidenciaService.altaIncidencia(altaIncidenciaVO);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/incidenciaByCdIncidencia", method = RequestMethod.GET)
	public ResponseEntity<IncidencVO> getIncidenciabyCdInciedencia(@RequestParam(value="cdIncidenc", required=true) String cdIncidenc) throws NotFoundException{
		IncidencVO incidenciaVO = incidenciaService.incidenciaByCdIncidencia(cdIncidenc);
		return new ResponseEntity<IncidencVO>(incidenciaVO, HttpStatus.OK);
	}
	
	
	
	
}
