package mx.com.teclo.siye.api.rest.expedienteImg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.responsehttp.BadRequestHttpResponse;
import mx.com.teclo.arquitectura.ortogonales.responsehttp.ConflictHttpResponse;
import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.negocio.service.expedienteImg.ExpedienteImgService;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestaDetalleDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.OrdenProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaIntentoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.IeStUsuEncuIntenDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.UsuarioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.IeStUsuEncuIntenDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsuarioDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CompresorImgConfigVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelPreguntaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.InfoEvidenciaNivelVO;
import mx.com.teclo.siye.persistencia.vo.tipoExpediente.TipoExpedienteVO;

@RestController
@RequestMapping("/expediente")
public class ExpedienteImgRestController {
	
	@Autowired
	private ExpedienteImgService expedienteImg;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private OrdenProcesoDAO ordenProcesoDAO;
	
	@Autowired
	private EncuestaDetalleDAO encuestaDetalleDAO;
	
	@Autowired
	private UsuarioEncuestaIntentoDAO usuarioEncuestaIntentoDAO;
	
	@Autowired
	private IeStUsuEncuIntenDAO ieStUsuEncuIntenDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping(value="/getInfoOS")
	//@PreAuthorize("hasAnyAuthority('GET_STATUS_CARGA_EVIDENCIAS')")
	public ResponseEntity<List<CargaExpedienteImgVO>> getImgExpediente(@RequestParam(value ="tipoBusqueda") String tipoBusqueda,
														   @RequestParam(value ="valor") String valor) throws NotFoundException{	
		List<CargaExpedienteImgVO> respuesta = expedienteImg.getInformacionExpediente(tipoBusqueda, valor);
		if(respuesta.isEmpty() || respuesta == null) {
			throw new NotFoundException("No se encontro expediente relacionado");
		}
		return new ResponseEntity<List<CargaExpedienteImgVO>>(respuesta, HttpStatus.OK);
	};
	
	@PostMapping(value="/saveEvidencia")
	//@PreAuthorize("hasAnyAuthority('GUARDAR_ALTA_EVIDENCIA')")
	public ResponseEntity<List<ImagenVO>> saveImgExpediente(@RequestBody List<ImagenVO> expedientes) 
			throws NotFoundException,ConflictHttpResponse,BadRequestHttpResponse{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<ImagenVO> respuesta=null;
		
		try{
			respuesta= expedienteImg.saveExpediente(expedientes, usuario.getId());
		}catch(BadRequestHttpResponse brq){
			throw new BadRequestHttpResponse(brq.getMessage());
		}catch (BusinessException b) {
			throw new ConflictHttpResponse(b.getMessage());
		}
		
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	};
	
	@GetMapping(value="/getTipoExpediente")
	//@PreAuthorize("hasAnyAuthority('GET_TIPO_EXPEDIENTE')")
	public ResponseEntity<List<TipoExpedienteVO>> getImgExpediente() throws NotFoundException{	
		List<TipoExpedienteVO> respuesta = expedienteImg.getTipoExpediente();
		return new ResponseEntity<List<TipoExpedienteVO>>(respuesta, HttpStatus.OK);
	};
	
	@PostMapping(value="/delEvidencia")
	//@PreAuthorize("hasAnyAuthority('DELETE_EVIDENCIA')")
	public ResponseEntity<List<ImagenVO>> deleteEvidencia(@RequestBody List<ImagenVO> expedientes) throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO(); 		
		List<ImagenVO> respuesta = expedienteImg.delListEvidencia(expedientes, usuario.getId());
		if(respuesta== null) {
			throw new NotFoundException("Ocurrio un error el eliminar");
		}
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping(value="/getInfoExpByNivel")
	//@PreAuthorize("hasAnyAuthority('GET_STATUS_CARGA_EVIDENCIAS')")
	public ResponseEntity<List<ImagenVO>> getInfoExpedienteByNivel(@RequestParam(value ="nuOrdenServicio") Long nuOrdenServicio,
														           @RequestParam(value ="cdNivel") String cdNivel,
														           @RequestParam(value="idValor", required= false) Long idparamBusqueda) throws NotFoundException{	
		List<ImagenVO> respuesta = expedienteImg.getInfoExpedienteByNivel(nuOrdenServicio, idparamBusqueda, cdNivel);
		if(respuesta.isEmpty()) {
			throw new NotFoundException("No tiene evidencias asignadas ");
		}
		return new ResponseEntity<List<ImagenVO>>(respuesta, HttpStatus.OK);
	};
	
