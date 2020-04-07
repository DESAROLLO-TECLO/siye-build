package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.IERespCausaDTO;

@Repository
public class IERespCausaDAOImpl extends BaseDaoHibernate<IERespCausaDTO>
		implements
			IERespCausaDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IERespCausaDTO> obtenerResCausaAnterior(Long idUsuEncuIntento, Long idEncuesta, Long idSeccion, Long idPregunta) {
		Criteria c= getCurrentSession().createCriteria(IERespCausaDTO.class);
		c.createAlias("usuarioEncuestaIntento", "usuarioEncuestaIntento");
		c.createAlias("encuesta", "encuesta");
		c.createAlias("seccion", "seccion");
		c.createAlias("preguntas", "preguntas");
		c.add(Restrictions.eq("usuarioEncuestaIntento.idUsuEncuIntento", idUsuEncuIntento));
		c.add(Restrictions.eq("encuesta.idEncuesta", idEncuesta));
		c.add(Restrictions.eq("seccion.idSeccion", idSeccion));
		c.add(Restrictions.eq("preguntas.idPregunta", idPregunta));
		c.add(Restrictions.eq("stActivo", true));
		return (List<IERespCausaDTO>)c.list();
	}
	



}
