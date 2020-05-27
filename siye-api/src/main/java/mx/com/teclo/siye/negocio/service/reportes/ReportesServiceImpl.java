package mx.com.teclo.siye.negocio.service.reportes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.siye.persistencia.hibernate.dao.reportes.ConfiguracionReportesDAO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.CargaExpedienteImgVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelPreguntaVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;
import mx.com.tecloreporte.jar.utils.comun.RutinasTiempo;
import mx.com.teclo.siye.persistencia.vo.reportes.EvidenciaReporteVO;
import mx.com.teclo.siye.persistencia.vo.reportes.ImagenesEvidenciaReporteVO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportesServiceImpl implements ReportesService {

	@Autowired
	private ServletContext context;
	
	@Autowired
	private RutinasTiempo rutinasTiempo;
	
	@Autowired
	private ConfiguracionReportesDAO configuracionReportesDAO;

	private static final Logger logger = Logger.getLogger(ReportesServiceImpl.class);

	@Transactional
	@Override
	public ByteArrayOutputStream getReporteDOS(OrdenServicioVO os, CargaExpedienteImgVO detalle, Boolean conImagenes) {
//		##Aqui esmpieza lo nuevo
//		ConfigReporteDTO configReporteDTO 
		Map<String, Object> param = new HashMap<>();
		
		InputStream img1 = null;
		InputStream img2 = null;
		
		try {
			img1 = configuracionReportesDAO.getConfigParametrosCdLlave("IMG_CDMX").getCdValorImg().getBinaryStream(); 
			img2 = configuracionReportesDAO.getConfigParametrosCdLlave("IMG_SEMOVI").getCdValorImg().getBinaryStream();
		}catch (Exception e) {
			 System.out.println(e);
		}
		
		String jasper = context.getRealPath("/WEB-INF/reportes/reporteDetOS_v2.jasper");
		
		param.put("IMAGEN1", img1);
		param.put("IMAGEN2", img2);
		
		param.put("NOMBRE_DOCUMENTO", "Orden de Servicio");
		param.put("FOLIO", os.getCdOrdenServicio());
		param.put("CTRO_INSTALA", os.getCentroInstalacion().getNbCentroInstalacion());
		param.put("FECHA_CITA", rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy HH:mm:ss", os.getFhCita()));
		param.put("ORIGEN", os.getIdOrigenOds() != null ? (os.getIdOrigenOds() == 1 ? "LOTE" : "INCIDENCIA") : "");
		param.put("ESTATUS", os.getStSeguimiento().getNbStSeguimiento());
		param.put("FH_INI", os.getFhAtencionIni() != null ? 
				rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy HH:mm:ss", os.getFhAtencionIni()) : ""
				);
		param.put("FH_FIN", os.getFhAtencionFin() != null ?  
				rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy HH:mm:ss", os.getFhAtencionFin()) : "");
		param.put("DURACION", getDuracion(detalle.getInfoEvidencia().getFechaIni(), detalle.getInfoEvidencia().getFechaFin()));
		param.put("PLAN", os.getPlan().getNbPlan());
		
		String sup = listToString(detalle.getInfoEvidencia().getNbSupervisor(), "Sin Supervisor");
		param.put("LIST_SUP", sup);
		String trans = listToString(detalle.getInfoEvidencia().getNbTrasportista(), "Sin Trasportista");
		param.put("LIST_TRASP", trans);
		
		param.put("PLACA", os.getVehiculo().getCdPlacaVehiculo());
		param.put("VIM", os.getVehiculo().getCdVin());
		param.put("TARJ_CIRC", os.getVehiculo().getCdTarjetaDeCirculacion());
		param.put("TIPO", os.getVehiculo().getTipoVehiculo().getNbTipoVehiculo());
		param.put("MARCA", os.getVehiculo().getNbMarca());
		param.put("SUB_MARCA", os.getVehiculo().getNbSubMarca());
		param.put("MODELO", os.getVehiculo().getCdModelo()+"");
		param.put("CONCECIONARIO", os.getVehiculo().getConsecionario().getNbConsecion());
		
//		##Bloque de Evidencia
		
		List<EvidenciaReporteVO> listEvidencia = getListEvidenciaReporte(detalle, conImagenes);
//		param.put("LIST_EVIDENCIA", listEvidencia);
		JRBeanCollectionDataSource evidencia = new JRBeanCollectionDataSource(listEvidencia);
		param.put("LIST_EVIDENCIA", evidencia);
		
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		try {
			reporte.write(JasperRunManager.runReportToPdf(jasper, param, new JREmptyDataSource()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;
	}

	@Override
	public byte[] getPDF(ByteArrayOutputStream b) {
		if(b != null)
			return b.toByteArray();
		return null;
	}
	
	private String getDuracion(String fhIni, String fhFin) {
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fi = format.parse(fhIni);
			Date ff = format.parse(fhFin);
			String dif="";
			long seg = 0L;
			
			if(fi == null)
				return "00:00:00";
			if(ff == null) {
				ff = new Date();
				seg = (fi.getTime()-ff.getTime())/1000;
			}
			seg = (fi.getTime()-ff.getTime())/1000;		
			Integer horas = Math.round(seg / 3600);
			Integer minutos = Math.round((seg-horas*3600)/60);
			Integer segundos = Math.round(seg-(horas*3600+minutos*60));
			
			dif = (horas<10 ? ("0"+horas) : horas) + ":" 
					+ (minutos<10 ? ("0"+minutos) : minutos) + ":" 
						+ (segundos<10 ? ("0"+segundos) : segundos);
			return dif;
		}catch (Exception e) {
			return "00:00:00";
		}
	}
	
	private String listToString(List<String> lista, String otro) {
		String r="";
		if(lista==null || lista.size()==0)
			return otro;
		for(int x=0; x<lista.size();x++) {
			r = r + lista.get(x);
			if((x+1)<lista.size())
				r = r + ", ";
		}
		return r;
	}
	
	private List<EvidenciaReporteVO> getListEvidenciaReporte(CargaExpedienteImgVO detalle, Boolean conImagenes) {
		List<EvidenciaReporteVO> lista = new ArrayList<EvidenciaReporteVO>();
		EvidenciaReporteVO e = new EvidenciaReporteVO();
		
		List<ImagenesEvidenciaReporteVO> listImagenes = new ArrayList<ImagenesEvidenciaReporteVO>();
		List<ImagenesEvidenciaReporteVO> listImagenes2 = new ArrayList<ImagenesEvidenciaReporteVO>();
//		List<ByteArrayInputStream> listImagenes = new ArrayList<ByteArrayInputStream>();
		ImagenesEvidenciaReporteVO imgEv = new ImagenesEvidenciaReporteVO();
		
		ByteArrayInputStream bimg;
		int contador=0;
		float media = 0;
		e.setNombre("Orden de Servicio");
		e.setFhInicio(detalle.getFechaInicio() != null ? rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy HH:mm:ss", detalle.getFechaInicio()) : "Sin Fecha");
		e.setFhFin(detalle.getFechaFin() != null  ? rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy HH:mm:ss", detalle.getFechaFin()) : "Sin Fecha");
		e.setDuracion(getDuracion(detalle.getInfoEvidencia().getFechaIni(), detalle.getInfoEvidencia().getFechaFin()));
		e.setSupervisores(listToString(detalle.getInfoEvidencia().getNbSupervisor(), "Sin Superervisor"));
		e.setInstaladores(listToString(detalle.getInfoEvidencia().getNbInstalador(), "Sin Instalador"));
		e.setTrasportistas(listToString(detalle.getInfoEvidencia().getNbTrasportista(), "Sin Trasportista"));
		if(detalle.getInfoEvidencia().getImagenes()!=null && conImagenes) {
			for(ImagenVO i : detalle.getInfoEvidencia().getImagenes()) {
				contador++;
				imgEv = new ImagenesEvidenciaReporteVO();
				imgEv.setFilename(i.getNbExpedienteODS());
				bimg = new ByteArrayInputStream(i.getLbExpedienteODS()); 	  
				imgEv.setFileBase64toBlob(bimg);
				media = detalle.getInfoEvidencia().getImagenes().size();
				media = media/2;
				if(contador <= Math.ceil(media))
					listImagenes.add(imgEv);
				else
					listImagenes2.add(imgEv);
//				listImagenes.add(bimg);
			}
		}
		e.setListImagenes(listImagenes);
		e.setListImagenes2(listImagenes2);
		lista.add(e);
		
		for(ExpedienteNivelProcesoVO p : detalle.getProcesos()) {
			e = new EvidenciaReporteVO();
//			listImagenes = new ArrayList<ByteArrayInputStream>();
			listImagenes = new ArrayList<ImagenesEvidenciaReporteVO>();
			listImagenes2 = new ArrayList<ImagenesEvidenciaReporteVO>();
			e = new EvidenciaReporteVO();
			e.setNombre(p.getName());
			e.setFhInicio(p.getInfoEvidencia().getFechaIni() == null ? "Sin Fecha" : p.getInfoEvidencia().getFechaIni());
			e.setFhFin(p.getInfoEvidencia().getFechaFin() == null ? "Sin Fecha": p.getInfoEvidencia().getFechaFin());
			e.setDuracion(getDuracion(p.getInfoEvidencia().getFechaIni(), p.getInfoEvidencia().getFechaFin()));
			e.setSupervisores(listToString(p.getInfoEvidencia().getNbSupervisor(), "Sin Superervisor"));
			e.setInstaladores(listToString(p.getInfoEvidencia().getNbInstalador(), "Sin Instalador"));
			e.setTrasportistas(listToString(p.getInfoEvidencia().getNbTrasportista(), "Sin Trasportista"));
			if(p.getInfoEvidencia().getImagenes()!=null && conImagenes) {
				contador=0;
				for(ImagenVO i : p.getInfoEvidencia().getImagenes()) {
					contador++;
					imgEv = new ImagenesEvidenciaReporteVO();
					imgEv.setFilename(i.getNbExpedienteODS());
					bimg = new ByteArrayInputStream(i.getLbExpedienteODS()); 	  
					imgEv.setFileBase64toBlob(bimg);
					media = p.getInfoEvidencia().getImagenes().size();
					media = media/2;
					if(contador <= Math.ceil(media))
						listImagenes.add(imgEv);
					else
						listImagenes2.add(imgEv);
//					listImagenes.add(bimg);
				}
			}
			e.setListImagenes(listImagenes);
			e.setListImagenes2(listImagenes2);
			lista.add(e);
			
			for(ExpedienteNivelEncuestaVO enc : p.getListEncuestas()) {
				e = new EvidenciaReporteVO();
//				listImagenes = new ArrayList<ByteArrayInputStream>();
				listImagenes = new ArrayList<ImagenesEvidenciaReporteVO>();
				listImagenes2 = new ArrayList<ImagenesEvidenciaReporteVO>();
				e = new EvidenciaReporteVO();
				e.setNombre(enc.getName());
				e.setFhInicio(enc.getInfoEvidencia().getFechaIni() == null ? "Sin Fecha" : enc.getInfoEvidencia().getFechaIni());
				e.setFhFin(enc.getInfoEvidencia().getFechaFin() == null ? "Sin Fecha" : enc.getInfoEvidencia().getFechaFin());
				e.setDuracion(getDuracion(enc.getInfoEvidencia().getFechaIni(), enc.getInfoEvidencia().getFechaFin()));
				e.setSupervisores(listToString(enc.getInfoEvidencia().getNbSupervisor(), "Sin Superervisor"));
				e.setInstaladores(listToString(enc.getInfoEvidencia().getNbInstalador(), "Sin Instalador"));
				e.setTrasportistas(listToString(enc.getInfoEvidencia().getNbTrasportista(), "Sin Trasportista"));
				if(enc.getInfoEvidencia().getImagenes()!=null && conImagenes) {
					contador=0;
					for(ImagenVO i : enc.getInfoEvidencia().getImagenes()) {
						imgEv = new ImagenesEvidenciaReporteVO();
						imgEv.setFilename(i.getNbExpedienteODS());
						bimg = new ByteArrayInputStream(i.getLbExpedienteODS()); 	  
						imgEv.setFileBase64toBlob(bimg);
						media = enc.getInfoEvidencia().getImagenes().size();
						media = media/2;
						if(contador<= Math.ceil(media))
							listImagenes.add(imgEv);
						else
							listImagenes2.add(imgEv);
//						listImagenes.add(bimg);
					}
				}
				e.setListImagenes(listImagenes);
				e.setListImagenes2(listImagenes2);
				lista.add(e);
				
				for(ExpedienteNivelPreguntaVO preg : enc.getListPreguntas()) {
					e = new EvidenciaReporteVO();
//					listImagenes = new ArrayList<ByteArrayInputStream>();
					listImagenes = new ArrayList<ImagenesEvidenciaReporteVO>();
					listImagenes2 = new ArrayList<ImagenesEvidenciaReporteVO>();
					e = new EvidenciaReporteVO();
					e.setNombre("Encuesta: " + enc.getName() + ". Pregunta: " + preg.getName());
					e.setFhInicio(preg.getInfoEvidencia().getFechaIni() == null ? "Sin Fecha" : preg.getInfoEvidencia().getFechaIni());
					e.setFhFin(preg.getInfoEvidencia().getFechaFin() == null ? "Sin Fecha" : preg.getInfoEvidencia().getFechaFin());
					e.setDuracion(getDuracion(preg.getInfoEvidencia().getFechaIni(), preg.getInfoEvidencia().getFechaFin()));
					e.setSupervisores(listToString(preg.getInfoEvidencia().getNbSupervisor(), "Sin Superervisor"));
					e.setInstaladores(listToString(preg.getInfoEvidencia().getNbInstalador(), "Sin Instalador"));
					e.setTrasportistas(listToString(preg.getInfoEvidencia().getNbTrasportista(), "Sin Trasportista"));
					if(preg.getInfoEvidencia().getImagenes()!=null && conImagenes) {
						contador=0;
						for(ImagenVO i : preg.getInfoEvidencia().getImagenes()) {
							imgEv = new ImagenesEvidenciaReporteVO();
							imgEv.setFilename(i.getNbExpedienteODS());
							bimg = new ByteArrayInputStream(i.getLbExpedienteODS()); 	  
							imgEv.setFileBase64toBlob(bimg);
							media = preg.getInfoEvidencia().getImagenes().size();
							media = media/2;
							if(contador<=Math.ceil(media))
								listImagenes.add(imgEv);
							else
								listImagenes2.add(imgEv);
//							listImagenes.add(bimg);
						}
					}
					e.setListImagenes(listImagenes);
					e.setListImagenes2(listImagenes2);
					lista.add(e);
				}
			}
			
		}
		
		return lista;
	}

}
