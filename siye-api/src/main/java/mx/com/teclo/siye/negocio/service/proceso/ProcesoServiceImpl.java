package mx.com.teclo.siye.negocio.service.proceso;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.KitInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.OrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.PlanDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.SeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.VehiculoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

@Service
public class ProcesoServiceImpl implements ProcesoService {

	private static final String MSG_ERROR_ORDEN_NULA = "La orden de servicio esta vac\u00EDa";
	private static final String MSG_ERROR_ORDEN_INCOMPLETA = "La orden de servicio est\u00E1 incompleta";
	private static final String MSG_ERROR_ORDEN_CON_REFERENCIAS_NULAS = "La orden de servicio hace referencia a datos nulos";

	@Autowired
	private LoteOrdenServicioDAO loteDAO;

	@Autowired
	private VehiculoDAO vehiculoDAO;

	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;

	@Autowired
	private KitInstalacionDAO kitDAO;

	@Autowired
	private PlanDAO planDAO;

	@Autowired
	private UsuarioFirmadoService contexto;

	@Autowired
	private OrdenServicioDAO ordenServicioDAO;

	@Autowired
	private SeguimientoDAO seguimientoDAO;

	@Override
	public OrdenServicioDTO getInfoBasicaOrdenServicio(Long idSolicitud) {
		return ordenServicioDAO.obtenerOrdenServicio(idSolicitud);
	}

	@Override
	@Transactional
	public Long crearOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException {
		return (Long) ordenServicioDAO.save(generarOrdenServicioDTO(ordenServicioVO));
	}

	@Override
	public void actualizarOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public OrdenServicioVO obtenerOrdenServicio(Long idOrdenServicio) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Extrae y valida cada propiedad del objeto VO para crear un objeto DTO
	 * 
	 * @param ordenServicioVO
	 * @return OrdenServicioDTO
	 * @throws BusinessException
	 */
	private OrdenServicioDTO generarOrdenServicioDTO(OrdenServicioVO ordenServicioVO) throws BusinessException {

		validarOrdenServicio(ordenServicioVO);
		OrdenServicioDTO ordenServicioDTO = new OrdenServicioDTO();
		ordenServicioDTO.setCdOrdenServicio(ordenServicioVO.getCdOrdenServicio());
		ordenServicioDTO.setLoteOrdenServicio(loteDAO.findOne(ordenServicioVO.getLoteOrdenServicio().getIdLoteOds()));
		ordenServicioDTO.setVehiculo(vehiculoDAO.findOne(ordenServicioVO.getVehiculo().getIdVehiculo()));
		ordenServicioDTO.setCentroInstalacion(
				centroInstalacionDAO.findOne(ordenServicioVO.getCentroInstalacion().getIdCentroInstalacion()));
		ordenServicioDTO.setKitInstalacion(kitDAO.findOne(ordenServicioVO.getKitInstalacion().getIdKitInstalacion()));
		ordenServicioDTO.setPlan(planDAO.findOne(ordenServicioVO.getPlan().getIdPlan()));
		ordenServicioDTO
				.setStSeguimiento(seguimientoDAO.findOne(ordenServicioVO.getStSeguimiento().getIdStSeguimiento()));
		ordenServicioDTO.setStActivo(Boolean.TRUE.booleanValue());
		ordenServicioDTO.setIdUsrCreacion(contexto.getUsuarioFirmadoVO().getId());
		ordenServicioDTO.setFhCreacion(new Date());
		ordenServicioDTO.setIdUsrModifica(contexto.getUsuarioFirmadoVO().getId());

		return ordenServicioDTO;
	}

	/**
	 * Valida que las referencias no est&eacute;n vac&iacute;as
	 * 
	 * @param ordenServicioVO
	 * @throws BusinessException
	 */
	private void validarOrdenServicio(OrdenServicioVO ordenServicioVO) throws BusinessException {
		boolean isConDatosRequeridos = false;
		boolean isConIDsRequeridas = false;

		if (ordenServicioVO == null) {
			throw new BusinessException(MSG_ERROR_ORDEN_NULA);
		}

		if (ordenServicioVO.getLoteOrdenServicio() != null)
			if (ordenServicioVO.getVehiculo() != null)
				if (ordenServicioVO.getCentroInstalacion() != null)
					if (ordenServicioVO.getKitInstalacion() != null)
						if (ordenServicioVO.getPlan() != null)
							if (ordenServicioVO.getStSeguimiento() != null)
								isConDatosRequeridos = true;
		if (!isConDatosRequeridos) {
			throw new BusinessException(MSG_ERROR_ORDEN_INCOMPLETA);
		}

		if (!StringUtils.isBlank(ordenServicioVO.getCdOrdenServicio()))
			if (ordenServicioVO.getLoteOrdenServicio().getIdLoteOds() != null)
				if (ordenServicioVO.getVehiculo().getIdVehiculo() != null)
					if (ordenServicioVO.getCentroInstalacion().getIdCentroInstalacion() != null)
						if (ordenServicioVO.getKitInstalacion().getIdKitInstalacion() != null)
							if (ordenServicioVO.getPlan().getIdPlan() != null)
								if (ordenServicioVO.getStSeguimiento().getIdStSeguimiento() != null)
									isConIDsRequeridas = true;

		if (!isConIDsRequeridas) {
			throw new BusinessException(MSG_ERROR_ORDEN_CON_REFERENCIAS_NULAS);
		}

	}

}
