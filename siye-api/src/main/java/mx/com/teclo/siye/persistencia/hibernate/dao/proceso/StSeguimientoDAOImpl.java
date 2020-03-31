package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.SeguimientoVO;

@Repository
public class StSeguimientoDAOImpl extends BaseDaoHibernate<StSeguimientoDTO> implements StSeguimientoDAO {

	@Override
	public SeguimientoVO obtenerSeguimiento(Long idSeguimiento) {
		return null;
	}
	
	

}
