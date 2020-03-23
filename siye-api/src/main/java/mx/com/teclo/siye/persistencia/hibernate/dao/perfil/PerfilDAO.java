package mx.com.teclo.siye.persistencia.hibernate.dao.perfil;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilDTO;

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

public interface PerfilDAO extends BaseDao<PerfilDTO> {

	public List<PerfilDTO> findProfilesByApp(String cdApplication);
	public PerfilDTO findProfileById(Long id, String cdApplication, boolean isByApplication);
	public List<PerfilDTO> findAllProfilesByApp(String cdApplication);
	public PerfilDTO findUniqueProfileById(Long id, String cdApplication, boolean isByApplication);
	public Long findNextValue();

}
