package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitDispositivoDTO;


@Repository
public class DispositivosDAOImpl extends BaseDaoHibernate<KitDispositivoDTO> implements DispositivosDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<KitDispositivoDTO> getListDispositivos(Long idTipoKit) {
		Criteria criteria = getCurrentSession().createCriteria(KitDispositivoDTO.class);
		criteria.createAlias("tipoKit", "tipoKit");
		criteria.createAlias("dispositivo", "dispositivo");
				
		criteria.add(Restrictions.eq("dispositivo.stActivo", true));
		criteria.add(Restrictions.eq("tipoKit.idTipoKit", idTipoKit));
		
		return (List<KitDispositivoDTO>) criteria.list();
	}

	@Override
	public KitDispositivoDTO getByDispositivo(Long idDisp) {
		Criteria criteria = getCurrentSession().createCriteria(KitDispositivoDTO.class);
		criteria.createAlias("dispositivo", "dispositivo");
				
		criteria.add(Restrictions.eq("dispositivo.stActivo", true));
		criteria.add(Restrictions.eq("dispositivo.idDispositivo", idDisp));
		
		
		return (KitDispositivoDTO) criteria.uniqueResult();
	}

}
