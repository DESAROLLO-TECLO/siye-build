package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ProveedorDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ConsecionarioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConcesionariaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

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


	@SuppressWarnings("unchecked")
	@Override
	public List<ConcesionariaVO> getCatConcesionaria() {		
		Criteria c = getCurrentSession().createCriteria(ConsecionarioDTO.class, "concesionaria");
		c.add(Restrictions.eq("concesionaria.stActivo", true));
		c.setProjection(
				Projections.projectionList()
						.add(Projections.property("concesionaria.idConsecion").as("idConsecion"))
						.add(Projections.property("concesionaria.cdConsecion").as("cdConsecion"))
						.add(Projections.property("concesionaria.nbConsecion").as("nbConsecion")));
		c.addOrder(Order.asc("concesionaria.nuOrden"));		
		c.setResultTransformer(Transformers.aliasToBean(ConcesionariaVO.class));		
		return (List<ConcesionariaVO>) c.list();
	}

}
