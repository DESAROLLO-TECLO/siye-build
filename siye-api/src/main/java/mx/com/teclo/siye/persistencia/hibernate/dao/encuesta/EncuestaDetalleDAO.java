package mx.com.teclo.siye.persistencia.hibernate.dao.encuesta;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.encuesta.UsuarioEncuestaDetalleDTO;


public interface EncuestaDetalleDAO extends BaseDao<UsuarioEncuestaDetalleDTO> {

	UsuarioEncuestaDetalleDTO getEncuestaDetalle(Long idEncuesta, Long idOrdenServicio);


}
