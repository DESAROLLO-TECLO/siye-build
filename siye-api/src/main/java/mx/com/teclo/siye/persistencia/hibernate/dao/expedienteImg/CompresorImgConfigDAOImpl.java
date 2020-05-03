package mx.com.teclo.siye.persistencia.hibernate.dao.expedienteImg;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.expedientesImg.CompresorImgConfigDTO;

@Repository
public class CompresorImgConfigDAOImpl extends BaseDaoHibernate<CompresorImgConfigDTO> implements CompresorImgConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CompresorImgConfigDTO> getAllConfoCompress() {
		Criteria c= getCurrentSession().createCriteria(CompresorImgConfigDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<CompresorImgConfigDTO>)c.list();
	}
	
}
