package mx.com.teclo.siye.negocio.service.monitoreoInicidencia;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.GerenteSupervisorDAO;

@Service
public class MonitoreoIncidenciaServiceImpl implements MonitoreoIncidenciaService{
	@Autowired
	private GerenteSupervisorDAO gerenteSupervisorDAO;
	
	@Transactional
	@Override
	public void getMonIncidencias(
			String mensajeErr
	) throws Exception, BusinessException, NotFoundException {
		try {
		//Long idSupervisor, List<String> columnas,List<String> colOmitidas, String fInicio, String fFin
		//List<SeguimientoOrdenServicioVO>
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	};
}
