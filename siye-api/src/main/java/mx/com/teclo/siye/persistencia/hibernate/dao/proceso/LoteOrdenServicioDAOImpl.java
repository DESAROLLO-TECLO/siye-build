package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.LoteOrdenServicioVO;

@Repository
public class LoteOrdenServicioDAOImpl extends BaseDaoHibernate<LoteOrdenServicioDTO> implements LoteOrdenServicioDAO {

	@Override
	public LoteOrdenServicioVO obtenerLote(Long idLote) {
		// TODO Auto-generated method stub
		return null;
	}

}
