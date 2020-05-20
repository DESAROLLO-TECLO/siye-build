package mx.com.teclo.siye.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.UsuarioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.OrdenProcesoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.IeStUsuEncuIntenDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsuarioDTO;
import mx.com.teclo.siye.negocio.service.expedienteImg.ExpedienteImgService;
import mx.com.teclo.siye.negocio.service.ordenServicio.OrdenServicioService;
import mx.com.teclo.siye.negocio.service.reportes.ReportesService;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.EncuestaDetalleDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.OrdenProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.encuesta.UsuarioEncuestaIntentoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.IeStUsuEncuIntenDAO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelPreguntaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.InfoEvidenciaNivelVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

@RestController
@RequestMapping("/reporte")
public class ReportesRestController {
	
	@Autowired
	private OrdenServicioService ordenServicioService;
	
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
	
	@Autowired
	private ReportesService reportesService;
	
	@Autowired
	private ExpedienteImgService expedienteImg;
	
	@Transactional
	@RequestMapping(value="/reporteDetOS", method = RequestMethod.GET)
	public ResponseEntity<byte[]> reporteDetOrdenServicio(
			@RequestParam(value="idOrdenservicio", required=true) Long id, 
			@RequestParam(value="conImagenes", required=false) Boolean conImagenes) throws NotFoundException, BusinessException{
		
		if(conImagenes==null)
			conImagenes = true;
		OrdenServicioVO osVO = ordenServicioService.findOrdenServicio(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List <String> transportistasOrden=new ArrayList<String>();
		List <String> instaladorOrden=new ArrayList<String>();
		List <String> supervisorOrden=new ArrayList<String>();
		List<CargaExpedienteImgVO> respuesta = expedienteImg.getInformacionExpediente("ORDEN_SERVICIO", osVO.getCdOrdenServicio());
		if(respuesta.isEmpty() || respuesta == null) {
			throw new NotFoundException("No se encontro expediente relacionado");
		}
		
		InfoEvidenciaNivelVO infoEvidenciaOrden = new InfoEvidenciaNivelVO();
		
		List<ImagenVO> imgOS = expedienteImg.getInfoExpedienteByNivel(
				respuesta.get(0).getIdOrdenServicio(), null, "ORDEN_SERVICIO");
		infoEvidenciaOrden.setImagenes(imgOS);
		infoEvidenciaOrden.setFechaIni(respuesta.get(0).getFechaInicio()!=null?sdf.format(respuesta.get(0).getFechaInicio()):"");
		infoEvidenciaOrden.setFechaFin(respuesta.get(0).getFechaFin()!=null?sdf.format(respuesta.get(0).getFechaFin()):"");
		infoEvidenciaOrden.setTieneImagen(imgOS!=null && imgOS.size()>0 ? 1 : 0);
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
			infoEvidenciaProceso.setTieneImagen(imgPRO!=null && imgPRO.size()>0 ? 1 : 0);
			
			for(ExpedienteNivelEncuestaVO encuesta : proceso.getListEncuestas()) {
				InfoEvidenciaNivelVO infoEvidenciaEncuesta = new InfoEvidenciaNivelVO();
		
				List<ImagenVO> imgENC=new ArrayList<ImagenVO>();
				UsuarioEncuestaDetalleDTO usuarioEncuestaDetalleDTO=encuestaDetalleDAO.getEncuestaDetalle(encuesta.getIdEncuesta(), respuesta.get(0).getIdOrdenServicio());
				if(usuarioEncuestaDetalleDTO!=null)
				{
					imgENC = expedienteImg.getInfoExpedienteByNivel(
							respuesta.get(0).getIdOrdenServicio(), encuesta.getIdEncuesta(), "ENCUESTA");
					infoEvidenciaEncuesta.setImagenes(imgENC);
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

				infoEvidenciaEncuesta.setTieneImagen(imgENC!=null && imgENC.size()>0 ? 1 : 0);
				encuesta.setInfoEvidencia(infoEvidenciaEncuesta);
				
				for(ExpedienteNivelPreguntaVO pregunta : encuesta.getListPreguntas()) {
					InfoEvidenciaNivelVO infoEvidenciaPregunta = new InfoEvidenciaNivelVO();
					List<ImagenVO> imgPREG = expedienteImg.getInfoExpedienteByNivel(
							respuesta.get(0).getIdOrdenServicio(), pregunta.getIdPregunta(), "PREGUNTA");
					infoEvidenciaPregunta.setImagenes(imgPREG);
					infoEvidenciaPregunta.setNbSupervisor(encuesta.getInfoEvidencia().getNbSupervisor()!=null?encuesta.getInfoEvidencia().getNbSupervisor():new ArrayList<String>());
					infoEvidenciaPregunta.setNbInstalador(encuesta.getInfoEvidencia().getNbInstalador()!=null?encuesta.getInfoEvidencia().getNbInstalador():new ArrayList<String>());
					infoEvidenciaPregunta.setNbTrasportista(encuesta.getInfoEvidencia().getNbTrasportista()!=null?encuesta.getInfoEvidencia().getNbTrasportista():new ArrayList<String>());
					infoEvidenciaPregunta.setTieneImagen(imgPREG!=null && imgPREG.size()>0 ? 1 : 0);
					pregunta.setInfoEvidencia(infoEvidenciaPregunta);
				}
				if(encuesta.getInfoEvidencia().getNbTrasportista()!=null)
				transportistasProceso.add(encuesta.getInfoEvidencia().getNbTrasportista().get(0));
				if(encuesta.getInfoEvidencia().getNbInstalador()!=null)
				instaladorProceso.add(encuesta.getInfoEvidencia().getNbInstalador().get(0));
				if(encuesta.getInfoEvidencia().getNbSupervisor()!=null)
				supervisorProceso.add(encuesta.getInfoEvidencia().getNbSupervisor().get(0));

			}
			if(transportistasProceso.size()>0)
			{
				HashSet<String> hashSet = new HashSet<String>(transportistasProceso);
				transportistasProceso.clear();
				transportistasProceso.addAll(hashSet);
	    
		    infoEvidenciaProceso.setNbTrasportista(transportistasProceso);
			}
			if(instaladorProceso.size()>0)
			{
				HashSet<String> hashSet = new HashSet<String>(instaladorProceso);
				instaladorProceso.clear();
				instaladorProceso.addAll(hashSet);
	    
		            infoEvidenciaProceso.setNbSupervisor(instaladorProceso);
			}
			if(supervisorProceso.size()>0)
			{
				HashSet<String> hashSet = new HashSet<String>(supervisorProceso);
				supervisorProceso.clear();
				supervisorProceso.addAll(hashSet);
	    
		            infoEvidenciaProceso.setNbInstalador(supervisorProceso);
			}
			
			proceso.setInfoEvidencia(infoEvidenciaProceso);
			
			if(proceso.getInfoEvidencia().getNbTrasportista()!=null)
				for(String transportista:proceso.getInfoEvidencia().getNbTrasportista())
				{
				transportistasOrden.add(transportista);
				}
			
			if(proceso.getInfoEvidencia().getNbInstalador()!=null)
				for(String instalador:proceso.getInfoEvidencia().getNbInstalador())
				{
					instaladorOrden.add(instalador);
				}
			
			if(proceso.getInfoEvidencia().getNbSupervisor()!=null)
				for(String supervisor:proceso.getInfoEvidencia().getNbSupervisor())
				{
					supervisorOrden.add(supervisor);
				}

		}
		
		if(transportistasOrden.size()>0)
		{
			HashSet<String> hashSet = new HashSet<String>(transportistasOrden);
			transportistasOrden.clear();
			transportistasOrden.addAll(hashSet);
	    	
			infoEvidenciaOrden.setNbSupervisor(transportistasOrden);

		}
		
		if(instaladorOrden.size()>0)
		{
			HashSet<String> hashSet = new HashSet<String>(instaladorOrden);
			instaladorOrden.clear();
			instaladorOrden.addAll(hashSet);
			
	        infoEvidenciaOrden.setNbInstalador(instaladorOrden);
		}
		
		if(supervisorOrden.size()>0)
		{
			HashSet<String> hashSet = new HashSet<String>(supervisorOrden);
			supervisorOrden.clear();
			supervisorOrden.addAll(hashSet);
	    		
	    		infoEvidenciaOrden.setNbTrasportista(supervisorOrden);

		}

		respuesta.get(0).setInfoEvidencia(infoEvidenciaOrden);
		respuesta.get(0).setImagenes(null);
		
//		##
//		Aqui se empieza la parte del reporte
//		##
		
		ByteArrayOutputStream os = reportesService.getReporteDOS(osVO, respuesta.get(0), conImagenes);
		byte [] byteToReturn = reportesService.getPDF(os);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =Resumen_encuesta.pdf");
		headers.add("filename", "Resumen_encuesta.pdf");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(byteToReturn.length);
		return new ResponseEntity<byte[]>(byteToReturn, headers, HttpStatus.OK);
	}
}
