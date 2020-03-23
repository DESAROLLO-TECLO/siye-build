package mx.com.teclo.siye.persistencia.hibernate.dao.usraplicacion;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsrAplicacionDTO;

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

public interface UsrAplicacionDAO extends BaseDao<UsrAplicacionDTO> {

	public UsrAplicacionDTO findUserAplication(String cdApplication, Long idUsuario);
	
	public AplicacionDTO getApplicationByCode(String cdApplication);

}
