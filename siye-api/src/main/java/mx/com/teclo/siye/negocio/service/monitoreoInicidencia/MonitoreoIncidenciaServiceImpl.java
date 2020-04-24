package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.negocio.service.proceso.ProcesoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.ExpedienteImgDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.MonitoreoIncidenciasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.EncuestaDetaVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenDetailVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.IncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenIncidenciaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenServicioDetVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.ProcesoDetalleVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;

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
	
	@Autowired
	MonitoreoIncidenciasDAO monitoreoIncidenciasDAO;
	
	@Autowired
	OrdenServicioDAO ordenServicioDAO;
	
	@Autowired
	private ExpedienteImgDAO expedienteImgDAO;
	
	private static final String MSG_ERROR_PROCESO_NULO = "No se encontraron procesos";
	private static final String MSG_ERROR_EXPEDIENTE_VACIO = "No se encontraron archivos";
	private static final String MSG_ERROR_SIN_INCIDENCIAS = "No se encontraron incidencias";
	
	@Transactional
	@Override
	public List<MonitoreoIncidenciasVO> getMonIncidencias(
		Long idSupervisor, String fechaInicio, String fechaFin, 
		Integer tipoBusqueda, String valor, Integer opcion, 
		Integer idCentroInstalacion, String mensajeErr
	) throws Exception, BusinessException, NotFoundException {
		try {
			List<Long> idsCentroInstalacion = gerenteSupervisorDAO.getIdCentroInstalacion(idSupervisor);
			List<MonitoreoIncidenciasVO> listaMonitoreoIncidenciasVO= null;
			if(!idsCentroInstalacion.isEmpty()) {
				switch (opcion) {
					case 1:
						//Consulta por general de centros de atencion
						listaMonitoreoIncidenciasVO = monitoreoIncidenciasDAO.getInfoSeguimientoGeneral(fechaInicio, fechaFin, idsCentroInstalacion);
						break;
					case 2:
						//Consulta por modulo
						listaMonitoreoIncidenciasVO = monitoreoIncidenciasDAO.getInfoSeguimientoXModulo(fechaInicio, fechaFin, tipoBusqueda, valor, idCentroInstalacion);
						break;
					default: break;
				}
			}else {
				mensajeErr = "No cuenta con centro de instalacion asignado";
				throw new NotFoundException("");
			}
			return listaMonitoreoIncidenciasVO;
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
	public OrdenIncidenciaDetalleVO getDetalleIncidenciasOS(Long idOrden,Long idPlan)throws Exception, BusinessException, NotFoundException{
		try {
		OrdenIncidenciaDetalleVO listiVO = new OrdenIncidenciaDetalleVO();
		List<ProcesoDetalleVO> lisProcesoVO =new ArrayList<ProcesoDetalleVO>();
		List<PlanProcesoDTO> planProcesoDTO = new ArrayList<PlanProcesoDTO>();
		planProcesoDTO = procesoService.getPlanOrdenServicio(idPlan);
		
		if (planProcesoDTO == null) 
			throw new NotFoundException(MSG_ERROR_PROCESO_NULO);		
		for (PlanProcesoDTO proce : planProcesoDTO) {
		ProcesoDetalleVO procesoVO =new ProcesoDetalleVO();
 		procesoVO = ResponseConverter.copiarPropiedadesFull(proce.getProceso(), ProcesoDetalleVO.class);
		List<IncidenciaDetalleVO> listIncidenciaPro =obtenerIncidencias(procesoVO.getIdProceso(),"PROCESO");
 		procesoVO.setIncidencia(listIncidenciaPro);
				 List<EncuestaDetaVO> listEncuesta =procesoEncuestaDAO.getEncuestaByIdOrden(procesoVO.getIdProceso());	
				 
		        	for (EncuestaDetaVO encuestaDetaVO : listEncuesta) {
		        		StEncuestaVO stEncuesta =procesoEncuestaDAO.getstEncuestaByIdEncuestaIdOrden(encuestaDetaVO.getIdEncuesta(),idOrden);
		        		encuestaDetaVO.setStEncuesta(stEncuesta);
		        		List<IncidenciaDetalleVO> listIncidenciaEncu =obtenerIncidencias(encuestaDetaVO.getIdEncuesta(),"ENCUESTA");
						encuestaDetaVO.setIncidencia(listIncidenciaEncu);
					}
		        	procesoVO.setEncuesta(listEncuesta);

		 lisProcesoVO.add(procesoVO);
		}
		listiVO.setProceso(lisProcesoVO);
		List<IncidenciaDetalleVO> listIncidenciaOrden =obtenerIncidencias(idOrden,"ORDEN");
//		OrdenServicioDetVO detOrden=ordenServicioDAO.getOrdenServicioByIdOrden(idOrden);
		OrdenServicioDTO dtoOs=ordenServicioDAO.obtenerOrdenServicio(idOrden);
		OrdenServicioDetVO detOrdenVO=new OrdenServicioDetVO();
		detOrdenVO.setCdOrdenServicio(dtoOs.getCdOrdenServicio());
		detOrdenVO.setIdOrdenServicio(dtoOs.getIdOrdenServicio());
		detOrdenVO.setNbStSeguimiento(dtoOs.getStSeguimiento()==null?null:dtoOs.getStSeguimiento().getNbStSeguimiento());
		detOrdenVO.setCdEncuestaActual(dtoOs.getEncuesta()==null?null:dtoOs.getEncuesta().getCdEncuesta());
		detOrdenVO.setNbEncuestaActual(dtoOs.getEncuesta()==null?null:dtoOs.getEncuesta().getNbEncuesta());
		detOrdenVO.setCdProcesoActual(dtoOs.getProceso()==null?null:dtoOs.getProceso().getCdProceso());
		detOrdenVO.setNbProcesoActual(dtoOs.getProceso()==null?null:dtoOs.getProceso().getNbProceso());
		detOrdenVO.setFhCita(dtoOs.getFhCita());
		detOrdenVO.setFhAtencionIni(dtoOs.getFhAtencionIni());
		detOrdenVO.setFhAtencionFin(dtoOs.getFhAtencionFin());
		listiVO.setIncidencia(listIncidenciaOrden);
		listiVO.setOrdenServicio(detOrdenVO);
		
		return listiVO;		
		
		} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador Error: "+e);
		}
	}
	
	
	private List<IncidenciaDetalleVO> obtenerIncidencias(Long id,String parametro){
		List<IncidenciaDetalleVO> listIncidencia=new ArrayList<IncidenciaDetalleVO>();
		
		switch (parametro) {
		case "ORDEN":
			listIncidencia =incidenciaDAO.getIncidenciasByIdOrden(id);
			break;
		case "PROCESO":
			listIncidencia =incidenciaDAO.getIncidenciasByProceso(id);
			break;
		case "ENCUESTA":
			listIncidencia =incidenciaDAO.getIncidenciasByIdEncuesta(id);
			break;
		default:
			break;
		}
		if (!listIncidencia.isEmpty()) {
			for (IncidenciaDetalleVO inc : listIncidencia) {
				inc.setStSeguimientoVO(ResponseConverter.copiarPropiedadesFull(inc.getStSeguimiento(), StSeguimientoVO.class));
				inc.setTpIncidenciaVO(ResponseConverter.copiarPropiedadesFull(inc.getTpIncidencia(), StSeguimientoVO.class));
				inc.setStIncidenciaVO(ResponseConverter.copiarPropiedadesFull(inc.getStIncidencia(), StSeguimientoVO.class));
				inc.setStAutorizacionVO(ResponseConverter.copiarPropiedadesFull(inc.getStAutorizacion(), StSeguimientoVO.class));
				inc.setPrioridadVO(ResponseConverter.copiarPropiedadesFull(inc.getPrioridad(), StSeguimientoVO.class));
				inc.setStSeguimiento(null);
				inc.setTpIncidencia(null);
				inc.setStIncidencia(null);
				inc.setStAutorizacion(null);
				inc.setPrioridad(null);
			}
			
		}
		
		return listIncidencia;
	}

	@Override
	@Transactional
	public List<ImagenVO> getExpedienteByIdIncidencia(Long idIncidencia)
			throws Exception, BusinessException, NotFoundException {
	
			List<ImagenVO> listImagenes=expedienteImgDAO.getImagenesByIdIncidencia(idIncidencia);
			if (listImagenes.isEmpty()) 
				throw new NotFoundException(MSG_ERROR_EXPEDIENTE_VACIO);	
			return listImagenes;
	}

	@Override
	@Transactional
	public List<IncidenDetailVO> getListIncidenciaByIdOrden(Long idCentroInstalacion, Integer tipoBusqueda, String valor,
			String fechaInicio, String fechaFin) throws Exception, BusinessException, NotFoundException {
	
		List<IncidenciaDTO> listIncidenciaDTO = new ArrayList<IncidenciaDTO>();
	
		switch (tipoBusqueda) {
		case 1:
			//Buscar por orden
			listIncidenciaDTO=incidenciaDAO.getIncidenciasByIdOrdenFechas(idCentroInstalacion, Long.parseLong(valor), fechaInicio, fechaFin);
			break;
		case 2:
			//Buscar por incidencia
			listIncidenciaDTO=incidenciaDAO.getIncidenciasByIdIncidenciaFechas(idCentroInstalacion, Long.parseLong(valor), fechaInicio, fechaFin);
			break;
		case 3:
			//Buscar sin orden
			listIncidenciaDTO=incidenciaDAO.getIncidenciasByIdCentroFechas(idCentroInstalacion, fechaInicio, fechaFin);
			break;

		default:
			break;
		}
		if (listIncidenciaDTO == null) 
			throw new NotFoundException(MSG_ERROR_SIN_INCIDENCIAS);	
		 List<IncidenDetailVO>listIncidencia=ResponseConverter.converterLista(new ArrayList<>(), listIncidenciaDTO, IncidenDetailVO.class);
		return listIncidencia;

	}


}
