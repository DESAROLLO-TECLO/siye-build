package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import org.hibernate.Criteria;
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
		criteria.createAlias("ue.usuario", "usr");
 
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.add(Restrictions.eq("en.idEncuesta", idEncuesta));
		criteria.add(Restrictions.eq("usr.idUsuario", idUsuario));
		criteria.add(Restrictions.eq("stMostrar", true));

		return (UsuarioEncuestaIntentosDTO) criteria.uniqueResult();
	}






 	
}
