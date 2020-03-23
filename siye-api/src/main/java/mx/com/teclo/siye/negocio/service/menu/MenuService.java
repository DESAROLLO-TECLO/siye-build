package mx.com.teclo.siye.negocio.service.menu;

import java.util.List;
import java.util.Map;

import mx.com.teclo.siye.persistencia.vo.menu.AccesosVO;
import mx.com.teclo.siye.persistencia.vo.menu.MenuVO;
	/**
	 *  Copyright (c) 2018, Teclo Mexicana. 
	 * 
	 *  Descripcion					: MenuService
	 *  Historial de Modificaciones	: 
	 *  Descripcion del Cambio 		: Creacion
	 *  @author 					: fjmb
	 *  @version 					: 1.0 
	 *  Fecha 						: 05/Diciembre/2018
	 */
public interface MenuService {
	/**
     * Consulta todos los menus por codigo de perfil
     * 
     * @param profile 			 	Codigo unico del perfil con respecto a la aplicacion.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @return  List<MenuVO>		Lista de objetos con la informacion de los menus.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public List<MenuVO> findMenusByProfile(Long profile, String cdApplication);
	/**
     * Consulta un menu por id de menu
     * 
     * @param id 			 		Identificador unico del menu.
     * @param cdApplication  		Código de aplicacion para filtrar usuarios unicamente de la aplicacion donde se realiza la busqueda. 
     * @return  MenuVO		 		Objetos con la informacion de los menus.
     * 
     * @author 				        fjmb 
     * @version 			        1.0
     * Fecha				        13/Dic/2018 
     */
	public MenuVO findMenusById(Long id, String cdApplication);
	
	@SuppressWarnings("rawtypes")
	public Map consultarMenusPorPerfil(Long profile, String cdApplication);
	
	public boolean autorizaTodoModulo(Long idPerfil);
	
	public boolean autorizaModulo(Long idPerfil, Long idMenu);
	
	public boolean revocaTodoModulo(Long idPerfil);
	
	public boolean revocaModulo(Long idPerfil, Long idMenu);
	
	public Boolean restablecerModulos(AccesosVO accesosVO);

}

