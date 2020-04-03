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
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.DispositivosDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenEncuestaDTO;
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
    	

    	Boolean banderaMostrarContestados=true;
		if(encuestasByUsuario.size()>0)
		{
        	OrdenServicioDTO  ordenServicioDTO = new OrdenServicioDTO();
        	ordenServicioDTO=getInfoBasicaOrdenServicio(idSolicitud);
			for(PlanProcesoVO actual:plan)
			{
                 if(banderaMostrarContestados)
                 {
                	 if(actual.getProceso().getIdProceso()<=ordenServicioDTO.getProceso().getIdProceso())
                	 {
                		 actual.setStatusProceos(true);
                		 actual.setProcesoCompleto(true);
                	 } 
                	 else
                	 {
                		 actual.setStatusProceos(false);
                		 actual.setProcesoCompleto(false);
                	 }
                 }else
                 {
                	 if(actual.getProceso().getIdProceso()==ordenServicioDTO.getProceso().getIdProceso())
                	 {
                		 actual.setStatusProceos(true);
                	 }
                	 else
                		 actual.setStatusProceos(false);
                 }

			}
			return plan;
		}else
		{
			for(PlanProcesoVO actual:plan)
			{
			procesoEncuestaDTO=procesoEncuestaDAO.obtenerEncuestasProceso(actual.getIdPlanProceso());
			if(procesoEncuestaDTO.size()>0)
			{
				for(ProcesoEncuestaDTO actualEncuestas:procesoEncuestaDTO)
				{
					/*if(actualEncuestas.getIdEncuesta().getCdEncuesta().equals("SAT01")||actualEncuestas.getIdEncuesta().getCdEncuesta().equals("SAT02"))
					{
						servicioEncuestasMyBatisDAO.insertarEncuestas(idSolicitud, actualEncuestas.getIdEncuesta().getIdEncuesta(),false);
					}else
					{*/
              servicioEncuestasMyBatisDAO.insertarEncuestas(idSolicitud, actualEncuestas.getIdEncuesta().getIdEncuesta(),true);
							
					//}
				}

			}
              
              if(actual.getNuorden()==1)
              {
            	  actual.setStatusProceos(true);
            	  actual.setProcesoCompleto(false);
              }
              else
              {
            	  actual.setStatusProceos(false);
            	  actual.setProcesoCompleto(false);
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
					

							for(OrdenEncuestaDTO encuestas:encuestasByUsuario)
							{
								if((actual.getIdEncuesta().getIdEncuesta()==encuestas.getEncuesta().getIdEncuesta() && !seEncontroActiva)||(actual.getIdEncuesta().getIdEncuesta()==encuestas.getEncuesta().getIdEncuesta() && (actual.getIdEncuesta().getCdEncuesta().equals("SAT01") || actual.getIdEncuesta().getCdEncuesta().equals("SAT02"))))
								{
									if(encuestas.getStAplicaEncuesta()==false && encuestas.getNuIntegerentos()>0)
									{
										actual.setStRespondida(true);
										actual.setStActivaMostrar(true);
									}
									else
									{
										actual.setStRespondida(false);	
										actual.setStActivaMostrar(true);	
										seEncontroActiva=true;
									}
									
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
		if(!orden.getStSeguimiento().getCdStSeguimiento().equals("NUEVO"))
		{
			orden.setStSeguimiento(stSeguimientoDAO.obtenerStSeguimientoByCodigo("CURSO"));
			orden.setFhAtencionIni(new Date());
			ordenServicioDAO.update(orden);
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
				orden.setStSeguimiento(stSeguimientoDAO.obtenerStSeguimientoByCodigo("FINALIZADO"));
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
		public Boolean avanzarProcesoOrden (Long idOrdenServicio, Long idProceso)
		{
			OrdenServicioDTO orden=ordenServicioDAO.obtenerOrdenServicio(idOrdenServicio);
			if(!orden.getStSeguimiento().getCdStSeguimiento().equals("CURSO"))
			{
				orden.setProceso(procesoDAO.obtenerProceso(idProceso));
				ordenServicioDAO.update(orden);
				return true;
				
			}else
			{
			return false;	
			}

		
		
	      }
				
				
		
	
		

}
