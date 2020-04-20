package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.MonitoreoIncidenciasVO;

@Repository
public class MonitoreoIncidenciasDAOImpl extends BaseDaoHibernate<OrdenServicioDTO> implements MonitoreoIncidenciasDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitoreoIncidenciasVO> getInfoSeguimientoGeneral(String fechaInicio, String fechaFin, List<Long> idsCentroInstalacion) {
		String consulta = "";
		
		consulta = ""
		+ "SELECT "
		+ "CI.ID_CENTRO_INSTALACION AS idCentroInstalacion, "
		+ "CI.NB_CENTRO_INSTALACION AS nbModulo, "
		+ "( "
		+ "	SELECT COUNT(OS2.ID_ST_SEGUIMIENTO) "
		+ "	FROM TIE026D_IE_ORDEN_SERVICIOS OS2 "
		+ "	LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION) "
		+ "	LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO) "
		+ "	WHERE "
		+ "	CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "	AND OS2.ST_ACTIVO = 1 "
		+ "	AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy')  AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ ") AS totalOrdenes, "
		+ "( "
		+ "	SELECT	COUNT(*) " 
		+ "	FROM TIE025D_IE_LOTE_ODS LODS " 
		+ "	LEFT JOIN TIE026D_IE_ORDEN_SERVICIOS OSE ON (OSE.ID_LOTE_ODS = LODS.ID_LOTE_ODS) " 
		+ "	WHERE "
		+ "	OSE.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "	AND OSE.ST_ACTIVO = 1 "
		+ "	AND LODS.ST_ACTIVO = 1 "
		+ "	AND TRUNC(ose.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy')  AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ ") AS nuOrdenesProgramadas, "
		+ "( "
		+ "	SELECT	COUNT (*) " 
		+ "	FROM TIE026D_IE_ORDEN_SERVICIOS OSE " 
		+ "	WHERE "
		+ "	OSE.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "	AND OSE.ID_LOTE_ODS IS NULL "
		+ "	AND OSE.ST_ACTIVO = 1 "
		+ "	AND TRUNC(ose.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy') AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ ") AS nuOrdenesNoProgramadas, "
		+ "( "
		+ "	SELECT COUNT(OS2.ID_ST_SEGUIMIENTO) " 
		+ "	FROM TIE026D_IE_ORDEN_SERVICIOS OS2 "
		+ "	LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION) " 
		+ "	LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO) "
		+ "	WHERE "
		+ "	SEG.CD_ST_SEGUIMIENTO = 'CURSO' " 
		+ "	AND SEG.CD_ST_SEGUIMIENTO != 'FINALIZADO' " 
		+ "	AND SEG.CD_ST_SEGUIMIENTO != 'FIN_INCIDENCIA' " 
		+ "	AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "	AND OS2.ST_ACTIVO = 1 "
		+ "	AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy') AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ ") AS nuOrdenesEnCurso, "
		+ "( "
		+ "	SELECT COUNT(OS2.ID_ST_SEGUIMIENTO) " 
		+ "	FROM TIE026D_IE_ORDEN_SERVICIOS OS2 "
		+ "	LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION) " 
		+ "	LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO) "
		+ "	WHERE "
		+ "	SEG.CD_ST_SEGUIMIENTO = 'NUEVO' " 
		+ "	AND SEG.CD_ST_SEGUIMIENTO != 'FINALIZADO' " 
		+ "	AND SEG.CD_ST_SEGUIMIENTO != 'FIN_INCIDENCIA' " 
		+ "	AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "	AND OS2.ST_ACTIVO = 1 "
		+ "	AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy') AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ ") AS nuOrdenesPendientes, "
		+ "( "
		+ "	SELECT " 
		+ "	COUNT(OS2.ID_ST_SEGUIMIENTO) " 
		+ "	FROM TIE026D_IE_ORDEN_SERVICIOS OS2 " 
		+ "	LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI2 ON (CI2.ID_CENTRO_INSTALACION = OS2.ID_CENTRO_INSTALACION) " 
		+ "	LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON (OS2.ID_ST_SEGUIMIENTO = SEG.ID_ST_SEGUIMIENTO) "
		+ "	WHERE "
		+ "	SEG.CD_ST_SEGUIMIENTO = 'FINALIZADO' " 
		+ "	AND CI2.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "	AND OS2.ST_ACTIVO = 1 "
		+ "	AND TRUNC(OS2.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy') AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ ") AS nuOrdenesAtendidas, "
		+ "( "
		+ "	( "
		+ "		SELECT count(*) " 
		+ "		FROM TIE058D_IE_ODS_INCIDENCIA incidencia " 
		+ "		LEFT JOIN TIE026D_IE_ORDEN_SERVICIOS OS ON incidencia.ID_ORDEN_SERVICIO  = OS.ID_ORDEN_SERVICIO "
		+ "		LEFT JOIN TIE051D_IE_INCIDENCIA TIE051 ON incidencia.ID_INCIDENCIA = TIE051.ID_INCIDENCIA "
		+ "		WHERE "
		+ "		OS.ST_ACTIVO = 1 "
		+ "		AND TIE051.ST_ACTIVO = 1 " 
		+ "		AND TIE051.ID_ENCUESTA IS NOT NULL "
		+ "		AND TIE051.ID_PROCESO IS NOT NULL "
		+ "		AND OS.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "		AND TRUNC(OS.FH_CITA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy')  AND TO_DATE(#{fechaFin},'dd/MM/yyyy') " 
		+ "	) + ( "
		+ "		SELECT COUNT(*) " 
		+ "		FROM TIE051D_IE_INCIDENCIA TIE051 "
		+ "		WHERE "
		+ "		TIE051.ID_CENTRO_INSTALACION = CI.ID_CENTRO_INSTALACION " 
		+ "		AND TIE051.ST_ACTIVO = 1 "
		+ "		AND TIE051.ID_ENCUESTA IS NULL "
		+ "		AND TIE051.ID_PROCESO IS NULL "
		+ "		AND TRUNC(TIE051.FH_CREACION) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/yyyy')  AND TO_DATE(#{fechaFin},'dd/MM/yyyy') "
		+ "	) "
		+ ")AS nuIncidencias "
		+ "FROM TIE029C_IE_CENTROS_INSTALACION CI "
		+ "WHERE "
		+ "	ci.ID_CENTRO_INSTALACION IN (:idsCentroInstalacion) ";
		
		consulta = StringUtils.replace(consulta, "#{fechaInicio}", fechaInicio);
		consulta = StringUtils.replace(consulta, "#{fechaFin}", fechaFin);
		
		List<MonitoreoIncidenciasVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idCentroInstalacion", LongType.INSTANCE)
				.addScalar("nbModulo", StringType.INSTANCE)
				.addScalar("totalOrdenes", LongType.INSTANCE)
				.addScalar("nuOrdenesProgramadas", LongType.INSTANCE)
				.addScalar("nuOrdenesNoProgramadas", LongType.INSTANCE)
				.addScalar("nuOrdenesEnCurso", LongType.INSTANCE)
				.addScalar("nuOrdenesPendientes", LongType.INSTANCE)
				.addScalar("nuOrdenesAtendidas", LongType.INSTANCE)
				.addScalar("nuIncidencias", LongType.INSTANCE)				
				.setParameterList("idsCentroInstalacion", idsCentroInstalacion)
				.setResultTransformer(Transformers.aliasToBean(MonitoreoIncidenciasVO.class)).list();
		return respuesta;
	}
}
