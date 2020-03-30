package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDTO;



@Repository
public class UsuarioEncuestaDAOImpl extends BaseDaoHibernate<UsuarioEncuestaDTO> implements UsuarioEncuentaDAO {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEncuestaDTO> getEncuestasPorOrden(Long idOrden)
	{
		Criteria c = getCurrentSession().createCriteria(UsuarioEncuestaDTO.class);
		c.createAlias("usuario", "orden");
		c.add(Restrictions.eq("orden.idOrdenServicio", idOrden));
		c.add(Restrictions.eq("stActivo", true));
		return (List<UsuarioEncuestaDTO>) c.list();
	}

}
