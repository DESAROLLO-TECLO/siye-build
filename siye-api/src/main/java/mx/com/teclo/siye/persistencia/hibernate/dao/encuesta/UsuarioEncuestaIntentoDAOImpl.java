package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaIntentosDTO;


@Repository
public class UsuarioEncuestaIntentoDAOImpl extends BaseDaoHibernate<UsuarioEncuestaIntentosDTO> implements UsuarioEncuentaIntentoDAO {

 
	@Override
	public UsuarioEncuestaIntentosDTO getEncuestaByUsuario(Long idEncuesta, Long idUsuario) {
		Criteria criteria = getCurrentSession().createCriteria(UsuarioEncuestaIntentosDTO.class);
		
		criteria.createAlias("usuarioEncuesta", "ue");
		criteria.createAlias("ue.encuesta", "en");
		criteria.createAlias("ue.ordenServicio", "usr");
 
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.add(Restrictions.eq("en.idEncuesta", idEncuesta));
		criteria.add(Restrictions.eq("usr.idOrdenServicio", idUsuario));
		criteria.add(Restrictions.eq("stMostrar", true));

		return (UsuarioEncuestaIntentosDTO) criteria.uniqueResult();
	}
	
	@Override
	public UsuarioEncuestaIntentosDTO buscaUsuEncuIntento(Long idUsuEncuIntento) {
		Criteria criteria = getCurrentSession().createCriteria(UsuarioEncuestaIntentosDTO.class);
		criteria.add(Restrictions.eqOrIsNull("stActivo", true));
		criteria.add(Restrictions.eq("idUsuEncuIntento", idUsuEncuIntento));
		return (UsuarioEncuestaIntentosDTO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEncuestaIntentosDTO> usuarioEncuesta(Long idUsuarioEncuesta) {
		Criteria c = getCurrentSession().createCriteria(UsuarioEncuestaIntentosDTO.class);
		c.createAlias("usuarioEncuesta", "ue");
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("ue.idUsuarioEncuesta", idUsuarioEncuesta));
		c.add(Restrictions.eq("stMostrar", true));
		return (List<UsuarioEncuestaIntentosDTO>)c.list();
	}

	@Override
	public UsuarioEncuestaIntentosDTO getIntentoById(Long idIntentoEncuesta) {
		Criteria criteria = getCurrentSession().createCriteria(UsuarioEncuestaIntentosDTO.class);
		criteria.add(Restrictions.eq("idUsuEncuIntento",idIntentoEncuesta));
  		criteria.add(Restrictions.eq("stActivo", true));

		return (UsuarioEncuestaIntentosDTO) criteria.uniqueResult();
 	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEncuestaIntentosDTO> intentoMismaEncuesta(Long idUsuarioEncuesta) {
		Criteria c = getCurrentSession().createCriteria(UsuarioEncuestaIntentosDTO.class);
		c.createAlias("usuarioEncuesta", "ue");
		c.add(Restrictions.eq("stActivo", true));
		c.add(Restrictions.eq("ue.idUsuarioEncuesta", idUsuarioEncuesta));
		
		c.addOrder(Order.desc("nuCalificacion"));
		c.addOrder(Order.desc("fhInicio"));
		
		return (List<UsuarioEncuestaIntentosDTO>)c.list();
	}





 	
}
