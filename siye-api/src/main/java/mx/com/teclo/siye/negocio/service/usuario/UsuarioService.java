package mx.com.teclo.siye.negocio.service.usuario;

import java.util.List;
import java.util.Map;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;
import mx.com.teclo.siye.util.comun.FilterValuesVO;


/**
 *  Copyright (c) 2018, Teclo Mexicana. 
 * 
 *  Descripcion					: UsuarioService
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Diciembre/2018
 */

public interface UsuarioService {

	/**
     * Consulta usuarios por identificador de registro  y codigo de aplicacion
     * 
     * @param id 					Identificador del registro de usuario.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @return UsuarioVO			Objeto que contiene la informacion necesaria de un usuario.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public UsuarioVO findUserById(Long id, String cdApplication);

	/**
     * Consulta usuarios por parametros de busqueda (usuario , nombre  ,apellidos)
     * 
     * @param parameter 			Codigo del parametro por el que se realiza la busqueda del usuario
     * @param value  		 		Valor para realizar la busqueda de acuerdo al criterio del parametro.
     * @return UsuarioVO			Objeto que contiene la informacion necesaria de un usuario.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public List<UsuarioVO> findUsersByParams(String parameter, String value);

	/**
     * Consulta usuarios por perfil de acceso.
     * 
     * @param profile 				Codigo del perfil asociado al usuario.
     * @return UsuarioVO			Objeto que contiene la informacion necesaria de un usuario.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public List<UsuarioVO> findUsersByProfile(String profile);

	/**
     * Guardar o actualizar un nuevo usuario.
     * 
     * @param usuarioVO 			Objeto que contiene la informacion necesaria para registrar o actualizar un usuario.
     * @return boolean				Resultado del proceso de almacenamiento
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public Boolean saveOrUpdateUser(UsuarioVO usuarioVO, String action) throws NotFoundException;

	/**
     * Activar o desactivar un usuario , permite cambiar el estatus del usuario, cambia el estatus del perfil asociado.
     * 
     * @param username 				Nombre de usuario de autenticacion correspondiente a un usuario.
     * @return boolean				Resultado del proceso de almacenamiento
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public boolean changeStatusUser(UsuarioVO usuarioVO);
	
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
	public boolean findExistentUser(Map<String, Object> parameters); 
	
	/**
     * Restauracion de contraseña , el reseteo setea la contraseña al mismo nombre de usuario.
     * 
     * @param username 				Nombre de usuario de autenticacion correspondiente a un usuario.
     * @return boolean				Resultado del proceso de almacenamiento
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public boolean resetPassword(String username);

	/**
     * Servicio para generacion de parametros de busquedas de usuarios.
     * 
     * @return List<FilterValuesVO>	Lista de valores utilizados para parametros de busquedas de usuario
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public List<FilterValuesVO> getQueryParamsUser();
	
	/**
     * Servicio para generacion de parametros de busquedas para la opción TODOS.
     * 
     * @return List<FilterValuesVO>	Lista de valores utilizados para parametros de busquedas de usuario
     * 
     * @author 				        cagl 
     * @version 			        1.0
     * Fecha				        17/Ene/2019
     */
	public List<FilterValuesVO> getQueryParamsAll();

	/**
     * Servicio para encriptar | desencriptar la contraseña del usuario.
     * 
     * @return String				Contraseña encriptada | desencriptada
     * 
     * @author 				        cagl 
     * @version 			        1.0
     * Fecha				        09/Ene/2019
     */
	public String toggleEncryption(String password, String action);

	/**
	 * Descripción: Método para consultar el usuario por su 
	 * codigo de identificación
	 * @author Jorge Luis
	 * @return UsuarioVO
	 */
	public UsuarioVO getUserByCd(String cdPlaca);
}
