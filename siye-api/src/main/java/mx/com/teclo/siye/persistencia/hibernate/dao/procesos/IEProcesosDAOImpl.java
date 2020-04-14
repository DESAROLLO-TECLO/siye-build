package mx.com.teclo.siye.persistencia.hibernate.dao.procesos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.procesos.IEprocesosDTO;


@Repository
public class IEProcesosDAOImpl extends BaseDaoHibernate<IEprocesosDTO> implements IEProcesosDAO {

	@Override
	public IEprocesosDTO consultarProcesoByidProceso(Long idProceso) {
		Criteria c = getCurrentSession().createCriteria(IEprocesosDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("idProceso", idProceso));
		return (IEprocesosDTO)c.uniqueResult();
	}
}
