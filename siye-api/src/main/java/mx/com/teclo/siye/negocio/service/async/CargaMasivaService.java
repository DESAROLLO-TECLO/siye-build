package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;

import org.hibernate.HibernateException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
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
	 */
	void procesarLineas(ConfigCargaMasivaVO config) throws BusinessException;

	/**
	 * Recibe un query insert para ejecutarlo
	 * 
	 * @param insertQuery
	 * @param linea       Valores tomada del archivo lote
	 * @return
	 * @throws BusinessException
	 */
	Long insertarEnTablas(ConfigCargaMasivaVO config, String linea) throws BusinessException, HibernateException;

	
}
