package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ConsecionarioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;

@Repository
public class ConcesionariaDAOImpl extends BaseDaoHibernate <ConsecionarioDTO> implements ConcesionariaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsecionarioDTO> getConcesionariaAll() {
		Criteria c = getCurrentSession().createCriteria(ConsecionarioDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("nuOrden"));
		return (List<ConsecionarioDTO>) c.list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsecionarioDTO> obtenerConsecionarioVisible(Long value) {
		Criteria c = getCurrentSession().createCriteria(ConsecionarioDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stConcesion", value));
		return (List<ConsecionarioDTO>) c.list();
	}

}
