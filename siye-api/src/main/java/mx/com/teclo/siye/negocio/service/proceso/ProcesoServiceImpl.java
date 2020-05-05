package mx.com.teclo.siye.negocio.service.proceso;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.EstatusCalificacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.StEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaIntentoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestasDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.OrdenProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.DispositivosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitDispositivoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.mybatis.dao.proceso.ProcesoDAO;
import mx.com.teclo.siye.persistencia.mybatis.dao.proceso.ServicioEncuestasMyBatisDAO;
import mx.com.teclo.siye.persistencia.vo.proceso.DispositivosVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ProcesoEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;




@Service
public class ProcesoServiceImpl implements ProcesoService {

	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Autowired
	private PlanProcesoDAO planProcesoDAO;
	
	@Autowired
	private ProcesoEncuestaDAO procesoEncuestaDAO;
	
	@Autowired
	private DispositivosDAO dispositivosDAO;
	
	@Autowired
	private UsuarioEncuestaDAO usuarioEncuestaDAO;
	
	@Autowired
	private ServicioEncuestasMyBatisDAO servicioEncuestasMyBatisDAO;
	
	@Autowired
	private StEncuestaDAO stEncuestaDAO;
	
	@Autowired
	private EstatusCalificacionDAO estatusCalificacionDAO;
	
	@Autowired
	private UsuarioEncuestaIntentoDAO usuarioEncuestaIntentoDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private StSeguimientoDAO stSeguimientoDAO;
	
	@Autowired
	private ProcesoDAO procesoDAO;
	
	@Autowired
	private EncuestasDAO encuestasDAO;
	
	@Autowired
	private OrdenProcesoDAO ordenProcesoDAO;
	
	

