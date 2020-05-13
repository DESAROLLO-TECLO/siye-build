package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;

@Repository
public class ProveedorDAOImpl extends BaseDaoHibernate<ProveedorDTO> implements ProveedorDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorDTO> getListProveedor() {
		Criteria c = getCurrentSession().createCriteria(ProveedorDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("nuOrden"));
		return (List<ProveedorDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorDTO> obtenerProveedorVisible(Long value) {
		Criteria c = getCurrentSession().createCriteria(ProveedorDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stProveedor", value));
		return (List<ProveedorDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorVO> getCatProveedor() {	
		Criteria c = getCurrentSession().createCriteria(ProveedorDTO.class, "proveedor");
		c.add(Restrictions.eq("proveedor.stActivo", true));
		c.setProjection(
				Projections.projectionList()
						.add(Projections.property("proveedor.idProveedor").as("idProveedor"))
						.add(Projections.property("proveedor.cdProveeror").as("cdProveeror"))
						.add(Projections.property("proveedor.nbProveedor").as("nbProveedor")));
		c.addOrder(Order.asc("proveedor.nuOrden"));		
		c.setResultTransformer(Transformers.aliasToBean(ProveedorVO.class));		
		return (List<ProveedorVO>) c.list();
	}

}
