package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.IERespCausaDTO;

public interface IERespCausaDAO extends BaseDao<IERespCausaDTO> {
	
	List<IERespCausaDTO> obtenerResCausaAnterior(Long idUsuEncuIntento, Long idEncuesta, Long idSeccion, Long idPregunta);

}
