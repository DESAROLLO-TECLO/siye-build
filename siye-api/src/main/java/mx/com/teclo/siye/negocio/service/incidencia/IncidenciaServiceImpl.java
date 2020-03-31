package mx.com.teclo.siye.negocio.service.incidencia;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.incidencia.IncidenciaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.vo.incidencia.IncidenciaVO;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {
	
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	
	private static final String MSG_ERROR_INCIDENCIA_NULA = "No se Encontraron Incidencias";


	@Override
	@Transactional
	public IncidenciaVO getIncidenciabycdIncidencia(String cdIncidencia)  throws NotFoundException{
		IncidenciaVO iVO = new IncidenciaVO();
		IncidenciaDTO iDTO = incidenciaDAO.getIncidenciabycdIncidencia(cdIncidencia);
		iVO = ResponseConverter.copiarPropiedadesFull(iDTO, IncidenciaVO.class);
		
		if (iVO == null) {
			throw new NotFoundException(MSG_ERROR_INCIDENCIA_NULA);
		}
		
		return iVO;
	}

}
