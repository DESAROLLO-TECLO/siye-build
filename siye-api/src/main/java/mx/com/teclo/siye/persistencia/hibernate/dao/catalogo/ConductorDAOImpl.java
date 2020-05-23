package mx.com.teclo.siye.persistencia.hibernate.dao.catalogo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.ConductorDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.seguimientoOs.OrdenServcioDetalleVO;

@Repository
public class ConductorDAOImpl extends BaseDaoHibernate<ConductorDTO> implements ConductorDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConductorDTO> getTransportistas() {
		Criteria criteria = getCurrentSession().createCriteria(ConductorDTO.class);
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.addOrder(Order.asc("nuOrden"));
		
		return (List<ConductorDTO>)criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConductorVO> getTransportistasSinVehiculo() {
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT ");
		consulta.append("TIE044.ID_CONDUCTOR AS idConductor, ");
		consulta.append("TIE044.NB_CONDUCTOR AS nbConductor, ");
		consulta.append("TIE044.NB_APEPAT_CONDUCTOR AS nbApepatConductor, ");
		consulta.append("TIE044.NB_APEMAT_CONDUCTOR AS nbApematConductor, ");
		consulta.append("TIE044.ST_CONDUCTOR AS stCondutor, ");
		consulta.append("TIE044.NU_ORDEN ");
		consulta.append("FROM TIE044C_IE_CONDUCTOR TIE044 ");
		consulta.append("LEFT JOIN TIE043D_IE_VEHICULO_CONDUCTOR TIE043 ");
		consulta.append("ON TIE043.ID_CONDUCTOR = TIE044.ID_CONDUCTOR ");
		consulta.append("WHERE TIE043.ID_VEHICULO_CONDUCTOR IS NULL ORDER BY TIE044.NU_ORDEN ASC ");
		List<ConductorVO> conductorListVO = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idConductor",LongType.INSTANCE)
				.addScalar("nbConductor",StringType.INSTANCE)
				.addScalar("nbApepatConductor",StringType.INSTANCE)
				.addScalar("nbApematConductor",StringType.INSTANCE)
				.addScalar("stCondutor",LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ConductorVO.class)).list();
		return conductorListVO;
	}
	
	@Override
	public List<ConductorDTO> getConductorXNombre(String nombre, String aPaterno, String aMaterno) {
		Criteria criteria = getCurrentSession().createCriteria(ConductorDTO.class);
		criteria.add(Restrictions.eq("nbConductor", nombre));
		criteria.add(Restrictions.eq("nbApepatConductor", aPaterno));
		criteria.add(Restrictions.eq("nbApematConductor", aMaterno));
		criteria.add(Restrictions.eq("stActivo", true));
		
		return (List<ConductorDTO>)criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConductorDTO> obtenerConductorVisible(Long value) {
		Criteria c = getCurrentSession().createCriteria(ConductorDTO.class);
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("stCondutor", value));
		return (List<ConductorDTO>) c.list();
	}
}
