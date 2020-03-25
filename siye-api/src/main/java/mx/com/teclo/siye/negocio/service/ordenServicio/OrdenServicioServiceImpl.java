package mx.com.teclo.siye.negocio.service.ordenServicio;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;
import mx.com.teclo.siye.util.enumerados.RespuestaHttp;

@Service
public class OrdenServicioServiceImpl implements OrdenServicioService{
	
	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Transactional
	@Override
	public List<OrdenServicioVO> consultaOrden(String cdTipoBusqueda, String valor) throws NotFoundException {
		
		List<OrdenServicioDTO> listOrdenServicioDTO = ordenServicioDAO.consultaOrden();
		if(listOrdenServicioDTO.isEmpty())
			throw new NotFoundException(RespuestaHttp.NOT_FOUND.getMessage());
		List<OrdenServicioVO> listOrdenServicioVO = ResponseConverter.converterLista(new ArrayList<>(), listOrdenServicioDTO, OrdenServicioVO.class);
		return listOrdenServicioVO;
	}

}
