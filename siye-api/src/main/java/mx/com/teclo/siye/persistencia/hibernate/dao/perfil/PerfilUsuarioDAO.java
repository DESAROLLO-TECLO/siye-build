package mx.com.teclo.siye.persistencia.hibernate.dao.perfil;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilUsuarioDTO;

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

public interface PerfilUsuarioDAO extends BaseDao<PerfilUsuarioDTO> {
	
	public PerfilUsuarioDTO getPerfilUsuario(Long idUsuario,Long idPerfil);
	
//
//	public boolean savePerfilUsuario(String username,PerfilVO perfilVO);
//	public boolean updatePerfilUsuario(String username,PerfilVO perfilVO);
}
