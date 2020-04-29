package mx.com.teclo.siye.negocio.service.seguimientoOs;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.ExpedienteImgDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.DetalleIncidenciaVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.EncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServcioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.PreguntasDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesoDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesosOrdenServicioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ReporteExcelVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

@Service
public class SeguimientoOsServiceImpl implements SeguimientoOsService {

	@Autowired
	private GerenteSupervisorDAO gerenteSupervisorDAO;

	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Autowired
	private ExpedienteImgDAO expedienteImgDAO;

	@Autowired
	private PlanProcesoDAO planProcesoDAO;

	@Autowired
	private ProcesoEncuestaDAO procesoEncuestaDAO;
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	@Autowired
	private RptGralSeguimientoService rptGeneral;
	
	@Autowired
	private RptDetalleSeguimientoService  rptDetalle;
	
	@Autowired
	private RptIncidenciaSeguimientoService rptIncidencia;
	

	private final String ENCURSO = "EN_CURSO", COMPLETA = "COMPLETADAS", PROGRAMADA = "PROGRAMADA",
			NOPROGRAMADA = "NO_PROGRAMADA", INCIDENCIA = "INCIDENCIAS";

	@Transactional
	@Override
	public List<SeguimientoOrdenServicioVO> getSeguimientoOrdenServicio(Long idSupervisor, List<String> columnas,
			List<String> colOmitidas, String fInicio, String fFin) {
		List<SeguimientoOrdenServicioVO> CentroInstalacion = new ArrayList<SeguimientoOrdenServicioVO>();
		
		// Consultar Centro Instalacion adminsitrados
		List<Long> idCentroInstalacion = gerenteSupervisorDAO.getIdCentroInstalacion(idSupervisor);

		if (!idCentroInstalacion.isEmpty()) {
			// Consultar informacion de seguimiento de OS , por cantidades, dependiendo las
			// columnas enviadas desde front
			StringBuilder consulta = generarConsulta(columnas, colOmitidas, fInicio, fFin);
			
			CentroInstalacion = ordenServicioDAO.getInfoSeguimientoGeneral(consulta, idCentroInstalacion, columnas);
			if (!CentroInstalacion.isEmpty()) {
				for (SeguimientoOrdenServicioVO ci : CentroInstalacion) {
					List<OrdenServcioDetalleVO> detalleOS = ordenServicioDAO.getDetalleOS(ci.getIdCentroInstalacion(), fInicio, fFin);
					if (!detalleOS.isEmpty()) {
						ci.setDetalleOrdenServicio(detalleOS);
					}
				}
			}
		}
		return CentroInstalacion;
	};

