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
				"ID_PROCESO_ENCUESTA  AS idProcesoEncuesta," + 
				"ID_PREGUNTA AS idPregunta," + 
				"NB_EXPEDIENTE_ODS AS nbExpedienteODS," + 
				"CD_TIPO_ARCHIVO AS cdTipoArchivo," + 
				"LB_EXPEDIENTE_ODS AS lbExpedienteODS " + 
				" FROM TIE050D_IE_EXPEDIENTES_IMG" + 
				"   WHERE ID_EXPEDIENTE_ODS IN (1)");
		 List<ImagenVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				 .addScalar("idExpedienteODS", LongType.INSTANCE)
				 .addScalar("idOrdenServicio", LongType.INSTANCE)
				 .addScalar("idProcesoEncuesta", LongType.INSTANCE)
				 .addScalar("idPregunta",LongType.INSTANCE)
				 .addScalar("nbExpedienteODS", StringType.INSTANCE)
				 .addScalar("CD_TIPO_ARCHIVO", StringType.INSTANCE)
				 .addScalar("lbExpedienteODS",StandardBasicTypes.BINARY)
				 .setResultTransformer(Transformers.aliasToBean(ImagenVO.class)).list();
		return respuesta;
	}

}
