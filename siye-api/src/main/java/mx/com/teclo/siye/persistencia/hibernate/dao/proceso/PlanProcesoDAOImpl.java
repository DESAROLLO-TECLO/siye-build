package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanProcesoDTO;
import mx.com.teclo.siye.persistencia.vo.expedientesImg.ExpedienteNivelProcesoVO;

@Repository
public class PlanProcesoDAOImpl extends BaseDaoHibernate<PlanProcesoDTO> implements PlanProcesoDAO {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteNivelProcesoVO> getProcesosPlanVO(Long idPlan) {
		StringBuilder consulta = new StringBuilder("SELECT pp.ID_PROCESO AS idProceso, proceso.TX_PROCESO AS cdProceso, proceso.NU_MAX_IMAGENES AS nuMaxImg " + 
				"   FROM TIE036D_IE_PLAN_PROCESO pp" + 
				"     INNER JOIN TIE035C_IE_PROCESOS proceso ON (pp.ID_PROCESO  = proceso.ID_PROCESO)" + 
				"     WHERE pp.ID_PLAN ="+ idPlan +" AND pp.ST_ACTIVO =1 AND proceso.ST_ACTIVO =1 ORDER BY proceso.ID_PROCESO ASC");
		List<ExpedienteNivelProcesoVO> respuesta = getCurrentSession().createSQLQuery(consulta.toString())
				.addScalar("idProceso",LongType.INSTANCE)
				.addScalar("cdProceso",StringType.INSTANCE)
				.addScalar("nuMaxImg",LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ExpedienteNivelProcesoVO.class)).list();
		return respuesta;
	}

}
