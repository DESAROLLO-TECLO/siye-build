package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

public interface OrdenServicioService {
	
	public List<OrdenServicioVO> consultaOrden(String cdTipoBusqueda, String valor) throws NotFoundException;

}
