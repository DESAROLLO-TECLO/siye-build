package mx.com.teclo.siye.persistencia.hibernate.dao.usuario;

import java.util.List;
import java.util.Map;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsuarioDTO;
import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;


	/**
	 *  Copyright (c) 2018, Teclo Mexicana. 
	 * 
	 *  Descripcion					: UsuarioDAO
	 *  Historial de Modificaciones	: 
	 *  Descripcion del Cambio 		: Creacion
	 *  @author 					: fjmb
	 *  @version 					: 1.0 
	 *  Fecha 						: 05/Diciembre/2018
	 */

public interface UsuarioDAO extends BaseDao<UsuarioDTO> {
	/**
     * Consulta usuarios por identificador de registro  y codigo de aplicacion
     * 
     * @param id 					Identificador del registro de usuario.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @return UsuarioDTO			Objeto de persistencia que contiene la informacion necesaria de un usuario.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public UsuarioDTO findUserById(Long id, String cdApplication);
	/**
     * Consulta usuarios por nombre de usuario.
     * 
     * @param username 				Nombre de usuario de autenticacion correspondiente a un usuario.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @param isByApplication  		Indicador utilizado para filtar la busqueda por aplicacion (true: si se requiere filtrar por aplicacion, false: Si unicamente se requiere buscar en la tabla de usuarios)
     * @param isActivo  		 	Indicador utilizado para filtar usuarios por estatus (true: Usuarios sin importar estatus, false: Usuarios activos)

     * @return UsuarioDTO			Objeto que contiene la informacion necesaria de un usuario.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public UsuarioDTO findUserByUserName(String username, String cdApplication, boolean isByApplication, boolean isActivo);
	
	/**
     * Consulta usuarios por parametros de busqueda (usuario , nombre  ,apellidos)
     * 
     * @param parameter 			Codigo del parametro por el que se realiza la busqueda del usuario
     * @param value  		 		Valor para realizar la busqueda de acuerdo al criterio del parametro.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @param isByApplication  		Indicador utilizado para filtar la busqueda por aplicacion (true: si se requiere filtrar por aplicacion, false: Si unicamente se requiere buscar en la tabla de usuarios)

     * @return  List<UsuarioDTO>	Lista de Objetos que contienen la informacion necesaria de un usuario.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public List<UsuarioDTO> findUsersByParams(String parameter, String value, String cdApplication, boolean isByApplication);
	
	/**
     * Consulta usuarios por perfil de acceso.
     * 
     * @param profile 				Codigo del perfil asociado al usuario.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @param isByApplication  		Indicador utilizado para filtar la busqueda por aplicacion (true: si se requiere filtrar por aplicacion, false: Si unicamente se requiere buscar en la tabla de usuarios)

     * @return  List<UsuarioDTO>	Lista de Objetos que contienen la informacion necesaria de un usuario.
     * 
     * @author 			            fjmb 
     * @version 		            1.0
     * Fecha			            13/Dic/2018 
     */
	public List<UsuarioDTO> findUsersByProfile(String profile, String cdApplication, boolean isByApplication);
	/**
     * Servicio dinamico que permite validar si un usuario existe en la tabla de Usuarios.
     * 
     * @param parameters 		    Lista de parametro para realizar la consulta bajo multiples criterios de la tabla usuarios.
     * @return boolean				Resultado del proceso de almacenamiento
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public List<UsuarioDTO> findExistentUser(Map<String, Object> parameters);

	/**
     * Servicio que permite validar si un usuario existe en la tabla de Usuarios.
     * 
     * @param usuarioVO 		    Objeto del usuario a validar.
     * @return boolean				Resultado del proceso de almacenamiento
     * 
     * @author 				        cagl
     * @version 			        1.0
	 * @param action 
     * @fecha				        07/DEne/2019
     */
	public boolean isUserExist(UsuarioVO usuarioVO, String action);

	/**
	 * Descripción: Método para consultar el usuario mediante su placa
	 * @author Jorge Luis
	 * @return UsuarioDTO
	 */
	public UsuarioDTO getUserByCd (String cdUsuario);
}
