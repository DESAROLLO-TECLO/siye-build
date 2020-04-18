package mx.com.teclo.siye.persistencia.hibernate.dao.procesoencuesta;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.EncuestaDetalleDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesoencuesta.ProcesoEncuestaDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelEncuestaVO;

@Repository
public class ProcesoEncuestaDAOImpl extends BaseDaoHibernate<ProcesoEncuestaDTO> implements ProcesoEncuestaDAO {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcesoEncuestaDTO> obtenerEncuestasProceso(Long idProceso) {
		Criteria c = getCurrentSession().createCriteria(ProcesoEncuestaDTO.class);
		c.createAlias("idProceso", "proceso");
		c.add(Restrictions.eq("proceso.idProceso", idProceso));
		c.addOrder(Order.asc("nuOrden"));
		c.add(Restrictions.eq("stActivo", true));
		return (List<ProcesoEncuestaDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteNivelEncuestaVO> getEncuestasByProcesoVO(Long idProceso) {
		StringBuilder consulta = new StringBuilder("SELECT encuesta.ID_ENCUESTA  AS idEncuesta, encuesta.NB_ENCUESTA_ORIGEN AS cdEncuesta, encuesta.NU_MAX_IMAGENES AS nuMaxImg " + 
				"FROM TIE037D_IE_PROCESO_ENCUESTA pE" + 
				" INNER JOIN TIE001D_EE_ENCUESTAS encuesta ON (pE.ID_ENCUESTA = encuesta.ID_ENCUESTA)" + 
				"  WHERE encuesta.ST_ACTIVO = 1 AND pE.ID_PROCESO ="+idProceso +"ORDER BY encuesta.NU_ORDEN ASC");
		List<ExpedienteNivelEncuestaVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idEncuesta",LongType.INSTANCE)
				.addScalar("cdEncuesta",StringType.INSTANCE)
				.addScalar("nuMaxImg", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ExpedienteNivelEncuestaVO.class)).list();
		return respuesta;
	}
	
	@Override
	public List<EncuestaDetalleDTO> getEncuestaByIdOrden(Long idProceso) {
		String hql = "SELECT  enc "
				+ "FROM EncuestaDetalleDTO as enc, "
				+ "ProcesoEncuestaDTO as proenc "
				+ "WHERE enc.idEncuesta = proenc.idEncuesta.idEncuesta "
				+ "and proenc.idProceso.idProceso=:idProceso "
				+ "and enc.stActivo=1";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idProceso", idProceso);
		return query.list();	
	}

}
