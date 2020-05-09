package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;

@Repository
public class LoteOrdenServicioDAOImpl extends BaseDaoHibernate<LoteOrdenServicioDTO> implements LoteOrdenServicioDAO {

	@Override
	public ArchivoLoteVO obtenerLote(Long idLote) {
		Criteria c = getCurrentSession().createCriteria(LoteOrdenServicioDTO.class, "lote");
		c.createAlias("lote.stSeguimiento", "seguimiento");
		c.createAlias("lote.tipoLayout", "tipoLayout", JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.eq("lote.idLoteOds", idLote));
		c.add(Restrictions.eq("lote.stActivo", Boolean.TRUE.booleanValue()));

		c.setProjection(Projections.projectionList().add(Projections.property("lote.idLoteOds").as("idLoteOds"))
				.add(Projections.property("lote.cdLoteOds").as("cdLoteOds"))
				.add(Projections.property("lote.nbLoteOds").as("nbLoteOds"))
				.add(Projections.property("lote.nbArchivoFinal").as("nbArchivoFinal"))
				.add(Projections.property("seguimiento.idStSeguimiento").as("idStSeguimiento"))
				.add(Projections.property("seguimiento.cdStSeguimiento").as("cdStSeguimiento"))
				.add(Projections.property("seguimiento.nbStSeguimiento").as("nbStSeguimiento"))
				.add(Projections.property("seguimiento.cdColor").as("cdColor"))
				.add(Projections.property("lote.nuOdsReportados").as("nuOdsReportados"))
				.add(Projections.property("lote.nuOdsCargados").as("nuOdsCargados"))
				.add(Projections.property("lote.nuOdsAtendidos").as("nuOdsAtendidos"))
				.add(Projections.property("lote.nuOdsPendientes").as("nuOdsPendientes"))
				.add(Projections.property("lote.nuOdsIncidencia").as("nuOdsIncidencia"))
				.add(Projections.property("tipoLayout.idTipoLayout").as("idTipoLayout"))
				.add(Projections.property("lote.txLoteOds").as("txLoteOds"))
				.add(Projections.property("lote.stActivo").as("stActivo"))
				.add(Projections.property("lote.idUsrCreacion").as("idUsrCreacion"))
				.add(Projections.property("lote.fhCreacion").as("fhCreacion")));

		c.setResultTransformer(Transformers.aliasToBean(ArchivoLoteVO.class));
		return (ArchivoLoteVO) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivoLoteVO> obtenerLotesPorFecha(Long idUserSession,Date date) {
		
		List<String> lisCdStatus=new ArrayList<>();
		lisCdStatus.add("RECIBIDO");
		lisCdStatus.add("CARGANDO");
		
		Criteria c = getCurrentSession().createCriteria(LoteOrdenServicioDTO.class, "lote");
		c.createAlias("lote.stSeguimiento", "seguimiento");
		c.createAlias("lote.tipoLayout", "tipoLayout", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("seguimiento.tipoSeguimiento", "tipoSeguimiento");
		//c.add(Restrictions.eq("lote.idUsrCreacion", idUserSession));
		//c.add(Restrictions.in("seguimiento.cdStSeguimiento", lisCdStatus));
		c.add(Restrictions.eq("tipoSeguimiento.nbTpSeguimiento", "TIE025D_IE_LOTE_ODS"));
		
		c.add(Restrictions.eq("lote.stActivo", Boolean.TRUE.booleanValue()));
		c.add(Restrictions.sqlRestriction("trunc({alias}.FH_CREACION) = ?", date,
				org.hibernate.type.StandardBasicTypes.DATE));
		c.addOrder(Order.desc("fhCreacion"));

		c.setProjection(Projections.projectionList().add(Projections.property("lote.idLoteOds").as("idLoteOds"))
				.add(Projections.property("lote.cdLoteOds").as("cdLoteOds"))
				.add(Projections.property("lote.nbLoteOds").as("nbLoteOds"))
				.add(Projections.property("lote.nbArchivoFinal").as("nbArchivoFinal"))
				.add(Projections.property("seguimiento.idStSeguimiento").as("idStSeguimiento"))
				.add(Projections.property("seguimiento.cdStSeguimiento").as("cdStSeguimiento"))
				.add(Projections.property("seguimiento.nbStSeguimiento").as("nbStSeguimiento"))
				.add(Projections.property("seguimiento.cdColor").as("cdColor"))
				.add(Projections.property("lote.nuOdsReportados").as("nuOdsReportados"))
				.add(Projections.property("lote.nuOdsCargados").as("nuOdsCargados"))
				.add(Projections.property("lote.nuOdsAtendidos").as("nuOdsAtendidos"))
				.add(Projections.property("lote.nuOdsPendientes").as("nuOdsPendientes"))
				.add(Projections.property("lote.nuOdsIncidencia").as("nuOdsIncidencia"))
				.add(Projections.property("tipoLayout.idTipoLayout").as("idTipoLayout"))
				.add(Projections.property("lote.txLoteOds").as("txLoteOds"))
				.add(Projections.property("lote.stActivo").as("stActivo"))
				.add(Projections.property("lote.idUsrCreacion").as("idUsrCreacion"))
				.add(Projections.property("lote.fhCreacion").as("fhCreacion")));

		c.setResultTransformer(Transformers.aliasToBean(ArchivoLoteVO.class));
		return (List<ArchivoLoteVO>) c.list();
	}

}
