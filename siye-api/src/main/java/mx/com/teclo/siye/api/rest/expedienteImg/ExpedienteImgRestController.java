package mx.com.teclo.siye.api.rest.expedienteImg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	//@PreAuthorize("hasAnyAuthority('GET_STATUS_CARGA_EVIDENCIAS')")
	public ResponseEntity<CargaExpedienteImgVO> getImgExpedienteNivel(
			@RequestParam(value ="tipoBusqueda") String tipoBusqueda,
			@RequestParam(value ="valor") String valor) throws NotFoundException{	
		List<CargaExpedienteImgVO> respuesta = expedienteImg.getInformacionExpediente(tipoBusqueda, valor);
		if(respuesta.isEmpty() || respuesta == null) {
			throw new NotFoundException("No se encontro expediente relacionado");
		}
		
		InfoEvidenciaNivelVO infoEvidencia = new InfoEvidenciaNivelVO();
		
		List<ImagenVO> imgOS = expedienteImg.getInfoExpedienteByNivel(
				respuesta.get(0).getIdOrdenServicio(), null, "ORDEN_SERVICIO");
		infoEvidencia.setImagenes(imgOS);
		infoEvidencia.setFechaIni("");
		infoEvidencia.setFechaFin("");
		infoEvidencia.setNbSupervisor("");
		infoEvidencia.setNbInstalador("");
		infoEvidencia.setNbTrasportista("");
		infoEvidencia.setTieneImagen(!imgOS.isEmpty() || imgOS!=null ? 1 : 0);
		respuesta.get(0).setInfoEvidencia(infoEvidencia);
		
		for(ExpedienteNivelProcesoVO proceso : respuesta.get(0).getProcesos()) {
			infoEvidencia = new InfoEvidenciaNivelVO();
			
			List<ImagenVO> imgPRO = expedienteImg.getInfoExpedienteByNivel(
					respuesta.get(0).getIdOrdenServicio(), proceso.getIdProceso(), "PROCESO");
			infoEvidencia.setImagenes(imgPRO);
			infoEvidencia.setFechaIni("");
			infoEvidencia.setFechaFin("");
			infoEvidencia.setNbSupervisor("");
			infoEvidencia.setNbInstalador("");
			infoEvidencia.setNbTrasportista("");
			infoEvidencia.setTieneImagen(!imgPRO.isEmpty() || imgPRO!=null ? 1 : 0);
			proceso.setInfoEvidencia(infoEvidencia);
			
			for(ExpedienteNivelEncuestaVO encuesta : proceso.getListEncuestas()) {
				List<ImagenVO> imgENC = expedienteImg.getInfoExpedienteByNivel(
						respuesta.get(0).getIdOrdenServicio(), encuesta.getIdEncuesta(), "ENCUESTA");
				infoEvidencia.setImagenes(imgENC);
				infoEvidencia.setFechaIni("");
				infoEvidencia.setFechaFin("");
				infoEvidencia.setNbSupervisor("");
				infoEvidencia.setNbInstalador("");
				infoEvidencia.setNbTrasportista("");
				infoEvidencia.setTieneImagen(!imgENC.isEmpty() || imgENC!=null ? 1 : 0);
				encuesta.setInfoEvidencia(infoEvidencia);
				
				for(ExpedienteNivelPreguntaVO pregunta : encuesta.getListPreguntas()) {
					List<ImagenVO> imgPREG = expedienteImg.getInfoExpedienteByNivel(
							respuesta.get(0).getIdOrdenServicio(), pregunta.getIdPregunta(), "PREGUNTA");
					infoEvidencia.setImagenes(imgENC);
					infoEvidencia.setFechaIni("");
					infoEvidencia.setFechaFin("");
					infoEvidencia.setNbSupervisor("");
					infoEvidencia.setNbInstalador("");
					infoEvidencia.setNbTrasportista("");
					infoEvidencia.setTieneImagen(!imgPREG.isEmpty() || imgPREG!=null ? 1 : 0);
					encuesta.setInfoEvidencia(infoEvidencia);
				}
			}
		}
		
		respuesta.get(0).setImagenes(null);
		
		return new ResponseEntity<CargaExpedienteImgVO>(respuesta.get(0), HttpStatus.OK);
	};
}
