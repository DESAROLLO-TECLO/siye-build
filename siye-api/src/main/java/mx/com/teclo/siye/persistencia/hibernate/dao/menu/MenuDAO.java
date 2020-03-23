package mx.com.teclo.siye.persistencia.hibernate.dao.menu;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.menu.MenuDTO;

/**
 *  Copyright (c) 2018, Teclo Mexicana. 
 * 
 *  Descripcion					: MenuDAO
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Diciembre/2018
 */

public interface MenuDAO extends BaseDao<MenuDTO> {

	public List<MenuDTO> findMenusByProfile(Long profile, String cdApplication);
	public List<MenuDTO> findMenusByProfileNotIn(Long profile, String cdApplication);
	public MenuDTO findMenusById(Long id, String cdApplication);
	public MenuDTO findMenusByName(String menu, String cdApplication);
	public MenuDTO findMenuByPerfil(Long idPerfil, Long idMenu, String cdApplication);
}
