package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;

@Repository
public class PlanProcesoDAOImpl extends BaseDaoHibernate<PlanProcesoDTO>
		implements
			PlanProcesoDAO {

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
	public void delete(PlanProcesoDTO arg0) {
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
	public List<PlanProcesoDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanProcesoDTO> findAll(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanProcesoDTO findOne(Serializable arg0) {
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
	public Serializable save(PlanProcesoDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanProcesoDTO saveOrUpdate(PlanProcesoDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanProcesoDTO saveOrUpdateLastId(PlanProcesoDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanProcesoDTO update(PlanProcesoDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanProcesoDTO> obtenerPorcesosPlan(Long idPlan) {
		Criteria c = getCurrentSession().createCriteria(PlanProcesoDTO.class);
		c.createAlias("plan", "plan");
		c.add(Restrictions.eq("plan.idPlan", idPlan));
		c.add(Restrictions.eq("stActivo", true));
		return (List<PlanProcesoDTO>) c.list();
	}

}
