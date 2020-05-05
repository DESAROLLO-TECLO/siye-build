package mx.com.teclo.siye.negocio.service.catalogo;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.OpcionCausaDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.CatTipoFechasVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConductorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ConfiguracionVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaGenericaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.ProveedorVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.StEncuestaVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TblCatalogosVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoKitVO;
import mx.com.teclo.siye.persistencia.vo.catalogo.TipoVehiculoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CatalogosOrdenProcesoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.ConsecionarioVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioCatalogoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.PlanVO;
import mx.com.teclo.siye.persistencia.vo.proceso.StSeguimientoVO;
import mx.com.teclo.siye.persistencia.vo.proceso.VehiculoVO;


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

	
	List<OpcionCausaDTO> getCatalogoCausas(Long idOpcion) ;
	

	/**
	 * @Descripción: Método para buscar persona por codigo de usuario 
	 * mediante su codigo
	 * @author Manuel Dirsio
	 * @return PersonaVO
	 */
	public PersonaGenericaVO buscarPersona(String cdPersona,Integer idTipoPersona)throws NotFoundException;


	/**
	 * @Descripción: Método para generar folio en base a parametro de configuracion
	 * @author Manuel Dirsio
	 * @return String
	 */
	public String generaFolioEmpl(Integer idPersona) throws NotFoundException;
	
	//ParametrosFolioDTO getParametroById(Long idParametro);

	/*@author Maverick
	 *@return List<CatTipoFechasVO>
	 *metodo para consultar el catalogo de fechas 
	 * */
	public List<CatTipoFechasVO> getCatTipoFechas();
	

	/**
	 * @Descripción: Método para generar folio en base a parametro de configuracion
	 * @author Manuel Dirsio
	 * @return String
	 */
	
	//ParametrosFolioDTO getParametroById(String cdParametro);
	public String generaFolio(String cdParametro);

	/**
	 * @Descripción: Método para extraer las opciones en incidencia
	 * @author Estephanie Chavez
	 * @param cdLlavePConfig1
	 * @param cdLlavePConfig2
	 * @param cdLlavePConfig3
	 * @return
	 * @throws NotFoundException
	 */
	public List<ConfiguracionVO> configuracionIncidencia(String cdLlavePConfig1, String cdLlavePConfig2)
			throws NotFoundException;
	
	public List<ConductorVO> getTransportistasVehiculo(Long idVehiculo) throws NotFoundException;
	
	/**
	 * @Descripción: Método para obtener la lista de catalogos
	 * @author Estephanie Chavez
	 * @return List<TblCatalogosDTO>
	 * @throws NotFoundException
	 */
	public List<TblCatalogosVO> getTblCatalogos() throws NotFoundException;
	
	/**
	 * @Descripción: Método para obtener la lista de CentroInstalacion
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<CentroInstalacionVO>consultaCentroIntalacion(String cdTipoBusqueda,Long valor) throws NotFoundException;

	/**
	 * @Descripción: Método para obtener la lista de personas
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<PersonaVO> consultalistPersona(String cdTipoBusqueda, Long valor) throws NotFoundException;

	/**
	 * @Descripción: Método para obtener la lista de conductores
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<ConductorVO> consultaConductor(String cdTipoBusqueda, Long valor) throws NotFoundException;

	/**
	 * @Descripción: Método para obtener la lista de proveedores
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<ProveedorVO> consultaProveedor(String cdTipoBusqueda, Long valor) throws NotFoundException;

	/**
	 * @Descripción: Método para obtener la lista de tipo de vehiculo
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<TipoVehiculoVO> consultaTipoVehiculo(String cdTipoBusqueda, Long valor) throws NotFoundException;

	/**
	 * @Descripción: Método para obtener la lista de vehiculo
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<VehiculoVO> consultaVehiculo(String cdTipoBusqueda, Long valor) throws NotFoundException;

	/**
	 * @Descripción: Método para obtener la lista de concesiones
	 * @author Estephanie Chavez
	 * @param cdTipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	public List<ConsecionarioVO> consultaConcesiones(String cdTipoBusqueda, Long valor) throws NotFoundException;
	
	public List<StSeguimientoVO> consultaStSeguimientoByTipo(Long tipo) throws NotFoundException;

	public List<TipoKitVO> consultaTipoKit() throws NotFoundException;
	
	public List<PlanVO> consultaPlan() throws NotFoundException;
}
