package mx.com.teclo.siye.api.rest.proceso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.ordenServicio.VehiculoService;
import mx.com.teclo.siye.negocio.service.proceso.ProcesoService;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.DispositivosVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ProcesoEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;




@RestController
@RequestMapping("/proceso")
public class ProcesoRestController {
	
	@Autowired
	private ProcesoService procesoService;	
	
	@Autowired
	private VehiculoService vehiculoService;
	
	

	 @RequestMapping(value = "/ordenServicioProceso", method = RequestMethod.GET)
	 @Transactional
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
	 
	 @RequestMapping(value = "/plan", method = RequestMethod.GET)
	public ResponseEntity<List<PlanProcesoVO>> consultaPlanOrdenServicio(
			@RequestParam("idPlan") Long idPlan,@RequestParam("idOrden") Long idOrden) throws BusinessException, NotFoundException {
        try
        {
        	List<PlanProcesoDTO> planProcesoDTO = new ArrayList<PlanProcesoDTO>();
        	List<OrdenEncuestaDTO> listaEvaluaciones=new ArrayList<OrdenEncuestaDTO>();
        	planProcesoDTO = procesoService.getPlanOrdenServicio(idPlan);
    		List<PlanProcesoVO> planProcesoVO = ResponseConverter.converterLista(new ArrayList<>(), planProcesoDTO,
    				PlanProcesoVO.class);
    		listaEvaluaciones=procesoService.obtenerEncuestas(idOrden);
    		return new ResponseEntity<List<PlanProcesoVO>>(procesoService.revisarEncuestasCompletas(listaEvaluaciones, planProcesoVO, idOrden), HttpStatus.OK);

        }catch(Exception e)
        {
        	e.printStackTrace();
    		throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
        }
	}
	 
	 @RequestMapping(value = "/encuestasProceso", method = RequestMethod.GET)
	 @Transactional
	public ResponseEntity<List<ProcesoEncuestaVO>> consultaEncuestasProceso(
			@RequestParam("idProceso") Long idProceso,@RequestParam("idOrden") Long idOrden) throws BusinessException, NotFoundException {
        try
        {
        	List<ProcesoEncuestaDTO> procesoEncuestaDTO = new ArrayList<ProcesoEncuestaDTO>();
        	List<OrdenEncuestaDTO> listaEvaluaciones=new ArrayList<OrdenEncuestaDTO>();
        	procesoEncuestaDTO = procesoService.getEncuestasProceso(idProceso);
    		List<ProcesoEncuestaVO> procesoEncuestaVO = ResponseConverter.converterLista(new ArrayList<>(), procesoEncuestaDTO,
    				ProcesoEncuestaVO.class);
    		listaEvaluaciones=procesoService.obtenerEncuestas(idOrden);
    		return new ResponseEntity<List<ProcesoEncuestaVO>>(procesoService.revisarEncuestasCompletas2(listaEvaluaciones, procesoEncuestaVO), HttpStatus.OK);

        }catch(Exception e)
        {
        	e.printStackTrace();
    		throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
        }
        
       
	}
	 @RequestMapping(value ="/dipositivosPorKit", method=RequestMethod.GET)
     public ResponseEntity<List<DispositivosVO>> consultaDispositivos(
    		 @RequestParam("idTipoKit") Long idTpKit)throws NotFoundException, BusinessException {
		 try	
		 {	
			 List<DispositivosVO> listDispositivoVO = procesoService.getKitDispositivo(idTpKit);				 
			 return new ResponseEntity<List<DispositivosVO>>(listDispositivoVO, HttpStatus.OK);				 
		 }catch(Exception e)	
		 {	
			 e.printStackTrace();	
			 throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");	
		 }

     }
	 
	 @RequestMapping(value ="/buscaPlacaVehiculo", method=RequestMethod.GET)
	 public ResponseEntity <VehiculoVO> consultaVehiculoPlaca(
			 @RequestParam("placa") String placa) throws NotFoundException {

		 VehiculoVO vehiculoVO = vehiculoService.bucarVehiculoPlaca(placa);			
		 return new ResponseEntity<VehiculoVO>(vehiculoVO,HttpStatus.OK);			
	 }
	 
		@RequestMapping(value="/iniciarProceso", method = RequestMethod.GET)
		public ResponseEntity<Boolean> iniciarProceso (
			@RequestParam(value="idOrdenServicio") long idOrdenServicio
			) throws Exception, BusinessException, NotFoundException {
			try {
				Boolean correcto=procesoService.inicarProcesoOrdenServicio(idOrdenServicio);
				return new ResponseEntity<Boolean>(correcto, HttpStatus.OK);
			}catch (Exception e) {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto!, porfavor contacte al administrador");
			}		
		}

		
		
		@RequestMapping(value="/avanzarProceso", method = RequestMethod.GET)
		public ResponseEntity<Boolean> avanzarProceso (
				@RequestParam(value="idOrdenServicio") long idOrdenServicio,
				@RequestParam(value="idProceso") long idProceso
				) throws Exception, BusinessException, NotFoundException {
				try {
					Boolean correcto=procesoService.avanzarProcesoOrden(idOrdenServicio,idProceso);
					return new ResponseEntity<Boolean>(correcto, HttpStatus.OK);
				}catch (Exception e) {
					e.printStackTrace();
					throw new NotFoundException("¡Ha ocurrido un imprevisto!, porfavor contacte al administrador");
				}		
			}
		
		@RequestMapping(value="/finalizarProceso", method = RequestMethod.GET)
		public ResponseEntity<Boolean> finalizarProceso (
			@RequestParam(value="idOrdenServicio") long idOrdenServicio
			) throws Exception, BusinessException, NotFoundException {
			try {
				Boolean correcto=procesoService.finalizarProceso(idOrdenServicio);
				return new ResponseEntity<Boolean>(correcto, HttpStatus.OK);
			}catch (Exception e) {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto!, porfavor contacte al administrador");
			}		
		}
}



