package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.ProcesoEncuestaDTO;

public class ProcesoEncuestaDAOImpl extends BaseDaoHibernate<ProcesoEncuestaDTO>
		implements
			ProcesoEncuestaDAO {

	@Override
	public void beginTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeSession() {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ProcesoEncuestaDTO arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Serializable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String desencriptarCampo(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encriptarCampo(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcesoEncuestaDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcesoEncuestaDTO> findAll(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcesoEncuestaDTO findOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getSequenceNextVal(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public Serializable save(ProcesoEncuestaDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcesoEncuestaDTO saveOrUpdate(ProcesoEncuestaDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcesoEncuestaDTO saveOrUpdateLastId(ProcesoEncuestaDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcesoEncuestaDTO update(ProcesoEncuestaDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcesoEncuestaDTO> obtenerEncuestasProceso(Long idProceso) {
		Criteria c = getCurrentSession().createCriteria(ProcesoEncuestaDTO.class);
		c.createAlias("proceso", "proceso");
		c.add(Restrictions.eq("proceso.idProceso", idProceso));
		c.add(Restrictions.eq("stActivo", true));
		return (List<ProcesoEncuestaDTO>) c.list();
	}

}
