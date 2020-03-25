package mx.com.teclo.siye.persistencia.hibernate.dao.proceso;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.KitInstalacionVO;

@Repository
public class KitInstalacionDAOImpl extends BaseDaoHibernate<KitInstalacionDTO> implements KitInstalacionDAO {

	@Override
	public KitInstalacionVO obtenerKitInstalacion(Long idKitInstalacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
