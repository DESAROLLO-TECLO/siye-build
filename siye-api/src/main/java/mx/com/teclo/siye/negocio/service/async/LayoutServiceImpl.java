package mx.com.teclo.siye.negocio.service.async;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.async.LayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.TipoLayoutDAO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;

	@Autowired
	private LayoutDAO layoutDAO;

	@Override
	@Transactional(readOnly = true)
	public TipoLayoutVO getLayoutVigente() {
		return tipoLayoutDAO.getLayoutVigente();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ColumnaVO> getLayout(Long idTipoLayout, String cdSeccion) {
		return layoutDAO.getLayout(idTipoLayout, cdSeccion);
	}

	@Override
	@Transactional(readOnly = true)
	public String getNbsColumnas(String tabla) {
		
		List<ColumnaVO> cols = layoutDAO.getNbsColumnas(tabla);
		if (cols == null || cols.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		
		for (ColumnaVO col : cols) {
			sb.append(",").append(col.getNbColumna());
		}
		return sb.toString().replaceFirst(",", "");
		
		
	}

}
