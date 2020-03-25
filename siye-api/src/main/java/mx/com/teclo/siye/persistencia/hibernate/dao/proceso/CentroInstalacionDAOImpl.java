package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

@Repository
public class CentroInstalacionDAOImpl extends BaseDaoHibernate<CentroInstalacionDTO> implements CentroInstalacionDAO {

	@Override
	public CentroInstalacionVO obtenerCentroInstalacion(Long idCentroInstalacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