	@GetMapping(value="/getConfigCompressImg")
	public ResponseEntity<List<CompresorImgConfigVO>> getConfigCompressImg() throws NotFoundException{	
		List<CompresorImgConfigVO> respuesta = expedienteImg.getAllConfigCompress();
		if(respuesta.isEmpty()) {
			throw new NotFoundException("No se encontraron configuraciones");
		}
		return new ResponseEntity<List<CompresorImgConfigVO>>(respuesta, HttpStatus.OK);
	};
	
	@GetMapping(value="/getInfoOSNivel")
	@Transactional
	//@PreAuthorize("hasAnyAuthority('GET_STATUS_CARGA_EVIDENCIAS')")
	public ResponseEntity<CargaExpedienteImgVO> getImgExpedienteNivel(
			@RequestParam(value ="tipoBusqueda") String tipoBusqueda,
			@RequestParam(value ="valor") String valor) throws NotFoundException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		List <String> transportistasOrden=new ArrayList<String>();
		List <String> instaladorOrden=new ArrayList<String>();
		List <String> supervisorOrden=new ArrayList<String>();
		List<CargaExpedienteImgVO> respuesta = expedienteImg.getInformacionExpediente(tipoBusqueda, valor);
		if(respuesta.isEmpty() || respuesta == null) {
			throw new NotFoundException("No se encontro expediente relacionado");
		}
		
		InfoEvidenciaNivelVO infoEvidenciaOrden = new InfoEvidenciaNivelVO();
		
