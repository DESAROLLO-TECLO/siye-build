package mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.ExpedientesImgDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ImagenVO;

@Repository
public class ExpedienteImgDAOImpl extends BaseDaoHibernate<ExpedientesImgDTO> implements ExpedienteImgDAO {
	
	private static Boolean ACTIVO = true;

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedientesImgDTO> getAllExpedientesDTO(Long idOrdenServicio) {
		Criteria c= getCurrentSession().createCriteria(ExpedientesImgDTO.class);
		c.createAlias("idOrdenServicio", "OrdenServicioDTO");
		c.add(Restrictions.eq("OrdenServicioDTO.idOrdenServicio", idOrdenServicio));
		c.add(Restrictions.eq("stActivo", ACTIVO));
		c.addOrder(Order.desc("nuOrden"));
		return (List<ExpedientesImgDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getAllExpedientesImgVO(List<Long> idOrdenServicio) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " +
				"ID_INCIDENCIA AS idIncidencia,"+
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente"+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO IN (:idOrdenServicio) AND ST_ACTIVO =1 ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idIncidencia",LongType.INSTANCE)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameterList("idOrdenServicio", idOrdenServicio)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImagenVO> getImgByProceso(Long OrdenServicio, Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " + 
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente"+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO =:OrdenServicio AND ID_PROCESO =:idProceso AND ST_ACTIVO=1 AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL "+
				"ORDER BY NU_ORDEN ASC");
		List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setParameter("idProceso", idProceso)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImagenVO> getImgByEncuesta(Long OrdenServicio, Long idEncuesta) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " +
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio AND ID_ODS_ENCUESTA=:idEncuesta AND ID_PREGUNTA IS NULL AND ST_ACTIVO=1 "+
				"ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setParameter("idEncuesta", idEncuesta)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImagenVO> getImgByPregunta(Long OrdenServicio, Long idPregunta) {
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " +
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio AND ID_PREGUNTA=:idPregunta AND ST_ACTIVO=1 "+
				"ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setParameter("idPregunta", idPregunta)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenVO> getImgByOrdenServicio(Long OrdenServicio) {//AND ID_PROCESO_ENCUESTA=NULL
		StringBuilder consulta = new StringBuilder("SELECT ID_EXPEDIENTE_ODS AS idExpedienteODS," + 
				"ID_ORDEN_SERVICIO AS idOrdenServicio," + 
				"ID_ODS_ENCUESTA AS idOdsEncuesta,"+				
				"ID_PROCESO  AS idProceso," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS, " + 
				"ID_TIPO_EXPEDIENTE AS idTipoExpediente "+
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_ORDEN_SERVICIO=:OrdenServicio  AND ST_ACTIVO=1 AND ID_PROCESO IS NULL AND ID_ODS_ENCUESTA IS NULL AND ID_PREGUNTA IS NULL " +
				"ORDER BY NU_ORDEN ASC");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOdsEncuesta", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProceso", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("cdTipoArchivo", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .addScalar("idTipoExpediente",LongType.INSTANCE)
				 .setParameter("OrdenServicio", OrdenServicio)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

}
