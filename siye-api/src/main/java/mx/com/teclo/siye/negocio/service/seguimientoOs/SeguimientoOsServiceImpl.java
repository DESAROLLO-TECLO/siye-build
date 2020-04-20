package mx.com.teclo.siye.negocio.service.seguimientoOs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg.ExpedienteImgDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanProcesoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta.ProcesoEncuestaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.EncuestaDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServcioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServicioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.ProcesoDetalleVO;
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
	

	private final String ENCURSO = "EN_CURSO", COMPLETA = "COMPLETADAS", PROGRAMADA = "PROGRAMADA",
						 NOPROGRAMADA = "NO_PROGRAMADA", INCIDENCIA = "INCIDENCIAS";

	@Transactional
	@Override
	public List<SeguimientoOrdenServicioVO> getSeguimientoOrdenServicio(Long idSupervisor, List<String> columnas,List<String> colOmitidas, String fInicio, String fFin) {
		List<SeguimientoOrdenServicioVO> CentroInstalacion = new ArrayList<SeguimientoOrdenServicioVO>();
		// Consultar Centro Instalacion adminsitrados 
		List<Long> idCentroInstalacion = gerenteSupervisorDAO.getIdCentroInstalacion(idSupervisor);
		
		if(!idCentroInstalacion.isEmpty()) {
			// Consultar informacion de seguimiento de OS , por cantidades, dependiendo las columnas enviadas desde front
			StringBuilder consulta = generarConsulta(columnas, colOmitidas, fInicio, fFin);
			CentroInstalacion = ordenServicioDAO.getInfoSeguimientoGeneral(consulta, idCentroInstalacion, columnas);
			if(!CentroInstalacion.isEmpty()) {
				for(SeguimientoOrdenServicioVO ci : CentroInstalacion) {
					List<OrdenServcioDetalleVO> detalleOS = ordenServicioDAO.getDetalleOS(ci.getIdCentroInstalacion(), fInicio, fFin);
					if(!detalleOS.isEmpty()) {
						ci.setDetalleOrdenServicio(detalleOS);
					}
				}
			}			
		}	
		return CentroInstalacion;
	};

	public StringBuilder generarConsulta(List<String> columnas,List<String> colOmitidas, String fechaInicio, String fechaFin) {
		int largo = columnas.size();
		StringBuilder consulta = new StringBuilder("SELECT CI.ID_CENTRO_INSTALACION AS idCentroInstalacion," +
														  "CI.NB_CENTRO_INSTALACION AS nbModulo, ");	
		
		for(int x=0; x<largo; x++) {
			switch (columnas.get(x)) {
			case ENCURSO:
				consulta.append("(SELECT COUNT(OS2.ID_ST_SEGUIMIENTO)" + 
						" FROM TIE026D_IE_ORDEN_SERVICIOS OS2" + 
						"  LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION)" + 
						"  LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO)" + 
						"WHERE SEG.CD_ST_SEGUIMIENTO != 'FINALIZADO' AND SEG.CD_ST_SEGUIMIENTO != 'FIN_INCIDENCIA'" + 
						"	AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION AND OS2.ST_ACTIVO = 1" + 
						"	AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE('"+fechaInicio+"','dd/MM/yyyy')  AND TO_DATE('"+fechaFin+"','dd/MM/yyyy')" + 
						") AS nuEnCurso");
				break;
			case COMPLETA:
				consulta.append("(SELECT" + 
						" COUNT(OS2.ID_ST_SEGUIMIENTO)" + 
						"  FROM TIE026D_IE_ORDEN_SERVICIOS OS2" + 
						"    LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION)" + 
						"	 LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO)" + 
						"  WHERE SEG.CD_ST_SEGUIMIENTO = 'FINALIZADO'" + 
						"			  AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION AND OS2.ST_ACTIVO = 1" + 
						"			   AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE('"+fechaInicio+"','dd/MM/yyyy')  AND TO_DATE('"+fechaFin+"','dd/MM/yyyy')" + 
						") AS nuCompleta");
				break;
			case PROGRAMADA:
				consulta.append("(SELECT	count(*)" + 
						" FROM TIE025D_IE_LOTE_ODS LODS" + 
						" 	LEFT JOIN TIE026D_IE_ORDEN_SERVICIOS OSE ON (OSE.ID_LOTE_ODS = LODS.ID_LOTE_ODS)" + 
						" WHERE OSE.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION" + 
						"   AND TRUNC(ose.FH_CITA) BETWEEN TO_DATE('"+fechaInicio+"','dd/MM/yyyy')  AND TO_DATE('"+fechaFin+"','dd/MM/yyyy')" + 
						") AS nuProgramado");
				break;
			case NOPROGRAMADA:
				consulta.append("(SELECT	COUNT (*)" + 
						" FROM  TIE026D_IE_ORDEN_SERVICIOS OSE" + 
						"	WHERE OSE.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION AND OSE.ID_LOTE_ODS IS NULL" + 
						"   AND TRUNC(ose.FH_CITA) BETWEEN TO_DATE('"+fechaInicio+"','dd/MM/yyyy')  AND TO_DATE('"+fechaFin+"','dd/MM/yyyy')" + 
						") AS nuNoProgramado");
				break;
			case INCIDENCIA:
				consulta.append("(SELECT count(*) " + 
						"  FROM TIE058D_IE_ODS_INCIDENCIA incidencia" + 
						"   INNER JOIN TIE026D_IE_ORDEN_SERVICIOS OS ON (incidencia.ID_ORDEN_SERVICIO  = os.ID_ORDEN_SERVICIO)" +
						"   WHERE TRUNC(OS.FH_CITA) BETWEEN TO_DATE('"+fechaInicio+"','dd/MM/yyyy')  AND TO_DATE('"+fechaFin+"','dd/MM/yyyy')" + 
						")AS nuIncidencias");
				break;
			}

			if(x+1 < largo) {
				consulta.append(", ");
			}
		}
		if(colOmitidas!=null && !colOmitidas.isEmpty()) {
			int largoOmitidas = colOmitidas.size();
			consulta.append(", ");
			
			for(int x=0; x<largoOmitidas; x++) {
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
				
				if(x+1 < largoOmitidas) {
					consulta.append(", ");
				}
			}
		}
		return consulta;
	}

	
	@Transactional
	@Override
	public OrdenServicioDetalleVO getDetalleByEtapas(Long idOrdenServicio) {
		OrdenServicioDetalleVO respuesta = new OrdenServicioDetalleVO();	
		// consultar OS 
		OrdenServicioDTO OrdenServicioDTO = ordenServicioDAO.findOne(idOrdenServicio);
		if(OrdenServicioDTO!=null) {
			respuesta.setIdOrdenServicio(idOrdenServicio);
			respuesta.setCdOrdenServicio(OrdenServicioDTO.getCdOrdenServicio());
			respuesta.setEvidencias(expedienteImgDAO.getImagenOS(idOrdenServicio, " AND ID_INCIDENCIA IS NULL ORDER BY NU_ORDEN ASC"));
			respuesta.setIncidencias(expedienteImgDAO.getImagenOS(idOrdenServicio, " AND ID_INCIDENCIA IS NOT NULL ORDER BY NU_ORDEN ASC"));
			respuesta.setEstatus(OrdenServicioDTO.getStSeguimiento().getNbStSeguimiento());
			respuesta.setNuPorcentaje(0.0);
			List<ProcesoDetalleVO> etapas = planProcesoDAO.getEtapasParaSeguimiento(OrdenServicioDTO.getPlan().getIdPlan());
			if(!etapas.isEmpty()) {
				respuesta.setProcesos(etapas);
			}
		}
		
		return respuesta;
	}

	@Transactional
	@Override
	public List<ProcesoDetalleVO> getDetalleProceso(Long idOrdenServicio, Long idProceso) {
		// Consultar el estatus del proceso de la os en especifico
		List<ProcesoDetalleVO> lstProcesos = planProcesoDAO.getDetalleProceso(idOrdenServicio, idProceso);
		
		if(!lstProcesos.isEmpty()) {
			for(ProcesoDetalleVO proceso: lstProcesos) {
				//consultar lista de imagenes de evidencia nivel PROCESO 
				proceso.setImagenes(expedienteImgDAO.getImgByProceso(idOrdenServicio, idProceso));
				
				//Consulta de encuestas y evidencias 
				List<EncuestaDetalleVO> lstEncuesta =  procesoEncuestaDAO.getDetalleEncuesta(idOrdenServicio, idProceso);
				if(!lstEncuesta.isEmpty()) {
					for(EncuestaDetalleVO encuesta: lstEncuesta) {
						// consultar evidencias a nivel de encuesta 
						encuesta.setImagenes(expedienteImgDAO.getImgByEncuesta(idOrdenServicio, encuesta.getIdEncuesta()));
					}
					proceso.setEncuestas(lstEncuesta);
				}
				
			}
		}
		return lstProcesos;
	}

}
