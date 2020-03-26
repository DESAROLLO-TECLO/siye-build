package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.InstaladorDTO;

@Repository
public class InstaladorDAOImpl extends BaseDaoHibernate<InstaladorDTO> implements InstaladorDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InstaladorDTO> getTecnicos() {
		Criteria criteria = getCurrentSession().createCriteria(InstaladorDTO.class);
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.addOrder(Order.asc("nuOrden"));
		
		return (List<InstaladorDTO>)criteria.list();
	}

	@Override
	public List<InstaladorDTO> getInstaladorXNombre(String nombre, String aPaterno, String aMaterno) {
		Criteria criteria = getCurrentSession().createCriteria(InstaladorDTO.class);
		criteria.add(Restrictions.eq("nbRhInstalador", nombre));
		criteria.add(Restrictions.eq("nbPatRhInstalador", aPaterno));
		criteria.add(Restrictions.eq("nbMatRhInstalador", aMaterno));
		criteria.add(Restrictions.eq("stActivo", true));
		
		return (List<InstaladorDTO>)criteria.list();
	}
}
