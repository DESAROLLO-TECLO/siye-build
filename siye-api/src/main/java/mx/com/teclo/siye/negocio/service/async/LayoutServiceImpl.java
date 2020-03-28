package mx.com.teclo.siye.negocio.service.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.async.TipoLayoutDAO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;

	@Override
	@Transactional(readOnly = true)
	public TipoLayoutVO getLayoutVigente() {
		return tipoLayoutDAO.getLayoutVigente();
	}

}
