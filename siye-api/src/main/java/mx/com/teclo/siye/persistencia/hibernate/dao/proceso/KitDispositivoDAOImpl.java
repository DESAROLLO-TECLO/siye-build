package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitDispositivoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitDispositivoVO;
/**
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
@Repository
public class KitDispositivoDAOImpl extends BaseDaoHibernate<KitDispositivoDTO> implements KitDispositivoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<KitDispositivoVO> getListDispositivos(Long idTipoKit) {
		Criteria criteria = getCurrentSession().createCriteria(KitDispositivoDTO.class, "kitDispositivo");
		criteria.createAlias("kitDispositivo.tipoKit", "tipoKit", JoinType.INNER_JOIN);
		criteria.createAlias("kitDispositivo.dispositivo", "dispositivo", JoinType.INNER_JOIN);

		criteria.add(Restrictions.eq("kitDispositivo.idKitDispositivo", idTipoKit));
		criteria.add(Restrictions.eq("kitDispositivo.stActivo", Boolean.TRUE));

		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("kitDispositivo.idKitDispositivo").as("idKitDispositivo"))
				.add(Projections.property("tipoKit.idTipoKit").as("idTipoKit"))
				.add(Projections.property("dispositivo.idDispositivo").as("idDispositivo"))
				.add(Projections.property("dispositivo.cdDispositivo").as("cdDispositivo"))
				.add(Projections.property("dispositivo.nbMarcaDispositivo").as("nbMarcaDispositivo"))
				.add(Projections.property("tipoKit.cdTipoKit").as("cdTipoKit"))
				.add(Projections.property("tipoKit.nbTipoKit").as("nbTipoKit"))
				.add(Projections.property("tipoKit.nuDispositivoKit").as("nuDispositivoKit")));
		criteria.addOrder(Order.asc("kitDispositivo.nuOrden"));
		criteria.setResultTransformer(Transformers.aliasToBean(KitDispositivoVO.class));
		return criteria.list();
	}

}