	public StringBuilder generarConsulta(List<String> columnas, List<String> colOmitidas, String fechaInicio,
			String fechaFin) {
		int largo = columnas.size();
		StringBuilder consulta = new StringBuilder(
				"SELECT CI.ID_CENTRO_INSTALACION AS idCentroInstalacion," + "CI.NB_CENTRO_INSTALACION AS nbModulo, ");

		for (int x = 0; x < largo; x++) {
			switch (columnas.get(x)) {
			case ENCURSO:
				consulta.append("(SELECT COUNT(OS2.ID_ST_SEGUIMIENTO)" + " FROM TIE026D_IE_ORDEN_SERVICIOS OS2"
						+ "  LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION)"
						+ "  LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO)"
						+ "WHERE SEG.CD_ST_SEGUIMIENTO != 'FINALIZADO' AND SEG.CD_ST_SEGUIMIENTO != 'FIN_INCIDENCIA'"
						+ "	AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION AND OS2.ST_ACTIVO = 1"
						+ "	AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE('" + fechaInicio + "','dd/MM/yyyy')  AND TO_DATE('"
						+ fechaFin + "','dd/MM/yyyy')" + ") AS nuEnCurso");
				break;
			case COMPLETA:
				consulta.append("(SELECT" + " COUNT(OS2.ID_ST_SEGUIMIENTO)" + "  FROM TIE026D_IE_ORDEN_SERVICIOS OS2"
						+ "    LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION)"
						+ "	 LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO)"
						+ "  WHERE SEG.CD_ST_SEGUIMIENTO = 'FINALIZADO'"
						+ "			  AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION AND OS2.ST_ACTIVO = 1"
						+ "			   AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE('" + fechaInicio
						+ "','dd/MM/yyyy')  AND TO_DATE('" + fechaFin + "','dd/MM/yyyy')" + ") AS nuCompleta");
				break;
			case PROGRAMADA:
				consulta.append("(SELECT	count(*)" + " FROM TIE025D_IE_LOTE_ODS LODS"
						+ " 	LEFT JOIN TIE026D_IE_ORDEN_SERVICIOS OSE ON (OSE.ID_LOTE_ODS = LODS.ID_LOTE_ODS)"
						+ " WHERE OSE.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION"
						+ "   AND TRUNC(ose.FH_CITA) BETWEEN TO_DATE('" + fechaInicio + "','dd/MM/yyyy')  AND TO_DATE('"
						+ fechaFin + "','dd/MM/yyyy')" + ") AS nuProgramado");
				break;
			case NOPROGRAMADA:
				consulta.append("(SELECT	COUNT (*)" + " FROM  TIE026D_IE_ORDEN_SERVICIOS OSE"
						+ "	WHERE OSE.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION AND OSE.ID_LOTE_ODS IS NULL"
						+ "   AND TRUNC(ose.FH_CITA) BETWEEN TO_DATE('" + fechaInicio + "','dd/MM/yyyy')  AND TO_DATE('"
						+ fechaFin + "','dd/MM/yyyy')" + ") AS nuNoProgramado");
				break;
			case INCIDENCIA:
				consulta.append("(SELECT count(*) " + "  FROM TIE058D_IE_ODS_INCIDENCIA incidencia"
						+ "   INNER JOIN TIE026D_IE_ORDEN_SERVICIOS OS ON (incidencia.ID_ORDEN_SERVICIO  = os.ID_ORDEN_SERVICIO)"
						+ "   WHERE TRUNC(OS.FH_CITA) BETWEEN TO_DATE('" + fechaInicio
						+ "','dd/MM/yyyy')  AND TO_DATE('" + fechaFin + "','dd/MM/yyyy')" + ")AS nuIncidencias");
				break;
			}

			if (x + 1 < largo) {
				consulta.append(", ");
			}
		}
		if (colOmitidas != null && !colOmitidas.isEmpty()) {
			int largoOmitidas = colOmitidas.size();
			consulta.append(", ");

			for (int x = 0; x < largoOmitidas; x++) {
				switch (colOmitidas.get(x)) {
				case ENCURSO:
					consulta.append("(0)AS nuEnCurso");
					break;

				case COMPLETA:
					consulta.append("(0)AS nuCompleta");
					break;

				case PROGRAMADA:
					consulta.append("(0)AS nuProgramado");
					break;

				case NOPROGRAMADA:
					consulta.append("(0)AS nuNoProgramado");
					break;

				case INCIDENCIA:
					consulta.append("(0)AS nuIncidencias");
					break;
				}

				if (x + 1 < largoOmitidas) {
					consulta.append(", ");
				}
			}
		}
		return consulta;
	}

	@Transactional
	@Override
	public ProcesosOrdenServicioDetalleVO getDetalleByEtapas(Long idOrdenServicio) {
		ProcesosOrdenServicioDetalleVO respuesta = new ProcesosOrdenServicioDetalleVO();
		// consultar OS
		OrdenServicioDTO OrdenServicioDTO = ordenServicioDAO.findOne(idOrdenServicio);
		if (OrdenServicioDTO != null) {
			respuesta.setIdOrdenServicio(idOrdenServicio);
			respuesta.setCdOrdenServicio(OrdenServicioDTO.getCdOrdenServicio());
			respuesta.setNbModuloAtencion(OrdenServicioDTO.getCentroInstalacion().getNbCentroInstalacion());
			respuesta.setNbPlan(OrdenServicioDTO.getPlan().getTxPlan());
			respuesta.setNbKit(OrdenServicioDTO.getKitInstalacion().getCdKitInstalacion());
			respuesta.setFinicio(OrdenServicioDTO.getFhAtencionIni());
			respuesta.setFfin(OrdenServicioDTO.getFhAtencionFin());
			respuesta.setEstatus(OrdenServicioDTO.getStSeguimiento().getNbStSeguimiento());
			respuesta.setNuPorcentaje(0.0);
			List<ProcesoDetalleVO> etapas = planProcesoDAO.getEtapasParaSeguimiento(idOrdenServicio);
			if (!etapas.isEmpty()) {
				//Consultar avance de encuestas del proceso 
				for(ProcesoDetalleVO etapa: etapas) {
					etapa.setEncuestas(procesoEncuestaDAO.getDetalleEncuestaParaNodos(idOrdenServicio, etapa.getIdProceso()));
				}
				respuesta.setProcesos(etapas);
			}
		}

		return respuesta;
	}

