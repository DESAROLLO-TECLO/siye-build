package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDTO;



@Repository
public class UsuarioEncuestaDAOImpl extends BaseDaoHibernate<UsuarioEncuestaDTO> implements UsuarioEncuestaDAO {
	

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEncuestaDTO> consultaOrdenByOrdenServicio(String valor){
		Criteria c= getCurrentSession().createCriteria(UsuarioEncuestaDTO.class);
		c.createAlias("usuario", "ordenServicio");
//		c.createAlias("encuesta", "encuesta");
//		c.createAlias("encuesta.tipoEncuesta", "tipoEncuesta");
		c.add(Restrictions.eq("ordenServicio.cdOrdenServicio", valor));
		c.add(Restrictions.eq("ordenServicio.stActivo", true));
//		c.add(Restrictions.eq("tipoEncuesta.idTipoEncuesta", 2));
		c.add(Restrictions.eq("stActivo", true));
		return (List<UsuarioEncuestaDTO>)c.list();
	}
}