		List<ImagenVO> imgOS = expedienteImg.getInfoExpedienteByNivel(
				respuesta.get(0).getIdOrdenServicio(), null, "ORDEN_SERVICIO");
		infoEvidenciaOrden.setImagenes(imgOS);
		infoEvidenciaOrden.setFechaIni(respuesta.get(0).getFechaInicio()!=null?sdf.format(respuesta.get(0).getFechaInicio()):"");
		infoEvidenciaOrden.setFechaFin(respuesta.get(0).getFechaFin()!=null?sdf.format(respuesta.get(0).getFechaFin()):"");
		infoEvidenciaOrden.setTieneImagen(imgOS.size()>0 ? 1 : 0);
		for(ExpedienteNivelProcesoVO proceso : respuesta.get(0).getProcesos()) {
			InfoEvidenciaNivelVO infoEvidenciaProceso = new InfoEvidenciaNivelVO();
			List <String> transportistasProceso=new ArrayList<String>();
			List <String> instaladorProceso=new ArrayList<String>();
			List <String> supervisorProceso=new ArrayList<String>();
			
			List<ImagenVO> imgPRO = expedienteImg.getInfoExpedienteByNivel(
					respuesta.get(0).getIdOrdenServicio(), proceso.getIdProceso(), "PROCESO");
			infoEvidenciaProceso.setImagenes(imgPRO);
			
			OrdenProcesoDTO ordenProcesoDTO=ordenProcesoDAO.getProceso(respuesta.get(0).getIdOrdenServicio(), proceso.getIdProceso());
			if(ordenProcesoDTO!=null)
					{
				infoEvidenciaProceso.setFechaIni(ordenProcesoDTO.getFhInicioProceso()!=null?sdf.format(ordenProcesoDTO.getFhInicioProceso()):"");
				infoEvidenciaProceso.setFechaFin(ordenProcesoDTO.getFhFinProceso()!=null?sdf.format(ordenProcesoDTO.getFhFinProceso()):"");
					}
			infoEvidenciaProceso.setTieneImagen(imgPRO.size()>0 ? 1 : 0);
			
			for(ExpedienteNivelEncuestaVO encuesta : proceso.getListEncuestas()) {
				InfoEvidenciaNivelVO infoEvidenciaEncuesta = new InfoEvidenciaNivelVO();
				List<ImagenVO> imgENC = expedienteImg.getInfoExpedienteByNivel(
						respuesta.get(0).getIdOrdenServicio(), encuesta.getIdEncuesta(), "ENCUESTA");
				infoEvidenciaEncuesta.setImagenes(imgENC);
				UsuarioEncuestaDetalleDTO usuarioEncuestaDetalleDTO=encuestaDetalleDAO.getEncuestaDetalle(encuesta.getIdEncuesta(), respuesta.get(0).getIdOrdenServicio());
				if(usuarioEncuestaDetalleDTO!=null)
				{
				List<UsuarioEncuestaIntentosDTO> usuarioEncuestaIntentosDTO=usuarioEncuestaIntentoDAO.usuarioEncuesta(usuarioEncuestaDetalleDTO.getIdUsuarioEncuesta());
				if(usuarioEncuestaIntentosDTO!=null)
				{
					infoEvidenciaEncuesta.setFechaIni(usuarioEncuestaIntentosDTO.get(0).getFhInicio()!=null?sdf.format(usuarioEncuestaIntentosDTO.get(0).getFhInicio()):"");
					infoEvidenciaEncuesta.setFechaFin(usuarioEncuestaIntentosDTO.get(0).getFhFin()!=null?sdf.format(usuarioEncuestaIntentosDTO.get(0).getFhFin()):"");
					IeStUsuEncuIntenDTO infoIntsSuperTrans=ieStUsuEncuIntenDAO.getInfoByUsuEncInt(usuarioEncuestaIntentosDTO.get(0).getIdUsuEncuIntento());
					if(infoIntsSuperTrans != null) {
						List <String> transportistas=new ArrayList<String>();
						List <String> instalador=new ArrayList<String>();
						List <String> supervisor=new ArrayList<String>();
						transportistas.add(infoIntsSuperTrans.getIdVehiculoConductor().getConductor().getNbConductor()+" "+infoIntsSuperTrans.getIdVehiculoConductor().getConductor().getNbApepatConductor()+" "+infoIntsSuperTrans.getIdVehiculoConductor().getConductor().getNbApematConductor());
						infoEvidenciaEncuesta.setNbTrasportista(transportistas);
						instalador.add(infoIntsSuperTrans.getIdRHInstalador().getNbPersona()+" "+infoIntsSuperTrans.getIdRHInstalador().getNbPatPersona()+" "+infoIntsSuperTrans.getIdRHInstalador().getNbMatPersona());
						infoEvidenciaEncuesta.setNbInstalador(instalador);
						UsuarioDTO usuario = usuarioDAO.findUserById(infoIntsSuperTrans.getIdGerenteSuoervisor().getSupervisor(),"SIE");
						if(usuario!=null) {
						supervisor.add(usuario.getNbUsuario()+" "+usuario.getNbApaterno()+" "+usuario.getNbApaterno());
						infoEvidenciaEncuesta.setNbSupervisor(supervisor);
						}
						}
				}
				}

				infoEvidenciaEncuesta.setTieneImagen(imgENC.size()>0 ? 1 : 0);
				encuesta.setInfoEvidencia(infoEvidenciaEncuesta);
				
				for(ExpedienteNivelPreguntaVO pregunta : encuesta.getListPreguntas()) {
					InfoEvidenciaNivelVO infoEvidenciaPregunta = new InfoEvidenciaNivelVO();
					List<ImagenVO> imgPREG = expedienteImg.getInfoExpedienteByNivel(
							respuesta.get(0).getIdOrdenServicio(), pregunta.getIdPregunta(), "PREGUNTA");
					infoEvidenciaPregunta.setImagenes(imgPREG);
					infoEvidenciaPregunta.setNbSupervisor(encuesta.getInfoEvidencia().getNbSupervisor()!=null?encuesta.getInfoEvidencia().getNbSupervisor():new ArrayList<String>());
					infoEvidenciaPregunta.setNbInstalador(encuesta.getInfoEvidencia().getNbInstalador()!=null?encuesta.getInfoEvidencia().getNbInstalador():new ArrayList<String>());
					infoEvidenciaPregunta.setNbTrasportista(encuesta.getInfoEvidencia().getNbTrasportista()!=null?encuesta.getInfoEvidencia().getNbTrasportista():new ArrayList<String>());
					infoEvidenciaPregunta.setTieneImagen(imgPREG.size()>0 ? 1 : 0);
					encuesta.setInfoEvidencia(infoEvidenciaPregunta);
				}
				if(encuesta.getInfoEvidencia().getNbTrasportista().size()>0)
				transportistasProceso.add(encuesta.getInfoEvidencia().getNbTrasportista().get(0));
				if(encuesta.getInfoEvidencia().getNbInstalador().size()>0)
				instaladorProceso.add(encuesta.getInfoEvidencia().getNbInstalador().get(0));
				if(encuesta.getInfoEvidencia().getNbSupervisor().size()>0)
				supervisorProceso.add(encuesta.getInfoEvidencia().getNbSupervisor().get(0));

			}
			if(transportistasProceso.size()>0)
			{
             List <String> transportistasProcesoNew=new ArrayList<String>();
             transportistasProcesoNew.add(transportistasProceso.get(0));
             int tamaño=0;
		            for (int x = 1; x < transportistasProceso.size(); x++) {
		            	tamaño=transportistasProcesoNew.size();
		                if (!transportistasProcesoNew.get(tamaño-1).equals(transportistasProceso.get(x))) {
		                	transportistasProcesoNew.add(transportistasProceso.get(x));
		                }
		            }
	    
		    infoEvidenciaProceso.setNbTrasportista(transportistasProcesoNew);
			}
			if(instaladorProceso.size()>0)
			{
             List <String> instaladorProcesonEW=new ArrayList<String>();
             instaladorProcesonEW.add(instaladorProceso.get(0));
             int tamaño=0;
		            for (int x = 1; x < instaladorProceso.size(); x++) {
		            	tamaño=instaladorProcesonEW.size();
		                if (!instaladorProcesonEW.get(tamaño-1).equals(instaladorProceso.get(x))) {
		                	instaladorProcesonEW.add(instaladorProceso.get(x));
		                }
		            }
	    
		            infoEvidenciaProceso.setNbSupervisor(instaladorProcesonEW);
			}
			if(supervisorProceso.size()>0)
			{
             List <String> supervisorProcesoNew=new ArrayList<String>();
             supervisorProcesoNew.add(supervisorProceso.get(0));
             int tamaño=0;
		            for (int x = 1; x < supervisorProceso.size(); x++) {
		            	tamaño=supervisorProcesoNew.size();
		                if (!supervisorProcesoNew.get(tamaño-1).equals(supervisorProceso.get(x))) {
		                	supervisorProcesoNew.add(supervisorProceso.get(x));
		                }
		            }
	    
		            infoEvidenciaProceso.setNbInstalador(supervisorProcesoNew);
			}
			
			proceso.setInfoEvidencia(infoEvidenciaProceso);
			
			if(proceso.getInfoEvidencia().getNbTrasportista().size()>0)
				for(String transportista:proceso.getInfoEvidencia().getNbTrasportista())
				{
				transportistasOrden.add(transportista);
				}
			
			if(proceso.getInfoEvidencia().getNbInstalador().size()>0)
				for(String instalador:proceso.getInfoEvidencia().getNbInstalador())
				{
					instaladorOrden.add(instalador);
				}
			
			if(proceso.getInfoEvidencia().getNbSupervisor().size()>0)
				for(String supervisor:proceso.getInfoEvidencia().getNbSupervisor())
				{
					supervisorOrden.add(supervisor);
				}

		}
		