	@Transactional
	@Override
	public List<EncuestaDetalleVO> getDetalleProcesos(Long idOrdenServicio, List<Long> idProceso) {
		List<EncuestaDetalleVO> respuesta = procesoEncuestaDAO.getDetalleEncuesta(idOrdenServicio, idProceso);
		return respuesta;
	}

	@Transactional
	@Override
	public List<PreguntasDetalleVO> getDetallePregunta(Long idOrdenServicio, Long idEncuesta) {
		List<PreguntasDetalleVO> respuesta = procesoEncuestaDAO.getSeguimientoDetallePregunta(idOrdenServicio,
				idEncuesta);
		return respuesta;
	}

	@Transactional
	@Override
	public List<ImagenVO> getDetalleImagenByNivel(Long idOrdenServicio, Long valor, String nivel, String clase) {
		List<ImagenVO> respuesta = new ArrayList<ImagenVO>();
		String txtTipo = ""; 
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " + 
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG WHERE ID_ORDEN_SERVICIO="+idOrdenServicio +"AND ST_ACTIVO=1 ");
		
		if(clase.equals("evidencia")) {
			txtTipo = " AND ID_INCIDENCIA IS NULL ORDER BY NU_ORDEN ASC";
		}else {
			txtTipo = " AND ID_INCIDENCIA IS NOT NULL ORDER BY NU_ORDEN ASC";
		}

		switch(nivel) {		
		case "ordenservicio":
			consulta.append(" AND ID_PROCESO IS NULL AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL");
			consulta.append(txtTipo);
			respuesta = expedienteImgDAO.getImagenPorNivel(consulta);
			break;
			
		case "proceso":
			consulta.append(" AND ID_PROCESO="+valor+ " AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL");
			consulta.append(txtTipo);
			respuesta = expedienteImgDAO.getImagenPorNivel(consulta);
			break;
			
		case "encuesta":
			consulta.append("AND ID_ODS_ENCUESTA="+ valor +" AND ID_PREGUNTA IS NULL");
			consulta.append(txtTipo);
			respuesta = expedienteImgDAO.getImagenPorNivel(consulta);
			break;
			
		case "pregunta":
			consulta.append("AND ID_PREGUNTA="+valor);
			consulta.append(txtTipo);
			respuesta = expedienteImgDAO.getImagenPorNivel(consulta);
			break;
			
		case "incidencia":
			consulta.append(" AND ID_INCIDENCIA="+valor);
			respuesta = expedienteImgDAO.getImagenPorNivel(consulta);
			break;
			
			
		}
		
		return respuesta;
	}

	@Transactional
	@Override
	public List<DetalleIncidenciaVO> getDetalleSeguimientoIncidencia(Long idOrdenServicio) {
		List<DetalleIncidenciaVO> respuesta = incidenciaDAO.getDetalleIncidencia(idOrdenServicio);
		return respuesta;
	}


	@Override
	public ByteArrayOutputStream getReporteSeguimientoOs(ReporteExcelVO listaObj) {
		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
		
		if(listaObj.getNivel().equals("general")){
			respuesta = rptGeneral.generarReporte(listaObj.getNivelGeneral(), listaObj.getColumnas(), listaObj.getFechaInicio(), listaObj.getFechaFin());
		}else if(listaObj.getNivel().equals("detalle")) {
			respuesta = rptDetalle.generarReporte(listaObj.getNivelDetalle(), listaObj.getCentroInstalacion(), listaObj.getFechaInicio(), listaObj.getFechaFin());
		}else if(listaObj.getNivel().equals("incidencia")) {
			respuesta = rptIncidencia.generarReporte(listaObj.getNivelIncidencia(), listaObj.getCentroInstalacion(),listaObj.getFechaInicio(), listaObj.getFechaFin());
		}
		return respuesta;
	}

	
	@Override
	@Transactional
	public String hacerCorteDiario(String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
