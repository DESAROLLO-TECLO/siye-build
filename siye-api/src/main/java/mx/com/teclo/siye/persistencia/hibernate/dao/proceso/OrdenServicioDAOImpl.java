package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.monitoreo.OrdenServicioDetVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServcioDetalleVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

@Repository
public class OrdenServicioDAOImpl extends BaseDaoHibernate<OrdenServicioDTO> implements OrdenServicioDAO{

	@Override
	public OrdenServicioDTO obtenerOrdenServicio(Long idOrdenServicio) {
		Criteria c = getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idOrdenServicio", idOrdenServicio));
		return (OrdenServicioDTO) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByPlaca(String valor, Long idCentroInstalacion) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("vehiculo", "vehiculo");
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.add(Restrictions.sqlRestriction("trunc(FH_CITA) = trunc(?)", new Date(), org.hibernate.type.StandardBasicTypes.DATE));
		c.add(Restrictions.eq("vehiculo.cdPlacaVehiculo", valor));
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", idCentroInstalacion));
		c.add(Restrictions.eq("stActivo", true));
		return (List<OrdenServicioDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByOrdenServicio(String valor, Long idCentroInstalacion) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.add(Restrictions.sqlRestriction("trunc(FH_CITA) = trunc(?)", new Date(), org.hibernate.type.StandardBasicTypes.DATE));
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", idCentroInstalacion));
		c.add(Restrictions.eq("cdOrdenServicio", valor));
		c.add(Restrictions.eq("stActivo", true));
		return (List<OrdenServicioDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByVin(String valor, Long idCentroInstalacion) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("vehiculo", "vehiculo");
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.add(Restrictions.sqlRestriction("trunc(FH_CITA) = trunc(?)", new Date(), org.hibernate.type.StandardBasicTypes.DATE));
		c.add(Restrictions.eq("vehiculo.cdVin", valor));
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", idCentroInstalacion));
		c.add(Restrictions.eq("stActivo", true));
		return (List<OrdenServicioDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByFhCita(Long idCentroInstalacion) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("centroInstalacion", "centroInstalacion");
//		c.add(Restrictions.eq("fhCita", new Date()));
		c.add(Restrictions.sqlRestriction("trunc(FH_CITA) = trunc(?)", new Date(), org.hibernate.type.StandardBasicTypes.DATE));
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", idCentroInstalacion));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("fhCita"));
		c.addOrder(Order.asc("proceso"));
		return (List<OrdenServicioDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByPlaca(String valor) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("vehiculo", "vehiculo");
		c.add(Restrictions.eq("vehiculo.cdPlacaVehiculo", valor));
		c.add(Restrictions.eq("vehiculo.stActivo", true));
		c.add(Restrictions.sqlRestriction("trunc(FH_CITA) = trunc(?)", new Date(), org.hibernate.type.StandardBasicTypes.DATE));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("fhCita"));
		c.addOrder(Order.asc("proceso"));
		return (List<OrdenServicioDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByOrdenServicio(String valor) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.add(Restrictions.eq("cdOrdenServicio", valor));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("fhCita"));
		c.addOrder(Order.asc("proceso"));
		return (List<OrdenServicioDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> consultaOrdenByVin(String valor) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("vehiculo", "vehiculo");
		c.add(Restrictions.eq("vehiculo.cdVin", valor));
		c.add(Restrictions.eq("vehiculo.stActivo", true));
		c.add(Restrictions.sqlRestriction("trunc(FH_CITA) = trunc(?)", new Date(), org.hibernate.type.StandardBasicTypes.DATE));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("fhCita"));
		c.addOrder(Order.asc("proceso"));
		return (List<OrdenServicioDTO>)c.list();
	}
	
	
	@Override
	public OrdenServicioDTO obtenerOrdenServicioCD_ORDEN_SERVICIO(String  cdOrdenServicio) {
		Criteria c = getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("cdOrdenServicio", cdOrdenServicio));
		return (OrdenServicioDTO) c.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrdenServcioDetalleVO> getDetalleOS(Long idCentroInstalacion, String fechaInicio, String fechaFin) {
		StringBuilder consulta = new StringBuilder("SELECT" + 
				"   OS.ID_ORDEN_SERVICIO AS idOrdenServicio, "+
				"	OS.CD_ORDEN_SERVICIO AS nuOrdenServicio," + 
				"	OS.FH_ATENCION_INI AS fechaAtencion," + 
				"	CONC.NB_CONCESION AS txTransportista," + 
				"	V.CD_PLACA_VEHICULO AS txPlaca," + 
				"	CO.NB_CONDUCTOR ||' ' || CO.NB_APEPAT_CONDUCTOR || ' ' || CO.NB_APEMAT_CONDUCTOR AS txtRepresentante," + 
				"	US.NB_USUARIO||' ' ||US.NB_APATERNO|| ' ' ||US.NB_AMATERNO AS txSupervisor," + 
				"	INST.NB_PERSONA||' '|| INST.NB_PAT_PERSONA || ' ' || INST.NB_MAT_PERSONA AS txTecnicoInstalacion," + 
				"	PR.NB_PROCESO AS txProceso," + 
				"	ENC.NB_ENCUESTA AS txEtapa," + 
				"	(SELECT COUNT(*) FROM TIE058D_IE_ODS_INCIDENCIA WHERE ID_ORDEN_SERVICIO = OS.ID_ORDEN_SERVICIO) AS nuIncidencia," + 
				"	SEG.NB_ST_SEGUIMIENTO AS estado " + 
				"FROM" + 
				"	TIE026D_IE_ORDEN_SERVICIOS OS" + 
				" LEFT JOIN TIE029C_IE_CENTROS_INSTALACION CI ON	(CI.ID_CENTRO_INSTALACION = OS.ID_CENTRO_INSTALACION)" + 
				" LEFT JOIN TIE027C_IE_VEHICULO V ON	(V.ID_VEHICULO = OS.ID_VEHICULO)" + 
				" LEFT JOIN TIE043D_IE_VEHICULO_CONDUCTOR VC ON	(VC.ID_VEHICULO = V.ID_VEHICULO)" + 
				" LEFT JOIN TIE040B_IE_ST_USU_ENCU_INTEN EI ON	(EI.ID_VEHICULO_CONDUCTOR = VC.ID_VEHICULO_CONDUCTOR)" + 
				" LEFT JOIN TIE046D_IE_GERENTE_SUPERVISOR GS ON	(GS.ID_GERENTE_SUPERVISOR = EI.ID_GERENTE_SUPERVISOR)" + 
				" LEFT JOIN TAQ025C_SE_USUARIOS US ON	(US.ID_USUARIO = GS.ID_GERENTE)" + 
				" LEFT JOIN TIE045C_IE_PERSONA INST ON	(INST.ID_PERSONA = EI.ID_RH_INSTALADOR)" + 
				" LEFT JOIN TIE058D_IE_ODS_INCIDENCIA ODSINC ON	(ODSINC.ID_ORDEN_SERVICIO = OS.ID_ORDEN_SERVICIO)" + 
				" LEFT JOIN TIE035C_IE_PROCESOS PR ON	(PR.ID_PROCESO = OS.ID_PROCESO_ACTUAL)" + 
				" LEFT JOIN TIE037D_IE_PROCESO_ENCUESTA PE ON	(PE.ID_PROCESO =  PR.ID_PROCESO )" + 
				" LEFT JOIN TIE001D_EE_ENCUESTAS ENC ON	(PE.ID_ENCUESTA = ENC.ID_ENCUESTA)" + 
				" LEFT JOIN TIE048C_IE_ST_SEGUIMIENTO SEG ON	(SEG.ID_ST_SEGUIMIENTO = OS.ID_ST_SEGUIMIENTO)" + 
				" LEFT JOIN TIE044C_IE_CONDUCTOR CO ON	(CO.ID_CONDUCTOR = VC.ID_CONDUCTOR)" + 
				" LEFT JOIN TIE052C_IE_CONCESIONES CONC ON (CONC.ID_CONCESION = V.ID_CONCESION) " + 
				"WHERE CI.ID_CENTRO_INSTALACION =:idCentroInstalacion" + 
				"	AND TRUNC(OS.FH_CITA) BETWEEN TO_DATE('"+fechaInicio+"','dd/MM/yyyy')  AND TO_DATE('"+ fechaFin+"','dd/MM/yyyy')" + 
				" ORDER BY OS.CD_ORDEN_SERVICIO, OS.FH_CITA DESC");		
		List<OrdenServcioDetalleVO> detalleEntrada = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idOrdenServicio", LongType.INSTANCE)
				.addScalar("nuOrdenServicio",StringType.INSTANCE)
				.addScalar("fechaAtencion",StringType.INSTANCE)
				.addScalar("txTransportista",StringType.INSTANCE)
				.addScalar("txPlaca",StringType.INSTANCE)
				.addScalar("txtRepresentante",StringType.INSTANCE)
				.addScalar("txSupervisor",StringType.INSTANCE)
				.addScalar("txTecnicoInstalacion",StringType.INSTANCE)
				.addScalar("txProceso",StringType.INSTANCE)
				.addScalar("txEtapa",StringType.INSTANCE)
				.addScalar("nuIncidencia",LongType.INSTANCE)
				.addScalar("estado",StringType.INSTANCE)
				.setParameter("idCentroInstalacion", idCentroInstalacion)
				.setResultTransformer(Transformers.aliasToBean(OrdenServcioDetalleVO.class)).list();
		return detalleEntrada;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SeguimientoOrdenServicioVO> getInfoSeguimientoGeneral(StringBuilder consulta, List<Long> CentroInstalacion, List<String> columnas) {
		consulta.append(" FROM TIE029C_IE_CENTROS_INSTALACION CI WHERE ci.ID_CENTRO_INSTALACION IN (:CentroInstalacion) ");
		List<SeguimientoOrdenServicioVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idCentroInstalacion", LongType.INSTANCE)
				.addScalar("nbModulo", StringType.INSTANCE)
				.addScalar("nuEnCurso", LongType.INSTANCE)
				.addScalar("nuCompleta", LongType.INSTANCE)
				.addScalar("nuProgramado", LongType.INSTANCE)
				.addScalar("nuNoProgramado", LongType.INSTANCE)
				.addScalar("nuIncidencias", LongType.INSTANCE)				
				.setParameterList("CentroInstalacion", CentroInstalacion)
				.setResultTransformer(Transformers.aliasToBean(SeguimientoOrdenServicioVO.class)).list();
		return respuesta;
	}

	@Override
	public OrdenServicioDetVO getOrdenServicioByIdOrden(Long idOrdenServicio) {
	
		String hql = "SELECT  os.idOrdenServicio as idOrdenServicio,os.cdOrdenServicio as cdOrdenServicio,os.stSeguimiento.nbStSeguimiento as nbStSeguimiento,"
				+ "os.proceso.cdProceso as cdProcesoActual,os.proceso.nbProceso as nbProcesoActual,os.encuesta.cdEncuesta as cdEncuestaActual,"
				+ "os.encuesta.nbEncuesta as nbEncuestaActual,os.fhCita as fhCita,os.fhAtencionIni as fhAtencionIni,os.fhAtencionFin as fhAtencionFin "
				+ "FROM OrdenServicioDTO as os "
				+ "WHERE os.idOrdenServicio=:idOrdenServicio "
				+ "AND os.stActivo=1";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idOrdenServicio", idOrdenServicio).setResultTransformer(Transformers.aliasToBean(OrdenServicioDetVO.class));
		return (OrdenServicioDetVO) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> todos(Long idCentroInstalacion,Integer numMostrar) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", idCentroInstalacion));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.desc("fhCita"));
		c.addOrder(Order.desc("proceso"));
        c.setMaxResults(numMostrar);
		return (List<OrdenServicioDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> getOrdenServicioByLote(String valor,Long centroInstalacion) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.createAlias("loteOrdenServicio", "lote");
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", centroInstalacion));
		c.add(Restrictions.eq("lote.nbLoteOds", valor));
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idOrigenOds",1));
		c.addOrder(Order.desc("fhCita"));
		c.addOrder(Order.desc("proceso"));
		return (List<OrdenServicioDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicioDTO> getOrdenServicioByIncidecnia(String valor,Long centroInstalacion) {
		Criteria c= getCurrentSession().createCriteria(OrdenServicioDTO.class);
		c.createAlias("centroInstalacion", "centroInstalacion");
		c.add(Restrictions.eq("centroInstalacion.idCentroInstalacion", centroInstalacion));
		c.add(Restrictions.eq("cdOrdenServicio", valor));
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idOrigenOds",2));
		c.addOrder(Order.desc("fhCita"));
		c.addOrder(Order.desc("proceso"));
		return (List<OrdenServicioDTO>)c.list();
	}



}
