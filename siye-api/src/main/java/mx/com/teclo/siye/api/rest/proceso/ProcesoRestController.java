package mx.com.teclo.siye.api.rest.proceso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.proceso.ProcesoService;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioProcesoVO;




@RestController
public class ProcesoRestController {
	
	@Autowired
	private ProcesoService procesoService;	

	 @RequestMapping(value = "/consultaOrdenServicioProceso", method = RequestMethod.GET)
	public ResponseEntity<List<OrdenServicioProcesoVO>> consultaOrdenParaProceso(
			@RequestParam("idSolicitud") Long idSolicitud) throws BusinessException, NotFoundException {
        try
        {
        	List<OrdenServicioDTO> OrdenesServicio = new ArrayList<OrdenServicioDTO>();
        	OrdenServicioDTO  ordenServicioDTO = new OrdenServicioDTO();
        	ordenServicioDTO = procesoService.getInfoBasicaOrdenServicio(idSolicitud);
        	OrdenesServicio.add(ordenServicioDTO);
    		List<OrdenServicioProcesoVO> ordenServicioProcesoVO = ResponseConverter.converterLista(new ArrayList<>(), OrdenesServicio,
    				OrdenServicioProcesoVO.class);
    		return new ResponseEntity<List<OrdenServicioProcesoVO>>(ordenServicioProcesoVO, HttpStatus.OK);

        }catch(Exception e)
        {
        	e.printStackTrace();
    		throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
        }
	}
}
