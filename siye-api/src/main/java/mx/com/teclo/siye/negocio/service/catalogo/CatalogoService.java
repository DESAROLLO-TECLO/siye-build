package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.OpcionCausaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioCatalogoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;


public interface CatalogoService {
	
	public List<StEncuestaVO> estatusEncuesta() throws NotFoundException;
	
	public List<TipoVehiculoVO> tipoVehiculo() throws NotFoundException;
	
	public List<ConductorVO> getTransportistas() throws NotFoundException;
	
	public List<PersonaVO> getTecnicos(Integer idTipoPersona) throws NotFoundException;
	
	public CatalogosOrdenProcesoVO getCatalogosOrdenProceso() throws NotFoundException;

	List<StSeguimientoVO> obtenerStSeguimientoByCdTpSeguimiento(String cdTpSeguimiento) throws NotFoundException;
	
	/**
	 * @Descripción: Método para consultar un parámetro 
	 * mediante su codigo identificador
	 * @author David Guerra
	 * @return ConfiguracionVO
	 */
	public ConfiguracionVO configuracion(String cdLlavePConfig)throws NotFoundException;


	CentroInstalacionVO getModAten() throws NotFoundException;

	List<OrdenServicioCatalogoVO> getOrdenServicio() throws NotFoundException;

	
	List<OpcionCausaDTO> getCatalogoCausas(Long idOpcion);

}
