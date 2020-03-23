package mx.com.teclo.siye.negocio.service.perfil;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.perfil.ConsultaPerfilVO;
import mx.com.teclo.siye.persistencia.vo.perfil.PerfilVO;


/**
 *  Copyright (c) 2018, Teclo Mexicana. 
 * 
 *  Descripcion					: PerfilService
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Diciembre/2018
 */
public interface PerfilService {
/**
 * Consulta de perfiles por aplicacion
 * 
 * @return List<PerfilVO>		Lista de objetos correspondientes a los perfiles encontrados de la apicacion.
 * 
 * @author 				        fjmb 
 * @version 			        1.0
 * Fecha				        13/Dic/2018 
 */
public List<PerfilVO> findProfilesByApp();

/**
 * Consulta de perfiles
 * 
 * @return List<ConsultaPerfilVO>Lista de objetos correspondientes a los perfiles encontrados de la apicacion.
 * 
 * @author 				        Sail
 * @version 			        1.0
 * Fecha				        13/Dic/2018
 */
public List<ConsultaPerfilVO> consultaPerfiles();
/**
 * Almacenamiento de un perfil
 * 
 * @param perfilVO				Objeto que contiene el perfil a persistir en base de datos
 * @return boolean				Resultado del proceso de almacenamiento
 * 
 * @author 				        fjmb 
 * @version 			        1.0
 * Fecha				        13/Dic/2018 
 */
public boolean savePerfil(PerfilVO perfilVO);
/**
 * Actualiza la informacion de un perfil
 * 
 * @param perfilVO				Objeto que contiene el perfil a persistir en base de datos
 * @return boolean				Resultado del proceso de almacenamiento
 * 
 * @author 				        fjmb 
 * @version 			        1.0
 * Fecha				        13/Dic/2018 
 */
public boolean updatePerfil(PerfilVO perfilVO);

public boolean habilitaDeshabilitaPerfil(Long idPerfil, Boolean activo);

public boolean crearPerfil(PerfilVO pVO);

}

