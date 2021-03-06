package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;

public interface CargaMasivaService {

	/**
	 * Registra al lote el tipo de layout que le ser&aacute; aplicado
	 * 
	 * @throws BusinessException
	 * @throws IOException 
	 */
	void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException;

	/**
	 * Recupera configuracion necesaria para interpretar el archivo lote<br>
	 * Abre el archivo lote y procesa linea por linea para insertar en la base de
	 * datos
	 * 
	 * @param config
	 * @throws BusinessException 
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	void procesarLineas(ConfigCargaMasivaVO config) throws BusinessException;
	
	/**
	 * Actualiza al estado de cargado un archivo lote
	 * @param archivoLoteVO
	 * @throws BusinessException 
	 */
	void actualizarCargaMasiva(ArchivoLoteVO archivoLoteVO) throws BusinessException;

}