	@Override
	@Transactional
	public OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud) {
		return ordenServicioDAO.obtenerOrdenServicio(idSolicitud);
	}

	@Override
	@Transactional
	public List<PlanProcesoDTO> getPlanOrdenServicio(Long idPlan) {
		return planProcesoDAO.obtenerPorcesosPlan(idPlan);	
	}

	@Override
	@Transactional
    public List<ProcesoEncuestaDTO> getEncuestasProceso(Long idProceso) {
	return procesoEncuestaDAO.obtenerEncuestasProceso(idProceso);
    }

	@Override
	@Transactional
	public List<DispositivosVO> getKitDispositivo(Long idTpKit) throws BusinessException {
		
		List<KitDispositivoDTO> listaDispositivosDTO = dispositivosDAO.getListDispositivos(idTpKit);
		if(listaDispositivosDTO.isEmpty())
			throw new BusinessException("No se encontrarón dispositivos");
			
		List<DispositivosVO> listaDispositivosVO = ResponseConverter.converterLista
				(new ArrayList<>(), listaDispositivosDTO, DispositivosVO.class);
		
		return listaDispositivosVO;
	}
	
	@Override
	@Transactional
	public List<OrdenEncuestaDTO> obtenerEncuestas(Long orden) {

		return usuarioEncuestaDAO.getEncuestasPorOrden(orden);
	}
	
	@Override
	@Transactional
	public List<PlanProcesoVO> revisarEncuestasCompletas(List<OrdenEncuestaDTO> encuestasByUsuario,List<PlanProcesoVO> plan, Long idSolicitud)
	{
    	List<ProcesoEncuestaDTO> procesoEncuestaDTO = new ArrayList<ProcesoEncuestaDTO>();
    	List<OrdenEncuestaDTO> nuevoOrdenEncuestas=new ArrayList<OrdenEncuestaDTO>();

		if(encuestasByUsuario.size()>0)
		{
			procesoEncuestaDTO=procesoEncuestaDAO.obtenerEncuestasProceso(plan.get(0).getProceso().getIdProceso());
        	OrdenServicioDTO  ordenServicioDTO = new OrdenServicioDTO();
        	ordenServicioDTO=getInfoBasicaOrdenServicio(idSolicitud);
        	Long nuOrdenProcesoActual=1L;
        	for(PlanProcesoVO actual:plan)
			{
        		if(actual.getProceso().getIdProceso()==(ordenServicioDTO.getProceso()!=null?ordenServicioDTO.getProceso().getIdProceso():procesoEncuestaDTO.get(0).getIdProceso().getIdProceso()))
        		{
        			nuOrdenProcesoActual=actual.getNuorden();
        		}
			}
			for(PlanProcesoVO actual:plan)
			{
				actual.getProceso().setFechaInicioProceso(obtenerFechaInicioProceso(actual.getProceso().getIdProceso(),idSolicitud));
				actual.getProceso().setFechaFinProceso(obtenerFechaFinProceso(actual.getProceso().getIdProceso(),idSolicitud));
				
				
                    if(actual.getNuorden()<=nuOrdenProcesoActual)
                	 {
                		 actual.setStatusProceos(true);
                		 //actual.setProcesoCompleto(true);
                	 } 
                	 else
                	 {
                		 actual.setStatusProceos(false);
                         //actual.setProcesoCompleto(false);
                	 }
                    
                   List<OrdenProcesoDTO> ordenProceso=ordenProcesoDAO.getProcesoPorOrden(idSolicitud);
                   for(OrdenProcesoDTO proceso:ordenProceso)
                   {
   					if(actual.getProceso().getIdProceso()==proceso.getProceso().getIdProceso()) {

									StSeguimientoVO steVO = new StSeguimientoVO();
									ResponseConverter.copiarPropriedades(steVO, proceso.getStSeguimiento());
									actual.setStSeguimiento(steVO);	

						}   
                   }

			}
			return plan;
		}else
		{
			for(PlanProcesoVO actual:plan)
			{
			servicioEncuestasMyBatisDAO.insertarprocesos(idSolicitud, actual.getProceso().getIdProceso());
			procesoEncuestaDTO=procesoEncuestaDAO.obtenerEncuestasProceso(actual.getIdPlanProceso());
			if(procesoEncuestaDTO.size()>0)
			{
				for(ProcesoEncuestaDTO actualEncuestas:procesoEncuestaDTO)
				{
					if(actualEncuestas.getIdEncuesta().getCdEncuesta().equals("SAT01")||actualEncuestas.getIdEncuesta().getCdEncuesta().equals("SAT02"))
					{
						servicioEncuestasMyBatisDAO.insertarEncuestas(idSolicitud, actualEncuestas.getIdEncuesta().getIdEncuesta(),false);
					}else
					{
                        servicioEncuestasMyBatisDAO.insertarEncuestas(idSolicitud, actualEncuestas.getIdEncuesta().getIdEncuesta(),true);
							
					}
				}

			}
              
              if(actual.getNuorden()==1)
              {
            	  actual.setStatusProceos(true);
            	  //actual.setProcesoCompleto(false);
              }
              else
              {
            	  actual.setStatusProceos(false);
            	  //actual.setProcesoCompleto(false);
              }
			}
            nuevoOrdenEncuestas=obtenerEncuestas(idSolicitud);
            for(OrdenEncuestaDTO ordenEncuesta:nuevoOrdenEncuestas)
            {
              UsuarioEncuestaIntentosDTO nuevoIntentoEncuesta=new UsuarioEncuestaIntentosDTO();
          	  nuevoIntentoEncuesta.setStEncuesta(stEncuestaDAO.encuesta("NI"));
          	  nuevoIntentoEncuesta.setNuCalificacion(0);
          	  nuevoIntentoEncuesta.setStCalificacion(estatusCalificacionDAO.calificacion("SC"));
          	  nuevoIntentoEncuesta.setStMostrar(true);
          	  nuevoIntentoEncuesta.setNuPreguntas(ordenEncuesta.getEncuesta().getNuPreguntas());
          	  nuevoIntentoEncuesta.setNuPreguntasCorrectas(0);
          	  nuevoIntentoEncuesta.setNuPreguntasIncorr(0);
          	  nuevoIntentoEncuesta.setNuSeccionesTot(ordenEncuesta.getEncuesta().getNuSecciones());
          	  nuevoIntentoEncuesta.setNuSeccionesRsp(0);
          	  nuevoIntentoEncuesta.setNuPreguntasVacias(0);
          	  nuevoIntentoEncuesta.setStActivo(true);
          	  nuevoIntentoEncuesta.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
          	  nuevoIntentoEncuesta.setFhCreacion(new Date());
          	  nuevoIntentoEncuesta.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
          	  nuevoIntentoEncuesta.setFhModificacion(new Date());
          	  nuevoIntentoEncuesta.setUsuarioEncuesta(ordenEncuesta);
          	  usuarioEncuestaIntentoDAO.save(nuevoIntentoEncuesta);

            }
			for(PlanProcesoVO actual:plan)
			{
				 List<OrdenProcesoDTO> ordenProceso=ordenProcesoDAO.getProcesoPorOrden(idSolicitud);
                   for(OrdenProcesoDTO proceso:ordenProceso)
                   {
   					if(actual.getProceso().getIdProceso()==proceso.getProceso().getIdProceso()) {

									StSeguimientoVO steVO = new StSeguimientoVO();
									ResponseConverter.copiarPropriedades(steVO, proceso.getStSeguimiento());
									actual.setStSeguimiento(steVO);	

						}   
                   }

			}
            
            
			return plan;
		}
	}
		
	@Override
	@Transactional
	public List<ProcesoEncuestaVO> revisarEncuestasCompletas2(List<OrdenEncuestaDTO> encuestasByUsuario,List<ProcesoEncuestaVO> encuestasByProceso)
	{

		Boolean seEncontroActiva=false;
		if(encuestasByUsuario.size()>0)
		{
			
			
				for(ProcesoEncuestaVO actual:encuestasByProceso )
				{
					actual.setFechaInicioProceso(obtenerFechaInicioProceso(actual.getIdProceso().getIdProceso(),encuestasByUsuario.get(0).getOrdenServicio().getIdOrdenServicio()));
					actual.setFechaFinProceso(obtenerFechaFinProceso(actual.getIdProceso().getIdProceso(),encuestasByUsuario.get(0).getOrdenServicio().getIdOrdenServicio()));

							for(OrdenEncuestaDTO encuestas:encuestasByUsuario)
							{
								if((actual.getIdEncuesta().getIdEncuesta()==encuestas.getEncuesta().getIdEncuesta() && !seEncontroActiva)||(actual.getIdEncuesta().getIdEncuesta()==encuestas.getEncuesta().getIdEncuesta() && (actual.getIdEncuesta().getCdEncuesta().equals("SAT01") || actual.getIdEncuesta().getCdEncuesta().equals("SAT02"))))
								{
									if(encuestas.getStAplicaEncuesta()==false && encuestas.getNuIntegerentos()>0)
									{
										//actual.setStRespondida(true);
										actual.setStActivaMostrar(true);
									}
									else
									{
										//actual.setStRespondida(false);	
										actual.setStActivaMostrar(true);	
										seEncontroActiva=true;
									}
									
									
								}
                          
								if(actual.getIdEncuesta().getIdEncuesta()==encuestas.getEncuesta().getIdEncuesta()) {
									// Obtener el detalle del intento actual
									UsuarioEncuestaIntentosDTO ueiDTO = usuarioEncuestaIntentoDAO.getEncuestaByUsuario(encuestas.getEncuesta().getIdEncuesta(),encuestas.getOrdenServicio().getIdOrdenServicio());
									if(ueiDTO != null) {
										if(ueiDTO.getStEncuesta() != null) {
											if(ueiDTO.getStEncuesta().getStSeguimiento()!=null)
											{
												StSeguimientoVO steVO = new StSeguimientoVO();
												ResponseConverter.copiarPropriedades(steVO, ueiDTO.getStEncuesta().getStSeguimiento());
												actual.setStSeguimiento(steVO);	
											}

										}
									}
									actual.setStSatisfaccion(encuestas.getStAplicaEncuesta());
									actual.getIdEncuesta().setFechaInicioEncuesta(obtenerFechaInicioEncuesta(encuestas.getIdUsuarioEncuesta()));
								    actual.getIdEncuesta().setFechaFinEncuesta(obtenerFechaFinEncuesta(encuestas.getIdUsuarioEncuesta()));
								}

					    }

				}
		}
		return  encuestasByProceso;
	}
	
	@Override
	@Transactional
	public Boolean inicarProcesoOrdenServicio (Long idOrdenServicio)
	{

		OrdenServicioDTO orden=ordenServicioDAO.obtenerOrdenServicio(idOrdenServicio);
		List<PlanProcesoDTO> planProceso=planProcesoDAO.obtenerPorcesosPlan(orden.getPlan().getIdPlan());
		List<ProcesoEncuestaDTO> procesoEncuestas=procesoEncuestaDAO.obtenerEncuestasProceso(planProceso.get(0).getProceso().getIdProceso());
		Date fechActual=new Date();
		if(orden.getStSeguimiento().getCdStSeguimiento().equals("NUEVO"))
		{
			orden.setStSeguimiento(stSeguimientoDAO.obtenerStSeguimientoByCodigo("CURSO","ID_ST_SEGUIMIENTO","TIE026D_IE_ORDEN_SERVICIOS"));
			orden.setProceso(procesoDAO.obtenerProceso(planProceso.get(0).getProceso().getIdProceso()));
		    orden.setEncuesta(encuestasDAO.findOne(procesoEncuestas.get(0).getIdEncuesta().getIdEncuesta()));
			orden.setFhAtencionIni(fechActual);
			ordenServicioDAO.update(orden);
            servicioEncuestasMyBatisDAO.iniciarProceso(idOrdenServicio, planProceso.get(0).getProceso().getIdProceso(),fechActual);
			
			return true;
			
		}else
		{
		return false;	
		}
	}
		
		@Override
		@Transactional
		public Boolean finalizarProceso (Long idOrdenServicio)
		{
			OrdenServicioDTO orden=ordenServicioDAO.obtenerOrdenServicio(idOrdenServicio);
			if(!orden.getStSeguimiento().getCdStSeguimiento().equals("CURSO"))
			{
				orden.setStSeguimiento(stSeguimientoDAO.obtenerStSeguimientoByCodigo("FINALIZADO","ID_ST_SEGUIMIENTO","TIE026D_IE_ORDEN_SERVICIOS"));
				orden.setFhAtencionFin(new Date());
				ordenServicioDAO.update(orden);
				return true;
				
			}else
			{
			return false;	
			}

		
		
	      }
		
		@Override
		@Transactional
		public Boolean avanzarProcesoOrden (Long idOrdenServicio)
		{
			Boolean encuestaActualEncontrada=false;
			Long encuestaSiguiente=null;
			Boolean tieneSatisfaccion=false;
			ProcesoEncuestaDTO encuestaSatisfaccion=new ProcesoEncuestaDTO();
			
			
			OrdenServicioDTO orden=ordenServicioDAO.obtenerOrdenServicio(idOrdenServicio);
			List<ProcesoEncuestaDTO> procesoEncuestas=procesoEncuestaDAO.obtenerEncuestasProceso(orden.getProceso().getIdProceso());
			//Se remueve de los procesos las encuestas de satisfaccion para no tomarlas encuenta
	           for(ProcesoEncuestaDTO actual:procesoEncuestas)
	           {
	        	   if(actual.getIdEncuesta().getCdEncuesta().equals("SAT01")||actual.getIdEncuesta().getCdEncuesta().equals("SAT02"))
	        	   {
	        		   tieneSatisfaccion=true;
	        		   encuestaSatisfaccion=actual;
	        	   }
	           }
	           if(tieneSatisfaccion)
	           {
	        	   procesoEncuestas.remove(encuestaSatisfaccion);
	           }
	           
				for(int i=0;i<procesoEncuestas.size();i++)
		        {

					if(orden.getEncuesta().getIdEncuesta()==procesoEncuestas.get(i).getIdEncuesta().getIdEncuesta())
					{
					encuestaActualEncontrada=true;
					}
					if(encuestaActualEncontrada && encuestaSiguiente==null )
					{ 
						try {
						encuestaSiguiente=procesoEncuestas.get(i+1).getIdEncuesta().getIdEncuesta();
						
			                } catch (IndexOutOfBoundsException e) {
			                	encuestaSiguiente=null;
			                }
				    	}
		        }
			
			if(orden.getStSeguimiento().getCdStSeguimiento().equals("CURSO") && encuestaSiguiente!=null )
			{
			
				orden.setEncuesta(encuestasDAO.findOne(encuestaSiguiente));
				ordenServicioDAO.update(orden);
				return true;
				
			}else if(orden.getStSeguimiento().getCdStSeguimiento().equals("CURSO") && encuestaSiguiente==null)
			{
		     Boolean procesoActualEncontrado=false;
			 Long procesoSiguiente= null;
			 Date fechActual=new Date();
			List<PlanProcesoDTO> planProceso=planProcesoDAO.obtenerPorcesosPlan(orden.getPlan().getIdPlan());
			for(int j=0;j<planProceso.size();j++)
	        {

				if(orden.getProceso().getIdProceso()==planProceso.get(j).getProceso().getIdProceso())
				{
					procesoActualEncontrado=true;
					//aqui
					servicioEncuestasMyBatisDAO.finalizarProceso(idOrdenServicio, orden.getProceso().getIdProceso(),fechActual);
				}
				if(procesoActualEncontrado && procesoSiguiente==null )
				{
					try {
						procesoSiguiente=planProceso.get(j+1).getProceso().getIdProceso();
					
		                } catch (IndexOutOfBoundsException e) {
		                	procesoSiguiente=null;
		                }
					
				}
	        }
			if(procesoSiguiente!=null)
			{
				List<ProcesoEncuestaDTO> procesoEncuestasSiguiente=procesoEncuestaDAO.obtenerEncuestasProceso(procesoSiguiente);
                orden.setProceso(procesoDAO.obtenerProceso(procesoSiguiente));
                orden.setEncuesta(encuestasDAO.findOne(procesoEncuestasSiguiente.get(0).getIdEncuesta().getIdEncuesta()));
                ordenServicioDAO.update(orden);
                //validar si se iniciara cuando termine la ultima encuesta del anterior proceso o al inicar la primera encuesta de este proceso
                //servicioEncuestasMyBatisDAO.iniciarProceso(idOrdenServicio, procesoSiguiente);
			}else
			{
				orden.setStSeguimiento(stSeguimientoDAO.obtenerStSeguimientoByCodigo("FINALIZADO","ID_ST_SEGUIMIENTO","TIE026D_IE_ORDEN_SERVICIOS"));
				orden.setFhAtencionFin(fechActual);
				ordenServicioDAO.update(orden);
			}
			return true;	
			}
			else
			{
				return false;
			}
		
	      }
				
				
		public Date obtenerFechaInicioProceso(Long idProceso,Long idOrden)
		{
			return servicioEncuestasMyBatisDAO.getFechaInicioProceso(idProceso, idOrden);
		}
		
		public Date obtenerFechaFinProceso(Long idProceso,Long idOrden)
		{
			return servicioEncuestasMyBatisDAO.getFechaFinProceso(idProceso, idOrden);
		}
		
		public Date obtenerFechaInicioEncuesta(Long idOdsEncuesta)
		{
			return servicioEncuestasMyBatisDAO.getFechaInicioEncuesta(idOdsEncuesta);
		}
		
		public Date obtenerFechaFinEncuesta(Long idOdsEncuesta)
		{
			return servicioEncuestasMyBatisDAO.getFechaFinEncuesta(idOdsEncuesta);
		}
	
		@Override
		@Transactional
		public Boolean iniciarTiempoProceso (Long idOrdenServicio, Long idProceso)
		{
			Date fechActual=new Date();
			return servicioEncuestasMyBatisDAO.iniciarProceso(idOrdenServicio,idProceso,fechActual);
		}
		

		

}
