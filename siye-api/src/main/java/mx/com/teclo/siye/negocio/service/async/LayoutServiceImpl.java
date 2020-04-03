package mx.com.teclo.siye.negocio.service.async;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siye.persistencia.hibernate.dao.async.LayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.TipoLayoutDAO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
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

	@Override
	@Transactional(readOnly = true)
	public InsercionTablaVO getPatronValues(String tabla) {

		List<ColumnaVO> cols = layoutDAO.getNbsColumnas(tabla);
		if (cols == null || cols.isEmpty()) {
			return null;
		}
		InsercionTablaVO insertVO = new InsercionTablaVO();

		StringBuilder sbCols = new StringBuilder();
		StringBuilder sbVals = new StringBuilder();
		for (ColumnaVO col : cols) {
			sbCols.append(",").append(col.getNbColumna());
			sbVals.append(",")
					.append(col.getNuOrden() == null
							? StringUtils.isBlank(col.getTxValorDefecto()) ? "null" : col.getTxValorDefecto()
							: "{" + col.getNuOrden() + "}");
		}

		insertVO.setColumnas(sbCols.toString().replaceFirst(",", ""));
		insertVO.setValores(sbVals.toString().replaceFirst(",", ""));
		return insertVO;
	}

}
