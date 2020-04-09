package mx.com.teclo.siye.negocio.service.seguimientoOs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mx.com.teclo.siye.persistencia.vo.seguimientoOs.SeguimientoOrdenServicioVO;

@Service
public class SeguimientoOsServiceImpl implements SeguimientoOsService {

	private final String ENCURSO = "EN_CURSO", COMPLETA = "COMPLETADAS", PROGRAMADA = "PROGRAMADA",
						 NOPROGRAMADA = "NO_PROGRAMADA", INCIDENCIA = "INCIDENCIAS";

	@Transactional
	@Override
	public List<SeguimientoOrdenServicioVO> getSeguimientoOrdenServicio(Long idSupervisor, List<String> columnas, String fInicio, String fFin) {
		List<SeguimientoOrdenServicioVO> respuesta = new ArrayList<SeguimientoOrdenServicioVO>();
		
		///Consultar Centro Instalacion adminsitrados 
		

		return respuesta;
	};

	public StringBuilder generarConsulta(List<String> columnas) {
		StringBuilder select = new StringBuilder("");
		for (String col : columnas) {
			switch (col) {
			case ENCURSO:
				select.append("");
				break;
			case COMPLETA:
				select.append("");
				break;
			case PROGRAMADA:
				select.append("");
				break;
			case NOPROGRAMADA:
				select.append("");
				break;
			case INCIDENCIA:
				select.append("");
				break;
			}
		}

		return select;
	}

}