		if(transportistasOrden.size()>0)
		{
         List <String> transportistasProcesoNew=new ArrayList<String>();
         transportistasProcesoNew.add(transportistasOrden.get(0));
         int tamaño=0;
	            for (int x = 1; x < transportistasOrden.size(); x++) {
	            	tamaño=transportistasProcesoNew.size();
	                if (!transportistasProcesoNew.get(tamaño-1).equals(transportistasOrden.get(x))) {
	                	transportistasProcesoNew.add(transportistasOrden.get(x));
	                }
	            }
	    		infoEvidenciaOrden.setNbSupervisor(transportistasProcesoNew);

 
		}
		
		if(instaladorOrden.size()>0)
		{
         List <String> instaladorProcesonEW=new ArrayList<String>();
         instaladorProcesonEW.add(instaladorOrden.get(0));
         int tamaño=0;
	            for (int x = 1; x < instaladorOrden.size(); x++) {
	            	tamaño=instaladorProcesonEW.size();
	                if (!instaladorProcesonEW.get(tamaño-1).equals(instaladorOrden.get(x))) {
	                	instaladorProcesonEW.add(instaladorOrden.get(x));
	                }
	            }
	          infoEvidenciaOrden.setNbInstalador(instaladorProcesonEW);
		}
		
		if(supervisorOrden.size()>0)
		{
         List <String> supervisorProcesoNew=new ArrayList<String>();
         supervisorProcesoNew.add(supervisorOrden.get(0));
         int tamaño=0;
	            for (int x = 1; x < supervisorOrden.size(); x++) {
	            	tamaño=supervisorProcesoNew.size();
	                if (!supervisorProcesoNew.get(tamaño-1).equals(supervisorOrden.get(x))) {
	                	supervisorProcesoNew.add(supervisorOrden.get(x));
	                }
	            }
	    		
	    		infoEvidenciaOrden.setNbTrasportista(supervisorProcesoNew);

		}

		respuesta.get(0).setInfoEvidencia(infoEvidenciaOrden);
		respuesta.get(0).setImagenes(null);
		
		return new ResponseEntity<CargaExpedienteImgVO>(respuesta.get(0), HttpStatus.OK);
	};
}
