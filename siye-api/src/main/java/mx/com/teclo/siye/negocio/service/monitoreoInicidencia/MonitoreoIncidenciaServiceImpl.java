package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.proceso.ProcesoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.EncuestaDetaVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.ProcesoDetalleVO;

@Service
public class MonitoreoIncidenciaServiceImpl implements MonitoreoIncidenciaService{
	@Autowired
	private GerenteSupervisorDAO gerenteSupervisorDAO;
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	@Autowired
	private ProcesoEncuestaDAO procesoEncuestaDAO;
	
	@Autowired
	private ProcesoService procesoService;	
	
	private static final String MSG_ERROR_PROCESO_NULO = "No se encontraron procesos";
	
	@Transactional
	@Override
	public void getMonIncidencias(
		Long idSupervisor, String fechaInicio, String fechaFin, 
		Integer tipoBusqueda, String valor, Integer opcion, 
		String mensajeErr
	) throws Exception, BusinessException, NotFoundException {
		try {
			List<Long> idsCentroInstalacion = gerenteSupervisorDAO.getIdCentroInstalacion(idSupervisor);
			if(!idsCentroInstalacion.isEmpty()) {
				
			}
		//List<SeguimientoOrdenServicioVO>
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	};
	
	@Override
	@Transactional
	public OrdenIncidenciaDetalleVO incidenciaByOS(Long idOrden,Long idPlan) throws NotFoundException {
		OrdenIncidenciaDetalleVO listiVO = new OrdenIncidenciaDetalleVO();
		List<ProcesoDetalleVO> lisProcesoVO =new ArrayList<ProcesoDetalleVO>();
		List<PlanProcesoDTO> planProcesoDTO = new ArrayList<PlanProcesoDTO>();
		planProcesoDTO = procesoService.getPlanOrdenServicio(idPlan);
		if (planProcesoDTO == null) 
			throw new NotFoundException(MSG_ERROR_PROCESO_NULO);		
		for (PlanProcesoDTO proce : planProcesoDTO) {
		ProcesoDetalleVO procesoVO =new ProcesoDetalleVO();
 		procesoVO = ResponseConverter.copiarPropiedadesFull(proce.getProceso(), ProcesoDetalleVO.class);
		 
				 List<EncuestaDetalleDTO> listEncuestaDTO = new ArrayList<EncuestaDetalleDTO>();
				 	listEncuestaDTO =procesoEncuestaDAO.getEncuestaByIdOrden(procesoVO.getIdProceso());				 	
		        	List<EncuestaDetaVO> listEncuesta=ResponseConverter.converterLista(new ArrayList<>(), listEncuestaDTO, EncuestaDetaVO.class);
		        	for (EncuestaDetaVO encuestaDetaVO : listEncuesta) {
						List<IncidenciaDTO> listIncidenciaDTO=incidenciaDAO.getIncidenciasByIdEncuesta(encuestaDetaVO.getIdEncuesta());
						List<IncidenciaDetalleVO> listIncidencia=ResponseConverter.converterLista(new ArrayList<>(), listIncidenciaDTO, IncidenciaDetalleVO.class);
						encuestaDetaVO.setIncidencia(listIncidencia);
					}
		        	procesoVO.setEncuesta(listEncuesta);

		 lisProcesoVO.add(procesoVO);
		}
		listiVO.setProceso(lisProcesoVO);
		
		return listiVO;		
	}
}
